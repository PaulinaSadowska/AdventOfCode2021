class SmokeBasin {

    fun sumLowPoints(fileName: String): Int {
        val smokeMatrix = loadData(fileName)

        return smokeMatrix.foldIndexed(0) { rowIndex, acc, row ->
            acc + row.indices.fold(0) { columnAcc, columnIndex ->
                val element = row[columnIndex]
                columnAcc + if (
                    element < row.getOrMax(columnIndex - 1) &&
                    element < row.getOrMax(columnIndex + 1) &&
                    element < smokeMatrix.getOrMax(columnIndex, rowIndex - 1) &&
                    element < smokeMatrix.getOrMax(columnIndex, rowIndex + 1)
                ) {
                    element + 1
                } else 0
            }
        }
    }

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
