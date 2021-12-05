class HydrothermalVenture {

    fun calculate(fileName: String, diagonal: Boolean = false): Int {
        val data = loadData(fileName)
        return findForHorizontalAndVertical(data, diagonal)
    }

    private fun findForHorizontalAndVertical(data: Data, diagonal: Boolean): Int {
        val result = MutableList(data.resultSize) { MutableList(data.resultSize) { 0 } }
        data.lines.forEach { line ->

            if (line.isHorizontal()) {
                line.horizontalRange().forEach { x ->
                    result[line.start.y][x] += 1
                }
            } else if (line.isVertical()) {
                line.verticalRange().forEach { y ->
                    result[y][line.start.x] += 1
                }
            }
        }

        return result.fold(0) { acc, row ->
            acc + row.map { if (it >= 2) 1 else 0 }.sum()
        }
    }


    data class Line(
        val start: Point,
        val end: Point
    ) {
        fun isVertical() = start.x == end.x
        fun isHorizontal() = start.y == end.y
        fun horizontalRange() = if (end.x > start.x) {
            start.x..end.x
        } else {
            end.x..start.x
        }

        fun verticalRange() = if (end.y > start.y) {
            start.y..end.y
        } else {
            end.y..start.y
        }
    }

    data class Point(
        val x: Int,
        val y: Int
    )

    private fun loadData(fileName: String): Data {
        var max = 0
        val lines = loadDataFromFile(fileName).map { row ->
            val line = row.split(" -> ").map { it ->
                val point = it.split(",").map { it.toInt() }
                point.forEach {
                    if (it > max) {
                        max = it
                    }
                }
                Point(point[0], point[1])
            }
            Line(line[0], line[1])
        }
        return Data(lines, resultSize = max + 1)
    }

    data class Data(
        val lines: List<Line>,
        val resultSize: Int
    )
}