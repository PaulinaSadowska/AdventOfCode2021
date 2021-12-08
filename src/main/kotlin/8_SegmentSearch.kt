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
        val numbersMap = mutableMapOf<Int, String>()
        val fives = mutableListOf<String>()
        val sixes = mutableListOf<String>()
        data.input.forEach {
            when (it.length) {
                2 -> numbersMap[1] = it
                3 -> numbersMap[7] = it
                4 -> numbersMap[4] = it
                7 -> numbersMap[8] = it
                5 -> if (!fives.contains(it)) {
                    fives.add(it)
                }
                6 -> if (!sixes.contains(it)) {
                    sixes.add(it)
                }
                // 2, 3, 5 0, 6, 9
            }
        }

        // 9 ma to co 4 (dlugosc 6)
        numbersMap[9] = sixes.find { it.containsAllOf(numbersMap[4]!!) }!!
        sixes.remove(numbersMap[9])

        // 0 ma to co 1 (dlugosc 6)
        numbersMap[0] = sixes.find { it.containsAllOf(numbersMap[1]!!) }!!
        sixes.remove(numbersMap[0])

        // 6 nie pasuje do niczego i zostaje jako ostatnie
        numbersMap[6] = sixes.first()

        // 5 ma to co 6 (dlugosc 5)
        numbersMap[5] = fives.find { numbersMap[6]!!.containsAllOf(it) }!!
        fives.remove(numbersMap[5])

        // 3 ma to co 1 (dlugosc 5)
        numbersMap[3] = fives.find { it.containsAllOf(numbersMap[1]!!) }!!
        fives.remove(numbersMap[3])

        // 2 nie pasuje do niczego i zostaje jako ostatnie
        numbersMap[2] = fives.first()

        val invertedMap = numbersMap.entries.associate { it.value.alphabetized() to it.key }
        return data.output.map {
            invertedMap[it.alphabetized()]!!
        }.joinToString("").toInt()
    }

    private fun String.alphabetized() = String(toCharArray().apply { sort() })

    private fun String.containsAllOf(test: String): Boolean {
        return test.toCharArray().fold(true) { acc, curr ->
            acc && contains(curr)
        }
    }

    /*
      0:      1:      2:      3:      4:
 aaaa    ....    aaaa    aaaa    ....
b    c  .    c  .    c  .    c  b    c
b    c  .    c  .    c  .    c  b    c
 ....    ....    dddd    dddd    dddd
e    f  .    f  e    .  .    f  .    f
e    f  .    f  e    .  .    f  .    f
 gggg    ....    gggg    gggg    ....

  5:      6:      7:      8:      9:
 aaaa    aaaa    aaaa    aaaa    aaaa
b    .  b    .  .    c  b    c  b    c
b    .  b    .  .    c  b    c  b    c
 dddd    dddd    ....    dddd    dddd
.    f  e    f  .    f  e    f  .    f
.    f  e    f  .    f  e    f  .    f
 gggg    gggg    ....    gggg    gggg
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
                val output = parts[1].split(" ").filter { it.isNotBlank() }
                Data(input, output)
            }
    }
}
