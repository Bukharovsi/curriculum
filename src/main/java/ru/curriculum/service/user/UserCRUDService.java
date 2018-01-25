package ru.curriculum.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.curriculum.domain.admin.user.entity.User;
import ru.curriculum.domain.admin.user.repository.UserRepository;
import ru.curriculum.domain.teacher.entity.Teacher;
import ru.curriculum.domain.teacher.repository.TeacherRepository;
import ru.curriculum.service.user.converter.DtoToUserConverter;
import ru.curriculum.service.user.dto.UserDTO;
import ru.curriculum.service.user.exception.UserNotFoundException;

import java.util.ArrayList;
import java.util.Collection;


@Component
public class UserCRUDService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private DtoToUserConverter dtoToUserConverter;

    public Collection<UserDTO> findAllUsers() {
        Collection<UserDTO> userDTOs = new ArrayList<>();
        userRepository.findAll().forEach(user ->
                userDTOs.add(new UserDTO(user)));

        return userDTOs;
    }

    public UserDTO getUser(Integer userId) {
        User user = userRepository.findOne(userId);
        if(null == user) {
            throw new UserNotFoundException(userId);
        }

        return new UserDTO(user);
    }

    public void create(UserDTO dto) {
        User newUser = dtoToUserConverter.convert(dto);
        userRepository.save(newUser);
    }

    public void update(UserDTO dto) {
        User user = userRepository.findOne(dto.getId());
        if(null == user) {
            throw new UserNotFoundException(dto.getId());
        }

        user.surname(dto.getSurname());
        user.firstName(dto.getFirstName());
        user.patronymic(dto.getPatronymic());
        if(dto.getIsTeacher()) {
            user.teacher(teacherRepository.findOne(dto.getId()));
        }
        if(null != dto.getPassword() && !dto.getPassword().isEmpty()) {
            user.changePassword(dto.getPassword());
        }

        userRepository.save(user);
    }

    public void delete(Integer userId) {
        Teacher teacher = teacherRepository.findByUserId(userId);
        if(null != teacher) {
            teacher.deleteUserAccount();
            teacherRepository.save(teacher);
        }
        userRepository.delete(userId);
    }
}
