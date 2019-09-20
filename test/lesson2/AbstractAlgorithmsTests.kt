package lesson2

import java.io.BufferedWriter
import java.io.File
import java.util.*
import kotlin.test.assertEquals

abstract class AbstractAlgorithmsTests {

    private val minPrice = 42

    private val maxPrice = 99999

    private fun generatePrices(size: Int): Pair<Int, Int> {
        val random = Random()
        val prices = mutableListOf<Int>()
        for (index in 1..size) {
            val price = minPrice + 1 + random.nextInt(maxPrice - 1 - minPrice)
            prices += price
        }
        val firstIndex = random.nextInt(size)
        val secondIndex = random.nextInt(size).let {
            when (it) {
                firstIndex -> if (firstIndex == size - 1) firstIndex - 1 else firstIndex + 1
                else -> it
            }
        }
        val (minIndex, maxIndex) =
            if (firstIndex < secondIndex) firstIndex to secondIndex else secondIndex to firstIndex
        prices[minIndex] = minPrice
        prices[maxIndex] = maxPrice

        fun BufferedWriter.writePrices() {
            for (price in prices) {
                write(price.toString())
                newLine()
            }
            close()
        }

        File("temp_prices.txt").bufferedWriter().writePrices()
        return minIndex + 1 to maxIndex + 1
    }

    fun optimizeBuyAndSell(optimizeBuyAndSell: (String) -> Pair<Int, Int>) {
        assertEquals(3 to 4, optimizeBuyAndSell("input/buysell_in1.txt"))
        assertEquals(8 to 12, optimizeBuyAndSell("input/buysell_in2.txt"))
        assertEquals(3 to 4, optimizeBuyAndSell("input/buysell_in3.txt"))
        try {
            val expectedAnswer = generatePrices(1000)
            assertEquals(expectedAnswer, optimizeBuyAndSell("temp_prices.txt"))
        } finally {
            File("temp_prices.txt").delete()
        }
        try {
            val expectedAnswer = generatePrices(100000)
            assertEquals(expectedAnswer, optimizeBuyAndSell("temp_prices.txt"))
        } finally {
            File("temp_prices.txt").delete()
        }
    }

    fun josephTask(josephTask: (Int, Int) -> Int) {
        assertEquals(1, josephTask(1, 1))
        assertEquals(2, josephTask(2, 1))
        assertEquals(50000000, josephTask(50000000, 1))
        assertEquals(3, josephTask(8, 5))
        assertEquals(28, josephTask(40, 3))
        var menNumber = 2
        for (i in 1..20) {
            assertEquals(1, josephTask(menNumber, 2))
            menNumber *= 2
        }
    }

    fun longestCommonSubstring(longestCommonSubstring: (String, String) -> String) {
        assertEquals("", longestCommonSubstring("мой мир", "я"))
        assertEquals("зд", longestCommonSubstring("здравствуй мир", "мы здесь"))
        assertEquals("СЕРВАТОР", longestCommonSubstring("ОБСЕРВАТОРИЯ", "КОНСЕРВАТОРЫ"))
        assertEquals(
            "огда ", longestCommonSubstring(
                """
Мой дядя самых честных правил,
Когда не в шутку занемог,
Он уважать себя заставил
И лучше выдумать не мог.
Его пример другим наука;
Но, боже мой, какая скука
С больным сидеть и день и ночь,
Не отходя ни шагу прочь!
Какое низкое коварство
Полуживого забавлять,
Ему подушки поправлять,
Печально подносить лекарство,
Вздыхать и думать про себя:
Когда же черт возьмет тебя!
                """.trimIndent(),
                """
Так думал молодой повеса,
Летя в пыли на почтовых,
Всевышней волею Зевеса
Наследник всех своих родных.
Друзья Людмилы и Руслана!
С героем моего романа
Без предисловий, сей же час
Позвольте познакомить вас:
Онегин, добрый мой приятель,
Родился на брегах Невы,
Где, может быть, родились вы
Или блистали, мой читатель;
Там некогда гулял и я:
Но вреден север для меня
                """.trimIndent()
            )
        )
        assertEquals(
            "(с) Этот весь длинный-длинный текст является цитатой из Пушкина, поэма \"Руслан и Людмила\"",
            longestCommonSubstring(
                File("input/ruslan_ludmila_1.txt").readText(),
                File("input/ruslan_ludmila_2.txt").readText()
            ).trim()
        )
    }

    fun calcPrimesNumber(calcPrimesNumber: (Int) -> Int) {
        assertEquals(0, calcPrimesNumber(-1))
        assertEquals(0, calcPrimesNumber(1))
        assertEquals(1, calcPrimesNumber(2))
        assertEquals(2, calcPrimesNumber(4))
        assertEquals(4, calcPrimesNumber(10))
        assertEquals(8, calcPrimesNumber(20))
        assertEquals(1000, calcPrimesNumber(7920))
        assertEquals(1229, calcPrimesNumber(10000))
        assertEquals(2262, calcPrimesNumber(20000))
        assertEquals(5133, calcPrimesNumber(50000))
        assertEquals(9592, calcPrimesNumber(100000))
        assertEquals(17984, calcPrimesNumber(200000))
        assertEquals(33860, calcPrimesNumber(400000))
        assertEquals(49098, calcPrimesNumber(600000))
        assertEquals(56543, calcPrimesNumber(700000))
        assertEquals(63951, calcPrimesNumber(800000))
        assertEquals(71274, calcPrimesNumber(900000))
        assertEquals(78498, calcPrimesNumber(1000000))
        assertEquals(148933, calcPrimesNumber(2000000))
        assertEquals(348513, calcPrimesNumber(5000000))
        assertEquals(664579, calcPrimesNumber(10000000))
    }

    fun baldaSearcher(baldaSearcher: (String, Set<String>) -> Set<String>) {
        assertEquals(
            setOf("ТРАВА", "КРАН", "АКВА", "НАРТЫ"),
            baldaSearcher("input/balda_in1.txt", setOf("ТРАВА", "КРАН", "АКВА", "НАРТЫ", "РАК"))
        )
        assertEquals(
            setOf("БАЛДА"),
            baldaSearcher("input/balda_in2.txt", setOf("БАЛАБОЛ", "БАЛДА", "БАЛДАЗАВР"))
        )
        assertEquals(
            setOf(
                "АПЕЛЬСИН", "МАРОККО", "ПЕРЕМЕНЫ", "ГРАВИТАЦИЯ",
                "РАССУДИТЕЛЬНОСТЬ", "КОНСТАНТИНОПОЛЬ", "ПРОГРАММИРОВАНИЕ", "ПОМЕХОУСТОЙЧИВОСТЬ", "АППРОКСИМАЦИЯ",
                "ЭЙНШТЕЙН"
            ),
            baldaSearcher(
                "input/balda_in3.txt", setOf(
                    "АПЕЛЬСИН", "МАРОККО", "ЭФИОПИЯ", "ПЕРЕМЕНЫ", "ГРАВИТАЦИЯ",
                    "РАССУДИТЕЛЬНОСТЬ", "БЕЗРАССУДНОСТЬ", "КОНСТАНТИНОПОЛЬ", "СТАМБУЛ", "ПРОГРАММИРОВАНИЕ",
                    "ПРОСТРАНСТВО", "ДИАЛЕКТИКА", "КВАЛИФИКАЦИЯ", "ПОМЕХОУСТОЙЧИВОСТЬ", "КОГЕРЕНТНОСТЬ",
                    "АППРОКСИМАЦИЯ", "ИНТЕРПОЛЯЦИЯ", "МАЙЕВТИКА", "ШРЕДИНГЕР", "ЭЙНШТЕЙН"
                )
            )
        )
    }
}