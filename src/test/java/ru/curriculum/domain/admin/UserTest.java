package ru.curriculum.domain.admin;

import org.junit.Before;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.curriculum.domain.admin.user.entity.Role;
import ru.curriculum.domain.admin.user.entity.User;
import ru.curriculum.service.validation.PasswordValidator;

import static org.junit.Assert.*;

public class UserTest {
    private PasswordEncoder encoder;

    @Before
    public void setUp() {
        encoder = new BCryptPasswordEncoder(11);
    }

    @Test
    public void createUser() {
        User user = new User("test", "123");

        assertEquals("test", user.username());
        assertTrue(encoder.matches("123", user.password().hash()));
    }

    @Test
    public void createRole() {
        Role role = new Role("test", "Тестовая роль");

        assertEquals("test", role.code());
        assertEquals("Тестовая роль", role.name());
    }

    @Test
    public void assignRoleForUser() {
        Role testRole = new Role("test", "Тестовая роль");
        User user = new User("test", "123");
        user.assignRole(testRole);

        assertEquals(testRole, user.role());
    }

    //TODO: updateUserPrincipal and changePassword
}