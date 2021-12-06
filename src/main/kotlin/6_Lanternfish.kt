class Lanternfish {

    fun calculate(fileName: String, numOfDays: Int = 80): Long {
        val data = loadData(fileName)
        return numOfFishAfterDays(data, numOfDays)
    }

    private fun numOfFishAfterDays(data: List<Int>, numOfDays: Int): Long {
        val generations = mutableMapOf<Int, Long>()
        data.forEach {
            generations[it] = generations.getOrDefault(it, 0) + 1
        }
        (1..numOfDays).forEach { day ->
            val newCount = generations.getOrDefault(key = 0, defaultValue = 0)
            generations[9] = newCount
            generations[7] = generations.getOrDefault(7, 0) + newCount
            (0..9).forEach {
                val newIndex = it + 1
                generations[it] = generations.getOrDefault(newIndex, defaultValue = 0)
            }
        }

        return generations.values.sum()
    }

    private fun loadData(fileName: String): List<Int> {
        return loadDataFromFile(fileName)
            .first()
            .split(",")
            .mapNotNull { it.toIntOrNull() }
    }
}