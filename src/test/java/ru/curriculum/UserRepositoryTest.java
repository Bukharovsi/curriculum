package ru.curriculum;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.curriculum.domain.user.User;
import ru.curriculum.domain.user.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void createAndSavedUser() {
        User user = new User("test", "test");
        User savedUser = userRepository.save(user);

        Assert.assertTrue(null != savedUser.id());
    }
}
