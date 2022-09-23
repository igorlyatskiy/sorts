package sorts;


import java.util.Random;

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

    public int[] quickSort(int[] array) {
        if (array.length < 2) return array;

        int[] left = new int[0];
        int[] right = new int[0];

        int pivot = array[0];

        for (int i = 1; i < array.length; i++) {
            if (array[i] >= pivot) {
                right = expandArray(right);
                right[right.length - 1] = array[i];
            }
            if (array[i] < pivot) {
                left = expandArray(left);
                left[left.length - 1] = array[i];
            }
        }

        int[] pivotArray = new int[1];
        pivotArray[0] = pivot;

        int[] sortedLeft = quickSort(left);
        int[] sortedRight = quickSort(right);

        return uniteArrays(uniteArrays(sortedLeft, pivotArray), sortedRight);
    }

    public int[] expandArray(int[] array) {
        int[] newArray = new int[array.length + 1];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }

        return newArray;
    }

    public int[] uniteArrays(int[] one, int[] two) {
        int[] resultArray = new int[one.length + two.length];

        int i = 0;
        int j = 0;
        int k = 0;
        while (i < one.length) {
            resultArray[k] = one[i];

            i++;
            k++;
        }
        while (j < two.length) {
            resultArray[k] = two[j];

            j++;
            k++;
        }

        return resultArray;
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
}
