import kotlin.math.abs
import kotlin.math.round

class Whales {
    fun calculate(fileName: String, part: Part): Result {
        val data = loadData(fileName)
        return calculatePositionAndFuel(data, part)
    }

    private fun calculatePositionAndFuel(data: List<Int>, part: Part): Result {
        val position = calculatePosition(data, part)
        val fuel = data.fold(0) { acc, curr ->
            acc + cost(curr, position, part)
        }

        println("position: $position")
        return Result(position, fuel)
    }

    private fun cost(curr: Int, position: Int, part: Part): Int {
        val distance = abs(curr - position)
        return when (part) {
            Part.FIRST -> distance
            Part.SECOND -> { // suma ciagu arytmetycznego
                (1 + distance) * distance / 2
            }
        }
    }

    private fun calculatePosition(data: List<Int>, part: Part): Int {
        return if (part == Part.FIRST) {
            data.median()
        } else {
            println("average: ${data.average()}")
            round(data.average()).toInt() - 1
        }
    }

    private fun List<Int>.median(): Int = sorted().let { (it[it.size / 2] + it[(it.size - 1) / 2]) / 2 }

    private fun loadData(fileName: String): List<Int> {
        return loadDataFromFile(fileName)
            .first()
            .split(",")
            .mapNotNull { it.toIntOrNull() }
    }

    data class Result(
        val position: Int,
        val fuel: Int
    )

    enum class Part {
        FIRST, SECOND
    }
}