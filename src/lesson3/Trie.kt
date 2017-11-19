package lesson3

import java.util.*
import kotlin.NoSuchElementException

class Trie : AbstractMutableSet<String>(), MutableSet<String> {
    override var size: Int = 0
        private set

    private class Node {
        val children: MutableMap<Char, Node> = linkedMapOf()
    }

    private var root = Node()

    override fun clear() {
        root.children.clear()
        size = 0
    }

    private fun String.withZero() = this + 0.toChar()

    private fun findNode(element: String): Node? {
        var current = root
        for (char in element) {
            current = current.children[char] ?: return null
        }
        return current
    }

    override fun contains(element: String): Boolean =
            findNode(element.withZero()) != null

    override fun add(element: String): Boolean {
        var current = root
        var modified = false
        for (char in element.withZero()) {
            val child = current.children[char]
            if (child != null) {
                current = child
            }
            else {
                modified = true
                val newChild = Node()
                current.children[char] = newChild
                current = newChild
            }
        }
        if (modified) {
            size++
        }
        return modified
    }

    override fun remove(element: String): Boolean {
        val current = findNode(element) ?: return false
        if (current.children.remove(0.toChar()) != null) {
            size--
            return true
        }
        return false
    }

    override fun iterator(): MutableIterator<String> {
        return TrieIterator()
    }

    private inner class TrieIterator: MutableIterator<String>{
        private val deque = ArrayDeque<Iterator<Map.Entry<Char, Node>>>()
        init {
            deque.addLast(root.children.iterator())
        }
        private val sb = StringBuilder()
        private var numberOfRemaining = size
        private var currentSize = size

        private fun findNext(): String {
            val childrenIterator = deque.last
            return if (childrenIterator.hasNext()) {
                val next = childrenIterator.next()
                if (next.key != 0.toChar()) {
                    sb.append(next.key)
                    deque.addLast(next.value.children.iterator())
                    findNext()
                } else {
                    sb.toString()
                }
            } else {
                deque.pollLast()
                sb.deleteCharAt(sb.lastIndex)
                findNext()
            }
        }

        /**
         * Returns the next element in the iteration.
         */
        override fun next(): String {
            if (!hasNext()){
                throw NoSuchElementException()
            } else if (currentSize != size){
                throw ConcurrentModificationException()
            } else{
                numberOfRemaining--
                return findNext()
            }
        }

        /**
         * Returns `true` if the iteration has more elements.
         */
        override fun hasNext(): Boolean {
            return numberOfRemaining != 0
        }

        /**
         * Removes from the underlying collection the last element returned by this iterator.
         */
        override fun remove() {
            if (remove(sb.toString())){
                currentSize--
            } else {
                throw IllegalStateException()
            }
        }
    }
}