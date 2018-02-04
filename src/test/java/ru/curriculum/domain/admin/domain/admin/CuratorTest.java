package ru.curriculum.domain.admin.domain.admin;

import boot.IntegrationBoot;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.curriculum.domain.admin.curator.entity.Curator;
import ru.curriculum.domain.admin.curator.entity.Role;
import ru.curriculum.domain.helper.UserTestFactory;
import ru.curriculum.service.curator.dto.CuratorDto;

public class CuratorTest extends IntegrationBoot {

    private PasswordEncoder encoder;

    @Autowired
    private UserTestFactory userTestFactory;

    @Before
    public void setUp() {
        encoder = new BCryptPasswordEncoder(11);
    }

    @Test
    public void createUser() {
        Curator curator = userTestFactory.createTestUser();

        assertEquals("test", curator.login());
        assertTrue(encoder.matches("123", curator.password().hash()));
        assertEquals("Иванов", curator.surname());
        assertEquals("Иван", curator.firstName());
        assertEquals("Иванович", curator.patronymic());
        assertEquals("Иванов И.И.", curator.fullName());
    }

    @Test
    public void createUserFromUserNameAndPassword() {
        Curator curator = new Curator("test", "test");

        assertNull("Id is not created because only system generate id", curator.id());
        assertEquals(curator.login(), "test");
        assertTrue(encoder.matches("test", curator.password().hash()));
        assertEquals("Default curator role", "curator", curator.role().code());
    }

    @Test
    public void changePassword_mustBeChangedCorrectly() {
        Curator curator = getUser();
        curator.changePassword("123");

        assertTrue(encoder.matches("123", curator.password().hash()));
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
        Curator curator = userTestFactory.createTestUser();
        curator.assignRole(testRole);

        assertEquals(testRole, curator.role());
    }

    @Test
    public void getFullNameWhenPartsOfFullNameIsEmpty_mustReturnCorrect() {
        Curator curator = new Curator();

        assertEquals(" ", curator.fullName());
    }

    private CuratorDto getUserDTO() {
        CuratorDto dto = new CuratorDto();
        dto.setId(22);
        dto.setUsername("newUserName");
        dto.setPassword("3333");
        dto.setFirstName("newName");
        dto.setSurname("newSurname");
        dto.setPatronymic("newLastName");

        return dto;
    }

    private Curator getUser() {
        return new Curator(
                "test",
                "123",
                "Иванов",
                "Иван",
                "Иванович");
    }
}