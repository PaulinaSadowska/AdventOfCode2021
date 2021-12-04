import org.junit.Test
import kotlin.test.assertEquals

internal class GiantSquidBingoTest {


    private val bingo = GiantSquidBingo()

    @Test
    fun `should calculate for short input`() {
        // when
        val result = bingo.calculate(SHORT_INPUT_DATA)

        // then
        assertEquals(4512, result) // 188 * 24.
    }

    @Test
    fun `should calculate for full input`() {
        // when
        val result = bingo.calculate(INPUT_DATA)

        // then
        assertEquals(25410, result) // 726 * 35
    }

    companion object {
        private const val SHORT_INPUT_DATA = "4_input_short.txt"
        private const val INPUT_DATA = "4_input.txt"
    }
}