package lesson3

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class SampleIterableCollectionTest {

    @Test
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
    }

    @Test
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