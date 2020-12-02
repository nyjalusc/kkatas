package maps.basics

/**
 *
 */
fun main() {
    val first = mutableMapOf(1 to mutableListOf(10), 2 to mutableListOf(5, 10), 3 to mutableListOf(5, 10, 15))
    val second = mutableMapOf(1 to mutableListOf(20), 2 to mutableListOf(15, 20), 5 to mutableListOf(15, 20, 25))

    val result = (first.keys + second.keys)
        .associateWith { (first.getOrDefault(it, mutableListOf()) + second.getOrDefault(it, mutableListOf())).sorted() }
    println(result)
}