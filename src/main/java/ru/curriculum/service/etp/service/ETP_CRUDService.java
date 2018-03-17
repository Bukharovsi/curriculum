package ru.curriculum.service.etp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.curriculum.domain.etp.entity.Comment;
import ru.curriculum.domain.etp.entity.ETP;
import ru.curriculum.domain.etp.repository.ETPCommentRepository;
import ru.curriculum.domain.etp.repository.ETPRepository;
import ru.curriculum.service.etp.statusManager.ETPStatusManager;
import ru.curriculum.service.etp.converter.ETPDtoToEtpConverter;
import ru.curriculum.service.etp.dto.ETPDto;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.String.*;

@Component
public class ETP_CRUDService {
    @Autowired
    private ETPRepository etpRepository;
    @Autowired
    private ETPDtoToEtpConverter etpDtoToEtpConverter;
    @Autowired
    private ETPStatusManager etpStatusManager;
    @Autowired
    private ETPCommentRepository etpCommentRepository;

    public List<ETPDto> getAll() {
        List<ETPDto> dtos = new ArrayList<>();
        etpRepository.findAll().forEach(etp -> dtos.add(new ETPDto(etp)));

        return dtos;
    }

    public ETPDto get(Integer etpId) {
        ETP etp = etpRepository.findOne(etpId);
        if(null == etp) {
            throw new EntityNotFoundException(format("УТП c иненитфикатором %s не найдено в системе", etpId));
        }
        List<Comment> comments = etpCommentRepository.findAllByEtpId(etpId);

        return new ETPDto(etp, comments);
    }

    public ETPDto getByStateProgramId(Integer stateProgramId) {
        ETP etp = etpRepository.findByStateProgramId(stateProgramId);
        if(null == etp) {
            throw new EntityNotFoundException(
                    format("УТП созданное на основе с идентификатором %s не найдено в системе", stateProgramId));
        }
        List<Comment> comments = etpCommentRepository.findAllByEtpId(etp.id());

        return new ETPDto(etp, comments);
    }

    public void create(ETPDto etpDTO) {
        ETP etp = etpDtoToEtpConverter.convert(etpDTO);
        etpRepository.save(etp);
    }

    public void update(ETPDto etpDTO) {
        ETP etpNeedToUpdate = etpRepository.findOne(etpDTO.getId());
        if(null == etpNeedToUpdate) {
            throw new EntityNotFoundException(format("УТП в иненитфикатором %s не найде в системе", etpDTO.getId()));
        }
        ETP etp = etpDtoToEtpConverter.convert(etpDTO);
        etpRepository.save(etp);
    }

    public void changeStatus(ETPDto etpDto) {
        ETP etpNeedToChangeStatus = etpRepository.findOne(etpDto.getId());
        if(null == etpNeedToChangeStatus) {
            throw new EntityNotFoundException(format("УТП в иненитфикатором %s не найде в системе", etpDto.getId()));
        }
        ETP etp = etpDtoToEtpConverter.convert(etpDto);
        etpStatusManager.moveEtpToNewStatus(etp, etpDto.getNewStatus());
        etpRepository.save(etp);
    }

    public void delete(Integer etpId) {
        etpRepository.delete(etpId);
    }
}

