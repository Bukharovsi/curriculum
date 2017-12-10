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

    public Collection<UserDTO> findAllUsers() {
        Collection<UserDTO> userDTOS = new ArrayList<>();
        userRepository.findAll().forEach(user ->
                userDTOS.add(new UserDTO(user)));

        return userDTOS;
    }

    public void create(UserDTO userDTO) {
        User newUser = new User(userDTO);
        userRepository.save(newUser);
    }

    public void update(UserDTO userDTO) {
        User user = userRepository.findOne(userDTO.getId());
        user.updatePrincipal(userDTO);
        userRepository.save(user);
    }

    public void delete(Integer userId) {
        userRepository.delete(userId);
    }

    public UserDTO getUser(Integer userId) {
        // TODO: либо применять get, либо проверять на null
        User user = userRepository.findOne(userId);

        return new UserDTO(user);
    }
}
