import java.io.File
import java.io.InputStream

fun loadDataFromFile(fileName: String): List<String> {
    val inputStream: InputStream = File("src/main/resources/$fileName").inputStream()
    return inputStream.bufferedReader().use { it.readText() }.split("\n")
}