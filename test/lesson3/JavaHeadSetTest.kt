package lesson3

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Tag
import kotlin.test.Test

class JavaHeadSetTest : AbstractHeadTailTest() {

    @BeforeEach
    fun fillTree() {
        fillTree(BinaryTree<Int>())
    }

    @Test
    @Tag("Hard")
    fun headSetTest() {
        doHeadSetTest()
    }

    @Test
    @Tag("Hard")
    fun tailSetTest() {
        doTailSetTest()
    }
}