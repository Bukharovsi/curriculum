package ru.curriculum.service.timetable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.curriculum.domain.timetable.entity.Timetable;
import ru.curriculum.domain.timetable.entity.WeeklyTimetable;
import ru.curriculum.domain.timetable.repository.TimetableRepository;
import ru.curriculum.domain.timetable.specification.ISpecification;
import ru.curriculum.domain.timetable.specification.ResultOfApplySpecification;
import ru.curriculum.service.etp.dto.ETPDto;
import ru.curriculum.service.timetable.converter.TimetableDtoToTimetableConverter;
import ru.curriculum.service.timetable.dto.TimetableDtoValidation;
import ru.curriculum.service.timetable.dto.WeeklyTimetableDto;

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
    private TimetableDtoToTimetableConverter timetableDtoToTimetableConverter;
    @Autowired
    private TimetableSpecificationResolver specificationResolver;

    public WeeklyTimetableDto get(Integer id) {
        Timetable timetable = timetableRepository.findOne(id);
        if(null == timetable) {
            throw new EntityNotFoundException(String.format("Расписание с id=%s не найдено", id));
        }
        return new WeeklyTimetableDto(new WeeklyTimetable(timetable));
    }

    public List<WeeklyTimetableDto> findAll() {
        List<WeeklyTimetableDto> timetableDtos = new ArrayList<>();
        timetableRepository.findAll().forEach(timetable ->
            timetableDtos.add(new WeeklyTimetableDto(new WeeklyTimetable(timetable)))
        );
        return timetableDtos
                .stream()
                .sorted(nullsLast(comparing(WeeklyTimetableDto::getBeginDate, nullsLast(naturalOrder()))))
                .collect(Collectors.toList());
    }

    public WeeklyTimetableDto makeTimetable(ETPDto etpDto) {
        Timetable timetable =timetableRepository.save(creationTimetableFromEtpService.makeTimetable(etpDto));
        return new WeeklyTimetableDto(new WeeklyTimetable(timetable));
    }

    public void delete(Integer id) {
        timetableRepository.delete(id);
    }

    public WeeklyTimetableDto update(WeeklyTimetableDto timetableDto) {
        if(null == timetableRepository.findOne(timetableDto.getId())) {
            throw new EntityNotFoundException(String.format("Расписание с id=%s не найдено", timetableDto.getId()));
        }

        Timetable timetable = timetableDtoToTimetableConverter.convert(timetableDto);

        ISpecification<Timetable> specification = specificationResolver.getSpecification(timetableDto);
        ResultOfApplySpecification result = specification.isSatisfiedBy(timetable);
        if(!result.isSuccess()) {
            timetableDto.setValidation(new TimetableDtoValidation(result));
            return timetableDto;
        }

        Timetable updateTimetable = timetableRepository.save(timetable);

        return new WeeklyTimetableDto(new WeeklyTimetable(updateTimetable));
    }
}
