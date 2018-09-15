package lesson6

import kotlin.test.Test
import kotlin.test.assertEquals

class TaskTests {

    @Test
    fun longestCommonSubSequence() {
        assertEquals("", longestCommonSubSequence("мой мир", "я"))
        assertEquals("здс", longestCommonSubSequence("здравствуй мир", "мы здесь"))
        assertEquals("emt ole", longestCommonSubSequence("nematode knowledge", "empty bottle"))
    }

    @Test
    fun longestIncreasingSubSequence() {
        assertEquals(listOf(), longestIncreasingSubSequence(listOf()))
        assertEquals(listOf(1), longestIncreasingSubSequence(listOf(1)))
        assertEquals(listOf(1, 2), longestIncreasingSubSequence(listOf(1, 2)))
        assertEquals(listOf(2, 1), longestIncreasingSubSequence(listOf(2)))
        assertEquals(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10),
                longestIncreasingSubSequence(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
        )
        assertEquals(listOf(2, 8, 9, 12), longestIncreasingSubSequence(listOf(2, 8, 5, 9, 12, 6)))
    }

    @Test
    fun shortestPathOnField() {
        assertEquals(1, shortestPathOnField("input/field_in2.txt"))
        assertEquals(12, shortestPathOnField("input/field_in1.txt"))
    }

}