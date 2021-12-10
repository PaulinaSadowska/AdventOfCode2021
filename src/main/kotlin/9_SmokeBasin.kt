class SmokeBasin {

    fun sumLowPoints(fileName: String): Int {
        val smokeMatrix = loadData(fileName)

        return findLowPoints(smokeMatrix).fold(0) { acc, point ->
            acc + smokeMatrix[point.row][point.column] + 1
        }
    }

    fun measureBiggestBasins(fileName: String): Int {
        val smokeMatrix = loadData(fileName)

        val lowPoints = findLowPoints(smokeMatrix)

        return 0
    }

    private fun findLowPoints(
        smokeMatrix: List<List<Int>>,
    ): List<Point> {
        return smokeMatrix.flatMapIndexed { rowIndex, row ->
            row.indices.mapNotNull { columnIndex ->
                val element = row[columnIndex]
                if (
                    element < row.getOrMax(columnIndex - 1) &&
                    element < row.getOrMax(columnIndex + 1) &&
                    element < smokeMatrix.getOrMax(columnIndex, rowIndex - 1) &&
                    element < smokeMatrix.getOrMax(columnIndex, rowIndex + 1)
                ) {
                    Point(rowIndex, columnIndex)
                } else null
            }
        }
    }

    data class Point(
        val row: Int,
        val column: Int
    )

    private fun List<Int>.getOrMax(index: Int) = getOrElse(index) { Int.MAX_VALUE }

    private fun List<List<Int>>.getOrMax(columnIndex: Int, rowIndex: Int): Int {
        return if (rowIndex in 0 until size) {
            return get(rowIndex)[columnIndex]
        } else Int.MAX_VALUE
    }

    private fun loadData(fileName: String): List<List<Int>> {
        return loadDataFromFile(fileName)
            .map { it ->
                it.split("")
                    .mapNotNull { it.toIntOrNull() }
            }
    }
}
