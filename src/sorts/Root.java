package sorts;


import sorts.bst.Tree;

import java.util.Random;
import java.util.*;

public class Root {
    public static final int ARRAY_SIZE = 50000;
    public static final int MAX_RANDOM_INT = 100000;
    public final int[] randomArray = new int[ARRAY_SIZE];
    public static int[] array = new int[ARRAY_SIZE];

    Root() {
        var random = new Random();

        for (int i = 0; i < randomArray.length; i++) {
            randomArray[i] = random.nextInt(MAX_RANDOM_INT) + 1;
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
        if (startIndex < endIndex) {
            int pivotIndex = getPivotIndex(array, startIndex, endIndex);
            quickSort(array, startIndex, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, endIndex);
        }

        return array;
    }

    private static void swap(int[] array, int startIndex, int pivotIndex) {
        int tmp = array[startIndex];
        array[startIndex] = array[pivotIndex];
        array[pivotIndex] = tmp;
    }

    private int getPivotIndex(int[] array, int startIndex, int endIndex) {
        int pivotIndex = startIndex;
        int i;
        for (i = startIndex + 1; i <= endIndex; i++) {
            if (array[i] < array[startIndex]) {
                swap(array, ++pivotIndex, i);
            }
        }

        swap(array, startIndex, pivotIndex);
        return pivotIndex;
    }

    public int[] oddEvenSort(int[] arr, int n) {
        boolean isSorted = false;

        while (!isSorted) {
            isSorted = true;
            int temp = 0;

            for (int i = 1; i <= n - 2; i = i + 2) {
                if (arr[i] > arr[i + 1]) {
                    temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    isSorted = false;
                }
            }

            for (int i = 0; i <= n - 2; i = i + 2) {
                if (arr[i] > arr[i + 1]) {
                    temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
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

    //TimSort
    static int MIN_MERGE = 32;

    public static int minRunLength(int n) {
        assert n >= 0;

        int r = 0;
        while (n >= MIN_MERGE) {
            r |= (n & 1);
            n >>= 1;
        }
        return n + r;
    }

    public static void timSortInsertionSort(int[] arr, int left,
                                            int right) {
        for (int i = left + 1; i <= right; i++) {
            int temp = arr[i];
            int j = i - 1;
            while (j >= left && arr[j] > temp) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = temp;
        }
    }

    public static void timSortMerge(int[] arr, int l,
                                    int m, int r) {
        int len1 = m - l + 1, len2 = r - m;
        int[] left = new int[len1];
        int[] right = new int[len2];
        for (int x = 0; x < len1; x++) {
            left[x] = arr[l + x];
        }
        for (int x = 0; x < len2; x++) {
            right[x] = arr[m + 1 + x];
        }

        int i = 0;
        int j = 0;
        int k = l;

        while (i < len1 && j < len2) {
            if (left[i] <= right[j]) {
                arr[k] = left[i];
                i++;
            } else {
                arr[k] = right[j];
                j++;
            }
            k++;
        }

        while (i < len1) {
            arr[k] = left[i];
            k++;
            i++;
        }

        while (j < len2) {
            arr[k] = right[j];
            k++;
            j++;
        }
    }

    public static int[] timSort(int[] arr, int n) {
        int minRun = minRunLength(MIN_MERGE);

        for (int i = 0; i < n; i += minRun) {
            timSortInsertionSort(arr, i,
                    Math.min((i + MIN_MERGE - 1), (n - 1)));
        }

        for (int size = minRun; size < n; size = 2 * size) {

            for (int left = 0; left < n;
                 left += 2 * size) {

                int mid = left + size - 1;
                int right = Math.min((left + 2 * size - 1),
                        (n - 1));

                if (mid < right)
                    timSortMerge(arr, left, mid, right);
            }
        }

        return arr;
    }


    public int[] treeSort() {
        var tree = new Tree();
        for (int n : array) {
            tree.add(n);
        }

        return tree.inorder().stream().mapToInt(i -> Integer.parseInt(i.toString())).toArray();
    }


    public static int[] shakerSort(int[] arr, int n) {
        int begin = 0;
        int end = n - 1;
        while (begin < end) {
            for (int j = begin; j < end; j++) {
                if (arr[j + 1] < arr[j]) {
                    int t;
                    t = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = t;
                }
            }

            end--;
            for (int j = end; j > begin; j--) {
                if (arr[j] < arr[j - 1]) {
                    int t;
                    t = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = t;
                }
            }
            begin++;
        }
        return arr;
    }

    public static int[] shellSort(int[] array) {
        int h = 1;
        while (h * 3 < array.length)
            h = h * 3 + 1;

        while (h >= 1) {
            int length = array.length;
            for (int i = h; i < length; i++) {
                for (int j = i; j >= h; j = j - h) {
                    if (array[j] < array[j - h])
                        swap(array, j, j - h);
                    else
                        break;
                }
            }
            h = h / 3;
        }
        return array;
    }


    public static int[] SelectionMethodSort(int[] array) {
        for (int i = 0; i < array.length; i++) {   // i - номер текущего шага
            int pos = i;
            int min = array[i];
            // цикл выбора наименьшего элемента
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < min) {
                    pos = j;    // pos - индекс наименьшего элемента
                    min = array[j];
                }
            }
            array[pos] = array[i];
            array[i] = min;    // меняем местами наименьший с array[i]
        }
        return array;
    }

    public static int[] bucketSort(int[] paramArray) {
        int maximum_value = 0;
        for (int d = 0; d < paramArray.length; d++)
            if (paramArray[d] > maximum_value)
                maximum_value = paramArray[d];

        int[] newbucket = new int[maximum_value + 1];

        int[] sorted_array = new int[paramArray.length];

        for (int a = 0; a < paramArray.length; a++)
            newbucket[paramArray[a]]++;

        int position = 0;
        for (int b = 0; b < newbucket.length; b++)
            for (int c = 0; c < newbucket[b]; c++)
                sorted_array[position++] = b;
        return sorted_array;
    }


    public static int[] patienceSort(int[] theArray) {
        List<List<Integer>> new_list = new ArrayList<>();
        for (int i = 0; i < theArray.length; i++) {
            List<Integer> bucket_list = new ArrayList<>();
            if (i == 0) {
                bucket_list.add(theArray[i]);
                new_list.add(bucket_list);
            } else {
                boolean is_ok = false;
                for (List<?> o : new_list) {
                    if (theArray[i] < (int) o.get(0)) {
                        ((List) o).add(0, theArray[i]);
                        is_ok = true;
                        break;
                    }
                }
                if (!is_ok) {
                    bucket_list.add(theArray[i]);
                    new_list.add(bucket_list);
                }
            }
        }
        int[] ok_list = new int[theArray.length];
        int q = 0;
        for (List<Integer> integers : new_list) {
            for (Integer integer : integers) {
                ok_list[q] = (int) integer;
                q++;
            }
        }
        int n = ok_list.length;
        int tmp;
        int j;
        for (int i = 1; i < n; i++) {
            tmp = ok_list[i];
            for (j = i - 1; j >= 0 && ok_list[j] > tmp; j--) {
                ok_list[j + 1] = ok_list[j];
            }
            ok_list[j + 1] = tmp;
        }
        return ok_list;
    }

    //Heap sort

    public static int[] heapSort(int[] a) {
        int heapSize = a.length;
        buildHeap(a, heapSize);
        while (heapSize > 1) {
            int temp = a[0];
            a[0] = a[heapSize - 1];
            a[heapSize - 1] = temp;
            heapSize--;
            heapify(a, 0, heapSize);
        }
        return a;
    }

    //доп метод для heapSort, строит кучу (переупорядочивает поддеревья)
    private static void buildHeap(int[] a, int heap) {

        for (int i = a.length / 2; i >= 0; i--) {
            heapify(a, i, heap);
        }
    }

    //доп метод переупорядочивает поддерево кучи начиная с узла i так, чтобы выполнялось
    //основное свойство кучи - a[parent] >= a[child].
    private static void heapify(int[] a, int i, int heapSize) {
        int l = 2 * i + 1; // индекс левого потомка текущего узла
        int r = 2 * i + 2; // индекс правого потомка текущего узла
        int largest = i;
        if (l < heapSize && a[i] < a[l]) {
            largest = l;
        }
        if (r < heapSize && a[largest] < a[r]) {
            largest = r;
        }
        if (i != largest) {
            int temp = a[i];
            a[i] = a[largest];
            a[largest] = temp;
            heapify(a, largest, heapSize);
        }
    }

    //Shuttle Sort
    public int[] shuttleSort(int[] source) {
        for (int holePosition = 0; holePosition < source.length; holePosition++) {
            int takenValue = source[holePosition];
            int c;
            for (c = holePosition - 1; c >= 0 && takenValue < source[c]; c--) {
                source[c + 1] = source[c];
            }
            source[c + 1] = takenValue;
        }
        return source;
    }
}

