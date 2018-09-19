package lesson3

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class KotlinHeadSetTest : AbstractHeadTailTest() {

    @BeforeEach
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