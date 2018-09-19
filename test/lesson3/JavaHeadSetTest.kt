package lesson3

import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test

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