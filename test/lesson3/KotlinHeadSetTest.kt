package lesson3

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Tag
import kotlin.test.Test

class KotlinHeadSetTest : AbstractHeadTailTest() {

    @BeforeEach
    fun fillTree() {
        fillTree(KtBinaryTree())
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

    @Test
    @Tag("Impossible")
    fun subSetTest() {
        doSubSetTest()
    }
}