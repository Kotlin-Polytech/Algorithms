package lesson7.genetic

import java.util.*

internal class Chromosome(val visitingOrder: List<Int>) {
    constructor(size: Int, random: Random) : this(
        (0 until size).toMutableList().apply { shuffle(random) }
    )

    fun mutate(random: Random): Chromosome =
        Chromosome(
            visitingOrder.toMutableList().apply {
                val first = random.nextInt(visitingOrder.size)
                var second: Int
                do {
                    second = random.nextInt(visitingOrder.size)
                } while (first == second)
                Collections.swap(this, first, second)
            }
        )

    fun crossBreed(other: Chromosome, random: Random): Chromosome {
        assert(visitingOrder.size == other.visitingOrder.size)
        val result = mutableSetOf<Int>()
        for ((index, vertexNumber) in visitingOrder.withIndex()) {
            val otherVertexNumber = other.visitingOrder[index]
            val resultVertexNumber = when (vertexNumber) {
                otherVertexNumber -> vertexNumber
                else -> {
                    val (first, second) = when (random.nextBoolean()) {
                        true -> vertexNumber to otherVertexNumber
                        false -> otherVertexNumber to vertexNumber
                    }
                    when {
                        first !in result -> first
                        second !in result -> second
                        else -> {
                            var randomNumber: Int
                            do {
                                randomNumber = random.nextInt(visitingOrder.size)
                            } while (randomNumber in result ||
                                visitingOrder.indexOf(randomNumber) == other.visitingOrder.indexOf(randomNumber)
                            )
                            randomNumber
                        }
                    }
                }
            }
            result += resultVertexNumber
        }
        assert(result.size == visitingOrder.size)
        return Chromosome(result.toList())
    }
}