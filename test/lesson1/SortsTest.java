package lesson1;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("WeakerAccess")
public class SortsTest {

    static private final Random r = new Random(Calendar.getInstance().getTimeInMillis());

    private static void assertSorted(int[] arr, String prefix) {
        for (int i = 0; i< arr.length-1; i++) {
            assertTrue(arr[i] <= arr[i+1],
                       prefix + " ERROR: i = " + i + " a[i] = " + arr[i] + " a[i+1] = " + arr[i+1]);
        }
    }

    private static <T extends Comparable<T>> void assertSorted(T[] arr, String prefix) {
        for (int i = 0; i< arr.length-1; i++) {
            assertTrue(arr[i].compareTo(arr[i+1]) <= 0,
                       prefix + " ERROR: i = " + i + " a[i] = " + arr[i] + " a[i+1] = " + arr[i+1]);
        }
    }

    @Test
    @Tag("Example")
    public void insertionSort() {
        int[] arr = new int[] { 3, 7, 5, 9, 1, 6, 19, 13 };
        Sorts.insertionSort(arr);
        assertSorted(arr, "INSERTION SORT");
    }

    @Test
    @Tag("Example")
    public void insertionSortStrings() {
        String[] arr = new String[] { "beta", "omega", "alpha", "", "!!!", "teta", "O" };
        Sorts.insertionSort(arr);
        assertSorted(arr, "INSERTION SORT");
    }

    @Test
    @Tag("Example")
    public void mergeSort() {
        int[] arr = new int[] { 3, 7, 5, 9, 1, 6, 19, 13 };
        Sorts.mergeSort(arr);
        assertSorted(arr, "MERGE SORT");
    }

    @Test
    @Tag("Example")
    public void longInsertionSort() {
        int LENGTH = 65536;
        int[] arr = new int[LENGTH];
        for (int i=0; i<LENGTH; i++) {
            arr[i] = r.nextInt();
        }
        Sorts.insertionSort(arr);
        assertSorted(arr, "INSERTION SORT LONG");
    }

    @Test
    @Tag("Example")
    public void longMergeSort() {
        int LENGTH = 65536;
        int[] arr = new int[LENGTH];
        for (int i=0; i<LENGTH; i++) {
            arr[i] = r.nextInt();
        }
        Sorts.mergeSort(arr);
        assertSorted(arr, "MERGE SORT LONG");
    }

    @Test
    @Tag("Example")
    public void longHeapSort() {
        int LENGTH = 65536;
        int[] arr = new int[LENGTH];
        for (int i=0; i<LENGTH; i++) {
            arr[i] = r.nextInt();
        }
        Sorts.heapSort(arr);
        assertSorted(arr, "HEAP SORT LONG");
    }

    @Test
    @Tag("Example")
    public void quickSort() {
        int[] arr = new int[] { 3, 7, 5, 9, 1, 6, 19, 13 };
        Sorts.quickSort(arr);
        assertSorted(arr, "QUICK SORT");
    }

    @Test
    @Tag("Example")
    public void longQuickSort() {
        int LENGTH = 65536;
        int[] arr = new int[LENGTH];
        for (int i=0; i<LENGTH; i++) {
            arr[i] = r.nextInt();
        }
        Sorts.quickSort(arr);
        assertSorted(arr, "QUICK SORT LONG");
    }

    @Test
    @Tag("Example")
    public void longCountingSort() {
        int LENGTH = 65536;
        int LIMIT = 262144;
        int[] arr = new int[LENGTH];
        for (int i=0; i<LENGTH; i++) {
            arr[i] = r.nextInt(LIMIT);
        }
        int[] result = Sorts.countingSort(arr, LIMIT - 1);
        assertSorted(result, "COUNTING SORT LONG");
        Sorts.quickSort(arr);
        assertArrayEquals(arr, result);
    }
}