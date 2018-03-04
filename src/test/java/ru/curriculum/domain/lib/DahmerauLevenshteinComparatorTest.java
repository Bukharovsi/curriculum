package ru.curriculum.domain.lib;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.curriculum.lib.DahmerauLevenshteinComparator;

@RunWith(DataProviderRunner.class)
public class DahmerauLevenshteinComparatorTest {

    @Test
    @UseDataProvider("dataProvider")
    public void compare(String s1, String s2, int allowableMetric, boolean compareResult) {
        DahmerauLevenshteinComparator comparator = new DahmerauLevenshteinComparator(allowableMetric);

        Assert.assertEquals(compareResult, comparator.isEqual(s1, s2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void tryToInstanceDahmerauLevenshteinComparatorByNegativeMetricValue_mustThrowException() {
        new DahmerauLevenshteinComparator(-1);
    }

    @DataProvider
    public static Object[][] dataProvider() {
        return new Object[][] {
                { "Очное", "Очное", 0, true },
                { "рыба", "рыба", 0, true },
                { "Иванов Иван Иванович", "Иванов Иван Иванович", 0, true },
                { "", "", 0, true },
                { "Слово", "", 0, false },
                { "", "Word", 0, false },
                { "one", "ones", 0, false },
                { "Очноя", "Очное", 1, true },
                { "ры", "рыба", 2, true },
                { "р", "рыба", 3, true },
                { "", "рыба", 3, false },
                { "рыб", "рыба", 3, true},
                { "POLYNOMIAL", "EXPONENTIAL", 6, true },
                { "POLYNOMIAL", "EXPONENTIAL", 7, true },
                { "POLYNOMIAL", "EXPONENTIAL", 1000, true },
                { "POLYNOMIAL", "EXPONENTIAL", 5, false },
                { "Очное", "Очная", 2 , true},
                { "Зачное", "Заочная", 3, true },
                { "очное-заочное", "очная-заочная", 4, true },
                { "А", "а", 1, true },
                { "", "", 0, true },
                { "blue", "", 4, true },
                { "blue", "", 1, false },
                { "", "light", 5, true },
                { "", "light", 4, false },
                { "", "light", 1, false },
                { "", "light", 0, false },
                { "ape", "ea", 3, true },
                { "", "", 2, true },
                { "", "", 0, true },
                { "1", "", 0, false }
        };
    }
}
