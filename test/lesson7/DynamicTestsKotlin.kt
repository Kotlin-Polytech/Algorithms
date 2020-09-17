package lesson7

import org.junit.jupiter.api.Tag
import kotlin.test.Test

class DynamicTestsKotlin : AbstractDynamicTests() {

    @Test
    @Tag("6")
    fun testLongestCommonSubSequence() {
        longestCommonSubSequence { first, second -> longestCommonSubSequence(first, second) }
    }

    @Test
    @Tag("7")
    fun testLongestIncreasingSubSequence() {
        longestIncreasingSubSequence { longestIncreasingSubSequence(it) }
    }

    @Test
    @Tag("5")
    fun testShortestPathOnField() {
        shortestPathOnField { shortestPathOnField(it) }
    }
}