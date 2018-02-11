package ru.curriculum.service.directories.academicDegree;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.curriculum.domain.directories.academicDegree.AcademicDegreeRepository;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class AcademicDegreeFindService {
    @Autowired
    private AcademicDegreeRepository repository;

    public Collection<AcademicDegreeDto> findAll() {
        Collection dtos = new ArrayList();
        repository.findAll().forEach(academicDegree ->
                dtos.add(new AcademicDegreeDto(academicDegree))
        );

        return dtos;
    }
}
