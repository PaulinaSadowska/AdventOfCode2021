import kotlin.math.pow

class BinaryDiagnostic {

    fun calculate(fileName: String): Result {
        val data = loadDataFromFile(fileName)
            .map { row ->
                row
                    .split("")
                    .mapNotNull { it.toIntOrNull() }
            }

        val rowSize = data[0].size
        val half = data.size / 2
        val numOfOnesInColumns = MutableList(rowSize) { 0 }
        data.forEach { row ->
            row.forEachIndexed { index, element ->
                numOfOnesInColumns[index] += element
            }
        }
        println("numOfOnes= $numOfOnesInColumns (half = $half)")
        val gamma = numOfOnesInColumns.map {
            if (it > half) 1 else 0
        }.bytesToInt(rowSize)
        val epsilon = numOfOnesInColumns.map {
            if (it > half) 0 else 1
        }.bytesToInt(rowSize)
        return Result(gamma, epsilon)
    }

    data class Result(
        val gamma: Int,
        val epsilon: Int
    ) {
        val powerConsumption
            get() = gamma * epsilon
    }
}

private fun List<Int>.bytesToInt(rowSize: Int): Int {
    return mapIndexed { index: Int, curr: Int ->
        curr * 2.toDouble().pow((rowSize - index - 1).toDouble()).toInt()
    }.reduce { a, c -> a + c }
}

