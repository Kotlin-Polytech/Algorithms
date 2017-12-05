package lesson5.genetic.knapsack

import org.junit.Assert.*
import org.junit.Test
import java.util.*

class KnapsackTest {

    private val load1 = 50
    private val load2 = 30
    private val load3 = 13
    private val load4 = 80

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

    private val list4 = listOf(
            Item(cost = 1, weight = 1),
            Item(cost = 2, weight = 2),
            Item(cost = 3, weight = 4),
            Item(cost = 4, weight = 8),
            Item(cost = 5, weight = 16),
            Item(cost = 6, weight = 32),
            Item(cost = 7, weight = 64),
            Item(cost = 8, weight = 128),
            Item(cost = 9, weight = 256),
            Item(cost = 10, weight = 512),
            Item(cost = 11, weight = 1024),
            Item(cost = 12, weight = 2048)
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

    @Test
    fun test4() {
        val knapsackProblemSolver = KnapsackProblemSolver(list4, load4)
        val solution = knapsackProblemSolver.findSolution(40)
        printSolution(solution)
    }

    /**
    Создаем 200 вещей, ценность и масса которых не превышает 300 единиц
    Для определенности используем seed = 10
    Лучшее решение : Solution: 2039 points, 100 weight
    При generations <~ 80 решения не находятся (вещи не помещаются в ранец)
    из-за большого кол-ва вещей
     **/
    @Test
    fun test5() {
        val load = 100
        val list = generateRandomItems(Random(10), 300, 300, 200)
        val knapsackProblemSolver = KnapsackProblemSolver(list, load, populations = 2000)
        val solution = knapsackProblemSolver.findSolution(2000)
        printSolution(solution)
    }


    private fun generateRandomItems(random: Random, maxCost: Int, maxWeight: Int, size: Int): List<Item> {
        val list = mutableListOf<Item>()
        for (i in 0 until size) {
            list += Item(cost = random.nextInt(maxCost), weight = random.nextInt(maxWeight))
        }
        return list
    }

    private fun printSolution(solution: List<Item>) {
        val points = solution.sumBy { it.cost }
        val weight = solution.sumBy { it.weight }
        val text = "Solution: $points points, $weight weight in : "
        println(text)
        solution.forEach { print("$it ") }
    }
}