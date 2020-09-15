package lesson4

import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

class KtOpenAddressingSetTest : AbstractOpenAddressingSetTest() {

    override fun <T : Any> create(bits: Int): MutableSet<T> {
        return KtOpenAddressingSet(bits)
    }

    @Test
    @Tag("Example")
    fun addTest() {
        doAddTest()
    }

    @Test
    @Tag("Normal")
    fun removeTest() {
        doRemoveTest()
    }

    @Test
    @Tag("Normal")
    fun iteratorTest() {
        doIteratorTest()
    }

    @Test
    @Tag("Hard")
    fun iteratorRemoveTest() {
        doIteratorRemoveTest()
    }
}