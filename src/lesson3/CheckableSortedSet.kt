package lesson3

import java.util.*

interface CheckableSortedSet<T> : SortedSet<T> {
    fun checkInvariant(): Boolean

    fun height(): Int
}