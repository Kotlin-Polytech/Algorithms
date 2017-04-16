package lesson4.rod

data class Cut(val cost: Int, val length: List<Int>) {
    operator fun plus(cut: Cut) = Cut(cost + cut.cost, length + cut.length)

    constructor(cost: Int, vararg length: Int): this(cost, length.asList())
}

private val storage = hashMapOf(0 to Cut(0, emptyList()))

fun cutRod(n: Int, cost: (Int) -> Int): Cut = storage.getOrPut(n) {
    var best = Cut(cost(n), n)
    for (first in 1..n-1) {
        val current = Cut(cost(first), first) + cutRod(n - first, cost)
        if (current.cost > best.cost) {
            best = current
        }
    }
    best
}

val cost = mapOf(1 to 1, 2 to 5, 3 to 9, 4 to 9, 5 to 10, 6 to 17, 7 to 17, 8 to 20, 9 to 24)

fun main(args: Array<String>) {
    println(cutRod(20) { cost[it] ?: 0 })
    println(cutRod(10) { cost[it] ?: 0 })
    println(cutRod(8) { cost[it] ?: 0 })
}