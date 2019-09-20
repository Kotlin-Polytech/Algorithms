package lesson5

import org.junit.jupiter.api.Tag
import kotlin.test.Test
import kotlin.test.assertEquals

class NoughtsAndCrossesTest {

    @Test
    @Tag("Example")
    fun gameOne() {
        val nc = NoughtsAndCrosses()
        nc.makeTurn(0, 0, true)
        nc.makeTurn(1, 0, false)
        nc.makeTurn(0, 1, true)
        assertEquals(NoughtsAndCrosses.Cell(0, 2), nc.findBestMoveAndEvaluation(false).first)
        nc.makeTurn(1, 1, false)
        assertEquals(NoughtsAndCrosses.Cell(0, 2), nc.findBestMoveAndEvaluation(true).first)
        nc.makeTurn(0, 2, true)
        assertEquals(true, nc.findWinnerIfAny())
    }

    @Test
    @Tag("Example")
    fun gameTwo() {
        val nc = NoughtsAndCrosses()
        nc.makeTurn(1, 1, true)
        nc.makeTurn(0, 0, false)
        nc.makeTurn(2, 2, true)
        assertEquals(NoughtsAndCrosses.Cell(0, 2), nc.findBestMoveAndEvaluation(false).first)
        nc.makeTurn(0, 2, false)
        assertEquals(NoughtsAndCrosses.Cell(0, 1), nc.findBestMoveAndEvaluation(true).first)
        nc.makeTurn(2, 0, true)
        nc.makeTurn(0, 1, false)
        assertEquals(false, nc.findWinnerIfAny())
    }

    @Test
    @Tag("Example")
    fun gameThree() {
        val nc = NoughtsAndCrosses()
        nc.makeTurn(1, 1, true)
        nc.makeTurn(0, 0, false)
        nc.makeTurn(2, 2, true)
        nc.makeTurn(1, 0, false)
        assertEquals(NoughtsAndCrosses.Cell(2, 0), nc.findBestMoveAndEvaluation(true).first)
        nc.makeTurn(2, 0, true)
        nc.makeTurn(0, 2, false)
        assertEquals(NoughtsAndCrosses.Cell(2, 1), nc.findBestMoveAndEvaluation(true).first)
        nc.makeTurn(2, 1, true)
        assertEquals(true, nc.findWinnerIfAny())
    }

    @Test
    @Tag("Example")
    fun gameFour() {
        val nc = NoughtsAndCrosses()
        nc.makeTurn(1, 1, true)
        nc.makeTurn(0, 0, false)
        nc.makeTurn(2, 2, true)
        nc.makeTurn(1, 0, false)
        nc.makeTurn(0, 1, true)
        assertEquals(NoughtsAndCrosses.Cell(2, 0), nc.findBestMoveAndEvaluation(false).first)
    }

    @Test
    @Tag("Example")
    fun gameFive() {
        val nc = NoughtsAndCrosses()
        nc.makeTurn(1, 1, true)
        nc.makeTurn(0, 0, false)
        nc.makeTurn(2, 2, true)
        nc.makeTurn(0, 2, false)
        assertEquals(0, nc.findBestMoveAndEvaluation(true).second)
        nc.makeTurn(0, 1, true)
        assertEquals(0, nc.findBestMoveAndEvaluation(false).second)
    }
}