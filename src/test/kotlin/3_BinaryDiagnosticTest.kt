import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

internal class BinaryDiagnosticTest {

    private val diagnostic = BinaryDiagnostic()

    @Test
    fun `should calculate for short input`() {
        // when
        val result = diagnostic.calculatePowerConsumption(SHORT_INPUT_DATA)

        // then
        assertEquals(22, result.gamma) // 10110
        assertEquals(9, result.epsilon) // 01001
        assertEquals(198, result.result)
    }

    @Test
    fun `should calculate for full input`() {
        // when
        val result = diagnostic.calculatePowerConsumption(INPUT_DATA)

        // then
        assertEquals(3875, result.gamma)
        assertEquals(220, result.epsilon)
        assertEquals(852500, result.result)
    }

    @Test
    fun `should calculate for short input (part 2)`() {
        // when
        val result = diagnostic.calculateLifeSupport(SHORT_INPUT_DATA)

        // then
        assertEquals(23, result.oxygen)
        assertEquals(10, result.co2)
        assertEquals(230, result.result)
    }

    @Test
    fun `should calculate for short 2 input (part 2)`() {
        // when
        val result = diagnostic.calculateLifeSupport(SHORT_INPUT_DATA)

        // then
        assertEquals(23, result.oxygen)
        assertEquals(10, result.co2)
        assertEquals(230, result.result)
    }


    @Test
    fun `should calculate for full input (part 2)`() {
        // when
        val result = diagnostic.calculateLifeSupport(INPUT_DATA)

        // then
        assertEquals(2235, result.oxygen)
        assertEquals(451, result.co2)
        assertEquals(1007985, result.result)
    }

    companion object {
        private const val SHORT_INPUT_DATA = "3_input_short.txt"
        private const val INPUT_DATA = "3_input.txt"
    }
}