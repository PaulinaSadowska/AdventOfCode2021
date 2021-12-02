

fun main() {
    println("Hello World!")

    val data = loadDataFromFile("1_input.txt").mapNotNull { it.toIntOrNull() }
    println("size: "+ data.size)

    var increased = 0
    data.forEachIndexed { index, element ->
        if(index > 0){
            if(element > data[index-1]){
                increased++
            }
        }
    }

    println("result: $increased")
}
