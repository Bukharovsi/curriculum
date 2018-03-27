package ru.curriculum.service.timetable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.curriculum.domain.timetable.entity.Timetable;
import ru.curriculum.domain.timetable.repository.TimetableRepository;
import ru.curriculum.service.etp.dto.ETPDto;
import ru.curriculum.service.timetable.dto.TimetableDto;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.*;
import static java.util.Comparator.naturalOrder;

@Component
public class TimetableCRUDService {
    @Autowired
    private TimetableRepository timetableRepository;
    @Autowired
    private CreationTimetableFromEtpService creationTimetableFromEtpService;
    @Autowired
    private TimetableEditService timetableEditService;

    public TimetableDto get(Integer id) {
        Timetable timetable = timetableRepository.findOne(id);
        if(null == timetable) {
            throw new EntityNotFoundException(String.format("Расписание с id=%s не найдено", id));
        }
        return new TimetableDto(timetable);
    }

    public List<TimetableDto> findAll() {
        List<TimetableDto> timetableDtos = new ArrayList<>();
        timetableRepository.findAll().forEach(timetable ->
            timetableDtos.add(new TimetableDto(timetable))
        );
        return timetableDtos
                .stream()
                .sorted(nullsLast(comparing(TimetableDto::getBeginDate, nullsLast(naturalOrder()))))
                .collect(Collectors.toList());
    }

    public void update(TimetableDto timetableDto) {
        Timetable timetable = timetableRepository.findOne(timetableDto.getId());
        if(null == timetable) {
            throw new EntityNotFoundException(String.format("Расписание с id=%s не найдено", timetableDto.getId()));
        }
        timetableEditService.editTimetable(timetable, timetableDto);
        timetableRepository.save(timetable);
    }

    public TimetableDto makeTimetable(ETPDto etpDto) {
        Timetable timetable =timetableRepository.save(creationTimetableFromEtpService.makeTimetable(etpDto));
        return new TimetableDto(timetable);
    }

    public void delete(Integer id) {
        timetableRepository.delete(id);
    }
}
