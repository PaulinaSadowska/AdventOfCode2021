import org.junit.Assert.*
import org.junit.Test

class SmokeBasinTest {

    private val smokeBasin = SmokeBasin()

    @Test
    fun `should calculate for short input`() {
        // when
        val result = smokeBasin.sumLowPoints(SHORT_INPUT_DATA)

        // then
        assertEquals(15, result)
    }

    @Test
    fun `should calculate for full input`() {
        // when
        val result = smokeBasin.sumLowPoints(INPUT_DATA)

        // then
        assertEquals(506, result)
    }

    @Test
    fun `should calculate for short input (part 2)`() {
        // when
        val result = smokeBasin.measureBiggestBasins(SHORT_INPUT_DATA)

        // then
        assertEquals(1137, result)
    }

    @Test
    fun `should calculate for full input (part 2)`() {
        // when
        val result = smokeBasin.measureBiggestBasins(INPUT_DATA)

        // then
        assertEquals(-1, result)
    }

    companion object {
        private const val SHORT_INPUT_DATA = "9_input_short.txt"
        private const val INPUT_DATA = "9_input.txt"
    }
}