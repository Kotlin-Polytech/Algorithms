package lesson5.genetic

import lesson2.Graph
import lesson2.Path
import java.util.*

class GeneticVoyagingPathSearcher(val g: Graph) {
    private val size = g.vertices.size
    private val vertexByIndex: MutableMap<Int, Graph.Vertex> = mutableMapOf()
    private val random = Random()

    init {
        for ((index, vertex) in g.vertices.withIndex()) {
            vertexByIndex[index] = vertex
        }
    }

    private fun generateChromosomes(number: Int): List<Chromosome> {
        val result = mutableListOf<Chromosome>()
        for (i in 0 until number) {
            result += Chromosome(size, random)
        }
        return result
    }

    private fun Chromosome.evaluation(): Int {
        var result = 0
        for (i in 0 until size) {
            val previousVertex = vertexByIndex[this.visitingOrder[i]]!!
            val nextVertex = vertexByIndex[this.visitingOrder[if (i < size - 1) i + 1 else 0]]!!
            val edge = g.getConnection(previousVertex, nextVertex)
            result += edge?.weight ?: 1000000
        }
        return result
    }

    private fun List<Chromosome>.generateCrossBreeds(): List<Chromosome> {
        val result = mutableListOf<Chromosome>()
        for (i in 0 until size) {
            val first = this[random.nextInt(size)]
            val second = this[random.nextInt(size)]
            result += if (first === second) first else first.crossBreed(second, random)
        }
        return result
    }

    fun findVoyagingPath(chromosomeNumber: Int, generationNumber: Int): Path {
        var chromosomes = generateChromosomes(chromosomeNumber)
        for (generation in 0 until generationNumber) {
            val crossBreeds = chromosomes.generateCrossBreeds()
            val crossBreedsAfterMutation = crossBreeds.map {
                if (random.nextDouble() < 0.1) it.mutate(random) else it
            }
            val evaluatedChromosomes = (chromosomes + crossBreedsAfterMutation)
                    .sortedBy { it.evaluation() }
            chromosomes = evaluatedChromosomes.subList(0, size)
        }
        val visitingOrder = chromosomes.first().visitingOrder
        var path = Path(vertexByIndex[visitingOrder[0]]!!)
        for (i in 1 until size) {
            path = Path(path, g, vertexByIndex[visitingOrder[i]]!!)
        }
        return Path(path, g, vertexByIndex[visitingOrder[0]]!!)
    }
}