package lesson5

class NoughtsAndCrosses {

    private data class Cell(val x: Int, val y: Int)

    private val field = mutableMapOf<Cell, Boolean>()

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
}