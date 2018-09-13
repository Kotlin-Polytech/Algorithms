package lesson6

import kotlin.test.Test
import kotlin.test.assertEquals

class TaskTests {

    @Test
    fun longestCommonSubsequence() {
        assertEquals("", longestCommonSubsequence("мой мир", "я"))
        assertEquals("здс", longestCommonSubsequence("здравствуй мир", "мы здесь"))
        assertEquals("emt ole", longestCommonSubsequence("nematode knowledge", "empty bottle"))
    }

}