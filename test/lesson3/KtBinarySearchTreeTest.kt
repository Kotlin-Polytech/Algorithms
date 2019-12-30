package lesson3

import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

class KtBinarySearchTreeTest : AbstractBinarySearchTreeTest() {

    override fun create(): CheckableSortedSet<Int> =
        KtBinarySearchTree()

    @Test
    @Tag("Example")
    fun initTest() {
        doInitTest()
    }

    @Test
    @Tag("Example")
    fun addTest() {
        doAddTest()
    }

    @Test
    @Tag("Example")
    fun firstAndLastTest() {
        doFirstAndLastTest()
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

    @Test
    @Tag("Impossible")
    fun subSetTest() {
        doSubSetTest()
    }

    @Test
    @Tag("Impossible")
    fun subSetRelationTest() {
        doSubSetRelationTest()
    }

    @Test
    @Tag("Impossible")
    fun subSetFirstAndLastTest() {
        doSubSetFirstAndLastTest()
    }

    @Test
    @Tag("Normal")
    fun headSetTest() {
        doHeadSetTest()
    }

    @Test
    @Tag("Hard")
    fun headSetRelationTest() {
        doHeadSetRelationTest()
    }

    @Test
    @Tag("Normal")
    fun tailSetTest() {
        doTailSetTest()
    }

    @Test
    @Tag("Hard")
    fun tailSetRelationTest() {
        doTailSetRelationTest()
    }

}