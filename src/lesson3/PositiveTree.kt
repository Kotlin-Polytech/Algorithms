package lesson3

import java.util.Comparator
import java.util.SortedSet

/**
 * Этот класс воспринимать только как пример (несмотря на TO DO)
 */
class PositiveSortedSet(private val delegate: SortedSet<Int>) : AbstractMutableSet<Int>(), SortedSet<Int> {
    override fun comparator(): Comparator<in Int>? = delegate.comparator()

    override fun subSet(fromElement: Int?, toElement: Int?): SortedSet<Int> {
        TODO()
    }

    override fun headSet(toElement: Int?): SortedSet<Int> {
        TODO()
    }

    override fun tailSet(fromElement: Int?): SortedSet<Int> {
        TODO()
    }

    override fun last(): Int {
        TODO()
    }

    override fun first(): Int {
        TODO()
    }

    override fun add(element: Int): Boolean {
        if (element <= 0) return false
        return delegate.add(element)
    }

    override fun remove(element: Int): Boolean {
        if (element <= 0) return false
        return delegate.remove(element)
    }

    override fun iterator(): MutableIterator<Int> = object : MutableIterator<Int> {
        private val delegate = this@PositiveSortedSet.delegate.iterator()

        private var next: Int? = null

        init {
            while (delegate.hasNext()) {
                val next = delegate.next()
                if (next > 0) {
                    this.next = next
                    break
                }
            }
        }

        override fun hasNext(): Boolean {
            return next != null
        }

        override fun next(): Int {
            val result = next ?: throw NoSuchElementException()
            next = if (delegate.hasNext()) delegate.next() else null
            return result
        }

        override fun remove() {
            delegate.remove()
        }

    }

    override val size: Int
        get() = delegate.count { it > 0 }

}

fun SortedSet<Int>.positives(): SortedSet<Int> = PositiveSortedSet(this)