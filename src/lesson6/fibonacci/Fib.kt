package lesson6.fibonacci

private val storage = hashMapOf(0 to 0L, 1 to 1L)

fun fib(n: Int): Long = storage.getOrPut(n) { fib(n - 1) + fib(n - 2) }

fun fib2(n: Int): Long = if (n < 2) n.toLong() else fib2(n - 1) + fib2(n - 2)

fun main() {
    println(fib(4))
    println(fib(6))
    println(fib(10))
    println(fib(20))
    println(fib(30))
    println(fib(40))
    println(fib(50))
    println(fib2(4))
    println(fib2(6))
    println(fib2(10))
    println(fib2(20))
    println(fib2(30))
    println(fib2(40))
    println(fib2(50))
}