package lesson5.genetic.knapsack

import java.util.*

class ChromosomeBool(val genes: BooleanArray) {
    constructor(size: Int, random: Random) :
            this(BooleanArray(size) { (random.nextInt() % 2 == 1) })

    var fined = false
    val size = genes.size

    fun crossover(another: ChromosomeBool): ChromosomeBool {
        assert(another.size == size)
        val del = size / 2
        return ChromosomeBool((this.genes.sliceArray(0 until del) + another.genes.sliceArray(del until size)))
    }

    fun crossoverPair(another: ChromosomeBool) = Pair(this.crossover(another), another.crossover(this))

    fun mutate(random: Random) {
        val index = random.nextInt(size)
        genes[index] = !genes[index]
    }
}

data class Item(val cost: Int, val weight: Int)

private val MUTATION_PROBABILITY = 0.3
private val DEFAULT_POPULATION_SIZE = 32

class KnapsackProblemSolver(private val knapsack: List<Item>,
                            private val load: Int,
                            private val populations: Int,
                            private val seed: Long) {

    constructor(knapsack: List<Item>, maxWeight: Int, seed: Long) :
            this(knapsack, maxWeight, DEFAULT_POPULATION_SIZE, seed)

    constructor(knapsack: List<Item>, maxWeight: Int, populations: Int) :
            this(knapsack, maxWeight, populations, Random().nextLong())

    constructor(knapsack: List<Item>, maxWeight: Int) :
            this(knapsack, maxWeight, DEFAULT_POPULATION_SIZE, Random().nextLong())

    private var population: List<ChromosomeBool>
    private val chromosomeSize = knapsack.size
    private val random = Random(seed)

    init {
        if (populations < 4) throw IllegalArgumentException("Population size can't be less then 4")
        population = when ((populations % 2)) {
            0 -> generateChromosomes(populations)
            else -> generateChromosomes(populations + 1)
        }
    }

    private fun generateChromosomes(number: Int): List<ChromosomeBool> {
        val result = mutableListOf<ChromosomeBool>()
        for (i in 0 until number) result += ChromosomeBool(chromosomeSize, random)
        return result
    }

    private fun ChromosomeBool.evolution(): Double {
        var points = 0.0
        var weight = 0.0
        knapsack.forEachIndexed { index, thing ->
            if (genes[index]) {
                points += thing.cost
                weight += thing.weight
            }
        }
        if (weight > load) {
            fined = true
            points -= points * (weight / load.toDouble())
        }
        return points
    }

    private fun selection() = population.sortedBy { -it.evolution() }.subList(0, population.size / 2)

    private fun crossover(): List<ChromosomeBool> {
        if (population.size % 2 == 1) population += population[0]
        val offSprings = mutableListOf<ChromosomeBool>()
        val pop = population.shuffled(random)
        val firstParentsList = pop.subList(0, pop.size / 2)
        val secondParentsList = pop.subList(pop.size / 2, pop.size)
        for (i in 0 until pop.size / 2) {
            val children = firstParentsList[i].crossoverPair(secondParentsList[i])
            offSprings += children.first
            offSprings += children.second
        }
        return offSprings.toList()
    }

    private fun mutation() = population.forEach { if (random.nextDouble() <= MUTATION_PROBABILITY) it.mutate(random) }

    fun findSolution(generations: Int): List<Item> {
        for (i in 0..generations) {
            population = selection()
            population += crossover()
            mutation()
        }
        val bestChromosome = population.filter { !it.fined }.sortedBy { -it.evolution() }.first()
        val solution = mutableListOf<Item>()
        knapsack.forEachIndexed { index, thing -> if (bestChromosome.genes[index]) solution += thing }
        return solution
    }
}
