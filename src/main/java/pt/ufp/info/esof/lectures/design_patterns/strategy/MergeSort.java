package pt.ufp.info.esof.lectures.design_patterns.strategy;

public class MergeSort<T extends Comparable<T>> implements Sort<T> {

    private final Comparable<T>[] a;

    public MergeSort(Comparable<T>[] a) {
        System.out.println("Using Merge sort");
        this.a = a;
    }

    private void merge(Comparable<T>[] a, Comparable<T>[] aux, int lo, int mid, int hi) {
        assert isSorted(a, lo, mid);

        assert isSorted(a, mid + 1, hi);

        int i;
        for (i = lo; i <= hi; ++i) {
            aux[i] = a[i];
        }

        i = lo;
        int j = mid + 1;

        for (int k = lo; k <= hi; ++k) {
            if (i > mid) {
                a[k] = aux[j++];
            } else if (j > hi) {
                a[k] = aux[i++];
            } else if (less(aux[j], aux[i])) {
                a[k] = aux[j++];
            } else {
                a[k] = aux[i++];
            }
        }

        assert isSorted(a, lo, hi);

    }

    private void sort(Comparable<T>[] a, Comparable<T>[] aux, int lo, int hi) {
        if (hi > lo) {
            int mid = lo + (hi - lo) / 2;
            sort(a, aux, lo, mid);
            sort(a, aux, mid + 1, hi);
            merge(a, aux, lo, mid, hi);
        }
    }

    public void sort() {
        Comparable<T>[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length - 1);

        assert isSorted(a);

    }

    private boolean less(Comparable<T> v, Comparable<T> w) {
        return v.compareTo((T) w) < 0;
    }

    private void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
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

    public boolean isSorted(){
        return isSorted(a);
    }

    private void merge(Comparable<T>[] a, int[] index, int[] aux, int lo, int mid, int hi) {
        int i;
        for (i = lo; i <= hi; ++i) {
            aux[i] = index[i];
        }

        i = lo;
        int j = mid + 1;

        for (int k = lo; k <= hi; ++k) {
            if (i > mid) {
                index[k] = aux[j++];
            } else if (j > hi) {
                index[k] = aux[i++];
            } else if (less(a[aux[j]], a[aux[i]])) {
                index[k] = aux[j++];
            } else {
                index[k] = aux[i++];
            }
        }

    }

    public int[] indexSort(Comparable<T>[] a) {
        int N = a.length;
        int[] index = new int[N];

        for (int i = 0; i < N; index[i] = i++) {
        }

        int[] aux = new int[N];
        sort(a, index, aux, 0, N - 1);
        return index;
    }

    private void sort(Comparable<T>[] a, int[] index, int[] aux, int lo, int hi) {
        if (hi > lo) {
            int mid = lo + (hi - lo) / 2;
            sort(a, index, aux, lo, mid);
            sort(a, index, aux, mid + 1, hi);
            merge(a, index, aux, lo, mid, hi);
        }
    }

    public void show() {
        for (Comparable<T> tComparable : a) {
            System.out.print(tComparable +" ");
        }
    }
}
