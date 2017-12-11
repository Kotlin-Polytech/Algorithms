package lesson5.genetic

import lesson2.Graph
import lesson2.Path
import lesson5.AbstractVoyagingPathSearcher

class GeneticVoyagingPathSearcher(
        g: Graph,
        val chromosomeNumber: Int,
        val generationNumber: Int
) : AbstractVoyagingPathSearcher(g) {
    private fun generateChromosomes(number: Int): List<Chromosome> {
        val result = mutableListOf<Chromosome>()
        for (i in 0 until number) {
            result += Chromosome(size, random)
        }
        return result
    }

    private fun Chromosome.evaluation(): Int = visitingOrder.evaluation()

    private fun List<Chromosome>.generateCrossBreeds(): List<Chromosome> {
        val result = mutableListOf<Chromosome>()
        for (i in 0 until size) {
            val first = this[random.nextInt(size)]
            val second = this[random.nextInt(size)]
            result += if (first === second) first else first.crossBreed(second, random)
        }
        return result
    }

    override fun findVoyagingPath(): Path {
        var chromosomes = generateChromosomes(chromosomeNumber)
        for (generation in 0 until generationNumber) {
            val crossBreeds = chromosomes.generateCrossBreeds()
            val crossBreedsAfterMutation = crossBreeds.map {
                if (random.nextDouble() < 0.1) it.mutate(random) else it
            }
            val evaluatedChromosomes = (chromosomes + crossBreedsAfterMutation)
                    .sortedBy { it.evaluation() }
            chromosomes = evaluatedChromosomes.subList(0, chromosomeNumber)
        }
        val visitingOrder = chromosomes.first().visitingOrder
        return visitingOrder.buildPath()
    }
}