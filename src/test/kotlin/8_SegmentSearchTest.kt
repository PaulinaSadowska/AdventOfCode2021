import org.junit.Assert.*
import org.junit.Test

class SegmentSearchTest{

    private val search = SegmentSearch()

    @Test
    fun `should calculate for short input`() {
        // when
        val result = search.calculateUniqueSegmentsInOutput(SHORT_INPUT_DATA)

        // then
        assertEquals(26, result)
    }

    @Test
    fun `should calculate for full input`() {
        // when
        val result = search.calculateUniqueSegmentsInOutput(INPUT_DATA)

        // then
        assertEquals(261, result)
    }

    @Test
    fun `should calculate for short input (part 2)`() {
        // when
        val result = search.calculateSumOfOutput(SHORT_INPUT_DATA)

        // then
        assertEquals(61229, result)
    }

    @Test
    fun `should calculate for full input (part 2)`() {
        // when
        val result = search.calculateSumOfOutput(INPUT_DATA)

        // then
        assertEquals(-1, result)
    }

    companion object {
        private const val SHORT_INPUT_DATA = "8_input_short.txt"
        private const val INPUT_DATA = "8_input.txt"
    }
}