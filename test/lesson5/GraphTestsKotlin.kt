package lesson5

import org.junit.jupiter.api.Tag
import kotlin.test.Test

class GraphTestsKotlin : AbstractGraphTests() {

    @Test
    @Tag("Normal")
    fun testFindEulerLoop() {
        findEulerLoop { findEulerLoop() }
    }

    @Test
    @Tag("Normal")
    fun testMinimumSpanningTree() {
        minimumSpanningTree { minimumSpanningTree() }
    }

    @Test
    @Tag("Hard")
    fun testLargestIndependentVertexSet() {
        largestIndependentVertexSet { largestIndependentVertexSet() }
    }

    @Test
    @Tag("Hard")
    fun testLongestSimplePath() {
        longestSimplePath { longestSimplePath() }
    }
}