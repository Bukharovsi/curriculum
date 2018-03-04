package ru.curriculum.domain.lib;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.curriculum.lib.PercentageMetric;

@RunWith(DataProviderRunner.class)
public class PercentageMetricTest {

    @Test
    @UseDataProvider("allowedConstructorArgDataProvider")
    public void instanceNewPercentageMetric_mustBeInstanceCorrectly(int percentage) {
        new PercentageMetric(percentage);
    }

    @Test(expected = IllegalArgumentException.class)
    public void instanceByNegativeValue_mustThrowException() {
        new PercentageMetric(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void instanceByValuesGraterThan100_mustThrowException() {
        new PercentageMetric(101);
    }

    @DataProvider
    public static Object[][] allowedConstructorArgDataProvider() {
        return new Object[][] {
                { 10 },
                { 25 },
                { 33 },
                { 10 },
                { 100 },
                { 0 }
        };
    }
}
