import org.junit.Test
import kotlin.test.assertEquals

internal class SonarSweepTest {

    @Test
    fun `should calculate correct answer for short input set`() {
        // given
        val data = loadDataFromFile("1_input_short.txt").mapNotNull { it.toIntOrNull() }

        // when
        val result = sonarSweep(data)

        // then
        assertEquals(expected = 7, result)
    }

    @Test
    fun `should calculate correct answer for full input set`() {
        // given
        val data = loadDataFromFile("1_input.txt").mapNotNull { it.toIntOrNull() }

        // when
        val result = sonarSweep(data)

        // then
        assertEquals(expected = 1553, result)
    }

}