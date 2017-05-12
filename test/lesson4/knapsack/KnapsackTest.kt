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
        assertEquals(Fill(cost = 22, items = listOf(
                Item(cost = 8, weight = 10),
                Item(cost = 10, weight = 15),
                Item(cost = 4, weight = 2)
        )), fillKnapsackDynamic(30, items))
    }

    @Test
    fun fillKnapsackGreedy() {
        assertEquals(Fill(cost = 18, items = listOf(
                Item(cost = 6, weight = 8),
                Item(cost = 8, weight = 10),
                Item(cost = 4, weight = 2)
        )), fillKnapsackGreedy(30, items))
    }

}