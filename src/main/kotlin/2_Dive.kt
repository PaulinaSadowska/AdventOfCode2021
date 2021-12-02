class Dive {

    fun calculate(fileName: String, submarine: SubmarineController): SubmarinePosition {
        val data = loadDataFromFile(fileName).map {
            val values = it.split(" ")
            DiveCommand(direction = values[0], value = values[1].toInt())
        }
        return dive(data, submarine)
    }

    private fun dive(data: List<DiveCommand>, submarine: SubmarineController): SubmarinePosition {
        data.forEach {
            when (it.direction) {
                "forward" -> submarine.forward(it.value)
                "down" -> submarine.down(it.value)
                "up" -> submarine.up(it.value)
            }
        }
        return submarine.getPosition()
    }


    class SubmarineControllerWithoutAim : SubmarineController {

        private val position = SubmarinePosition()
        override fun getPosition() = position

        override fun forward(value: Int) {
            position.horizontal += value
        }

        override fun down(value: Int) {
            position.depth += value
        }

        override fun up(value: Int) {
            position.depth -= value
        }
    }

    class SubmarineControllerWithAim : SubmarineController {

        private val position = SubmarinePosition()
        override fun getPosition() = position

        override fun forward(value: Int) {
            position.horizontal += value
            position.depth += value * position.aim
        }

        override fun down(value: Int) {
            position.aim += value
        }

        override fun up(value: Int) {
            position.aim -= value
        }
    }

    interface SubmarineController {
        fun forward(value: Int)
        fun down(value: Int)
        fun up(value: Int)
        fun getPosition(): SubmarinePosition
    }

    data class DiveCommand(
        val direction: String,
        val value: Int
    )

    data class SubmarinePosition(
        var horizontal: Int = 0,
        var depth: Int = 0,
        var aim: Int = 0,
    ) {
        fun finalResult() = horizontal * depth
    }
}

