package lesson6

import org.junit.jupiter.api.Tag
import kotlin.test.Test

class DynamicTestsKotlin : AbstractDynamicTests() {

    @Test
    @Tag("Normal")
    fun testLongestCommonSubSequence() {
        longestCommonSubSequence { first, second -> longestCommonSubSequence(first, second) }
    }

    @Test
    @Tag("Hard")
    fun testLongestIncreasingSubSequence() {
        longestIncreasingSubSequence { longestIncreasingSubSequence(it) }
    }

    @Test
    @Tag("Normal")
    fun testShortestPathOnField() {
        shortestPathOnField { shortestPathOnField(it) }
    }
}