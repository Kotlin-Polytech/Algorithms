package lesson2

import org.junit.jupiter.api.Tag
import kotlin.test.Test

class AlgorithmsTestsKotlin : AbstractAlgorithmsTests() {
    @Test
    @Tag("Easy")
    fun testOptimizeBuyAndSell() {
        optimizeBuyAndSell { optimizeBuyAndSell(it) }
    }

    @Test
    @Tag("Easy")
    fun testJosephTask() {
        josephTask { menNumber, choiceInterval -> josephTask(menNumber, choiceInterval) }
    }

    @Test
    @Tag("Normal")
    fun testLongestCommonSubstring() {
        longestCommonSubstring { first, second -> longestCommonSubstring(first, second) }
    }

    @Test
    @Tag("Easy")
    fun testCalcPrimesNumber() {
        calcPrimesNumber { calcPrimesNumber(it) }
    }

    @Test
    @Tag("Hard")
    fun testBaldaSearcher() {
        baldaSearcher { inputName, words -> baldaSearcher(inputName, words) }
    }
}