package ru.curriculum.service.etp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.curriculum.domain.etp.entity.ETP;
import ru.curriculum.domain.etp.repository.ETPRepository;
import ru.curriculum.domain.teacher.entity.Teacher;
import ru.curriculum.domain.teacher.repository.TeacherRepository;
import ru.curriculum.service.etp.dto.ETP_DTO;
import ru.curriculum.service.teacher.dto.TeacherDTO;

import java.util.ArrayList;
import java.util.List;

@Component
public class ETP_CRUDService {
    @Autowired
    private ETPRepository etpRepository;
    @Autowired
    private TeacherRepository teacherRepository;

    public List<ETP_DTO> getAll() {
        List<ETP_DTO> dtos = new ArrayList<>();
        etpRepository.findAll().forEach(etp -> dtos.add(new ETP_DTO(etp)));

        return dtos;
    }

    public ETP_DTO get(Integer etpId) {
        ETP etp = etpRepository.findOne(etpId);

        return new ETP_DTO(etp);
    }

    public void create(ETP_DTO etpDTO) {
        bindTeacherWithPlan(etpDTO);
        ETP etp = new ETP(etpDTO);
        etpRepository.save(etp);
    }

    public void update(ETP_DTO etpDTO) {
//        bindTeacherWithPlan(etpDTO);
//        ETP etp = new ETP(etpDTO);
//        etpRepository.save(etp);
        create(etpDTO);
    }

    public void delete(Integer etpId) {
        etpRepository.delete(etpId);
    }

    private void bindTeacherWithPlan(ETP_DTO etpDTO) {
        // TODO: пока что быстрое решение потом переделать как-нибудь получше
        etpDTO.getModules().forEach(module -> {
            module.getSections().forEach(section -> {
                if(null != section.getPlan().getTeacherId()) {
                    Teacher teacher = teacherRepository.findOne(section.getPlan().getTeacherId());
                    section.getPlan().setDomainTeacher(teacher);
                }
            });
        });
    }
}

