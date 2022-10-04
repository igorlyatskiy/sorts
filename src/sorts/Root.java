package sorts;


import java.util.Random;
import java.util.*;

public class Root {
    public final int[] randomArray = new int[10000];
    public int[] array = new int[10000];

    Root() {
        var random = new Random();

        for (int i = 0; i < randomArray.length; i++) {
            randomArray[i] = random.nextInt(10000) + 1;
        }
    }

    public void showData() {
        for (int item : array) {
            System.out.println(item);
        }
    }

    public int[] bubbleSort() {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (array[i] < array[j]) {
                    var tmp = array[i];
                    array[i] = array[j];
                    array[j] = tmp;
                }
            }
        }
        return array;
    }

    public int[] insertionSort() {
        for (int i = 1; i < array.length; i++) {
            int current = array[i];
            int j = i - 1;
            while (j >= 0 && current < array[j]) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = current;
        }
        return array;
    }

    public int[] mergeSort(int[] arr, int lo, int hi) {

        if (lo == hi) {
            int[] br = new int[1];
            br[0] = arr[lo];

            return br;
        }

        int mid = (lo + hi) / 2;

        int[] fh = mergeSort(arr, lo, mid);
        int[] sh = mergeSort(arr, mid + 1, hi);

        return mergeTwoSortedArrays(fh, sh);
    }

    public int[] mergeTwoSortedArrays(int[] one, int[] two) {

        int[] sorted = new int[one.length + two.length];

        int i = 0;
        int j = 0;
        int k = 0;

        while (i < one.length && j < two.length) {

            if (one[i] < two[j]) {
                sorted[k] = one[i];
                k++;
                i++;
            } else {
                sorted[k] = two[j];
                k++;
                j++;
            }
        }

        if (i == one.length) {

            while (j < two.length) {
                sorted[k] = two[j];
                k++;
                j++;
            }
        }

        if (j == two.length) {

            while (i < one.length) {
                sorted[k] = one[i];
                k++;
                i++;
            }
        }

        return sorted;

    }

    public int[] quickSort(int[] array, int startIndex, int endIndex) {
        if(startIndex < endIndex) {
            int pivotIndex = getPivotIndex(array, startIndex, endIndex);
            quickSort(array, startIndex, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, endIndex);
        }

        return array;
    }

    private void swap(int[] array, int startIndex, int pivotIndex) {
        int tmp = array[startIndex];
        array[startIndex] = array[pivotIndex];
        array[pivotIndex] = tmp;
    }

    private int getPivotIndex(int[] array, int startIndex, int endIndex) {
        int pivotIndex = startIndex;
        int i;
        for(i = startIndex + 1; i <= endIndex; i++) {
            if(array[i] < array[startIndex]) {
                swap(array, ++pivotIndex, i);
            }
        }

        swap(array, startIndex, pivotIndex);
        return pivotIndex;
    }

    public int[] oddEvenSort(int[] arr, int n)
    {
        boolean isSorted = false;

        while (!isSorted)
        {
            isSorted = true;
            int temp =0;

            for (int i=1; i<=n-2; i=i+2)
            {
                if (arr[i] > arr[i+1])
                {
                    temp = arr[i];
                    arr[i] = arr[i+1];
                    arr[i+1] = temp;
                    isSorted = false;
                }
            }

            for (int i=0; i<=n-2; i=i+2)
            {
                if (arr[i] > arr[i+1])
                {
                    temp = arr[i];
                    arr[i] = arr[i+1];
                    arr[i+1] = temp;
                    isSorted = false;
                }
            }
        }

        return arr;
    }

    public int[] radixSort(int[] arr) {
        int max = getMaximum(arr);
        int numberOfDigits = getNumberOfDigits(max);

        List<Integer>[] buckets = new List[10];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }

        int divisor = 1;
        // i == digit index
        for (int i = 0; i < numberOfDigits; i++) {
            if (i != 0) divisor *= 10;
            // раскидываем по корзинам с учетом digit
            for (int elem : arr) {
                int digit = elem / divisor % 10;
                buckets[digit].add(elem);
            }

            //обновляем исходный массив
            int arrIndex = 0;
            for (List<Integer> bucket : buckets) {
                for (int elem : bucket) {
                    arr[arrIndex] = elem;
                    arrIndex++;
                }
                bucket.clear();
            }
        }

        return arr;
    }

    private static int getMaximum(int[] arr) {
        int max = arr[0];
        for (int elem : arr) {
            if (elem > max) {
                max = elem;
            }
        }
        return max;
    }

    private int getNumberOfDigits(int number) {
        return (int) (Math.log10(number) + 1);
    }

}
