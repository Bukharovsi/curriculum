package ru.curriculum.service.user.converter;

import org.springframework.stereotype.Component;
import ru.curriculum.domain.admin.user.entity.User;
import ru.curriculum.service.user.dto.UserDTO;

@Component
public class DtoToUserConverter {
    public User convert(UserDTO dto) {
        return new User(
                dto.getId(),
                dto.getUsername(),
                dto.getPassword(),
                dto.getSurname(),
                dto.getFirstName(),
                dto.getPatronymic()
        );
    }
}
