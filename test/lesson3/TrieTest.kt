package lesson3

import org.junit.Test

import org.junit.Assert.*
import java.util.*

class TrieTest {

    @Test
    fun generalTest() {
        val trie = Trie()
        trie.add("abcdefg")
        assertTrue("abcdefg" in trie)
        assertFalse("abcdef" in trie)
        assertFalse("a" in trie)
        assertFalse("g" in trie)

        trie.add("zyx")
        trie.add("zwv")
        trie.add("zyt")
        trie.add("abcde")
        assertEquals(5, trie.size)
        assertTrue("abcdefg" in trie)
        assertFalse("abcdef" in trie)
        assertTrue("abcde" in trie)
        assertTrue("zyx" in trie)
        assertTrue("zyt" in trie)
        assertTrue("zwv" in trie)
        assertFalse("zy" in trie)
        assertFalse("zv" in trie)

        trie.remove("zwv")
        trie.remove("zy")
        assertEquals(4, trie.size)
        assertTrue("zyt" in trie)
        assertFalse("zwv" in trie)

        trie.clear()
        assertEquals(0, trie.size)
        assertFalse("zyx" in trie)
    }

    @Test
    fun iteratorTest() {
        val trie = Trie()
        trie.add("Пластмассовый ")
        trie.add("мир ")
        trie.add("VVV")
        trie.add("победил\n")
        trie.add("Макет ")
        trie.add("vvv")
        trie.add("оказался ")
        trie.add("сильней\n")

        assertTrue("VVV" in trie)
        assertTrue("vvv" in trie)

        var iterator = trie.iterator()
        while (iterator.hasNext()){
            print(iterator.next())
        }
        iterator = trie.iterator()
        while (iterator.hasNext()){
            val str = iterator.next()
            if (str == "vvv" || str == "VVV"){
                iterator.remove()
            }
        }

        assertFalse("VVV" in trie)
        assertFalse("vvv" in trie)

        iterator = trie.iterator()
        while (iterator.hasNext()){
            print(iterator.next())
        }

        assertTrue("Пластмассовый " in trie)
        assertTrue("мир " in trie)
        assertTrue("победил\n" in trie)
        assertTrue("Макет " in trie)
        assertTrue("оказался " in trie)
        assertTrue("сильней\n" in trie)
    }

    @Test
    fun iteratorTestForEmpty() {
        val trie = Trie()
        val iterator = trie.iterator()
        assertFalse(iterator.hasNext())
    }

    @Test
    fun iteratorDeleteTest() {
        val trie = Trie()
        trie.add("Оооо, ")
        trie.add("моя")
        trie.add("оборона")
        assertTrue("Оооо, " in trie)
        assertTrue("моя" in trie)
        assertTrue("оборона" in trie)
        assertEquals(3, trie.size)
        val iterator = trie.iterator()
        try {
            while (iterator.hasNext()) {
                val next = iterator.next()
                if (next == "Оооо, ") {
                    iterator.remove()
                }
                if (next == "моя") {
                    trie.remove("моя")
                }
            }
            assert(false)
        } catch (ex: ConcurrentModificationException){
            assert(true)
        }
    }
}