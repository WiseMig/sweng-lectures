package pt.ufp.info.esof.lectures.strategy;

public interface Sort<T extends Comparable<T>> {
    void sort();
    boolean isSorted();
}
