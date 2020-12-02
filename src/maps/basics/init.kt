package maps.basics

/**
 *
 */
fun main() {
    // Empty Maps
    // 1. Mutable map
    val mutableMap = mutableMapOf<String, Int>()
    mutableMap["a"] = 1

    // 2. Immutable map
    val immutableMap = mapOf<String, Int>()
    // immutableMap["a"] = 1 NOT ALLOWED

    // Create a initialize with values
    val numbersMapMutable = mutableMapOf("one" to 1, "two" to 2, "three" to 3)
    val numbersMapImmutable = mapOf("one" to 1, "two" to 2, "three" to 3)

}