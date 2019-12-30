package lesson3

import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

class KtTrieTest : AbstractTrieTest() {

    override fun create(): MutableSet<String> =
        KtTrie()

    @Test
    @Tag("Example")
    fun generalTest() {
        doGeneralTest()
    }

    @Test
    @Tag("Hard")
    fun iteratorTest() {
        doIteratorTest()
    }

    @Test
    @Tag("Hard")
    fun iteratorRemoveTest() {
        doIteratorRemoveTest()
    }

}