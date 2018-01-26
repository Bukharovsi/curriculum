package ru.curriculum.domain.admin.service.user;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.curriculum.domain.admin.user.entity.User;
import ru.curriculum.domain.helper.UserTestFactory;
import ru.curriculum.service.user.converter.DtoToUserConverter;
import ru.curriculum.service.user.dto.UserDTO;

public class DtoToUserConverterTest {
    private DtoToUserConverter dtoToUserConverter;

    @Before
    public void setUp() {
        dtoToUserConverter = new DtoToUserConverter();
    }

    @Test
    public void convertDotToUser_mustBeConvertCorrectly() {
        UserDTO dto = getUserDTO();

        User user = dtoToUserConverter.convert(dto);

        Assert.assertEquals(dto.getId(), user.id());
        Assert.assertEquals(dto.getSurname(), user.surname());
        Assert.assertEquals(dto.getFirstName(), user.firstName());
        Assert.assertEquals(dto.getPatronymic(), user.patronymic());
        Assert.assertEquals(dto.getUsername(), user.username());
    }

    private UserDTO getUserDTO() {
        UserDTO dto = new UserDTO(new UserTestFactory().createTestUser());
        dto.setPassword("123");

        return dto;
    }
}
