package lesson4

import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import ru.spbstu.kotlin.generate.util.nextString
import java.util.*
import kotlin.math.abs
import kotlin.test.*

class OpenAddressingSetTest {

    @Test
    @Tag("Example")
    fun addTest() {
        val random = Random()
        for (iteration in 1..100) {
            val controlSet = mutableSetOf<Int>()
            val bitsNumber = random.nextInt(4) + 5
            val openAddressingSet = OpenAddressingSet<Int>(bitsNumber)
            assertTrue(openAddressingSet.size == 0, "Size of an empty set is not zero.")
            for (i in 1..50) {
                val nextInt = random.nextInt(32)
                val additionResult = openAddressingSet.add(nextInt)
                assertEquals(
                    nextInt !in controlSet, additionResult,
                    "An element was ${if (additionResult) "" else "not"} added when it ${if (additionResult) "was already in the set" else "should have been"}."
                )
                controlSet += nextInt
                assertTrue(nextInt in openAddressingSet, "A supposedly added element is not in the set.")
                assertEquals(controlSet.size, openAddressingSet.size, "The size of the set is not as expected.")
            }
            val smallSet = OpenAddressingSet<Int>(bitsNumber)
            assertFailsWith<IllegalStateException>("A table overflow is not being prevented.") {
                for (i in 1..4000) {
                    smallSet.add(random.nextInt())
                }
            }
        }
    }

    @Test
    @Tag("Example")
    fun removeTest() {
        val random = Random()
        for (iteration in 1..100) {
            val bitsNumber = random.nextInt(4) + 6
            val openAddressingSet = OpenAddressingSet<Int>(bitsNumber)
            for (i in 1..50) {
                val firstInt = random.nextInt(32)
                val secondInt = firstInt + (1 shl bitsNumber)
                openAddressingSet += secondInt
                openAddressingSet += firstInt
                val expectedSize = openAddressingSet.size - 1
                assertTrue(
                    openAddressingSet.remove(secondInt),
                    "An element wasn't removed contrary to expected."
                )
                assertFalse(
                    secondInt in openAddressingSet,
                    "A supposedly removed element is still in the set."
                )
                assertTrue(
                    firstInt in openAddressingSet,
                    "The removal of the element prevented access to the other elements."
                )
                assertEquals(
                    expectedSize, openAddressingSet.size,
                    "The size of the set is not as expected."
                )
                assertFalse(
                    openAddressingSet.remove(secondInt),
                    "A removed element was supposedly removed twice."
                )
                assertEquals(
                    expectedSize, openAddressingSet.size,
                    "The size of the set is not as expected."
                )
            }
        }
    }

    @Test
    @Tag("Example")
    fun iteratorTest() {
        val random = Random()
        for (iteration in 1..100) {
            val controlSet = mutableSetOf<String>()
            for (i in 1..15) {
                val string = random.nextString("abcdefgh12345678", 1, 15)
                controlSet.add(string)
            }
            println("Control set: $controlSet")
            val openAddressingSet = OpenAddressingSet<String>(random.nextInt(6) + 4)
            assertFalse(
                openAddressingSet.iterator().hasNext(),
                "Iterator of an empty set should not have any next elements."
            )
            for (element in controlSet) {
                openAddressingSet += element
            }
            val iterator1 = openAddressingSet.iterator()
            val iterator2 = openAddressingSet.iterator()
            println("Checking if calling hasNext() changes the state of the iterator...")
            while (iterator1.hasNext()) {
                assertEquals(
                    iterator2.next(), iterator1.next(),
                    "Calling OpenAddressingSetIterator.hasNext() changes the state of the iterator."
                )
            }
            val openAddressingSetIter = openAddressingSet.iterator()
            println("Checking if the iterator traverses the entire set...")
            while (openAddressingSetIter.hasNext()) {
                controlSet.remove(openAddressingSetIter.next())
            }
            assertTrue(
                controlSet.isEmpty(),
                "OpenAddressingSetIterator doesn't traverse the entire set."
            )
            assertFailsWith<IllegalStateException>("Something was supposedly returned after the elements ended") {
                openAddressingSetIter.next()
            }
            println("All clear!")
        }
    }

    @Test
    @Tag("Example")
    fun iteratorRemoveTest() {
        val random = Random()
        for (iteration in 1..100) {
            val controlSet = mutableSetOf<String>()
            val removeIndex = random.nextInt(15) + 1
            var toRemove = ""
            for (i in 1..15) {
                val string = random.nextString("abcdefgh12345678", 1, 15)
                controlSet.add(string)
                if (i == removeIndex) {
                    toRemove = string
                }
            }
            println("Initial set: $controlSet")
            val openAddressingSet = OpenAddressingSet<String>(random.nextInt(6) + 4)
            for (element in controlSet) {
                openAddressingSet += element
            }
            controlSet.remove(toRemove)
            println("Control set: $controlSet")
            println("Removing element \"$toRemove\" from open addressing set through the iterator...")
            val iterator = openAddressingSet.iterator()
            assertFailsWith<IllegalStateException>("Something was supposedly deleted before the iteration started") {
                iterator.remove()
            }
            var counter = openAddressingSet.size
            while (iterator.hasNext()) {
                val element = iterator.next()
                counter--
                if (element == toRemove) {
                    iterator.remove()
                }
            }
            assertEquals(
                0, counter,
                "OpenAddressingSetIterator.remove() changed iterator position: ${abs(counter)} elements were ${if (counter > 0) "skipped" else "revisited"}."
            )
            assertEquals(
                controlSet.size, openAddressingSet.size,
                "The size of the set is incorrect: was ${openAddressingSet.size}, should've been ${controlSet.size}."
            )
            for (element in controlSet) {
                assertTrue(
                    openAddressingSet.contains(element),
                    "Open addressing set doesn't have the element $element from the control set."
                )
            }
            for (element in openAddressingSet) {
                assertTrue(
                    controlSet.contains(element),
                    "Open addressing set has the element $element that is not in control set."
                )
            }
            println("All clear!")
        }
    }

}