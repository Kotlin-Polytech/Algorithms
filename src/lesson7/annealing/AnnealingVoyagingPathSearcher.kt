package lesson7.annealing

import lesson5.Graph
import lesson5.Path
import lesson7.AbstractVoyagingPathSearcher
import kotlin.math.exp

class AnnealingVoyagingPathSearcher(
    g: Graph,
    private val startTemperature: Int,
    private val iterationNumber: Int
) : AbstractVoyagingPathSearcher(g) {

    private fun List<Int>.generateNewState(): List<Int> {
        val newState = mutableListOf<Int>()
        val i = random.nextInt(size)
        val j = random.nextInt(size)
        val first = minOf(i, j)
        val second = maxOf(i, j)
        for (k in 0 until first) {
            newState += this[k]
        }
        for (k in second downTo first) {
            newState += this[k]
        }
        for (k in second + 1 until size) {
            newState += this[k]
        }
        assert(newState.size == size)
        return newState
    }

    private fun transitionProbability(evaluationIncrease: Int, temperature: Double): Double {
        return exp(-evaluationIncrease / temperature)
    }

    override fun findVoyagingPath(): Path {
        var state: List<Int> = (0 until size).toMutableList().apply { shuffle() }
        for (i in 1..iterationNumber) {
            val newState = state.generateNewState()
            assert(newState.size == size)
            val evaluation = state.evaluation()
            val newEvaluation = newState.evaluation()

            if (newEvaluation < evaluation) {
                state = newState
            } else if (random.nextDouble() <
                transitionProbability(
                    newEvaluation - evaluation,
                    startTemperature.toDouble() / i
                )
            ) {
                state = newState
            }
        }
        return state.buildPath()
    }
}

fun Graph.findVoyagingPathAnnealing(
    startTemperature: Int,
    iterationNumber: Int,
    @Suppress("UNUSED_PARAMETER") vararg otherParams: Any
): Path = AnnealingVoyagingPathSearcher(this, startTemperature, iterationNumber).findVoyagingPath()