package ru.curriculum.domain.admin.repository;


import boot.IntegrationBoot;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import ru.curriculum.domain.admin.user.entity.Role;
import ru.curriculum.domain.admin.user.entity.User;
import ru.curriculum.domain.admin.user.repository.UserRepository;


public class UserRepositoryTest extends IntegrationBoot {
    @Autowired
    private UserRepository userRepository;

    @Before
    public void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    public void createAndSavedUser() {
        User user = new User("test", "test");
        User savedUser = userRepository.save(user);

        Assert.assertTrue(null != savedUser.id());
        Assert.assertEquals(user.username(), savedUser.username());
        Assert.assertEquals(user.password(), savedUser.password());
    }

    @Test
    public void assignRoleForUserAndSave_mustBeSaveWithRole() {
        Role role = new Role("admin", "Администратор");
        User user = new User("test", "123");
        user.assignRole(role);

        User savedUser = userRepository.save(user);

        Assert.assertEquals(role, savedUser.role());
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void tryToSaveUserWithNoneExistenceRole_mustBeException() {
        User user = new User("test", "123");
        user.assignRole(new Role("none", "Не существующая роль"));

        userRepository.save(user);
    }
}