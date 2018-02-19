package ru.curriculum.domain.admin.service.stateSchelule;

import boot.IntegrationBoot;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import ru.curriculum.domain.admin.curator.entity.Curator;
import ru.curriculum.domain.admin.curator.repository.CuratorRepository;
import ru.curriculum.domain.stateSchedule.entity.StateProgram;
import ru.curriculum.domain.stateSchedule.stateProgramFileParser.StateProgramFileParser;

@Ignore
public class StateProgramCreationFromFileServiceTest extends IntegrationBoot {

    //TODO: точно прверить работу с .doc файлами
    private String filename = "test.docx";

    @Autowired
    private StateProgramFileParser stateProgramFileParser;

    @Autowired
    private CuratorRepository curatorRepository;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void readDocFileTest() throws IOException {
        Curator curator = createAndSaveCurator();
//        List<StateProgram> list = stateProgramFileParser.parse(new File(filename));

//        Assert.assertNotNull(list);
    }

    private Curator createAndSaveCurator() {
        Curator curator = new Curator(
                "ilvira",
                "123",
                "Лукманова",
                "Эльвира",
                "Равшановна"
        );

        return curatorRepository.save(curator);
    }
}
