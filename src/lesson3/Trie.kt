package lesson3

import java.util.*

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
        return TrieIterator(this)
    }

    private inner class TrieIterator(private val trie: Trie): MutableIterator<String>{
        private val deque = ArrayDeque<Node>()
        init {
            deque.addLast(trie.root)
        }
        private val sb = StringBuilder()
        private var currentChar: Char? = null
        private var numberOfRemaining = trie.size

        private fun findNext(): String {
            val node = deque.last
            val childrenIterator = node.children.iterator()
            if (currentChar != null) {
                while (childrenIterator.hasNext() && childrenIterator.next().key != currentChar) {}
            }
            if (childrenIterator.hasNext()) {
                val next = childrenIterator.next()
                return if (next.key != 0.toChar()) {
                    currentChar = null
                    sb.append(next.key)
                    deque.addLast(next.value)
                    findNext()
                } else {
                    currentChar = 0.toChar()
                    sb.toString()
                }
            }
            else {
                deque.pollLast()
                currentChar = sb.last()
                sb.deleteCharAt(sb.lastIndex)
                return findNext()
            }
        }

        /**
         * Returns the next element in the iteration.
         */
        override fun next(): String {
            numberOfRemaining--
            return findNext()
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
            trie.remove(sb.toString())
        }
    }
}