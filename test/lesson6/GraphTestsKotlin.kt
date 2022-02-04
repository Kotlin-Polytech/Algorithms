package lesson6

import org.junit.jupiter.api.Tag
import kotlin.test.Test

class GraphTestsKotlin : AbstractGraphTests() {

    @Test
    @Tag("6")
    fun testFindEulerLoop() {
        findEulerLoop { findEulerLoop() }
    }

    @Test
    @Tag("7")
    fun testMinimumSpanningTree() {
        minimumSpanningTree { minimumSpanningTree() }
    }

    @Test
    @Tag("10")
    fun testLargestIndependentVertexSet() {
        largestIndependentVertexSet { largestIndependentVertexSet() }
    }

    @Test
    @Tag("8")
    fun testLongestSimplePath() {
        longestSimplePath { longestSimplePath() }
    }

    @Test
    @Tag("6")
    fun testBaldaSearcher() {
        baldaSearcher { inputName, words -> baldaSearcher(inputName, words) }
    }
}