package ru.curriculum.domain.admin.domain.admin;


import boot.IntegrationBoot;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import ru.curriculum.domain.admin.curator.entity.Curator;
import ru.curriculum.domain.admin.curator.entity.Role;
import ru.curriculum.domain.admin.curator.repository.CuratorRepository;


public class CuratorRepositoryTest extends IntegrationBoot {
    @Autowired
    private CuratorRepository curatorRepository;

    @After
    public void tearDown() {
        curatorRepository.deleteAll();
    }

    @Test
    public void createAndSavedUser() {
        Curator curator = new Curator("test", "test", "Иванов", "Иван","Иванович");
        Curator savedCurator = curatorRepository.save(curator);

        Assert.assertTrue(null != savedCurator.id());
        Assert.assertEquals(curator.login(), savedCurator.login());
        Assert.assertEquals(curator.password(), savedCurator.password());
    }

    @Test
    public void assignRoleForUserAndSave_mustBeSaveWithRole() {
        Role role = new Role("curator", "Пользователь");
        Curator curator = new Curator("testUser", "123", "Иванов", "Иван","Иванович");
        curator.assignRole(role);

        Curator savedCurator = curatorRepository.save(curator);

        Assert.assertEquals(role, savedCurator.role());
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void tryToSaveUserWithNoneExistenceRole_mustBeException() {
        Curator curator = new Curator("test", "123", "Иванов", "Иван","Иванович");
        curator.assignRole(new Role("none", "Не существующая роль"));

        curatorRepository.save(curator);
    }
}