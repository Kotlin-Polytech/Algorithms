package lesson6

import kotlin.test.Test
import kotlin.test.assertEquals

class TaskTests {

    @Test
    fun longestCommonSubSequence() {
        assertEquals("", longestCommonSubSequence("мой мир", "я"))
        assertEquals("здс", longestCommonSubSequence("здравствуй мир", "мы здесь"))
        assertEquals("emt ole", longestCommonSubSequence("nematode knowledge", "empty bottle"))
    }

}