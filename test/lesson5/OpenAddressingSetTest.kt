package lesson5

import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

class OpenAddressingSetTest : AbstractOpenAddressingSetTest() {

    override fun <T : Any> create(bits: Int): MutableSet<T> {
        return OpenAddressingSet(bits)
    }

    @Test
    @Tag("Example")
    fun addTestJava() {
        doAddTest()
    }

    @Test
    @Tag("Normal")
    fun removeTestJava() {
        doRemoveTest()
    }

    @Test
    @Tag("Normal")
    fun iteratorTestJava() {
        doIteratorTest()
    }

    @Test
    @Tag("Hard")
    fun iteratorRemoveTestJava() {
        doIteratorRemoveTest()
    }
}