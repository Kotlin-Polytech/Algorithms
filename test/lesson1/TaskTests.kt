package lesson1

import org.junit.Assert.assertArrayEquals
import java.io.BufferedWriter
import java.io.File
import java.util.*
import kotlin.math.abs
import kotlin.test.Test

class TaskTests : AbstractFileTests() {

    @Test
    fun sortTimes() {
        try {
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
        } finally {
            File("temp.txt").delete()
        }
        try {
            sortTimes("input/time_in2.txt", "temp.txt")
            assertFileContent("temp.txt",
                    """
                     00:00:00
                """.trimIndent()
            )
        } finally {
            File("temp.txt").delete()
        }
        try {
            sortTimes("input/time_in3.txt", "temp.txt")
            assertFileContent("temp.txt", File("input/time_out3.txt").readLines().joinToString(separator = "\n"))
        } finally {
            File("temp.txt").delete()
        }
    }

    @Test
    fun sortAddresses() {
        try {
            sortAddresses("input/addr_in1.txt", "temp.txt")
            assertFileContent("temp.txt",
                    """
                    Железнодорожная 3 - Петров Иван
                    Железнодорожная 7 - Иванов Алексей, Иванов Михаил
                    Садовая 5 - Сидоров Петр, Сидорова Мария
                """.trimIndent()
            )
        } finally {
            File("temp.txt").delete()
        }
    }

    private fun generateTemperatures(size: Int) {
        val random = Random()
        val temperatures = mutableListOf<Int>()
        for (t in -2730..5000) {
            val count = random.nextInt(size)
            for (i in 1..count) {
                temperatures += t
            }
        }

        fun BufferedWriter.writeTemperatures() {
            for (t in temperatures) {
                if (t < 0) write("-")
                write("${abs(t) / 10}.${abs(t) % 10}")
                newLine()
            }
            close()
        }

        File("temp_sorted_expected.txt").bufferedWriter().writeTemperatures()
        temperatures.shuffle(random)
        File("temp_unsorted.txt").bufferedWriter().writeTemperatures()
    }

    @Test
    fun sortTemperatures() {
        try {
            sortTemperatures("input/temp_in1.txt", "temp.txt")
            assertFileContent("temp.txt",
                    """
                    -98.4
                    -12.6
                    -12.6
                    11.0
                    24.7
                    99.5
                    121.3
                """.trimIndent()
            )
        } finally {
            File("temp.txt").delete()
        }

        fun testGeneratedTemperatures(size: Int) {
            try {
                generateTemperatures(size)
                sortTemperatures("temp_unsorted.txt", "temp_sorted_actual.txt")
                assertFileContent("temp_sorted_actual.txt",
                        File("temp_sorted_expected.txt").readLines().joinToString(separator = "\n")
                )
            } finally {
                File("temp_unsorted.txt").delete()
                File("temp_sorted_expected.txt").delete()
                File("temp_sorted_actual.txt").delete()
            }
        }
        testGeneratedTemperatures(10)
        testGeneratedTemperatures(5000)
    }

    @Test
    fun sortSequence() {
        try {
            sortSequence("input/seq_in1.txt", "temp.txt")
            assertFileContent("temp.txt",
                    """
                        1
                        3
                        3
                        1
                        2
                        2
                        2
                    """.trimIndent())
        } finally {
            File("temp.txt").delete()
        }
    }

    @Test
    fun mergeArrays() {
        val result = arrayOf(null, null, null, null, null, 1, 3, 9, 13, 18, 23)
        mergeArrays(arrayOf(4, 9, 15, 20, 23), result)
        assertArrayEquals(arrayOf(1, 3, 4, 9, 9, 13, 15, 18, 20, 23, 23), result)

        fun generateArrays(firstSize: Int, secondSize: Int): Triple<Array<Int>, Array<Int?>, Array<Int?>> {
            val random = Random()
            val expectedResult = Array<Int?>(firstSize + secondSize) {
                it * 10 + random.nextInt(10)
            }
            val first = mutableListOf<Int>()
            val second = mutableListOf<Int?>()
            for (i in 1..firstSize) second.add(null)
            for (element in expectedResult) {
                if (first.size < firstSize && (random.nextBoolean() || second.size == firstSize + secondSize)) {
                    first += element!!
                } else {
                    second += element
                }
            }
            return Triple(first.toTypedArray(), second.toTypedArray(), expectedResult)
        }

        run {
            val (first, second, expectedResult) = generateArrays(20000, 20000)
            mergeArrays(first, second)
            assertArrayEquals(expectedResult, second)
        }

        run {
            val (first, second, expectedResult) = generateArrays(500000, 500000)
            mergeArrays(first, second)
            assertArrayEquals(expectedResult, second)
        }
    }
}