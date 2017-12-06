package ru.curriculum.application.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.curriculum.domain.admin.user.entity.Role;
import ru.curriculum.domain.admin.user.repository.RoleRepository;
import ru.curriculum.domain.admin.user.entity.User;
import ru.curriculum.domain.admin.user.repository.UserRepository;

import javax.transaction.Transactional;

/*
 * При первом старте приложения создаем пользователя с ролью "администратор".
 */
@Component
public class AdminAuthenticationIfnoInitializationService implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Value("${auth.admin.username}")
    private String username;
    @Value("${auth.admin.password}")
    private String password;
    boolean alreadySetup = false;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }

        User user = new User(username, password);
        Role roleAdmin = roleRepository.findOne("admin");
        user.assignRole(roleAdmin);
        userRepository.save(user);

        User ivan = new User(
                "Balalaika",
                passwordEncoder.encode("123"),
                "Софья",
                "Павловна",
                "Ириновская");
        User revy = new User(
                "Двурукая",
                passwordEncoder.encode("123"));
        userRepository.save(ivan);
        userRepository.save(revy);

        alreadySetup = true;
    }
}
