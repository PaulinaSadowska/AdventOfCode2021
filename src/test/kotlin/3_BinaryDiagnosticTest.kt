import org.junit.Test
import kotlin.test.assertEquals

internal class BinaryDiagnosticTest {

    private val diagnostic = BinaryDiagnostic()

    @Test
    fun `should calculate for short input`() {
        // when
        val result = diagnostic.calculate(SHORT_INPUT_DATA)

        // then
        assertEquals(22, result.gamma) // 10110
        assertEquals(9, result.epsilon) // 01001
        assertEquals(198, result.powerConsumption)
    }

    @Test
    fun `should calculate for full input`() {
        // when
        val result = diagnostic.calculate(INPUT_DATA)

        // then
        assertEquals(3875, result.gamma)
        assertEquals(220, result.epsilon)
        assertEquals(852500, result.powerConsumption)
    }

    companion object {
        private const val SHORT_INPUT_DATA = "3_input_short.txt"
        private const val INPUT_DATA = "3_input.txt"
    }
}