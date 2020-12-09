package pt.ufp.info.esof.lectures.strategy;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class MergeSortTest {

    @Test
    void sort() {

        Integer[] values=new Integer[10];
        Random random=new Random(1);
        for(int i=0;i<10;i++){
            values[i]=random.nextInt();
        }

        Sort<Integer> mergeSort=new MergeSort<>(values);
        assertFalse(mergeSort.isSorted());
        mergeSort.sort();
        assertTrue(mergeSort.isSorted());
    }
}