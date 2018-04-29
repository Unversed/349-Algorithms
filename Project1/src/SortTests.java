import static org.junit.Assert.fail;
import static org.junit.Assert.assertTrue;


import java.util.Arrays;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;


public class SortTests {

    private int[] numbers;
    private final static int SIZE = 7;
    private final static int MAX = 20;

    @Before
    public void setUp() throws Exception {
        numbers = new int[SIZE];
        Random generator = new Random();
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = generator.nextInt(MAX);
        }
    }

    @Test
    public void testMergeSort() {
        long startTime = System.currentTimeMillis();

        Sorts.mergeSort(numbers,  numbers.length);

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        //System.out.println("Mergesort " + elapsedTime);
        //System.out.println(numbers.length);

        for (int i = 0; i < numbers.length-1; i++) {
            System.out.println(numbers[i]);

            if (numbers[i] > numbers[i + 1]) {
                System.out.println("I: "+numbers[i]);
                System.out.println("I+1: " +numbers[(i+1)]);
                System.out.println("I index is :" + i);
                fail("Should not happen");
            }

        }
        assertTrue(true);

    }
}


