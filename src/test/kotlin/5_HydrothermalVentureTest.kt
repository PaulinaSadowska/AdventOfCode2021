import org.junit.Test
import kotlin.test.assertEquals

class HydrothermalVentureTest {

    private val hydro = HydrothermalVenture()

    @Test
    fun `should calculate for short input`() {
        // when
        val result = hydro.calculate(SHORT_INPUT_DATA)

        // then
        assertEquals(5, result)
    }

    @Test
    fun `should calculate for full input`() {
        // when
        val result = hydro.calculate(INPUT_DATA)

        // then
        assertEquals(7674, result)
    }

    @Test
    fun `should calculate for short input (part 2)`() {
        // when
        val result = hydro.calculate(SHORT_INPUT_DATA, diagonal = true)

        // then
        assertEquals(12, result)
    }

    @Test
    fun `should calculate for full input (part 2)`() {
        // when
        val result = hydro.calculate(INPUT_DATA, diagonal = true)

        // then
        assertEquals(20898, result)
    }

    companion object {
        private const val SHORT_INPUT_DATA = "5_input_short.txt"
        private const val INPUT_DATA = "5_input.txt"
    }
}
