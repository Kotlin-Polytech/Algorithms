package lesson8

import kotlin.test.Test
import org.junit.jupiter.api.Tag

class HeuristicsTestsJava : AbstractHeuristicsTests() {
    @Test
    @Tag("Impossible")
    fun testFillKnapsackCompareWithGreedyTestJava() {
        fillKnapsackCompareWithGreedyTest { load, items -> JavaHeuristicsTasks.fillKnapsackHeuristics(load, items) }
    }

    @Test
    @Tag("Impossible")
    fun testFindVoyagingPathHeuristicsJava() {
        findVoyagingPathHeuristics { let { JavaHeuristicsTasks.findVoyagingPathHeuristics(it) } }
    }

}