package lesson2

import kotlin.test.Test

class AlgorithmsTestsKotlin : AbstractAlgorithmsTests() {
    @Test
    fun testOptimizeBuyAndSell() {
        optimizeBuyAndSell { optimizeBuyAndSell(it) }
    }

    @Test
    fun testJosephTask() {
        josephTask { menNumber, choiceInterval -> josephTask(menNumber, choiceInterval) }
    }

    @Test
    fun testLongestCommonSubstring() {
        longestCommonSubstring { first, second -> longestCommonSubstring(first, second) }
    }

    @Test
    fun testCalcPrimesNumber() {
        calcPrimesNumber { calcPrimesNumber(it) }
    }

    @Test
    fun testBaldaSearcher() {
        baldaSearcher { inputName, words -> baldaSearcher(inputName, words) }
    }
}