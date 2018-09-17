package lesson2

import kotlin.test.Test

class AlgorithmsTestsJava : AbstractAlgorithmsTests() {
    @Test
    fun testOptimizeBuyAndSell() {
        optimizeBuyAndSell { JavaAlgorithms.optimizeBuyAndSell(it) }
    }

    @Test
    fun testJosephTask() {
        josephTask { menNumber, choiceInterval -> JavaAlgorithms.josephTask(menNumber, choiceInterval) }
    }

    @Test
    fun testLongestCommonSubstring() {
        longestCommonSubstring { first, second -> JavaAlgorithms.longestCommonSubstring(first, second) }
    }

    @Test
    fun testCalcPrimesNumber() {
        calcPrimesNumber { JavaAlgorithms.calcPrimesNumber(it) }
    }

    @Test
    fun testBaldaSearcher() {
        baldaSearcher { inputName, words -> JavaAlgorithms.baldaSearcher(inputName, words) }
    }
}