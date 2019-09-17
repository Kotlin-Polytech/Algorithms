package lesson6.rod

data class Cut(val cost: Int, val length: List<Int>) {
    operator fun plus(cut: Cut) = Cut(cost + cut.cost, length + cut.length)

    constructor(cost: Int, vararg length: Int) : this(cost, length.asList())
}

fun cutRod(n: Int, storage: MutableMap<Int, Cut> = hashMapOf(), cost: (Int) -> Int): Cut = storage.getOrPut(n) {
    var best = Cut(cost(n), n)
    for (first in 1 until n) {
        val current = Cut(cost(first), first) + cutRod(n - first, storage, cost)
        if (current.cost > best.cost) {
            best = current
        }
    }
    best
}

private val cost = mapOf(1 to 1, 2 to 5, 3 to 9, 4 to 9, 5 to 10, 6 to 17, 7 to 17, 8 to 20, 9 to 24)

fun main() {
    println(cutRod(20) { cost[it] ?: 0 })
    println(cutRod(10) { cost[it] ?: 0 })
    println(cutRod(8) { cost[it] ?: 0 })
}