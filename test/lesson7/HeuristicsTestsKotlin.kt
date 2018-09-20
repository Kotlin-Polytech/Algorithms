package lesson7

import org.junit.jupiter.api.Tag
import kotlin.test.Test

class HeuristicsTestsKotlin : AbstractHeuristicsTests() {

    @Test
    @Tag("Impossible")
    fun testFillKnapsackCompareWithGreedyTest() {
        fillKnapsackCompareWithGreedyTest { load, items -> fillKnapsackHeuristics(load, items) }
    }

    @Test
    @Tag("Impossible")
    fun testFindVoyagingPathHeuristics() {
        findVoyagingPathHeuristics { findVoyagingPathHeuristics() }
    }
}