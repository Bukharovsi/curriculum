package ru.curriculum.application.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import ru.curriculum.domain.admin.user.entity.Role;
import ru.curriculum.domain.admin.user.entity.User;
import ru.curriculum.domain.admin.user.repository.RoleRepository;
import ru.curriculum.domain.admin.user.repository.UserRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

/*
 * При первом старте приложения создаем пользователя с ролью "администратор".
 */
@Component
public class AdminAuthenticationInfoInitializationService implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Value("${auth.admin.username}")
    private String username;

    @Value("${auth.admin.password}")
    private String password;

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (null != userRepository.findByUsername(username)) {
            return; //already exists
        }

        User user = new User(username, password);
        Role roleAdmin = roleRepository.findOne("admin");
        if(null == roleAdmin) {
            log.error("Failed to create user with administrator role. Because role not found");
            throw new EntityNotFoundException("Роль \"Администратор\" не найдена в системе");
        }
        user.assignRole(roleAdmin);
        userRepository.save(user);

        log.info("User with administrator role was created");
    }
}
