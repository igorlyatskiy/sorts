package sorts;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;

public class TestRoot {
    private final Root sorts = new Root();
    private int[] sortedData;

    TestRoot() {
        sortedData = Arrays.copyOf(sorts.randomArray, sorts.randomArray.length);
        Arrays.sort(sortedData);
    }

    @BeforeMethod()
    public void restoreArray() {
        sorts.array = Arrays.copyOf(sorts.randomArray, sorts.randomArray.length);
    }

    @Test
    public void isBubbleSortCorrect() {
        Assert.assertEquals(sorts.bubbleSort(), sortedData);
    }

    @Test
    public void isInsertionSortCorrect() {
        Assert.assertEquals(sorts.insertionSort(), sortedData);
    }

    @Test
    public void isMergeSortCorrect() {
        Assert.assertEquals(sorts.mergeSort(sorts.array, 0, sorts.array.length - 1), sortedData);
    }

    @Test
    public void isQuickSortCorrect() {
        Assert.assertEquals(sorts.quickSort(sorts.array, 0, sorts.array.length - 1), sortedData);
    }

    @Test
    public void isOddEvenSortCorrect() { Assert.assertEquals(sorts.oddEvenSort(sorts.array, sorts.array.length), sortedData);}

    @Test
    public void isTimSort(){
        Assert.assertEquals(sorts.timSort(sorts.array, sorts.array.length), sortedData);
    }

    @Test
    public void isShakerSortCorrect() {
        Assert.assertEquals(sorts.shakerSort(sorts.array, sorts.array.length), sortedData);
    }
}
