package lesson3

import java.util.SortedSet
import kotlin.NoSuchElementException

// Attention: comparable supported but comparator is not
class KtBinaryTree<T : Comparable<T>> : AbstractMutableSet<T>(), SortedSet<T> {

    private var root: Node<T>? = null

    override var size = 0
        private set

    private class Node<T>(val value: T) {

        var left: Node<T>? = null

        var right: Node<T>? = null
    }

    override fun add(element: T): Boolean {
        val closest = find(element)
        val comparison = if (closest == null) -1 else element.compareTo(closest.value)
        if (comparison == 0) {
            return false
        }
        val newNode = Node(element)
        when {
            closest == null -> root = newNode
            comparison < 0 -> {
                assert(closest.left == null)
                closest.left = newNode
            }
            else -> {
                assert(closest.right == null)
                closest.right = newNode
            }
        }
        size++
        return true
    }

    internal fun checkInvariant(): Boolean =
            root?.let { checkInvariant(it) } ?: true

    private fun checkInvariant(node: Node<T>): Boolean {
        val left = node.left
        if (left != null && (left.value >= node.value || !checkInvariant(left))) return false
        val right = node.right
        return right == null || right.value > node.value && checkInvariant(right)
    }

    override fun remove(element: T): Boolean {
        throw UnsupportedOperationException()
    }

    override operator fun contains(element: T): Boolean {
        val closest = find(element)
        return closest != null && element.compareTo(closest.value) == 0
    }

    private fun find(value: T): Node<T>? =
            root?.let { find(it, value) }

    private fun find(start: Node<T>, value: T): Node<T> {
        val comparison = value.compareTo(start.value)
        return when {
            comparison == 0 -> start
            comparison < 0 -> start.left?.let { find(it, value) } ?: start
            else -> start.right?.let { find(it, value) } ?: start
        }
    }

    inner class BinaryTreeIterator : MutableIterator<T> {

        private var current: Node<T>? = null

        private fun findNext(): Node<T>? {
            throw UnsupportedOperationException()
        }

        override fun hasNext(): Boolean = findNext() != null

        override fun next(): T {
            current = findNext()
            return (current ?: throw NoSuchElementException()).value
        }

        override fun remove() {
            throw UnsupportedOperationException()
        }
    }

    override fun iterator(): MutableIterator<T> = BinaryTreeIterator()

    override fun comparator(): Comparator<in T>? = null

    override fun subSet(fromElement: T, toElement: T): SortedSet<T> {
        throw UnsupportedOperationException()
    }

    override fun headSet(toElement: T): SortedSet<T> {
        throw UnsupportedOperationException()
    }

    override fun tailSet(fromElement: T): SortedSet<T> {
        throw UnsupportedOperationException()
    }

    override fun first(): T {
        var current: Node<T> = root ?: throw NoSuchElementException()
        while (current.left != null) {
            current = current.left!!
        }
        return current.value
    }

    override fun last(): T {
        var current: Node<T> = root ?: throw NoSuchElementException()
        while (current.right != null) {
            current = current.right!!
        }
        return current.value
    }
}
