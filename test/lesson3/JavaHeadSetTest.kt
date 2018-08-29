package lesson3

import org.junit.Before
import kotlin.test.Test

class JavaHeadSetTest : AbstractHeadTailTest() {

    @Before
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