package ru.curriculum.domain.admin.domain.stateSchedule;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.curriculum.domain.stateSchedule.stateProgramFileParser.InternshipParser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InternshipDateParserTest {
    private InternshipParser internshipParser;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    @Before
    public void setUp() {
        internshipParser = new InternshipParser();
    }

    @Test
    public void findDatesFromString_mustReturnTwoDates() throws ParseException {
        String searchString = " 20.01.2018-28.01.2018 МБОУ №96";
        Date[] dates = internshipParser.getStartAndFinishDates(searchString);

        Assert.assertEquals(2, dates.length);
        Assert.assertEquals(dateFormat.parse("20.01.2018"), dates[0]);
        Assert.assertEquals(dateFormat.parse("28.01.2018"), dates[1]);
    }

    @Test
    public void findDatesFromStringWithDifferentFormats_mustReturnAllDates() throws ParseException {
        String searchString = " 20.01.2018-28.01.18 МБОУ №96";
        Date[] dates = internshipParser.getStartAndFinishDates(searchString);

        Assert.assertEquals(2, dates.length);
        Assert.assertEquals(dateFormat.parse("20.01.2018").getTime(), dates[0].getTime());
        Assert.assertEquals(dateFormat.parse("28.01.2018"), dates[1]);
    }

    @Test
    public void tryToFindDatesFromStringWhereAreNotPresent_mustReturnNull() {
        String searchString = "1.  МБОУ №96";
        Date[] dates = internshipParser.getStartAndFinishDates(searchString);

        Assert.assertNull(dates[0]);
        Assert.assertNull(dates[1]);
    }

    @Test
    public void getAddressFromStringWherePresentDates_mustReturnOnlyAddress() {
        String searchString = " 20.01.2018-28.01.2018 МБОУ №96";
        String address = internshipParser.getAddress(searchString);

        Assert.assertEquals("МБОУ №96", address);
    }

    @Test
    public void getAddressFromEmptyString_mustReturnEmptyString() {
        String address = internshipParser.getAddress("");

        Assert.assertEquals("", address);
    }
}
