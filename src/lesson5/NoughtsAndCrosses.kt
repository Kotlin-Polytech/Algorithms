package lesson5

class NoughtsAndCrosses() {

    data class Cell(val x: Int, val y: Int)

    private val field = mutableMapOf<Cell, Boolean>()

    constructor(original: NoughtsAndCrosses) : this() {
        field.putAll(original.field)
    }

    fun makeTurn(x: Int, y: Int, isCross: Boolean): Boolean {
        val cell = Cell(x, y)
        if (cell in field) return false
        field[cell] = isCross
        return true
    }

    private val winningLines = listOf(
        listOf(Cell(0, 0), Cell(0, 1), Cell(0, 2)),
        listOf(Cell(1, 0), Cell(1, 1), Cell(1, 2)),
        listOf(Cell(2, 0), Cell(2, 1), Cell(2, 2)),
        listOf(Cell(0, 0), Cell(1, 0), Cell(2, 0)),
        listOf(Cell(0, 1), Cell(1, 1), Cell(2, 1)),
        listOf(Cell(0, 2), Cell(1, 2), Cell(2, 2)),
        listOf(Cell(0, 0), Cell(1, 1), Cell(2, 2)),
        listOf(Cell(0, 2), Cell(1, 1), Cell(2, 0))
    )

    fun findWinnerIfAny(): Boolean? {
        for (line in winningLines) {
            if (line.all { field[it] == true }) return true
            if (line.all { field[it] == false }) return false
        }
        return null
    }

    fun findBestMoveAndEvaluation(crossesTurn: Boolean, depth: Int = 0): Pair<Cell?, Int> {
        when (findWinnerIfAny()) {
            crossesTurn -> return null to 10 - depth
            !crossesTurn -> return null to -10 + depth
        }
        var bestMove: Pair<Cell?, Int> = null to -11
        for (x in 0..2) {
            for (y in 0..2) {
                val move = Cell(x, y)
                if (move in field) continue
                val copy = NoughtsAndCrosses(this)
                copy.makeTurn(x, y, crossesTurn)
                val evaluation = -copy.findBestMoveAndEvaluation(!crossesTurn, depth + 1).second
                if (evaluation > bestMove.second) {
                    bestMove = move to evaluation
                }
            }
        }
        if (bestMove.first == null) {
            return null to 0
        }
        return bestMove
    }
}