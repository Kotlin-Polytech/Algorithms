package lesson3

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class JavaHeadSetTest : AbstractHeadTailTest() {

    @BeforeEach
    fun fillTree() {
        fillTree(BinaryTree<Int>())
    }

    @Test
    fun headSetTest() {
        doHeadSetTest()
    }

    @Test
    fun tailSetTest() {
        doTailSetTest()
    }
}