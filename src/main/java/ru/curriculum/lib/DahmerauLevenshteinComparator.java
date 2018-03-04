package ru.curriculum.lib;


public class DahmerauLevenshteinComparator {
    private DahmerauLevenshtein dahmerauLevenshtein;
    private int allowableStringMetric = 0;
    private PercentageMetric percentageMetric;

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

    public DahmerauLevenshteinComparator(PercentageMetric percentageMetric) {
        this();
        this.percentageMetric = percentageMetric;
    }

    public boolean isEqual(String s1, String s2) {
        return isMetrickPresent() ? compareConsiderPercentageMetric(s1, s2) : compare(s1, s2);
    }

    private boolean compareConsiderPercentageMetric(String s1, String s2) {
        int distance = dahmerauLevenshtein.findDistance(s1, s2);
        int maxPossibleDistance = Math.max(
                dahmerauLevenshtein.findDistance(s1, ""),
                dahmerauLevenshtein.findDistance("", s2)
        );
        if(0 == maxPossibleDistance) {
            return true;
        }

        int distanceAsPercentage = distance  * 100 / maxPossibleDistance;

        return percentageMetric.getPercentage() >= distanceAsPercentage;
    }

    private boolean compare(String s1, String s2) {
        int distance = dahmerauLevenshtein.findDistance(s1, s2);
        return allowableStringMetric >= distance;
    }

    private boolean isMetrickPresent() {
        return null != percentageMetric;
    }
}
