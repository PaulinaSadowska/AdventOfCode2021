import kotlin.math.pow

class BinaryDiagnostic {

    fun calculatePowerConsumption(fileName: String): PowerConsumption {
        val data = loadData(fileName)
        val rowSize = data[0].size

        val numOfOnesInColumns = MutableList(rowSize) { 0 }
        data.forEach { row ->
            row.forEachIndexed { index, element ->
                numOfOnesInColumns[index] += element
            }
        }

        val halfOfDataSize = data.size / 2
        println("numOfOnes= $numOfOnesInColumns (half = $halfOfDataSize)")

        val mostCommonBits = numOfOnesInColumns.map {
            if (it > halfOfDataSize) 1 else 0
        }
        val leastCommonBits = numOfOnesInColumns.map {
            if (it < halfOfDataSize) 1 else 0
        }
        return PowerConsumption(
            gamma = mostCommonBits.bytesToInt(),
            epsilon = leastCommonBits.bytesToInt()
        )
    }

    fun calculateLifeSupport(fileName: String): LifeSupport {
        val data = loadData(fileName)

        val oxygen = calculateOxygen(data, transposeData(data))
        val co2 = calculateCo2(data, transposeData(data))

        return LifeSupport(oxygen = oxygen.bytesToInt(), co2 = co2.bytesToInt())
    }

    private fun calculateOxygen(notTransposed: List<List<Int>>, data: Array<IntArray>): List<Int> {
        val row = data.size

        val indexes = data[0]
        val oxygen = MutableList(row) { 0 }
        oxygen[0] = 1
        data.forEachIndexed { index, element ->
            var sum = 0
            if (index != 0) {
                element.forEachIndexed { index2, element2 ->
                    if (indexes[index2] == 1) {
                        sum += element2
                    }
                }
                oxygen[index] = if (sum * 2 >= indexes.sum()) {
                    1
                } else 0
                if (indexes.sum() == 1) {
                    val i = indexes.indexOf(1)
                    return notTransposed[i]
                }
                element.forEachIndexed { index2, element2 ->
                    if (element2 != oxygen[index]) {
                        indexes[index2] = 0
                    }
                }
                sum = 0
            }
        }
        println(oxygen.joinToString(" "))
        return oxygen
    }

    private fun calculateCo2(notTransposed: List<List<Int>>, data: Array<IntArray>): List<Int> {
        val row = data.size

        val indexes: MutableList<Int> = data[0].map { if (it == 1) 0 else 1 }.toMutableList()
        val co2 = MutableList(row) { 0 }
        co2[0] = 0
        data.forEachIndexed { index, element ->
            var sumOfOnes = 0
            if (index != 0) {
                element.forEachIndexed { index2, element2 ->
                    if (indexes[index2] == 1) {
                        sumOfOnes += element2
                    }
                }
                co2[index] = if (sumOfOnes * 2 >= indexes.sum()) {
                    0
                } else 1
                if (indexes.sum() == 1) {
                    val i = indexes.indexOf(1)
                    return notTransposed[i]
                }
                element.forEachIndexed { index2, element2 ->
                    if (element2 != co2[index]) {
                        indexes[index2] = 0
                    }
                }
            }
        }
        return co2
    }

    private fun transposeData(matrix: List<List<Int>>): Array<IntArray> {
        val column = matrix[0].size
        val row = matrix.size
        val transpose = Array(column) { IntArray(row) }
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

