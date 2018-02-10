package ru.curriculum.service.directories.staffTable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.curriculum.domain.directories.staffTable.StaffPositionRepository;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class StaffTableFindService {
    @Autowired
    private StaffPositionRepository staffPositionRepository;

    public Collection<StaffPositionDto> findAll() {
        Collection dtos = new ArrayList();
        staffPositionRepository.findAll().forEach(staffTable ->
                dtos.add(new StaffPositionDto(staffTable.id(), staffTable.positionHeld()))
        );

        return dtos;
    }
}
