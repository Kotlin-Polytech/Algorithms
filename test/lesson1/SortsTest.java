package lesson1;

import org.junit.Test;

import java.util.Calendar;
import java.util.Random;

import static org.junit.Assert.*;

public class SortsTest {

    static private final Random r = new Random(Calendar.getInstance().getTimeInMillis());

    private static void assertSorted(int[] arr, String prefix) {
        for (int i = 0; i< arr.length-1; i++) {
            assertTrue(prefix + " ERROR: i = " + i + " a[i] = " + arr[i] + " a[i+1] = " + arr[i+1],
                       arr[i] <= arr[i+1]);
        }
    }

    private static <T extends Comparable<T>> void assertSorted(T[] arr, String prefix) {
        for (int i = 0; i< arr.length-1; i++) {
            assertTrue(prefix + " ERROR: i = " + i + " a[i] = " + arr[i] + " a[i+1] = " + arr[i+1],
                       arr[i].compareTo(arr[i+1]) <= 0);
        }
    }

    @Test
    public void insertionSort() {
        int[] arr = new int[] { 3, 7, 5, 9, 1, 6, 19, 13 };
        Sorts.insertionSort(arr);
        assertSorted(arr, "INSERTION SORT");
    }

    @Test
    public void insertionSortStrings() {
        String[] arr = new String[] { "beta", "omega", "alpha", "", "!!!", "teta", "O" };
        Sorts.insertionSort(arr);
        assertSorted(arr, "INSERTION SORT");
    }

    @Test
    public void mergeSort() {
        int[] arr = new int[] { 3, 7, 5, 9, 1, 6, 19, 13 };
        Sorts.mergeSort(arr);
        assertSorted(arr, "MERGE SORT");
    }

    @Test
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
    public void longMergeSort() {
        int LENGTH = 65536;
        int[] arr = new int[LENGTH];
        for (int i=0; i<LENGTH; i++) {
            arr[i] = r.nextInt();
        }
        Sorts.mergeSort(arr);
        assertSorted(arr, "MERGE SORT LONG");

    }
}