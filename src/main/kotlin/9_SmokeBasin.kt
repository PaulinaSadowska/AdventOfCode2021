import kotlin.math.max
import kotlin.math.min

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
        val basins = mutableListOf<Basin>()

        lowPoints.forEach { point ->
            println("low: $point")
            val newMatrix = smokeMatrix.copySmall(point)
/*            val lowest = findLowPoints(newMatrix.)

            val basinElements = basinVertical(smokeMatrix, point)*/

            println(newMatrix)
            //basins.add(Basin(point, basinElements))
        }

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

    data class Basin(
        val lowPoint: Point,
        val elements: List<Int>
    )

    private fun List<Int>.getOrMax(index: Int) = getOrElse(index) { MAX }

    private fun List<List<Int>>.getOrMax(columnIndex: Int, rowIndex: Int): Int {
        return if (rowIndex in 0 until size) {
            return get(rowIndex)[columnIndex]
        } else MAX
    }

    // zle...
    private fun List<List<Int>>.copySmall(point: Point): MutableList<MutableList<Int>> {
        val startRow = max(point.row - 1, 0)
        val endRow = min(point.row + 1, size - 1)
        val startColumn = max(point.column - 1, 0)
        val endColumn = min(point.column + 1, first().size - 1)
        println(startRow)
        println(endRow)
        println(startColumn)
        println(endColumn)
        return mutableListOf<MutableList<Int>>().also { newMatrix ->
            (startRow..endRow).forEach { row ->
                newMatrix.add((startColumn..endColumn).map { this[row][it] }.toMutableList())
                newMatrix[point.row][point.column] = MAX
            }
        }
    }

    private fun loadData(fileName: String): List<List<Int>> {
        return loadDataFromFile(fileName)
            .map { it ->
                it.split("")
                    .mapNotNull { it.toIntOrNull() }
            }
    }

    companion object {
        private const val MAX = 9
    }
}
