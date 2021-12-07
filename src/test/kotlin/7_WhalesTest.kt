import org.junit.Test
import kotlin.test.assertEquals

internal class WhalesTest {

    private val whales = Whales()

    @Test
    fun `should calculate for short input`() {
        // when
        val result = whales.calculate(SHORT_INPUT_DATA)

        // then
        assertEquals(2, result.position)
        assertEquals(37, result.fuel)
    }

    @Test
    fun `should calculate for full input`() {
        // when
        val result = whales.calculate(INPUT_DATA)

        // then
        assertEquals(362, result.position)
        assertEquals(342534, result.fuel)
    }

    companion object {
        private const val SHORT_INPUT_DATA = "7_input_short.txt"
        private const val INPUT_DATA = "7_input.txt"
    }
}