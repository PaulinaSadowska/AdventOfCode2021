import kotlin.math.abs

class Whales {
    fun calculate(fileName: String): Result {
        val data = loadData(fileName)
        return calculatePosition(data)
    }

    private fun calculatePosition(data: List<Int>): Result {

        val median = data.median()
        val fuel = data.fold(0) { acc, curr ->
            acc + abs(curr - median)
        }

        return Result(position = median, fuel = fuel)
    }

    private fun List<Int>.median() = sorted().let { (it[it.size / 2] + it[(it.size - 1) / 2]) / 2 }

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
}