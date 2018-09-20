package lesson6.fibonacci

import org.junit.jupiter.api.Tag
import kotlin.test.*

class FibTest {
    @Test
    @Tag("Example")
    fun fib() {
        assertEquals(1L, fib(1))
        assertEquals(1L, fib(2))
        assertEquals(2L, fib(3))
        assertEquals(3L, fib(4))
        assertEquals(5L, fib(5))
        assertEquals(13L, fib(7))
        assertEquals(89L, fib(11))
        assertEquals(610L, fib(15))
        assertEquals(6765L, fib(20))
        assertEquals(832040L, fib(30))
    }
}