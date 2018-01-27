package ru.curriculum.application.auth.principal;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.curriculum.domain.admin.curator.entity.Curator;

import java.util.ArrayList;
import java.util.Collection;

public class CuratorPrincipal implements UserDetails {
    private AuthorityFactory authorityFactory;
    private Curator curator;

    public CuratorPrincipal(Curator curator) {
        this.curator = curator;
        this.authorityFactory = new AuthorityFactory();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList();
        authorities.add(authorityFactory.create(curator.role()));

        return authorities;
    }

    @Override
    public String getPassword() {
        return curator.password().hash();
    }

    @Override
    public String getUsername() {
        return curator.login();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Curator curator() {
        return curator;
    }
}
