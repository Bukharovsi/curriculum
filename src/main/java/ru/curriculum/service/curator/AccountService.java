package ru.curriculum.service.curator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.curriculum.domain.admin.curator.repository.CuratorRepository;
import ru.curriculum.service.curator.dto.CuratorDto;
import ru.curriculum.service.teacher.dto.TeacherDto;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class AccountService {
    @Autowired
    private CuratorRepository curatorRepository;
    @Autowired
    private CuratorCRUDService curatorCRUDService;

    public Collection<CuratorDto> getFreeAccounts() {
        Collection<CuratorDto> accounts = new ArrayList<>();
        curatorRepository.findAllByTeacherIsNull().forEach(curator ->
                accounts.add(new CuratorDto(curator)));

        return accounts;
    }

    public Collection<CuratorDto> getFreeAccountsAndTeacherAccount(TeacherDto teacherDto) {
        Collection<CuratorDto> accounts = getFreeAccounts();
        if(null != teacherDto.getCuratorId()) {
            accounts.add(curatorCRUDService.getCurator(teacherDto.getCuratorId()));
        }

        return accounts;
    }
}
