package lesson3

import org.junit.jupiter.api.Tag
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class SampleIterableCollectionTest {

    @Test
    @Tag("Example")
    fun testAdd() {
        val collection = SampleIterableCollection<String>()
        collection.add("Omega")
        collection.add("Beta")
        collection.add("Alpha")
        assertEquals(3, collection.size)

        val checkList = listOf("Alpha", "Beta", "Omega")
        assertTrue(collection.containsAll(checkList))

        var sizeByIterator = 0
        for (element in collection) {
            sizeByIterator++
        }
        assertEquals(3, collection.size)
        assertEquals(3, sizeByIterator)
    }

    @Test
    @Tag("Example")
    fun testRemove() {
        val collection = SampleIterableCollection<String>()
        collection.add("Omega")
        collection.add("Beta")
        collection.add("Alpha")

        val iterator = collection.iterator()
        iterator.next()
        iterator.next()
        iterator.remove()

        assertEquals(2, collection.size)
        assertFalse("Beta" in collection)
        assertTrue("Omega" in collection)
        assertTrue("Alpha" in collection)

        iterator.next()
        iterator.remove()
        assertEquals(1, collection.size)
        assertFalse("Omega" in collection)
    }

    @Test
    @Tag("Example")
    fun testRemoveFirst() {
        val collection = SampleIterableCollection<String>()
        collection.add("Omega")
        collection.add("Beta")
        collection.add("Alpha")

        val iterator = collection.iterator()
        iterator.next()
        iterator.remove()

        assertEquals(2, collection.size)
        assertTrue("Beta" in collection)
        assertTrue("Omega" in collection)
        assertFalse("Alpha" in collection)
    }
}