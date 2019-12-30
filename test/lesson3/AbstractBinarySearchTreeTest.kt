package lesson3

import java.util.*
import kotlin.math.abs
import kotlin.test.*
import org.junit.jupiter.api.assertDoesNotThrow
import kotlin.IllegalStateException
import kotlin.NoSuchElementException

abstract class AbstractBinarySearchTreeTest {

    abstract fun create(): CheckableSortedSet<Int>

    private fun <R> implementationTest(function: () -> R) {
        try {
            function()
        } catch (e: Error) {
            if (e is NotImplementedError) {
                throw e
            }
        } catch (e: Exception) {
            // let it slide for now
        }
    }

    protected fun doInitTest() {
        val binarySet = create()
        assertEquals(0, binarySet.size, "Size of an empty tree should be zero.")
        assertEquals(0, binarySet.height(), "Height of an empty tree should be zero.")
        assertFalse(42 in binarySet, "An empty tree should contain no elements.")
    }

    protected fun doAddTest() {
        implementationTest { create().add(0) }
        val random = Random()
        for (iteration in 1..100) {
            val controlSet = mutableSetOf<Int>()
            for (i in 1..10) {
                controlSet += random.nextInt(100)
            }
            val binarySet = create()
            for (element in controlSet) {
                assertTrue(
                    binarySet.add(element),
                    "An element was supposedly not added to the tree when it should have been."
                )
                assertTrue(
                    element in binarySet,
                    "The tree doesn't contain a supposedly added element."
                )
                assertTrue(
                    binarySet.checkInvariant(),
                    "The binary search tree invariant is false after BinarySearchTree.add()."
                )
                assertFalse(
                    binarySet.add(element),
                    "An element was supposedly added to the tree twice."
                )
            }
            assertEquals(
                controlSet.size, binarySet.size,
                "The size of the tree is incorrect: was ${binarySet.size}, should've been ${controlSet.size}."
            )
            for (element in controlSet) {
                assertTrue(
                    binarySet.contains(element),
                    "The tree doesn't have the element $element from the control set."
                )
            }
        }
    }

    protected fun doFirstAndLastTest() {
        implementationTest { create().first() }
        implementationTest { create().last() }
        val random = Random()
        for (iteration in 1..100) {
            val controlSet = sortedSetOf<Int>()
            for (i in 1..10) {
                controlSet += random.nextInt(100)
            }
            println("Control set: $controlSet")
            val expectedFirst = controlSet.first()
            val expectedLast = controlSet.last()
            val binarySet = create()
            assertFailsWith<NoSuchElementException>("A first element was found in an empty tree.") {
                binarySet.first()
            }
            assertFailsWith<NoSuchElementException>("A last element was found in an empty tree.") {
                binarySet.last()
            }
            for (element in controlSet) {
                binarySet += element
            }
            val actualFirst = binarySet.first()
            val actualLast = binarySet.last()
            assertEquals(
                expectedFirst, actualFirst,
                "The first element was determined incorrectly: was $actualFirst, should've been $expectedFirst."
            )
            assertEquals(
                expectedLast, actualLast,
                "The last element was determined incorrectly: was $actualLast, should've been $expectedLast."
            )
            println("First element: $actualFirst. Last element: $actualLast.")
        }
    }

    protected fun doRemoveTest() {
        implementationTest { create().remove(0) }
        val random = Random()
        for (iteration in 1..100) {
            val controlSet = mutableSetOf<Int>()
            val removeIndex = random.nextInt(20) + 1
            var toRemove = 0
            for (i in 1..20) {
                val newNumber = random.nextInt(100)
                controlSet.add(newNumber)
                if (i == removeIndex) {
                    toRemove = newNumber
                }
            }
            println("Initial set: $controlSet")
            val binarySet = create()
            for (element in controlSet) {
                binarySet += element
            }
            controlSet.remove(toRemove)
            println("Control set: $controlSet")
            val expectedSize = binarySet.size - 1
            val maxHeight = binarySet.height()
            println("Removing element $toRemove from the tree...")
            assertTrue(
                binarySet.remove(toRemove),
                "An element was supposedly not removed from the tree when it should have been."
            )
            assertTrue(
                toRemove !in binarySet,
                "The tree contains a supposedly removed element."
            )
            assertTrue(
                binarySet.checkInvariant(),
                "The binary search tree invariant is false after BinarySearchTree.remove()."
            )
            assertTrue(
                binarySet.height() <= maxHeight,
                "The tree's height increased after BinarySearchTree.remove()."
            )
            assertFalse(
                binarySet.remove(toRemove),
                "An element that was already not in the tree was supposedly removed."
            )
            assertEquals(
                expectedSize, binarySet.size,
                "The size of the tree is incorrect: was ${binarySet.size}, should've been $expectedSize."
            )
            for (element in controlSet) {
                assertTrue(
                    binarySet.contains(element),
                    "The tree doesn't have the element $element from the control set."
                )
            }
            println("All clear!")
        }
    }

    protected fun doIteratorTest() {
        implementationTest { create().iterator().hasNext() }
        implementationTest { create().iterator().next() }
        val random = Random()
        for (iteration in 1..100) {
            val controlSet = TreeSet<Int>()
            for (i in 1..20) {
                controlSet.add(random.nextInt(100))
            }
            println("Control set: $controlSet")
            val binarySet = create()
            assertFalse(
                binarySet.iterator().hasNext(),
                "Iterator of an empty tree should not have any next elements."
            )
            for (element in controlSet) {
                binarySet += element
            }
            val iterator1 = binarySet.iterator()
            val iterator2 = binarySet.iterator()
            println("Checking if calling hasNext() changes the state of the iterator...")
            while (iterator1.hasNext()) {
                assertEquals(
                    iterator2.next(), iterator1.next(),
                    "Calling BinarySearchTreeIterator.hasNext() changes the state of the iterator."
                )
            }
            val controlIter = controlSet.iterator()
            val binaryIter = binarySet.iterator()
            println("Checking if the iterator traverses the tree correctly...")
            while (controlIter.hasNext()) {
                assertEquals(
                    controlIter.next(), binaryIter.next(),
                    "BinarySearchTreeIterator doesn't traverse the tree correctly."
                )
            }
            assertFailsWith<IllegalStateException>("Something was supposedly returned after the elements ended") {
                binaryIter.next()
            }
            println("All clear!")
        }
    }

    protected fun doIteratorRemoveTest() {
        implementationTest { create().iterator().remove() }
        val random = Random()
        for (iteration in 1..100) {
            val controlSet = TreeSet<Int>()
            val removeIndex = random.nextInt(20) + 1
            var toRemove = 0
            for (i in 1..20) {
                val newNumber = random.nextInt(100)
                controlSet.add(newNumber)
                if (i == removeIndex) {
                    toRemove = newNumber
                }
            }
            println("Initial set: $controlSet")
            val binarySet = create()
            for (element in controlSet) {
                binarySet += element
            }
            controlSet.remove(toRemove)
            println("Control set: $controlSet")
            println("Removing element $toRemove from the tree through the iterator...")
            val iterator = binarySet.iterator()
            assertFailsWith<IllegalStateException>("Something was supposedly removed before the iteration started") {
                iterator.remove()
            }
            var counter = binarySet.size
            print("Iterating: ")
            while (iterator.hasNext()) {
                val element = iterator.next()
                print("$element, ")
                counter--
                if (element == toRemove) {
                    iterator.remove()
                    assertFailsWith<IllegalStateException>("BinarySearchTreeIterator.remove() was successfully called twice in a row.") {
                        iterator.remove()
                    }
                }
            }
            assertEquals(
                0, counter,
                "BinarySearchTreeIterator.remove() changed iterator position: ${abs(counter)} elements were ${if (counter > 0) "skipped" else "revisited"}."
            )
            assertTrue(
                binarySet.checkInvariant(),
                "The binary search tree invariant is false after BinarySearchTreeIterator.remove()."
            )
            assertEquals(
                controlSet.size, binarySet.size,
                "The size of the tree is incorrect: was ${binarySet.size}, should've been ${controlSet.size}."
            )
            for (element in controlSet) {
                assertTrue(
                    binarySet.contains(element),
                    "The tree doesn't have the element $element from the control set."
                )
            }
            for (element in binarySet) {
                assertTrue(
                    controlSet.contains(element),
                    "The tree has the element $element that is not in control set."
                )
            }
            println("All clear!")
        }
    }

    protected fun doSubSetTest() {
        implementationTest { create().subSet(0, 0) }
        assertEquals(
            0, create().subSet(0, 0).size,
            "The subset with the same lower and upper bounds is not empty."
        )
        val random = Random()
        for (iteration in 1..100) {
            val controlSet = mutableSetOf<Int>()
            val initialSet = create()
            for (i in 1..30) {
                controlSet.add(random.nextInt(100))
            }
            for (element in controlSet) {
                initialSet.add(element)
            }
            println("Control set: $controlSet")
            val fromElement = random.nextInt(50)
            val toElement = random.nextInt(50) + 50
            val subSet = initialSet.subSet(fromElement, toElement)
            println("Checking if the boundaries of the subset from $fromElement to $toElement are respected...")
            for (element in controlSet) {
                assertEquals(
                    element in fromElement until toElement, subSet.contains(element),
                    "$element is ${if (element in subSet) "" else "not"} in the subset when it should ${if (element in subSet) "not" else ""} be."
                )
                if (element in fromElement until toElement) {
                    assertTrue(
                        subSet.remove(element),
                        "An element of the subset was not removed."
                    )
                } else {
                    assertFailsWith<IllegalArgumentException>("An illegal argument was passed to remove() without raising an exception") {
                        subSet.remove(element)
                    }
                }
            }
            val validAddition = toElement - 1
            assertDoesNotThrow("An exception is thrown on the attempt of adding a valid element") {
                subSet.add(validAddition)
            }
            val invalidAddition = fromElement - 1
            assertFailsWith<IllegalArgumentException>("An illegal argument was passed to add() without raising an exception") {
                subSet.add(invalidAddition)
            }
            println("All clear!")
        }
    }

    protected fun doSubSetRelationTest() {
        implementationTest { create().subSet(0, 0) }
        val random = Random()
        for (iteration in 1..100) {
            val initialSet = create()
            val fromElement = random.nextInt(50)
            val toElement = random.nextInt(50) + 50
            val subSet = initialSet.subSet(fromElement, toElement)
            println("Checking if the subset from $fromElement to $toElement is a valid view of the initial set...")
            var allElementCounter = 0
            var validElementCounter = 0
            for (i in 1..50) {
                val value = random.nextInt(100)
                if (value in fromElement until toElement) {
                    if (random.nextBoolean()) {
                        if (initialSet.add(value)) {
                            allElementCounter++
                            validElementCounter++
                        }
                        assertTrue(
                            subSet.contains(value),
                            "A subset doesn't contain a valid element of the initial set."
                        )
                    } else {
                        if (subSet.add(value)) {
                            allElementCounter++
                            validElementCounter++
                        }
                        assertTrue(
                            initialSet.contains(value),
                            "The initial set doesn't contain an element of the subset."
                        )
                    }
                } else {
                    if (initialSet.add(value)) {
                        allElementCounter++
                    }
                    assertFalse(
                        subSet.contains(value),
                        "A subset contains an illegal element of the initial set."
                    )
                }
            }
            assertEquals(
                allElementCounter, initialSet.size,
                "The size of the initial set is not as expected."
            )
            assertEquals(
                validElementCounter, subSet.size,
                "The size of the subset is not as expected."
            )
            println("All clear!")
        }
    }

    protected fun doSubSetFirstAndLastTest() {
        implementationTest { create().subSet(0, 0).first() }
        implementationTest { create().subSet(0, 0).last() }
        val edgeCaseSet = create()
        assertFailsWith<NoSuchElementException>("A first element was found in an empty subset.") {
            edgeCaseSet.subSet(-1, 1).first()
        }
        assertFailsWith<NoSuchElementException>("A last element was found in an empty subset.") {
            edgeCaseSet.subSet(-1, 1).last()
        }
        edgeCaseSet += 42
        assertFailsWith<NoSuchElementException>("A first element was found in an empty subset.") {
            edgeCaseSet.subSet(-1, 1).first()
        }
        assertFailsWith<NoSuchElementException>("A last element was found in an empty subset.") {
            edgeCaseSet.subSet(-1, 1).last()
        }
        val random = Random()
        for (iteration in 1..100) {
            val controlSet = sortedSetOf<Int>()
            val binarySet = create()
            for (i in 1..20) {
                val nextInt = random.nextInt(100)
                controlSet += nextInt
                binarySet += nextInt
            }
            val fromElement = random.nextInt(50)
            val toElement = random.nextInt(50) + 50
            val controlSubSet = controlSet.subSet(fromElement, toElement)
            val expectedFirst = try {
                controlSubSet.first()
            } catch (e: NoSuchElementException) {
                null
            }
            val expectedLast = try {
                controlSubSet.last()
            } catch (e: NoSuchElementException) {
                null
            }
            println("Control set: $controlSet")
            println("Control subset: $controlSubSet")
            val subSet = binarySet.subSet(fromElement, toElement)
            val actualFirst = try {
                subSet.first()
            } catch (e: NoSuchElementException) {
                null
            }
            val actualLast = try {
                subSet.last()
            } catch (e: NoSuchElementException) {
                null
            }
            assertEquals(
                expectedFirst, actualFirst,
                "The first element was determined incorrectly: was $actualFirst, should've been $expectedFirst."
            )
            assertEquals(
                expectedLast, actualLast,
                "The last element was determined incorrectly: was $actualLast, should've been $expectedLast."
            )
            println("First element: $actualFirst. Last element: $actualLast.")
        }
    }

    protected fun doHeadSetTest() {
        implementationTest { create().headSet(0) }
        val random = Random()
        for (iteration in 1..100) {
            val controlSet = mutableSetOf<Int>()
            val initialSet = create()
            for (i in 1..30) {
                controlSet.add(random.nextInt(100))
            }
            for (element in controlSet) {
                initialSet.add(element)
            }
            println("Control set: $controlSet")
            val toElement = random.nextInt(100)
            val headSet = initialSet.headSet(toElement)
            println("Checking if the boundaries of the headset to $toElement are respected...")
            for (element in controlSet) {
                assertEquals(
                    element < toElement, headSet.contains(element),
                    "$element is ${if (element in headSet) "" else "not"} in the headset when it should ${if (element in headSet) "not" else ""} be."
                )
                if (element < toElement) {
                    assertTrue(
                        headSet.remove(element),
                        "An element of the headset was not removed."
                    )
                } else {
                    assertFailsWith<IllegalArgumentException>("An illegal argument was passed to remove() without raising an exception") {
                        headSet.remove(element)
                    }
                }
            }
            val validAddition = toElement - 1
            assertDoesNotThrow("An exception is thrown on the attempt of adding a valid element") {
                headSet.add(validAddition)
            }
            val invalidAddition = toElement + 1
            assertFailsWith<IllegalArgumentException>("An illegal argument was passed to add() without raising an exception") {
                headSet.add(invalidAddition)
            }
            println("All clear!")
        }
    }

    protected fun doHeadSetRelationTest() {
        implementationTest { create().headSet(0) }
        val random = Random()
        for (iteration in 1..100) {
            val initialSet = create()
            val toElement = random.nextInt(100)
            val headSet = initialSet.headSet(toElement)
            println("Checking if the headset to $toElement is a valid view of the initial set...")
            var allElementCounter = 0
            var validElementCounter = 0
            for (i in 1..50) {
                val value = random.nextInt(100)
                if (value < toElement) {
                    if (random.nextBoolean()) {
                        if (initialSet.add(value)) {
                            allElementCounter++
                            validElementCounter++
                        }
                        assertTrue(
                            headSet.contains(value),
                            "A headset doesn't contain a valid element of the initial set."
                        )
                    } else {
                        if (headSet.add(value)) {
                            allElementCounter++
                            validElementCounter++
                        }
                        assertTrue(
                            initialSet.contains(value),
                            "The initial set doesn't contain an element of the headset."
                        )
                    }
                } else {
                    if (initialSet.add(value)) {
                        allElementCounter++
                    }
                    assertFalse(
                        headSet.contains(value),
                        "A headset contains an illegal element of the initial set."
                    )
                }
            }
            assertEquals(
                allElementCounter, initialSet.size,
                "The size of the initial set is not as expected."
            )
            assertEquals(
                validElementCounter, headSet.size,
                "The size of the headset is not as expected."
            )
            println("All clear!")
        }
    }

    protected fun doTailSetTest() {
        implementationTest { create().tailSet(0) }
        val random = Random()
        for (iteration in 1..100) {
            val controlSet = mutableSetOf<Int>()
            val initialSet = create()
            for (i in 1..30) {
                controlSet.add(random.nextInt(100))
            }
            for (element in controlSet) {
                initialSet.add(element)
            }
            println("Control set: $controlSet")
            val fromElement = random.nextInt(100)
            val tailSet = initialSet.tailSet(fromElement)
            println("Checking if the boundaries of the tailset from $fromElement are respected...")
            for (element in controlSet) {
                assertEquals(
                    element >= fromElement, tailSet.contains(element),
                    "$element is ${if (element in tailSet) "" else "not"} in the tailset when it should ${if (element in tailSet) "not" else ""} be."
                )
                if (element >= fromElement) {
                    assertTrue(
                        tailSet.remove(element),
                        "An element of the tailset was not removed."
                    )
                } else {
                    assertFailsWith<IllegalArgumentException>("An illegal argument was passed to remove() without raising an exception") {
                        tailSet.remove(element)
                    }
                }
            }
            val validAddition = fromElement + 1
            assertDoesNotThrow("An exception is thrown on the attempt of adding a valid element") {
                tailSet.add(validAddition)
            }
            val invalidAddition = fromElement - 1
            assertFailsWith<IllegalArgumentException>("An illegal argument was passed to add() without raising an exception") {
                tailSet.add(invalidAddition)
            }
            println("All clear!")
        }
    }

    protected fun doTailSetRelationTest() {
        implementationTest { create().tailSet(0) }
        val random = Random()
        for (iteration in 1..100) {
            val initialSet = create()
            val fromElement = random.nextInt(100)
            val tailSet = initialSet.tailSet(fromElement)
            println("Checking if the tailset from $fromElement is a valid view of the initial set...")
            var allElementCounter = 0
            var validElementCounter = 0
            for (i in 1..50) {
                val value = random.nextInt(100)
                if (value >= fromElement) {
                    if (random.nextBoolean()) {
                        if (initialSet.add(value)) {
                            allElementCounter++
                            validElementCounter++
                        }
                        assertTrue(
                            tailSet.contains(value),
                            "A tailset doesn't contain a valid element of the initial set."
                        )
                    } else {
                        if (tailSet.add(value)) {
                            allElementCounter++
                            validElementCounter++
                        }
                        assertTrue(
                            initialSet.contains(value),
                            "The initial set doesn't contain an element of the tailset."
                        )
                    }
                } else {
                    if (initialSet.add(value)) {
                        allElementCounter++
                    }
                    assertFalse(
                        tailSet.contains(value),
                        "A tailset contains an illegal element of the initial set."
                    )
                }
            }
            assertEquals(
                allElementCounter, initialSet.size,
                "The size of the initial set is not as expected."
            )
            assertEquals(
                validElementCounter, tailSet.size,
                "The size of the tailset is not as expected."
            )
            println("All clear!")
        }
    }

}