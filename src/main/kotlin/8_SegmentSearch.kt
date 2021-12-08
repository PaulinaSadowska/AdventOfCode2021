class SegmentSearch {

    fun calculateUniqueSegmentsInOutput(fileName: String): Int {
        val data = loadSegments(fileName)
        return countUnique(data)
    }

    private fun countUnique(data: List<Data>): Int {
        return data
            .flatMap { it.output }
            .fold(0) { acc, digitCode ->
                acc + if (digitCode.length in uniqueDigitSizes) 1 else 0
            }
    }

    fun calculateSumOfOutput(fileName: String): Int {
        val data = loadSegments(fileName)
        return data.sumOf { calculateOutputSum(it) }
    }

    private fun calculateOutputSum(data: Data): Int {

        return 0
    }

    /*
0 - abcefg - 6
1 - cf - 2 <----- unique
2 - acdeg - 5
3 - acdfg - 5
4 - bcdf - 4 <--- unique
5 - abdfg - 5
6 - abdefg - 6
7 - acf - 3 <----- unique
8 - abcdefg - 7 <---- unique
9 - abcdfg - 6
 */

    data class Data(
        val input: List<String>,
        val output: List<String>
    )

    companion object {
        private val uniqueDigitSizes = listOf(2, 4, 3, 7)
    }

    private fun loadSegments(fileName: String): List<Data> {
        return loadDataFromFile(fileName)
            .map {
                val parts = it.split("|")
                val input = parts.first().split(" ")
                val output = parts[1].split(" ")
                Data(input, output)
            }
    }
}