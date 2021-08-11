package strings.cw

import kotlin.test.assertEquals

/**
 *
 */
fun wave(str: String): List<String> {
    val result = mutableListOf<String>()
    var lastIndex = -1
    var shouldIgnoreRest = false
    repeat(str.length) {
        val partial = str.mapIndexed { i, it ->
            if (it.isLetter() && !shouldIgnoreRest && i > lastIndex) {
                val capitalize = it.toString().capitalize()
                lastIndex = i
                shouldIgnoreRest = true
                capitalize
            } else {
                it
            }
        }.joinToString("")

        if (shouldIgnoreRest) result.add(partial)
        shouldIgnoreRest = false
    }
    return result
}

fun main() {
    assertEquals(listOf("A       b    ", "a       B    "), wave("a       b    "))
}