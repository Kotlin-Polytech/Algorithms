package lesson6.rod

import org.junit.jupiter.api.Tag
import kotlin.test.*

class CutTest {
    private val cost = mapOf(1 to 1, 2 to 5, 3 to 9, 4 to 9, 5 to 10, 6 to 17, 7 to 17, 8 to 20, 9 to 24)

    @Test
    @Tag("Example")
    fun cutRod() {
        assertEquals(Cut(cost = 59, length = listOf(2, 3, 3, 3, 3, 3, 3)), cutRod(20) { cost[it] ?: 0 })
        assertEquals(Cut(cost = 28, length = listOf(1, 3, 3, 3)), cutRod(10) { cost[it] ?: 0 })
        assertEquals(Cut(cost = 23, length = listOf(2, 3, 3)), cutRod(8) { cost[it] ?: 0 })
    }

    private val cost2 = mapOf(1 to 1, 2 to 2, 3 to 4, 4 to 7)

    @Test
    @Tag("Example")
    fun cutRod2() {
        assertEquals(Cut(cost = 15, length = listOf(1, 4, 4)), cutRod(9) { cost2[it] ?: 0 })
        assertEquals(Cut(cost = 4, length = listOf(3)), cutRod(3) { cost2[it] ?: 0 })
    }

    @Test
    @Tag("Example")
    fun cutRod3() {
        assertEquals(Cut(cost = 17, length = listOf(1, 4)), cutRod(5) { listOf(0, 3, 5, 10, 14, 16)[it] })
    }
}