class GiantSquidBingo {

    fun calculate(fileName: String): Int {
        val data = loadData(fileName)
        println(data.numbers)
        println(data.boards)

        return playBingo(data)
    }

    private fun playBingo(data: Bingo): Int {
        val numbers = data.numbers
        val boards = data.boards

        return 0
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

    data class Bingo(
        val numbers: List<Int>,
        val boards: List<Board>
    )

    data class Board(
        val board: List<List<Int>>
    )
}