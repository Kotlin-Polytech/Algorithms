package lesson3

import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test

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