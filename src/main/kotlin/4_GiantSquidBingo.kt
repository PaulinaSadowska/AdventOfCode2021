class GiantSquidBingo {

    fun calculate(fileName: String, firstPlace: Boolean = true): Int {
        val data = loadData(fileName)
        return playBingo(data, firstPlace)
    }

    private fun playBingo(data: Bingo, firstPlace: Boolean): Int {
        val numbers = data.numbers
        val boards = data.boards
        val activePlayers = MutableList(boards.size) { true }

        numbers.forEach { number ->
            boards.forEachIndexed { boardIndex, board ->
                if (activePlayers[boardIndex]) {
                    board.board.forEachIndexed { i, row ->
                        val index = row.indexOf(number)
                        if (index >= 0) {
                            board.selection[i][index] = 1
                            if (board.hasBingo()) {
                                println("Bingo for number: $number")
                                if (firstPlace) {
                                    return board.sumOfUnmarkedNumbers() * number
                                } else {
                                    if (activePlayers.count { !it } >= boards.size - 1) {
                                        return board.sumOfUnmarkedNumbers() * number
                                    }
                                }
                                activePlayers[boardIndex] = false
                            }
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

    data class Board(val board: List<List<Int>>) {

        val selection = MutableList(board.size) { MutableList(board[0].size) { 0 } }

        fun hasBingo() = hasBingoInRow() || hasBingoInColumn()

        private fun hasBingoInRow(): Boolean {
            return selection.map { it.sum() }.any { it >= board.size }
        }

        private fun hasBingoInColumn(): Boolean {
            val sums = MutableList(selection.size) { 0 }
            selection.forEach { row ->
                row.forEachIndexed { index, element ->
                    sums[index] += element
                }
            }
            return sums.any { it >= selection.size }
        }

        fun sumOfUnmarkedNumbers(): Int {
            return board.mapIndexed { iRow, row ->
                row.foldIndexed(0) { i, acc, curr ->
                    acc + curr * if (selection[iRow][i] == 1) 0 else 1
                }
            }.sum().also {
                println("sum of unmarked: $it")
            }
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