package lesson6

import org.junit.jupiter.api.Tag
import kotlin.test.Test

class DynamicTestsJava : AbstractDynamicTests() {

    @Test
    @Tag("Normal")
    fun testLongestCommonSubSequence() {
        longestCommonSubSequence { first, second -> JavaDynamicTasks.longestCommonSubSequence(first, second) }
    }

    @Test
    @Tag("Hard")
    fun testLongestIncreasingSubSequence() {
        longestIncreasingSubSequence { JavaDynamicTasks.longestIncreasingSubSequence(it) }
    }

    @Test
    @Tag("Normal")
    fun testShortestPathOnField() {
        shortestPathOnField { JavaDynamicTasks.shortestPathOnField(it) }
    }
}