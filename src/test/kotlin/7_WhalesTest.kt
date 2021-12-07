import org.junit.Test
import kotlin.test.assertEquals

internal class WhalesTest {

    private val whales = Whales()

    @Test
    fun `should calculate for short input`() {
        // when
        val result = whales.calculate(SHORT_INPUT_DATA, Whales.Part.FIRST)

        // then
        assertEquals(2, result.position)
        assertEquals(37, result.fuel)
    }

    @Test
    fun `should calculate for full input`() {
        // when
        val result = whales.calculate(INPUT_DATA, Whales.Part.FIRST)

        // then
        assertEquals(362, result.position)
        assertEquals(342534, result.fuel)
    }

    @Test
    fun `should calculate for short input (part 2)`() {
        // when
        val result = whales.calculate(SHORT_INPUT_DATA, Whales.Part.SECOND)

        // then
        assertEquals(5, result.position)
        assertEquals(168, result.fuel)
    }

    @Test
    fun `should calculate for full input (part 2)`() {
        // when
        val result = whales.calculate(INPUT_DATA, Whales.Part.SECOND)

        // then
        assertEquals(474, result.position)
        assertEquals(94004208, result.fuel)
    }

    companion object {
        private const val SHORT_INPUT_DATA = "7_input_short.txt"
        private const val INPUT_DATA = "7_input.txt"
    }
}