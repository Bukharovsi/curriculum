package ru.curriculum.lib;


public class DahmerauLevenshteinComparator {
    private DahmerauLevenshtein dahmerauLevenshtein;
    private int allowableStringMetric = 0;

    public DahmerauLevenshteinComparator() {
        this.dahmerauLevenshtein = new DahmerauLevenshtein();
    }

    public DahmerauLevenshteinComparator(int allowableStringMetric) {
        this();
        if(0 > allowableStringMetric) {
            throw new IllegalArgumentException("Allowable string metric cannot be negative");
        }
        this.allowableStringMetric = allowableStringMetric;
    }

    public boolean isEqual(String s1, String s2) {
        int distance = dahmerauLevenshtein.findDistance(s1, s2);
        return allowableStringMetric >= distance;
    }
}
