package lesson2

import org.junit.jupiter.api.Tag
import kotlin.test.Test

class AlgorithmsTestsJava : AbstractAlgorithmsTests() {
    @Test
    @Tag("Easy")
    fun testOptimizeBuyAndSellJava() {
        optimizeBuyAndSell { JavaAlgorithms.optimizeBuyAndSell(it) }
    }

    @Test
    @Tag("Easy")
    fun testJosephTaskJava() {
        josephTask { menNumber, choiceInterval -> JavaAlgorithms.josephTask(menNumber, choiceInterval) }
    }

    @Test
    @Tag("Normal")
    fun testLongestCommonSubstringJava() {
        longestCommonSubstring { first, second -> JavaAlgorithms.longestCommonSubstring(first, second) }
    }

    @Test
    @Tag("Easy")
    fun testCalcPrimesNumberJava() {
        calcPrimesNumber { JavaAlgorithms.calcPrimesNumber(it) }
    }
}