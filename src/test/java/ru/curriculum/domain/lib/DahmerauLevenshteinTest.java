package ru.curriculum.domain.lib;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Assert;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.curriculum.lib.DahmerauLevenshtein;

@RunWith(DataProviderRunner.class)
public class DahmerauLevenshteinTest {
    private DahmerauLevenshtein dahmerauLevenshtein;

    @Before
    public void setUp() {
        dahmerauLevenshtein = new DahmerauLevenshtein();
    }

    @Test(expected = IllegalArgumentException.class)
    public void findDahmerauLevenshteinByNullInputString_mustThrowException() {
        dahmerauLevenshtein.findDistance(null, null);
    }

    @Test
    @UseDataProvider("dataProvider")
    public void findingDahmerauLevenshteinTest(String s1, String s2, int expectedDistance) {
        int distance = dahmerauLevenshtein.findDistance(s1, s2);

        Assert.assertEquals(expectedDistance, distance);
    }

    @DataProvider
    public static Object[][] dataProvider() {
        return new Object[][]{
                { "POLYNOMIAL", "EXPONENTIAL", 6 },
                { "Очное", "Очная", 2 },
                { "Зачное", "Заочная", 3 },
                { "очное-заочное", "очная-заочная", 4 },
                { "А", "а", 1 },
                { "", "", 0 },
                { "blue", "", 4 },
                { "", "light", 5 },
                { "baba", "arab", 3 },
                { "contest", "toner", 4 },
                { "martial", "marital", 1 },
                { "monarchy", "democracy", 5 },
                { "seatback", "backseat", 8 },
                { "warfare", "farewell", 6 },
                { "smoking", "hospital", 7 },
                { "ape", "ea", 3 }
        };
    }
}
