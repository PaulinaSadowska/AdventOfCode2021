class SonarSweep {

    fun calculate(fileName: String, window: Int = 1): Int {
        val data = loadDataFromFile(fileName).mapNotNull { it.toIntOrNull() }
        return sonarSweep(data, window)
    }

    private fun sonarSweep(data: List<Int>, window: Int): Int {
        return data
            .mapIndexed { index, current ->
                val previous = data.getOrElse(index - window) { Int.MAX_VALUE }
                if (current > previous) 1 else 0
            }.reduce { acc, curr -> acc + curr }
    }
}

