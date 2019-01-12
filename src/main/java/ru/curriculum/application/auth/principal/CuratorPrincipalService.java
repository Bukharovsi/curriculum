package ru.curriculum.application.auth.principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.curriculum.domain.admin.curator.entity.Curator;
import ru.curriculum.domain.admin.curator.repository.CuratorRepository;


@Component
public class CuratorPrincipalService implements UserDetailsService {
    @Autowired
    private CuratorRepository curatorRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Curator curator = curatorRepository.findByLogin(username);
        if(null == curator) {
            throw new UsernameNotFoundException(String.format("Curator with login \"%s\" not found", username));
        }

        return new CuratorPrincipal(curator);
    }
}
