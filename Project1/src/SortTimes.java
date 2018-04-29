//Kyler Ramsey karamsey@calpoly.edu + Liam Lefferts Ljcates@calpoly.edu - 1/19/18 Project 1

import java.util.Random;

public class SortTimes {


    static long endTime;
    static long startTime;
    static long selectionRuntime;
    static long mergeRuntime;
    static long quickRuntime;

    public static void main(String[] args) {
        for (int i = 5000; i <= 160000; i*=2) {
            for (int j = 0; j < 5; j++) {
                int[] arr1 = list();
                int[] arr2 = new int[160000];
                int[] arr3 = new int[160000];
                System.arraycopy(arr1, 0, arr2, 0, arr1.length);
                System.arraycopy(arr1, 0, arr3, 0, arr1.length);


                startTime = getTime();
                Sorts.selectionSort(arr1, i);
                endTime = getTime();
                selectionRuntime = computeTime(startTime, endTime);


                startTime = getTime();
                Sorts.mergeSort(arr2, i);
                endTime = getTime();
                mergeRuntime = computeTime(startTime, endTime);

                startTime = getTime();
                Sorts.quickSort(arr3, i);
                endTime = getTime();
                quickRuntime = computeTime(startTime, endTime);
                System.out.println("N=" + i + ":     " + "T_ss= " + selectionRuntime +",     T_ms=" + mergeRuntime + ",     T_qs= " + quickRuntime);
            }
            System.out.println();
        }
    }


    public static int randomFill(){
        Random rand = new Random();
        int randomNum = rand.nextInt(160000);
        return randomNum;
    }

    public static int[] list(){
        int anArray[] = new int[160000];
        for (int i = 0; i < anArray.length; i++){
            anArray[i] = randomFill();
        }
        return anArray;
    }

    public static long computeTime(long start, long end){
        return end - start;
    }

    public static long getTime(){
        return (System.nanoTime()/1000000);
    }
}
