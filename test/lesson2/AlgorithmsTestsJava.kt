package lesson2

import org.junit.jupiter.api.Tag
import kotlin.test.Test

class AlgorithmsTestsJava : AbstractAlgorithmsTests() {
    @Test
    @Tag("Easy")
    fun testOptimizeBuyAndSell() {
        optimizeBuyAndSell { JavaAlgorithms.optimizeBuyAndSell(it) }
    }

    @Test
    @Tag("Easy")
    fun testJosephTask() {
        josephTask { menNumber, choiceInterval -> JavaAlgorithms.josephTask(menNumber, choiceInterval) }
    }

    @Test
    @Tag("Normal")
    fun testLongestCommonSubstring() {
        longestCommonSubstring { first, second -> JavaAlgorithms.longestCommonSubstring(first, second) }
    }

    @Test
    @Tag("Easy")
    fun testCalcPrimesNumber() {
        calcPrimesNumber { JavaAlgorithms.calcPrimesNumber(it) }
    }

    @Test
    @Tag("Hard")
    fun testBaldaSearcher() {
        baldaSearcher { inputName, words -> JavaAlgorithms.baldaSearcher(inputName, words) }
    }
}