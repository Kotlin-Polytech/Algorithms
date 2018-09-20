package lesson3

import org.junit.jupiter.api.Tag
import kotlin.test.*
import java.util.TreeSet

class PositiveSortedSetTest {
    @Test
    @Tag("Example")
    fun basic() {
        val set = listOf(7, -4, 5, 9, -3, 4, 0, 99, -12).let {
            val tree = TreeSet<Int>()
            tree.addAll(it)
            tree
        }
        val subSet = set.positives()
        assertEquals(5, subSet.size)
        subSet.add(10)
        assertEquals(6, subSet.size)
        assertEquals(10, set.size)
        subSet.add(-10)
        assertEquals(6, subSet.size)
        assertEquals(10, set.size)
        set.remove(5)
        assertEquals(5, subSet.size)
        assertEquals(9, set.size)
        set.remove(-4)
        assertEquals(5, subSet.size)
        assertEquals(8, set.size)
        subSet.remove(99)
        assertEquals(4, subSet.size)
        assertEquals(7, set.size)
    }
}