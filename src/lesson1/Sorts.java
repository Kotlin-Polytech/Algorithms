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

    private static void heapify(int[] elements, int start, int length) {
        int left = 2 * start + 1;
        int right = left + 1;
        int max = start;
        if (left < length && elements[left] > elements[max]) {
            max = left;
        }
        if (right < length && elements[right] > elements[max]) {
            max = right;
        }
        if (max != start) {
            int temp = elements[max];
            elements[max] = elements[start];
            elements[start] = temp;
            heapify(elements, max, length);
        }
    }

    private static void buildHeap(int[] elements) {
        for (int start = elements.length / 2 - 1; start >= 0; start--) {
            heapify(elements, start, elements.length);
        }
    }

    public static void heapSort(int[] elements) {
        buildHeap(elements);
        for (int j = elements.length - 1; j >= 1; j--) {
            int temp = elements[0];
            elements[0] = elements[j];
            elements[j] = temp;
            heapify(elements, 0, j);
        }
    }
}