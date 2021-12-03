import kotlin.math.pow

class BinaryDiagnostic {

    fun calculatePowerConsumption(fileName: String): PowerConsumption {
        val data = loadData(fileName)
        val transposed = transpose(data)

        val numOfOnesInColumns = transposed.map { it.sum() }

        val mostCommonBits = numOfOnesInColumns.map {
            if (it * 2 > data.size) 1 else 0
        }
        val leastCommonBits = numOfOnesInColumns.map {
            if (it * 2 > data.size) 0 else 1
        }
        return PowerConsumption(
            gamma = mostCommonBits.bytesToInt(),
            epsilon = leastCommonBits.bytesToInt()
        )
    }

    fun calculateLifeSupport(fileName: String): LifeSupport {
        val data = loadData(fileName)
        val transposed = transpose(data)

        val oxygen = calculateLifeSupportFactor(data, transposed) { if (it) 1 else 0 }
        val co2 = calculateLifeSupportFactor(data, transposed) { if (it) 0 else 1 }

        return LifeSupport(oxygen = oxygen.bytesToInt(), co2 = co2.bytesToInt())
    }

    private fun calculateLifeSupportFactor(
        notTransposed: List<List<Int>>,
        data: List<List<Int>>,
        bitCriteria: (Boolean) -> Int
    ): List<Int> {
        val row = data.size

        val indexes = data[0].map { bitCriteria(it == 1) }.toMutableList()
        val result = MutableList(row) { 0 }
        result[0] = bitCriteria(true)
        data.forEachIndexed { index, element ->
            var sum = 0
            if (index != 0) {
                element.forEachIndexed { index2, element2 ->
                    if (indexes[index2] == 1) {
                        sum += element2
                    }
                }
                result[index] = bitCriteria(sum * 2 >= indexes.sum())
                if (indexes.sum() == 1) {
                    val i = indexes.indexOf(1)
                    return notTransposed[i]
                }
                element.forEachIndexed { index2, element2 ->
                    if (element2 != result[index]) {
                        indexes[index2] = 0
                    }
                }
            }
        }
        return result
    }

    private fun transpose(matrix: List<List<Int>>): List<List<Int>> {
        val column = matrix[0].size
        val row = matrix.size
        val transpose = MutableList(column) { MutableList(row) { 0 } }
        for (i in 0 until row) {
            for (j in 0 until column) {
                transpose[j][i] = matrix[i][j]
            }
        }
        return transpose
    }

    private fun loadData(fileName: String): List<List<Int>> {
        return loadDataFromFile(fileName)
            .map { row ->
                row.split("").mapNotNull { it.toIntOrNull() }
            }
    }

    data class PowerConsumption(
        val gamma: Int,
        val epsilon: Int
    ) {
        val result
            get() = gamma * epsilon
    }

    data class LifeSupport(
        val oxygen: Int,
        val co2: Int
    ) {
        val result
            get() = oxygen * co2
    }
}

private fun List<Int>.bytesToInt(): Int {
    return foldIndexed(0) { index: Int, acc: Int, curr: Int ->
        acc + curr * 2f.pow(size - index - 1).toInt()
    }
}

