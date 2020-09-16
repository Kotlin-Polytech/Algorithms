package lesson7

import org.junit.jupiter.api.Tag
import kotlin.test.Test

class DynamicTestsJava : AbstractDynamicTests() {

    @Test
    @Tag("Normal")
    fun testLongestCommonSubSequenceJava() {
        longestCommonSubSequence { first, second -> JavaDynamicTasks.longestCommonSubSequence(first, second) }
    }

    @Test
    @Tag("Hard")
    fun testLongestIncreasingSubSequenceJava() {
        longestIncreasingSubSequence { JavaDynamicTasks.longestIncreasingSubSequence(it) }
    }

    @Test
    @Tag("Normal")
    fun testShortestPathOnFieldJava() {
        shortestPathOnField { JavaDynamicTasks.shortestPathOnField(it) }
    }
}