package ru.curriculum.domain.admin;

import org.junit.Test;
import ru.curriculum.domain.admin.user.entity.Role;
import ru.curriculum.domain.admin.user.entity.User;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void createUser() {
        User user = new User("test", "123");

        assertEquals("test", user.username());
        assertEquals("123", user.password());
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