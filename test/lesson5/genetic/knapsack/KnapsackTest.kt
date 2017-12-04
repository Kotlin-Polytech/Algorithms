package lesson5.genetic.knapsack

import org.junit.Assert.*
import org.junit.Test

class KnapsackTest {

    private val load1 = 50
    private val load2 = 30
    private val load3 = 13

    private val list1 = listOf(
            Item(cost = 44, weight = 10),
            Item(cost = 80, weight = 11),
            Item(cost = 90, weight = 9),
            Item(cost = 22, weight = 16),
            Item(cost = 9, weight = 4),
            Item(cost = 8, weight = 4)
    )

    private val solution1 = listOf(
            Item(cost = 44, weight = 10),
            Item(cost = 80, weight = 11),
            Item(cost = 90, weight = 9),
            Item(cost = 22, weight = 16),
            Item(cost = 9, weight = 4)
    )

    private val list2 = listOf(
            Item(cost = 8, weight = 10),
            Item(cost = 5, weight = 12),
            Item(cost = 6, weight = 8),
            Item(cost = 10, weight = 15),
            Item(cost = 4, weight = 2)
    )

    private val solution2 = listOf(
            Item(cost = 8, weight = 10),
            Item(cost = 10, weight = 15),
            Item(cost = 4, weight = 2)
    )

    private val list3 = listOf(
            Item(cost = 1, weight = 3),
            Item(cost = 6, weight = 4),
            Item(cost = 4, weight = 5),
            Item(cost = 7, weight = 8),
            Item(cost = 6, weight = 9)
    )

    private val solution3 = listOf(
            Item(cost = 6, weight = 4),
            Item(cost = 7, weight = 8)
    )

    @Test
    fun test1() {
        val knapsackProblemSolver = KnapsackProblemSolver(list1, load1)
        val solution = knapsackProblemSolver.findSolution(30)
        assertEquals(solution1, solution)
    }

    @Test
    fun test2() {
        val knapsackProblemSolver = KnapsackProblemSolver(list2, load2, populations = 41)
        val solution = knapsackProblemSolver.findSolution(30)
        assertEquals(solution2, solution)
    }

    @Test
    fun test3() {
        val knapsackProblemSolver = KnapsackProblemSolver(list3, load3)
        val solution = knapsackProblemSolver.findSolution(30)
        assertEquals(solution3, solution)
    }
}