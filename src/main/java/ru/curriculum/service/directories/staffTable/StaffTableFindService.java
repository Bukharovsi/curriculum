package ru.curriculum.service.directories.staffTable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.curriculum.domain.directories.staffTable.StaffTableRepository;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class StaffTableFindService {
    @Autowired
    private StaffTableRepository staffTableRepository;

    public Collection<StaffTableDto> findAll() {
        Collection dtos = new ArrayList();
        staffTableRepository.findAll().forEach(staffTable ->
                dtos.add(new StaffTableDto(staffTable.id(), staffTable.positionHeld()))
        );

        return dtos;
    }
}
