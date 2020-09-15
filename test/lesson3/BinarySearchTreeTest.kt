package lesson3

import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

class BinarySearchTreeTest : AbstractBinarySearchTreeTest() {

    override fun create(): CheckableSortedSet<Int> =
        BinarySearchTree()

    @Test
    @Tag("Example")
    fun initTestJava() {
        doInitTest()
    }

    @Test
    @Tag("Example")
    fun addTestJava() {
        doAddTest()
    }

    @Test
    @Tag("Example")
    fun firstAndLastTestJava() {
        doFirstAndLastTest()
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

    @Test
    @Tag("Impossible")
    fun subSetTestJava() {
        doSubSetTest()
    }

    @Test
    @Tag("Impossible")
    fun subSetRelationTestJava() {
        doSubSetRelationTest()
    }

    @Test
    @Tag("Impossible")
    fun subSetFirstAndLastTestJava() {
        doSubSetFirstAndLastTest()
    }

    @Test
    @Tag("Normal")
    fun headSetTestJava() {
        doHeadSetTest()
    }

    @Test
    @Tag("Hard")
    fun headSetRelationTestJava() {
        doHeadSetRelationTest()
    }

    @Test
    @Tag("Normal")
    fun tailSetTestJava() {
        doTailSetTest()
    }

    @Test
    @Tag("Hard")
    fun tailSetRelationTestJava() {
        doTailSetRelationTest()
    }

}