package lesson3

import kotlin.test.Test
import kotlin.test.assertEquals
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

}