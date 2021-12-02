import org.junit.Test
import kotlin.test.assertEquals

internal class DiveTest {

    private val dive = Dive()

    @Test
    fun `should calculate for short input`() {
        // when
        val result = dive.calculate(SHORT_INPUT_DATA)

        // then
        assertEquals(15, result.horizontal)
        assertEquals(10, result.depth)
        assertEquals(150, result.finalResult())
    }

    @Test
    fun `should calculate for full input`() {
        // when
        val result = dive.calculate(INPUT_DATA)

        // then
        assertEquals(1817, result.horizontal)
        assertEquals(1072, result.depth)
        assertEquals(1947824, result.finalResult())
    }

    companion object {
        private const val SHORT_INPUT_DATA = "2_input_short.txt"
        private const val INPUT_DATA = "2_input.txt"
    }
}