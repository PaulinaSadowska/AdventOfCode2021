class Dive(private val submarineController: SubmarineController) {

    fun calculate(fileName: String): SubmarinePosition {
        val data = loadDataFromFile(fileName).map {
            val values = it.split(" ")
            DiveCommand(direction = values[0], value = values[1].toInt())
        }

        return data.fold(SubmarinePosition()) { acc, curr ->
            submarineController.dive(position = acc, command = curr)
        }
    }

    class SubmarineControllerWithoutAim : SubmarineController {

        override fun dive(position: SubmarinePosition, command: DiveCommand): SubmarinePosition {
            when (command.direction) {
                FORWARD -> position.horizontal += command.value
                DOWN -> position.depth += command.value
                UP -> position.depth -= command.value
            }
            return position
        }
    }

    class SubmarineControllerWithAim : SubmarineController {

        override fun dive(position: SubmarinePosition, command: DiveCommand): SubmarinePosition {
            when (command.direction) {
                FORWARD -> {
                    position.horizontal += command.value
                    position.depth += command.value * position.aim
                }
                DOWN -> position.aim += command.value
                UP -> position.aim -= command.value
            }
            return position
        }
    }

    interface SubmarineController {
        fun dive(position: SubmarinePosition, command: DiveCommand): SubmarinePosition
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

    companion object {
        private const val FORWARD = "forward"
        private const val UP = "up"
        private const val DOWN = "down"
    }
}

