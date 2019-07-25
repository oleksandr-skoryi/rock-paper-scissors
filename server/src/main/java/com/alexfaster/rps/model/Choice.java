package com.alexfaster.rps.model;

import java.util.Comparator;

public enum Choice implements Comparable<Choice> {
    R, P, S;

    public static Choice getEnemy(final Choice choice) {
        switch (choice) {
            case P:
                return S;
            case R:
                return P;
            case S:
                return R;
            default:
                throw new IllegalArgumentException("Unpredictable choice");
        }
    }

    public static class ChoiceComparator implements Comparator<Choice> {

        public int compare(final Choice o1, final Choice o2) {

            if (o1 == R && o2 == S) {
                return 1;
            }
            if (o1 == R && o2 == P) {
                return -1;
            }
            if (o1 == S && o2 == P) {
                return 1;
            }
            if (o1 == S && o2 == R) {
                return -1;
            }
            if (o1 == P && o2 == R) {
                return 1;
            }
            if (o1 == P && o2 == S) {
                return -1;
            }
            return 0;
        }

    }
}
