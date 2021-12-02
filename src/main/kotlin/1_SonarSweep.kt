fun main() {
    val data = loadDataFromFile("1_input.txt").mapNotNull { it.toIntOrNull() }
    println("num of data points: " + data.size)
    println("result: ${sonarSweep(data)}")
}


fun sonarSweep(data: List<Int>): Int {
    return data
        .mapIndexed { index, current ->
            val previous = data.getOrElse(index - 1) { Int.MAX_VALUE }
            if (current > previous) 1 else 0
        }.reduce { acc, curr -> acc + curr }
}