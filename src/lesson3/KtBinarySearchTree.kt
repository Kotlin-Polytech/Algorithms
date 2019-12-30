package lesson3

import java.util.*
import kotlin.math.max

// attention: Comparable is supported but Comparator is not
class KtBinarySearchTree<T : Comparable<T>> : AbstractMutableSet<T>(), CheckableSortedSet<T> {

    private class Node<T>(
        val value: T
    ) {
        var left: Node<T>? = null
        var right: Node<T>? = null
    }

    private var root: Node<T>? = null

    override var size = 0
        private set

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

    override operator fun contains(element: T): Boolean {
        val closest = find(element)
        return closest != null && element.compareTo(closest.value) == 0
    }

    /**
     * Добавление элемента в дерево
     *
     * Если элемента нет в множестве, функция добавляет его в дерево и возвращает true.
     * В ином случае функция оставляет множество нетронутым и возвращает false.
     *
     * Спецификация: https://docs.oracle.com/javase/8/docs/api/java/util/Set.html#add-E-
     *
     * Пример
     */
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

    /**
     * Удаление элемента из дерева
     *
     * Если элемент есть в множестве, функция удаляет его из дерева и возвращает true.
     * В ином случае функция оставляет множество нетронутым и возвращает false.
     * Высота дерева не должна увеличиться в результате удаления.
     *
     * Спецификация: https://docs.oracle.com/javase/8/docs/api/java/util/Set.html#remove-java.lang.Object-
     * (в Котлине тип параметера изменён с Object на тип хранимых в дереве данных)
     *
     * Средняя
     */
    override fun remove(element: T): Boolean {
        TODO()
    }

    override fun comparator(): Comparator<in T>? =
        null

    override fun iterator(): MutableIterator<T> =
        BinarySearchTreeIterator()

    inner class BinarySearchTreeIterator internal constructor() : MutableIterator<T> {

        /**
         * Проверка наличия следующего элемента
         *
         * Функция возвращает true, если итерация по множеству ещё не окончена (то есть, если вызов next() вернёт
         * следующий элемент множества, а не бросит исключение); иначе возвращает false.
         *
         * Спецификация: https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html#hasNext--
         *
         * Средняя
         */
        override fun hasNext(): Boolean {
            // TODO
            throw NotImplementedError()
        }

        /**
         * Получение следующего элемента
         *
         * Функция возвращает следующий элемент множества. Так как KtBinarySearchTree реализует интерфейс SortedSet,
         * последовательные вызовы next() должны возвращать элементы в порядке возрастания.
         *
         * Бросает NoSuchElementException, если все элементы уже были возвращены.
         *
         * Спецификация: https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html#next--
         *
         * Средняя
         */
        override fun next(): T {
            // TODO
            throw NotImplementedError()
        }

        /**
         * Удаление предыдущего элемента
         *
         * Функция удаляет из множества элемент, возвращённый крайним вызовом функции next().
         *
         * Бросает IllegalStateException, если функция была вызвана до вызова функции next() или же была вызвана
         * более одного раза после любого вызова next().
         *
         * Спецификация: https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html#remove--
         *
         * Сложная
         */
        override fun remove() {
            // TODO
            throw NotImplementedError()
        }

    }

    /**
     * Вернуть подмножество всех элементов в диапазоне [fromElement, toElement).
     *
     * Функция возвращает множество, содержащее в себе все элементы дерева, которые
     * больше или равны fromElement и строго меньше toElement.
     * При равенстве fromElement и toElement возвращается пустое множество.
     * Изменения в дереве должны отображаться в полученном подмножестве, и наоборот.
     *
     * При попытке добавить в подмножество или удалить из него элемент за пределами указанного диапазона
     * должен бросаться IllegalArgumentException.
     *
     * Спецификация: https://docs.oracle.com/javase/8/docs/api/java/util/SortedSet.html#subSet-E-E-
     *
     * Очень сложная
     */
    override fun subSet(fromElement: T, toElement: T): SortedSet<T> {
        TODO()
    }

    /**
     * Вернуть подмножество всех элементов строго меньше заданного.
     *
     * Для деталей см. subSet(T, T): SortedSet<T>.
     *
     * Спецификация: https://docs.oracle.com/javase/8/docs/api/java/util/SortedSet.html#headSet-E-
     *
     * Сложная
     */
    override fun headSet(toElement: T): SortedSet<T> {
        TODO()
    }

    /**
     * Вернуть подмножество всех элементов больше заданного или равных ему.
     *
     * Для деталей см. subSet(T, T): SortedSet<T>.
     *
     * Спецификация: https://docs.oracle.com/javase/8/docs/api/java/util/SortedSet.html#tailSet-E-
     *
     * Сложная
     */
    override fun tailSet(fromElement: T): SortedSet<T> {
        TODO()
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

    override fun height(): Int =
        height(root)

    private fun height(node: Node<T>?): Int {
        if (node == null) return 0
        return 1 + max(height(node.left), height(node.right))
    }

    override fun checkInvariant(): Boolean =
        root?.let { checkInvariant(it) } ?: true

    private fun checkInvariant(node: Node<T>): Boolean {
        val left = node.left
        if (left != null && (left.value >= node.value || !checkInvariant(left))) return false
        val right = node.right
        return right == null || right.value > node.value && checkInvariant(right)
    }

}