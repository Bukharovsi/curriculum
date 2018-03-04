package ru.curriculum.lib;

/**
 * Implementation of search distance "dahmerau-levenshtein"
 *
 * https://en.wikipedia.org/wiki/Damerau%E2%80%93Levenshtein_distance
 */
public class DahmerauLevenshtein {

    public int findDistance(String s1, String s2) {
        if(null == s1 || null == s2) {
            throw new IllegalArgumentException("Input strings cannot be null");
        }

        int[][] matrix = new int[s1.length() + 1][s2.length() + 1];

        for(int i = 0; i <= s1.length(); i++) {
            for(int j = 0; j <= s2.length(); j++) {

                if(firstCase(i, j)) {
                    matrix[i][j] = minForFirstCase(i, j);
                    continue;
                }

                if(secondCase(i, j, s1, s2)) {
                    matrix[i][j] = minForSecondCase(i, j, matrix, s1, s2);
                    continue;
                }

                // All cases if the first and second cases are false
                matrix[i][j] = minForThirdCase(i, j, matrix, s1, s2);
            }
        }

        return matrix[s1.length()][s2.length()];
    }

    /**
     * if min(i, j)
     */
    private boolean firstCase(int i, int j) {
        return 0 == Math.min(i, j);
    }

    private int minForFirstCase(int i, int j) {
        return Math.max(i, j);
    }

    /**
     * if i,j>1 and Ai=Bj-1 and Ai-1=Bj
     */
    private boolean secondCase(int i, int j, String s1, String s2) {
        return i > 1 && j > 1
                && s1.charAt(i - 1) == s2.charAt(j - 2)
                && s2.charAt(j - 1) == s1.charAt(i - 2);
    }

    private int minForSecondCase(int i, int j, int[][] matrix, String s1, String s2) {
        int minByLivenshtein = minForThirdCase(i, j, matrix, s1, s2);
        int minAdditionalByDahmerau = matrix[i-2][j-2] + 1;

        return Math.min(minByLivenshtein, minAdditionalByDahmerau);
    }

    private int minForThirdCase(int i, int j, int[][] matrix, String s1, String s2) {
        int first = matrix[i][j - 1] + 1;
        int second = matrix[i - 1][j] + 1;
        int third = matrix[i - 1][j - 1] + m(s1.charAt(i - 1), s2.charAt(j - 1));

        return Math.min(Math.min(first, second), third);
    }

    /**
     * Indicator function. Base function of "dahmerau-levenshtein" to define operation weight
     */
    private int m(char c1, char c2) {
        return c1 == c2 ? 0 : 1;
    }
}
