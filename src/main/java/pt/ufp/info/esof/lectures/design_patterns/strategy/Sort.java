package pt.ufp.info.esof.lectures.design_patterns.strategy;

public interface Sort<T extends Comparable<T>> {
    void sort();
    boolean isSorted();
}
