class GiantSquidBingo {

    fun calculate(fileName: String): Int {
        val data = loadData(fileName)
        return playBingo(data)
    }

    private fun playBingo(data: Bingo): Int {
        val numbers = data.numbers
        val boards = data.boards

        numbers.forEach { number ->
            println("number: $number")
            boards.forEach { board ->
                board.board.forEachIndexed { i, row ->
                    val index = row.indexOf(number)
                    if (index >= 0) {
                        board.selection[i][index] = 1
                        if (board.hasBingo()) {
                            val sum = board.sumOfUnmarkedNumbers()
                            println("sum: $sum")
                            return sum * number
                        }
                    }
                }
            }
        }

        return 0
    }

    data class Bingo(
        val numbers: List<Int>,
        val boards: List<Board>
    )

    data class Board(
        val board: List<List<Int>>,
    ) {
        val selection = MutableList(board.size) { MutableList(board[0].size) { 0 } }

        fun hasBingo(): Boolean {
            val row = hasBingoInRow()
            val column = hasBingoInColumn()
            val bingo = row || column
            if (bingo) {
                println("BINGO! for row: $row, column: $column")
            }
            return bingo
        }

        private fun hasBingoInRow(): Boolean {
            return selection.map { it.sum() }.any { it >= board.size }
        }

        private fun hasBingoInColumn(): Boolean {
            val sums = MutableList(selection.size) { 0 }
            selection.forEach {
                it.forEachIndexed { index, e ->
                    sums[index] += e
                }
            }
            return sums.any { it >= selection.size }
        }

        fun sumOfUnmarkedNumbers(): Int {
            val sums = board.mapIndexed { iRow, row ->
                row.foldIndexed(0) { i, acc, curr ->
                    acc + curr * if (selection[iRow][i] == 1) 0 else 1
                }
            }
            return sums.sum()
        }
    }

    private fun loadData(fileName: String): Bingo {
        val data = loadDataFromFile(fileName)
        val numbers = data[0].split(",").mapNotNull { it.toIntOrNull() }
        val boards = mutableListOf<Board>()
        val newBoard = mutableListOf<List<Int>>()
        data.forEachIndexed { index, element ->
            if (index > 1) {
                if (element.isNotBlank()) {
                    val row = element.split(" ").mapNotNull { it.toIntOrNull() }
                    newBoard.add(row)
                } else if (newBoard.isNotEmpty()) {
                    boards.add(Board(newBoard.toList()))
                    newBoard.clear()
                }
            }
        }
        boards.add(Board(newBoard.toList()))

        return Bingo(numbers, boards)
    }
}