package ru.curriculum.service.curator.converter;

import org.springframework.stereotype.Component;
import ru.curriculum.domain.admin.curator.entity.Curator;
import ru.curriculum.service.curator.dto.CuratorDto;

@Component
public class DtoToCuratorConverter {
    public Curator convert(CuratorDto dto) {
        return new Curator(
                dto.getId(),
                dto.getUsername(),
                dto.getPassword(),
                dto.getSurname(),
                dto.getFirstName(),
                dto.getPatronymic()
        );
    }
}
