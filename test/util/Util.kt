package util

import org.jetbrains.research.runner.perf.PerfEstimation
import org.jetbrains.research.runner.perf.estimate

data class PerfResult<T>(
    val size: Int = 0,
    val time: Long = 0,
    val data: T
)

fun <T> estimate(data: List<PerfResult<T>>) = estimate(data.map { it.size to it.time })

fun <T> estimate(sizes: List<Int>, body: (Int) -> PerfResult<T>): PerfEstimation {
    val data: MutableList<PerfResult<T>> = mutableListOf()

    for (size in sizes) {
        data += body(size)
    }

    return estimate(data)
}
