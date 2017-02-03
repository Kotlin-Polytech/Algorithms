package lesson1;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Random;

@SuppressWarnings("WeakerAccess")
public class Sorts {

    public static <T extends Comparable<T>> void insertionSort(T[] elements) {
        for (int i = 1; i < elements.length; i++) {
            T current = elements[i];
            int j = i - 1;
            for (; j >= 0; j--) {
                if (elements[j].compareTo(current) > 0) elements[j+1] = elements[j];
                else break;
            }
            elements[j+1] = current;
        }
    }

    public static void insertionSort(int[] elements) {
        for (int i = 1; i < elements.length; i++) {
            int current = elements[i];
            int j = i - 1;
            for (; j >= 0; j--) {
                if (elements[j] > current) elements[j+1] = elements[j];
                else break;
            }
            elements[j+1] = current;
        }
    }

    private static void merge(int[] elements, int begin, int middle, int end) {
        int[] left = Arrays.copyOfRange(elements, begin, middle);
        int[] right = Arrays.copyOfRange(elements, middle, end);
        int li = 0, ri = 0;
        for (int i = begin; i < end; i++) {
            if (li < left.length && (ri == right.length || left[li] <= right[ri])) {
                elements[i] = left[li++];
            }
            else {
                elements[i] = right[ri++];
            }
        }
    }

    private static void mergeSort(int[] elements, int begin, int end) {
        if (end - begin <= 1) return;
        int middle = (begin + end) / 2;
        mergeSort(elements, begin, middle);
        mergeSort(elements, middle, end);
        merge(elements, begin, middle, end);
    }

    public static void mergeSort(int[] elements) {
        mergeSort(elements, 0, elements.length);
    }

    public static void main(String[] args) {
        int[] arr = new int[] { 3, 7, 5, 9, 1, 6, 19, 13 };
        int[] arr3 = Arrays.copyOf(arr, arr.length);
        insertionSort(arr);
        mergeSort(arr3);
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(arr3));
        String[] arr2 = new String[] { "beta", "omega", "alpha", "", "!!!", "teta", "O" };
        insertionSort(arr2);
        System.out.println(Arrays.toString(arr2));
        Random r = new Random(Calendar.getInstance().getTimeInMillis());
        int LENGTH = 65536;
        int[] arr4 = new int[LENGTH];
        for (int i=0; i<LENGTH; i++) {
            arr4[i] = r.nextInt();
        }
        int[] arr5 = Arrays.copyOf(arr4, arr4.length);
        insertionSort(arr4);
        mergeSort(arr5);
        checkSort(arr4, "InsertionSort");
        checkSort(arr5, "MergeSort");
    }

    private static void checkSort(int[] arr, String prefix) {
        for (int i = 0; i< arr.length-1; i++) {
            if (arr[i] > arr[i+1]) {
                System.out.println(prefix + " ERROR: i = " + i + " a[i] = " + arr[i] + " a[i+1] = " + arr[i+1]);
            }
        }
    }
}