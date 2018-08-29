package lesson3

import org.junit.Before
import kotlin.test.Test

class KotlinHeadSetTest : AbstractHeadTailTest() {

    @Before
    fun fillTree() {
        fillTree(KtBinaryTree())
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