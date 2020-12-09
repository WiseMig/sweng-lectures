package pt.ufp.info.esof.lectures.strategy;
import java.util.Comparator;

public class InsertionSort<T extends Comparable<T>> implements Sort<T> {
    private Comparable<T>[] a;


    public InsertionSort(Comparable<T>[] a) {
        System.out.println("Using Insertion sort");
        this.a = a;
    }

    public void sort() {
        int N = a.length;

        for (int i = 0; i < N; ++i) {
            for (int j = i; j > 0 && less(a[j], (T) a[j - 1]); --j) {
                exch((Object[]) a, j, j - 1);
            }

            assert isSorted(a, 0, i);
        }

        assert isSorted(a);

    }

    public void sort(Comparator<T> c) {
        int N = a.length;

        for (int i = 0; i < N; ++i) {
            for (int j = i; j > 0 && less(c, (T) a[j], (T) a[j - 1]); --j) {
                exch(a, j, j - 1);
            }

            assert isSorted((T[]) a, c, 0, i);
        }

        assert isSorted((T[]) a, c);

    }

    public int[] indexSort() {
        int N = a.length;
        int[] index = new int[N];

        int i;
        for (i = 0; i < N; index[i] = i++) {
        }

        for (i = 0; i < N; ++i) {
            for (int j = i; j > 0 && less(a[index[j]], (T) a[index[j - 1]]); --j) {
                exch(index, j, j - 1);
            }
        }

        return index;
    }

    private boolean less(Comparable<T> v, T w) {
        return v.compareTo(w) < 0;
    }

    private boolean less(Comparator<T> c, T v, T w) {
        return c.compare(v, w) < 0;
    }

    private void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    private void exch(int[] a, int i, int j) {
        int swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    private boolean isSorted(Comparable<T>[] a) {
        return isSorted(a, 0, a.length - 1);
    }

    private boolean isSorted(Comparable<T>[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; ++i) {
            if (less(a[i], (T) a[i - 1])) {
                return false;
            }
        }

        return true;
    }

    private boolean isSorted(T[] a, Comparator<T> c) {
        return isSorted(a, c, 0, a.length - 1);
    }

    private boolean isSorted(T[] a, Comparator<T> c, int lo, int hi) {
        for (int i = lo + 1; i <= hi; ++i) {
            if (less(c, a[i], a[i - 1])) {
                return false;
            }
        }
        return true;
    }

    public boolean isSorted(){
        return isSorted(a);
    }

    public void show() {
        for (Comparable<T> tComparable : a) {
            System.out.print(tComparable +" ");
        }

    }
}
