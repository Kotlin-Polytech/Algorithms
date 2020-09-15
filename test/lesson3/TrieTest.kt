package lesson3

import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

class TrieTest : AbstractTrieTest() {

    override fun create(): MutableSet<String> =
        Trie()

    @Test
    @Tag("Example")
    fun generalTestJava() {
        doGeneralTest()
    }

    @Test
    @Tag("Hard")
    fun iteratorTestJava() {
        doIteratorTest()
    }

    @Test
    @Tag("Hard")
    fun iteratorRemoveTestJava() {
        doIteratorRemoveTest()
    }

}