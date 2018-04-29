//Kyler Ramsey karamsey@calpoly.edu + Liam Lefferts Ljcates@calpoly.edu - 1/19/18 Project 1

import java.util.Random;

public class SortCounts {


    static int selectionCount;
    static int mergeCount;
    static int quickCount;

    private static int MAX_SIZE = 12800;

    public static void main(String[] args) {
        Random rand = new Random();

        long selectionTotal = 0;
        long mergeTotal = 0;
        long quickTotal = 0;
        int[] arr1 = new int[MAX_SIZE];
        int[] arr2 = new int[MAX_SIZE];
        int[] arr3 = new int[MAX_SIZE];

        for (int i = 100; i <= MAX_SIZE; i*=2) {
            selectionCount = quickCount = mergeCount = 0;
            selectionTotal = quickTotal = mergeTotal = 0;
           for (int j = 0; j < 100; j++) {
               for (int k = 0; k < i; k++) {
                arr1[k] = arr2[k] = arr3[k] = rand.nextInt(i);
                }

                selectionCount = Sorts1.selectionSort(arr1, i);
                selectionTotal += selectionCount;

                mergeCount = Sorts1.mergeSort(arr2, i);
                mergeTotal += mergeCount;

                quickCount = Sorts1.quickSort(arr3, i);
                quickTotal += quickCount;

                }
                System.out.println("N=" + i + ":     " + "C_ss= " + selectionTotal/100 +",     C_ms=" + mergeTotal/100 + ",     C_qs= " + quickTotal/100);
        }
        System.out.println();
    }


    public static long computeTime(long start, long end){
        return end - start;
    }

    public static long getTime(){
        return (System.nanoTime()/1000000);
    }
}
