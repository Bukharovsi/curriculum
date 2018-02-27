package ru.curriculum.lib;

public class DahmerauLevenshtein {

    public int find(String s1, String s2) {
        int[][] matrix = new int[s1.length() + 1][s2.length() + 1];

        for(int i = 0; i <= s1.length(); i++) {
            for(int j = 0; j <= s2.length(); j++) {
                if(i == 0 && j == 0) {
                    matrix[i][j] = 0;
                    continue;
                }

                if(i == 0 && j > 0) {
                    matrix[i][j] = j;
                    continue;
                }

                if(i > 0 && j == 0) {
                    matrix[i][j] = i;
                    continue;
                }

                matrix[i][j] = min(i, j, matrix, s1, s2);
            }
        }

        return matrix[s1.length()][s2.length()];
    }

    private int min(int i, int j, int[][] matrix, String s1, String s2) {
        int first = matrix[i][j - 1] + 1;
        int second = matrix[i - 1][j] + 1;
        int third = matrix[i - 1][j - 1] + operationWeight(s1.charAt(i - 1), s2.charAt(j - 1));
        return Math.min(Math.min(first, second), third);
    }

    private int operationWeight(char c1, char c2) {
        return c1 == c2 ? 0 : 1;
    }
}
