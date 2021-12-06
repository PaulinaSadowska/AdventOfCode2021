import org.junit.Test
import kotlin.test.assertEquals

internal class LanternfishTest {

    private val lanternfish = Lanternfish()

    @Test
    fun `should calculate for short input (80 days)`() {
        // when
        val result = lanternfish.calculate(SHORT_INPUT_DATA)

        // then
        assertEquals(5934, result)
    }

    @Test
    fun `should calculate for short input (1 days)`() {
        // when
        val result = lanternfish.calculate(SHORT_INPUT_DATA, numOfDays = 1)

        // then
        assertEquals(5, result)
    }

    @Test
    fun `should calculate for short input (2 days)`() {
        // when
        val result = lanternfish.calculate(SHORT_INPUT_DATA, numOfDays = 2)

        // then
        assertEquals(6, result)
    }

    @Test
    fun `should calculate for short input (3 days)`() {
        // when
        val result = lanternfish.calculate(SHORT_INPUT_DATA, numOfDays = 3)

        // then
        assertEquals(7, result)
    }

    @Test
    fun `should calculate for short input (4 days)`() {
        // when
        val result = lanternfish.calculate(SHORT_INPUT_DATA, numOfDays = 4)

        // then
        assertEquals(9, result)
    }

    @Test
    fun `should calculate for short input (5 days)`() {
        // when
        val result = lanternfish.calculate(SHORT_INPUT_DATA, numOfDays = 5)

        // then
        assertEquals(10, result)
    }

    @Test
    fun `should calculate for short input (6 days)`() {
        // when
        val result = lanternfish.calculate(SHORT_INPUT_DATA, numOfDays = 6)

        // then
        assertEquals(10, result)
    }

    @Test
    fun `should calculate for short input (18 days)`() {
        // when
        val result = lanternfish.calculate(SHORT_INPUT_DATA, numOfDays = 18)

        // then
        assertEquals(26, result)
    }

    @Test
    fun `should calculate for full input`() {
        // when
        val result = lanternfish.calculate(INPUT_DATA)

        // then
        assertEquals(356190, result)
    }

    @Test
    fun `should calculate for short input (part 2)`() {
        // when
        val result = lanternfish.calculate(SHORT_INPUT_DATA, numOfDays = 256)

        // then
        assertEquals(26984457539, result)
    }

    @Test
    fun `should calculate for full input (part 2)`() {
        // when
        val result = lanternfish.calculate(INPUT_DATA, numOfDays = 256)

        // then
        assertEquals(1617359101538, result)
    }

    companion object {
        private const val SHORT_INPUT_DATA = "6_input_short.txt"
        private const val INPUT_DATA = "6_input.txt"
    }
}