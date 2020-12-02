package pt.ufp.info.esof.lectures.strategy;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class InsertionSortTest {

    @Test
    void sort() {


        Integer[] values=new Integer[10];
        Random random=new Random(1);
        for(int i=0;i<10;i++){
            values[i]=random.nextInt();
        }

        Sort<Integer> insertionSort=new InsertionSort<>(values);
        assertFalse(insertionSort.isSorted());
        insertionSort.sort();
        assertTrue(insertionSort.isSorted());

    }
}