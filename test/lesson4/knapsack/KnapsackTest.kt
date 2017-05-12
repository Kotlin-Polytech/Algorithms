package lesson4.knapsack

import org.junit.Test

import org.junit.Assert.*

class KnapsackTest {
    private val items = listOf(
            Item(cost = 8, weight = 10),
            Item(cost = 5, weight = 12),
            Item(cost = 6, weight = 8),
            Item(cost = 10, weight = 15),
            Item(cost = 4, weight = 2)
    )

    @Test
    fun fillKnapsackDynamic() {
        assertEquals(Fill(22,
                Item(cost = 8, weight = 10),
                Item(cost = 10, weight = 15),
                Item(cost = 4, weight = 2)
        ), fillKnapsackDynamic(30, items))
    }

    @Test
    fun fillKnapsackGreedy() {
        assertEquals(Fill(18,
                Item(cost = 6, weight = 8),
                Item(cost = 8, weight = 10),
                Item(cost = 4, weight = 2)
        ), fillKnapsackGreedy(30, items))
    }

    private val items2 = listOf(
            Item(cost = 1, weight = 3),
            Item(cost = 6, weight = 4),
            Item(cost = 4, weight = 5),
            Item(cost = 7, weight = 8),
            Item(cost = 6, weight = 9)
    )

    @Test
    fun fillKnapsackDynamic2() {
        assertEquals(Fill(13,
                Item(cost = 6, weight = 4),
                Item(cost = 7, weight = 8)
        ), fillKnapsackDynamic(13, items2))
    }

    @Test
    fun fillKnapsackGreedy2() {
        assertEquals(Fill(13,
                Item(cost = 6, weight = 4),
                Item(cost = 7, weight = 8)
        ), fillKnapsackGreedy(13, items2))
    }

}