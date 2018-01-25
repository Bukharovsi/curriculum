package ru.curriculum.application.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import ru.curriculum.domain.admin.curator.entity.Curator;
import ru.curriculum.domain.admin.curator.entity.Role;
import ru.curriculum.domain.admin.curator.repository.RoleRepository;
import ru.curriculum.domain.admin.curator.repository.CuratorRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

/*
 * При первом старте приложения создаем пользователя с ролью "администратор".
 */
@Component
public class AdminAuthenticationInfoInitializationService implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private CuratorRepository curatorRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Value("${auth.admin.login}")
    private String login;

    @Value("${auth.admin.password}")
    private String password;

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (null != curatorRepository.findByLogin(login)) {
            return; //already exists
        }

        Curator curator = new Curator(login, password);
        Role roleAdmin = roleRepository.findOne("admin");
        if(null == roleAdmin) {
            log.error("Failed to create curator with role administrator role. Because role not found");
            throw new EntityNotFoundException("Роль \"Администратор\" не найдена в системе");
        }
        curator.assignRole(roleAdmin);
        curatorRepository.save(curator);

        log.info("Curator with administrator role was created");
    }
}
