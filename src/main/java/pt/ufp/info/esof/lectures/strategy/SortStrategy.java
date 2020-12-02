package pt.ufp.info.esof.lectures.strategy;

import java.util.Comparator;

public class SortStrategy<T extends Comparable<T>> implements Sort<T>{

    private Comparable<T>[]a;

    public SortStrategy(Comparable<T>[] a) {
        this.a = a;
    }


    @Override
    public void sort() {
        if(a.length<=10){
            new InsertionSort<>(a).sort();
        }else{
            new MergeSort<>(a).sort();
        }
    }


    @Override
    public boolean isSorted() {
        return isSorted(a);
    }

    private boolean isSorted(Comparable<T>[] a) {
        return isSorted(a, 0, a.length - 1);
    }

    private boolean isSorted(Comparable<T>[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; ++i) {
            if (less(a[i], a[i - 1])) {
                return false;
            }
        }

        return true;
    }
    private boolean less(Comparable<T> v, Comparable<T> w) {
        return v.compareTo((T) w) < 0;
    }
}
