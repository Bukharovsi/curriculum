package ru.curriculum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.curriculum.domain.admin.user.entity.User;
import ru.curriculum.domain.admin.user.repository.UserRepository;

import java.util.ArrayList;
import java.util.Collection;


@Component
public class UserCRUDService {
    @Autowired
    private UserRepository userRepository;

    public Collection<UserDto> findAllUsers() {
        Collection<UserDto> userDtos = new ArrayList<>();
        userRepository.findAll().forEach(user ->
                userDtos.add(new UserDto(user)));

        return userDtos;
    }

    public void saveUser(UserDto userDto) {
        User newUser = new User(userDto);
        userRepository.save(newUser);
    }

    public void deleteUser(Integer userId) {
        userRepository.delete(userId);
    }

    public UserDto getUser(Integer userId) {
        // TODO: либо применять get, либо проверять на null
        User user = userRepository.findOne(userId);

        return new UserDto(user);
    }
}
