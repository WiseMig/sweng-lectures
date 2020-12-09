package pt.ufp.info.esof.lectures.design_patterns.strategy;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class SortStrategyTest {

    @Test
    void sort() {
        Integer[] values=new Integer[10];
        Random random=new Random(1);
        for(int i=0;i<10;i++){
            values[i]=random.nextInt();
        }

        Sort<Integer> sortStrategy=new SortStrategy<>(values);
        assertFalse(sortStrategy.isSorted());
        sortStrategy.sort();
        assertTrue(sortStrategy.isSorted());

        Integer[] randomValues=new Integer[100];
        random=new Random(1);
        for(int i=0;i<100;i++){
            randomValues[i]=random.nextInt();
        }

        sortStrategy=new SortStrategy<>(randomValues);
        assertFalse(sortStrategy.isSorted());
        sortStrategy.sort();
        assertTrue(sortStrategy.isSorted());


    }
}