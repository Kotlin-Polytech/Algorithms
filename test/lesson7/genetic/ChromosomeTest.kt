package lesson7.genetic

import org.junit.jupiter.api.Tag
import kotlin.test.*
import java.util.*

class ChromosomeTest {
    private val random = Random()

    private val defaultSize = 1000

    @Test
    @Tag("Example")
    fun mutate() {
        for (i in 1..5000) {
            val chromosome = Chromosome(defaultSize, random)
            val other = chromosome.mutate(random)
            assertEquals(defaultSize, other.visitingOrder.size)
            assertEquals(defaultSize, other.visitingOrder.toSet().size)
            for (element in other.visitingOrder) {
                assertTrue(element in 0 until defaultSize)
            }
        }
    }

    @Test
    @Tag("Example")
    fun crossBreed() {
        for (i in 1..2000) {
            val first = Chromosome(defaultSize, random)
            val second = Chromosome(defaultSize, random)
            val third = first.crossBreed(second, random)
            assertEquals(defaultSize, third.visitingOrder.size)
            assertEquals(defaultSize, third.visitingOrder.toSet().size)
            for (element in third.visitingOrder) {
                assertTrue(element in 0 until defaultSize)
            }
        }
    }
}