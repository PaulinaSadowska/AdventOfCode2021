import org.junit.Test
import kotlin.test.assertEquals

internal class SonarSweepTest {

    private val sonarSweep = SonarSweep()

    @Test
    fun `should calculate correct answer for short input set`() {
        // when
        val result = sonarSweep.calculate(SHORT_INPUT_DATA)

        // then
        assertEquals(expected = 7, result)
    }

    @Test
    fun `should calculate correct answer for full input set`() {
        // when
        val result = sonarSweep.calculate(INPUT_DATA)

        // then
        assertEquals(expected = 1553, result)
    }

    @Test
    fun `should calculate correct answer for short input set (part 2)`() {
        // when
        val result = sonarSweep.calculate(SHORT_INPUT_DATA, window = 3)

        // then
        assertEquals(expected = 5, result)
    }

    @Test
    fun `should calculate correct answer for full input set (part 2)`() {
        // when
        val result = sonarSweep.calculate(INPUT_DATA, window = 3)

        // then
        assertEquals(expected = 1597, result)
    }

    companion object {
        private const val SHORT_INPUT_DATA = "1_input_short.txt"
        private const val INPUT_DATA = "1_input.txt"
    }
}