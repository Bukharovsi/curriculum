package ru.curriculum.domain.admin.service.user;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.curriculum.domain.admin.curator.entity.Curator;
import ru.curriculum.domain.helper.UserTestFactory;
import ru.curriculum.service.curator.converter.DtoToCuratorConverter;
import ru.curriculum.service.curator.dto.CuratorDTO;

public class DtoToCuratorConverterTest {
    private DtoToCuratorConverter dtoToCuratorConverter;

    @Before
    public void setUp() {
        dtoToCuratorConverter = new DtoToCuratorConverter();
    }

    @Test
    public void convertDotToUser_mustBeConvertCorrectly() {
        CuratorDTO dto = getUserDTO();

        Curator curator = dtoToCuratorConverter.convert(dto);

        Assert.assertEquals(dto.getId(), curator.id());
        Assert.assertEquals(dto.getSurname(), curator.surname());
        Assert.assertEquals(dto.getFirstName(), curator.firstName());
        Assert.assertEquals(dto.getPatronymic(), curator.patronymic());
        Assert.assertEquals(dto.getUsername(), curator.login());
    }

    private CuratorDTO getUserDTO() {
        CuratorDTO dto = new CuratorDTO(new UserTestFactory().createTestUser());
        dto.setPassword("123");

        return dto;
    }
}
