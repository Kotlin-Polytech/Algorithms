package lesson1

import java.util.*

private val random = Random(Calendar.getInstance().timeInMillis)

fun <T : Comparable<T>> insertionSort(elements: MutableList<T>) {
    for (i in 1 until elements.size) {
        val current = elements[i]
        var j = i - 1
        while (j >= 0) {
            if (elements[j] > current)
                elements[j + 1] = elements[j]
            else
                break
            j--
        }
        elements[j + 1] = current
    }
}

private fun merge(elements: IntArray, begin: Int, middle: Int, end: Int) {
    val left = elements.copyOfRange(begin, middle)
    val right = elements.copyOfRange(middle, end)
    var li = 0
    var ri = 0
    for (i in begin until end) {
        if (li < left.size && (ri == right.size || left[li] <= right[ri])) {
            elements[i] = left[li++]
        } else {
            elements[i] = right[ri++]
        }
    }
}

private fun mergeSort(elements: IntArray, begin: Int, end: Int) {
    if (end - begin <= 1) return
    val middle = (begin + end) / 2
    mergeSort(elements, begin, middle)
    mergeSort(elements, middle, end)
    merge(elements, begin, middle, end)
}

fun mergeSort(elements: IntArray) {
    mergeSort(elements, 0, elements.size)
}

private fun heapify(elements: IntArray, start: Int, length: Int) {
    val left = 2 * start + 1
    val right = left + 1
    var max = start
    if (left < length && elements[left] > elements[max]) {
        max = left
    }
    if (right < length && elements[right] > elements[max]) {
        max = right
    }
    if (max != start) {
        val temp = elements[max]
        elements[max] = elements[start]
        elements[start] = temp
        heapify(elements, max, length)
    }
}

private fun buildHeap(elements: IntArray) {
    for (start in elements.size / 2 - 1 downTo 0) {
        heapify(elements, start, elements.size)
    }
}

fun heapSort(elements: IntArray) {
    buildHeap(elements)
    for (j in elements.size - 1 downTo 1) {
        val temp = elements[0]
        elements[0] = elements[j]
        elements[j] = temp
        heapify(elements, 0, j)
    }
}

private fun partition(elements: IntArray, min: Int, max: Int): Int {
    val x = elements[min + random.nextInt(max - min + 1)]
    var left = min
    var right = max
    while (left <= right) {
        while (elements[left] < x) {
            left++
        }
        while (elements[right] > x) {
            right--
        }
        if (left <= right) {
            val temp = elements[left]
            elements[left] = elements[right]
            elements[right] = temp
            left++
            right--
        }
    }
    return right
}

private fun quickSort(elements: IntArray, min: Int, max: Int) {
    if (min < max) {
        val border = partition(elements, min, max)
        quickSort(elements, min, border)
        quickSort(elements, border + 1, max)
    }
}

fun quickSort(elements: IntArray) {
    quickSort(elements, 0, elements.size - 1)
}

fun countingSort(elements: IntArray, limit: Int): IntArray {
    val count = IntArray(limit + 1)
    for (element in elements) {
        count[element]++
    }
    for (j in 1..limit) {
        count[j] += count[j - 1]
    }
    val out = IntArray(elements.size)
    for (j in elements.indices.reversed()) {
        out[count[elements[j]] - 1] = elements[j]
        count[elements[j]]--
    }
    return out
}

/**
 * Библиотечные сортировки (приведены только для примера,
 * рекомендуется использовать list.sorted() и list.sort() напрямую)
 */
fun <T : Comparable<T>> librarySortForImmutable(list: List<T>): List<T> = list.sorted()

fun <T : Comparable<T>> librarySortForMutable(list: MutableList<T>) {
    list.sort()
}