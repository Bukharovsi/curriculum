package ru.curriculum.service.curator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.curriculum.domain.admin.curator.repository.CuratorRepository;
import ru.curriculum.service.teacher.dto.TeacherDTO;
import ru.curriculum.service.curator.dto.CuratorDTO;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class AccountService {
    @Autowired
    private CuratorRepository curatorRepository;
    @Autowired
    private CuratorCRUDService curatorCRUDService;

    public Collection<CuratorDTO> getFreeAccounts() {
        Collection<CuratorDTO> accounts = new ArrayList<>();
        curatorRepository.findAllByTeacherIsNull().forEach(curator ->
                accounts.add(new CuratorDTO(curator)));

        return accounts;
    }

    public Collection<CuratorDTO> getFreeAccountsAndTeacherAccount(TeacherDTO teacherDTO) {
        Collection<CuratorDTO> accounts = getFreeAccounts();
        if(null != teacherDTO.getCuratorId()) {
            accounts.add(curatorCRUDService.getCurator(teacherDTO.getCuratorId()));
        }

        return accounts;
    }
}
