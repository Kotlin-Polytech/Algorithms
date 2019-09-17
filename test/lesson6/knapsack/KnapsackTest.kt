package lesson6.knapsack

import org.junit.jupiter.api.Tag
import kotlin.test.*
import java.util.*

class KnapsackTest {
    private var items = listOf(
        Item(cost = 8, weight = 10),
        Item(cost = 5, weight = 12),
        Item(cost = 6, weight = 8),
        Item(cost = 10, weight = 15),
        Item(cost = 4, weight = 2)
    )

    @Test
    @Tag("Example")
    fun fillKnapsackDynamic() {
        assertEquals(
            Fill(
                22,
                Item(cost = 8, weight = 10),
                Item(cost = 10, weight = 15),
                Item(cost = 4, weight = 2)
            ), fillKnapsackDynamic(30, items)
        )
    }

    @Test
    @Tag("Example")
    fun fillKnapsackGreedy() {
        assertEquals(
            Fill(
                18,
                Item(cost = 6, weight = 8),
                Item(cost = 8, weight = 10),
                Item(cost = 4, weight = 2)
            ), fillKnapsackGreedy(30, items)
        )
    }

    private val items2 = listOf(
        Item(cost = 1, weight = 3),
        Item(cost = 6, weight = 4),
        Item(cost = 4, weight = 5),
        Item(cost = 7, weight = 8),
        Item(cost = 6, weight = 9)
    )

    @Test
    @Tag("Example")
    fun fillKnapsackDynamic2() {
        assertEquals(
            Fill(
                13,
                Item(cost = 6, weight = 4),
                Item(cost = 7, weight = 8)
            ), fillKnapsackDynamic(13, items2)
        )
    }

    @Test
    @Tag("Example")
    fun fillKnapsackGreedy2() {
        assertEquals(
            Fill(
                13,
                Item(cost = 6, weight = 4),
                Item(cost = 7, weight = 8)
            ), fillKnapsackGreedy(13, items2)
        )
    }

    private val items3 = listOf(
        Item(cost = 2, weight = 1),
        Item(cost = 5, weight = 3),
        Item(cost = 10, weight = 5),
        Item(cost = 15, weight = 7)
    )

    @Test
    @Tag("Example")
    fun fillKnapsackDynamic3() {
        assertEquals(
            Fill(
                20,
                Item(cost = 5, weight = 3),
                Item(cost = 15, weight = 7)
            ), fillKnapsackDynamic(10, items3)
        )
    }

    @Test
    @Tag("Example")
    fun fillKnapsackGreedy3() {
        assertEquals(
            Fill(
                17,
                Item(cost = 2, weight = 1),
                Item(cost = 15, weight = 7)
            ), fillKnapsackGreedy(10, items3)
        )
    }

    @Test
    @Tag("Example")
    fun fillKnapsackGreedyBigTest() {
        for (i in 0..9) {
            val items = mutableListOf<Item>()
            val random = Random()
            for (j in 0 until 10000) {
                items += Item(1 + random.nextInt(10000), 300 + random.nextInt(600))
            }
            try {
                val fillGreedy = fillKnapsackGreedy(1000, items)
                println("Жадный набрал = " + fillGreedy.cost)
                val weight = fillGreedy.items.sumBy { it.weight }
                println("Вес: $weight")
            } catch (e: StackOverflowError) {
                println("Жадный выбыл")
                throw AssertionError(e)
            }
        }
    }
}