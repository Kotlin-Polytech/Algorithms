package lesson5

import kotlin.test.Test
import kotlin.test.assertEquals

class NoughtsAndCrossesTest {

    @Test
    fun gameOne() {
        val nc = NoughtsAndCrosses()
        nc.makeTurn(0, 0, true)
        nc.makeTurn(1, 0, false)
        nc.makeTurn(0, 1, true)
        nc.makeTurn(1, 1, false)
        nc.makeTurn(0, 2, true)
        assertEquals(true, nc.findWinnerIfAny())
    }

    @Test
    fun gameTwo() {
        val nc = NoughtsAndCrosses()
        nc.makeTurn(1, 1, true)
        nc.makeTurn(0, 0, false)
        nc.makeTurn(2, 2, true)
        nc.makeTurn(2, 0, false)
        nc.makeTurn(0, 2, true)
        nc.makeTurn(1, 0, false)
        assertEquals(false, nc.findWinnerIfAny())
    }
}