//Kyler Ramsey karamsey@calpoly.edu + Liam Lefferts Ljcates@calpoly.edu - 1/19/18 Project 1

public class Sorts {


    public static void selectionSort(int[] arr, int N) {
        //int length = N;
        for (int i = 0; i < N - 1; i++) {
            int min_index = i;
            for (int j = i + 1; j < N; j++) {
                if (arr[j] < arr[min_index])
                    min_index = j;
            }
            int temp = arr[min_index];
            arr[min_index] = arr[i];
            arr[i] = temp;
        }

    }

    public static void mergeSort(int[] arr, int N) {
        mergeSort(arr, 0, N - 1);
    }

    private static void mergeSort(int[] arr, int first, int last) {
        if (first < last) {
            int middle = first + (last - first) / 2;
            mergeSort(arr, first, middle);
            mergeSort(arr, middle + 1, last);
            mergeSortedHalves(arr, first, middle, last);
        }
    }

    private static void mergeSortedHalves(int arr[], int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;
        /* Create temp arrays */
        int L[] = new int[n1];
        int R[] = new int[n2];
        /*Copy data to temp arrays*/
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];
        /* Merge the temp arrays */
        // Initial indexes of first and second subarrays
        int i = 0, j = 0;
        // Initial index of merged subarry array
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
        /* Copy remaining elements of L[] if any */
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }
        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }


    public static void quickSort(int[] list, int N) {
        quicksort(list, 0, N - 1);
    }

    private static void quicksort(int[] list, int first, int last) {
        if (first < last) {
            setPivotToEnd(list, first, last);
            int pivotIndex = splitList(list, first, last);
            quicksort(list, first, pivotIndex - 1);
            quicksort(list, pivotIndex + 1, last);
        }
    }

    private static void setPivotToEnd(int[] arr, int left, int right) {
        int center = (left + right) / 2;

        int i = arr[left];
        int j = arr[center];
        int k = arr[right];
        int temp;

        if (arr[left] > arr[center]) {
            temp = arr[center];
            arr[center] = arr[left];
            arr[left] = temp;

        }

        if (arr[left] > arr[right]) {
            temp = arr[right];
            arr[right] = arr[left];
            arr[left] = temp;

        }

        if (arr[center] < arr[right]) {
            temp = arr[center];
            arr[center] = arr[right];
            arr[right] = temp;
        }
    }

    private static int splitList(int[] arr, int left, int right) {

        int indexL = left;
        int indexR = right - 1;
        int pivot = arr[right];
        int temp;

        while (indexL <= indexR) {
            while (arr[indexL] < pivot) {
                indexL++;
            }

            while ((indexR >= indexL)) {
                //count++;
                if (arr[indexR] > pivot) {
                    indexR--;
                } else {
                    break;
                }
            }

            if (indexL <= indexR) {
                temp = arr[indexL];
                arr[indexL] = arr[indexR];
                arr[indexR] = temp;
                indexL++;
                indexR--;
            }
        }

        temp = arr[indexL];
        arr[indexL] = pivot;
        arr[right] = temp;

        return indexL;
    }
}
