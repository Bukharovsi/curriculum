package ru.curriculum.domain.admin.domain;

import org.junit.Before;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.curriculum.domain.admin.user.entity.Role;
import ru.curriculum.domain.admin.user.entity.User;
import ru.curriculum.service.UserDTO;

import static org.junit.Assert.*;

public class UserTest {
    private PasswordEncoder encoder;

    @Before
    public void setUp() {
        encoder = new BCryptPasswordEncoder(11);
    }

    @Test
    public void createUser() {
        User user = getUser();

        assertEquals("test", user.username());
        assertTrue(encoder.matches("123", user.password().hash()));
        assertEquals("Иванов", user.surname());
        assertEquals("Иван", user.firstName());
        assertEquals("Иванович", user.lastName());
    }

    @Test
    public void createUserFromUserDTO() {
        UserDTO dto = getUserDTO();
        User user = new User(dto);

        assertNull("Id is not created because only system generate id", user.id());
        assertEquals(user.username(), dto.getUsername());
        assertTrue(encoder.matches("3333", user.password().hash()));
        assertEquals(user.firstName(), dto.getFirstname());
        assertEquals(user.surname(), dto.getSurname());
        assertEquals(user.lastName(), dto.getLastname());
    }

    @Test
    public void updateUserPrincipalInfo() {
        UserDTO dto = getUserDTO();
        User user = getUser();

        user.updatePrincipal(dto);

        assertNotEquals("Username immutable", user.username(), dto.getUsername());
        assertEquals(user.firstName(), dto.getFirstname());
        assertEquals(user.surname(), dto.getSurname());
        assertEquals(user.lastName(), dto.getLastname());
        assertTrue(encoder.matches(dto.getPassword(), user.password().hash()));
    }

    @Test
    public void updateUserPrincipalInfoFromDTOWhereNoPassword_passwordRemainsTheSame() {
        UserDTO dto = getUserDTO();
        dto.setPassword(null);
        User user = getUser();

        user.updatePrincipal(dto);

        assertTrue(encoder.matches("123", user.password().hash()));
    }

    @Test
    public void updateUserPrincipalInfoFromDTOWherePasswordIsEmtpyString_passwordRemainsTheSame() {
        UserDTO dto = getUserDTO();
        dto.setPassword("");
        User user = getUser();

        user.updatePrincipal(dto);

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
        User user = getUser();
        user.assignRole(testRole);

        assertEquals(testRole, user.role());
    }

    private UserDTO getUserDTO() {
        UserDTO dto = new UserDTO();
        dto.setId(22);
        dto.setUsername("newUserName");
        dto.setPassword("3333");
        dto.setFirstname("newName");
        dto.setSurname("newSurname");
        dto.setLastname("newLastName");

        return dto;
    }

    private User getUser() {
        return new User(
                "test",
                "123",
                "Иванов",
                "Иван",
                "Иванович");
    }

    //TODO: updateUserPrincipal and changePassword
}