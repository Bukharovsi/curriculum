package ru.curriculum.domain.lib;

import org.junit.Before;
import org.junit.Test;
import ru.curriculum.lib.DahmerauLevenshtein;

public class DahmerauLevenshteinTest {
    private DahmerauLevenshtein dahmerauLevenshtein;

    @Before
    public void setUp() throws Exception {
        dahmerauLevenshtein = new DahmerauLevenshtein();
    }

    @Test
    public void findTest() {
//        String s1 = "POLYNOMIAL";
//        String s2 = "EXPONENTIAL";
        String s1 = "ape";
        String s2 = "ea";

        int [][] a = new int[s1.length() + 1][s2.length() + 1];

        int result = this.dahmerauLevenshtein.find(s1, s2);

        System.out.println(result);
    }

    @Test
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
}
