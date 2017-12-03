package ru.curriculum.application.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.curriculum.domain.user.User;
import ru.curriculum.domain.user.UserRepository;

import javax.transaction.Transactional;

@Component
public class AdminAuthenticationIfnoInitializationService implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
//    @Value("${auth.admin.username}")
//    private String username;
//    @Value("${auth.admin.password}")
//    private String password;
    boolean alreadySetup = false;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }

//        User admin = new User(username, passwordEncoder.encode(password));
        User admin = new User("admin", passwordEncoder.encode("123"));
        userRepository.save(admin);

        alreadySetup = true;
    }
}
