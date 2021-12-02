class Dive {

    fun calculate(fileName: String): DiveResult {
        val data = loadDataFromFile(fileName).map {
            val values = it.split(" ")
            DiveData(direction = values[0], value = values[1].toInt())
        }
        return dive(data)
    }

    fun calculate2(fileName: String): DiveResult {
        val data = loadDataFromFile(fileName).map {
            val values = it.split(" ")
            DiveData(direction = values[0], value = values[1].toInt())
        }
        return dive2(data)
    }

    private fun dive(data: List<DiveData>): DiveResult {
        val diveResult = DiveResult()
        data.forEach {
            when (it.direction) {
                "forward" -> diveResult.horizontal += it.value
                "down" -> diveResult.depth += it.value
                "up" -> diveResult.depth -= it.value
            }
        }
        return diveResult
    }

    private fun dive2(data: List<DiveData>): DiveResult {
        val diveResult = DiveResult(0, 0)
        data.forEach {
            when (it.direction) {
                "forward" -> {
                    diveResult.horizontal += it.value
                    diveResult.depth += it.value * diveResult.aim
                }
                "down" -> {
                    //diveResult.depth += it.value
                    diveResult.aim += it.value
                }
                "up" -> {
                    //diveResult.depth -= it.value
                    diveResult.aim -= it.value
                }
            }
        }
        return diveResult
    }

    data class DiveData(
        val direction: String,
        val value: Int
    )

    data class DiveResult(
        var horizontal: Int = 0,
        var depth: Int = 0,
        var aim: Int = 0
    ) {
        fun finalResult() = horizontal * depth
    }
}

