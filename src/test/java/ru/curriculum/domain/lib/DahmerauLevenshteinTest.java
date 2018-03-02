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
@Cu
public class DahmerauLevenshteinTest {
    private DahmerauLevenshtein dahmerauLevenshtein;

    @Before
    public void setUp() {
        dahmerauLevenshtein = new DahmerauLevenshtein();
    }

    public void findTest() {
//        String s1 = "POLYNOMIAL";
//        String s2 = "EXPONENTIAL";
        String s1 = "ape";
        String s2 = "ea";

        int [][] a = new int[s1.length() + 1][s2.length() + 1];

        int result = this.dahmerauLevenshtein.find(s1, s2);

        System.out.println(result);
    }

    public void test() {
        compare("baba", "arab");
        compare("contest", "toner");
        compare("martial", "marital");
        compare("monarchy", "democracy");
        compare("seatback", "backseat");
        compare("warfare", "farewell");
        compare("smoking", "hospital");
        compare("ape", "ea");
    }

    private void compare(String s1 ,String s2) {
        System.out.println(s1 + " " + s2 + " " + dahmerauLevenshtein.find(s1, s2));
    }

    @Test
    @UseDataProvider("dataProvider")
    public void findingDahmerauLevenshteinTest(String s1, String s2, int expectedDistance) {
        int distance = dahmerauLevenshtein.find(s1, s2);

        Assert.assertEquals(expectedDistance, distance);
    }

    @DataProvider
    public static Object[][] dataProvider() {
        return new Object[][]{
                { "POLYNOMIAL", "EXPONENTIAL", 6 },
                { "Очное", "Очная", 2 },
                { "Зачное", "Заочная", 3 },
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
