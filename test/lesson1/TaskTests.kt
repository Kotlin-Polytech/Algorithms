package lesson1

import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals

class TaskTests {

    private fun assertFileContent(name: String, expectedContent: String) {
        val file = File(name)
        val content = file.readLines().joinToString("\n")
        assertEquals(expectedContent, content)
    }

    @Test
    fun sortTimes() {
        sortTimes("input/time_in1.txt", "temp.txt")
        assertFileContent("temp.txt",
                """
                     00:40:31
                     07:26:57
                     10:00:03
                     13:15:19
                     13:15:19
                     19:56:14
                """.trimIndent()
        )
        File("temp.txt").delete()
    }
}