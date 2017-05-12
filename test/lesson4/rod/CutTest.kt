package lesson4.rod

import org.junit.Test

import org.junit.Assert.*

class CutTest {
    private val cost = mapOf(1 to 1, 2 to 5, 3 to 9, 4 to 9, 5 to 10, 6 to 17, 7 to 17, 8 to 20, 9 to 24)

    @Test
    fun cutRod() {
        assertEquals(Cut(cost = 59, length = listOf(2, 3, 3, 3, 3, 3, 3)), cutRod(20) { cost[it] ?: 0 })
        assertEquals(Cut(cost = 28, length = listOf(1, 3, 3, 3)), cutRod(10) { cost[it] ?: 0 })
        assertEquals(Cut(cost = 23, length = listOf(2, 3, 3)), cutRod(8) { cost[it] ?: 0 })
    }

}