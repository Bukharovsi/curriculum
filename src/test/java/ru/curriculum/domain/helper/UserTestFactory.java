package ru.curriculum.domain.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.curriculum.domain.admin.curator.entity.Curator;
import ru.curriculum.domain.admin.curator.repository.CuratorRepository;

import java.util.UUID;

@Component
public class UserTestFactory {

    @Autowired
    private CuratorRepository curatorRepository;

    public Curator createTestUser() {
        return new Curator(
            "test",
            "123",
            "Иванов",
            "Иван",
            "Иванович");
    }

    public Curator createAndSaveRandomUser() {
        Curator curator = new Curator(
            UUID.randomUUID().toString(),
            "123",
            "Иванов",
            "Иван",
            "Иванович"
        );

        return curatorRepository.save(curator);
    }
}
