package lesson1

import org.junit.jupiter.api.Tag
import kotlin.test.Test

class TaskTestsKotlin : AbstractTaskTests() {

    @Test
    @Tag("3")
    fun testSortTimes() {
        sortTimes { inputName, outputName -> sortTimes(inputName, outputName) }
    }

    @Test
    @Tag("4")
    fun testSortAddresses() {
        sortAddresses { inputName, outputName -> sortAddresses(inputName, outputName) }
    }

    @Test
    @Tag("4")
    fun testSortTemperatures() {
        sortTemperatures { inputName, outputName -> sortTemperatures(inputName, outputName) }
    }

    @Test
    @Tag("4")
    fun testSortSequence() {
        sortSequence { inputName, outputName -> sortSequence(inputName, outputName) }
    }

    @Test
    @Tag("2")
    fun testMergeArrays() {
        mergeArrays { first, second -> mergeArrays(first, second) }
    }
}
