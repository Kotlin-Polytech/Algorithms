package lesson2

import kotlin.test.Test
import kotlin.test.assertEquals

class AlgorithmsTests {

    @Test
    fun optimizeBuyAndSell() {
        assertEquals(3 to 4, optimizeBuyAndSell("input/buysell_in1.txt"))
        assertEquals(8 to 12, optimizeBuyAndSell("input/buysell_in2.txt"))
        assertEquals(3 to 4, optimizeBuyAndSell("input/buysell_in3.txt"))
    }
}