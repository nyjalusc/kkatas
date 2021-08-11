package strings.cw

import kotlin.test.assertEquals

/**
 * 7kyu https://www.codewars.com/kata/57cf50a7eca2603de0000090/train/kotlin
 *
 * Move every letter in the provided string forward 10 letters through the alphabet.
 * If it goes past 'z', start again at 'a'.
 * Input will be a string with length > 0.
 */
object Move10 {
    fun moveTen(s: String): String {
        return s.map {
            var move10 = it + 10
            if (move10 > 'z') {
                move10 = 'a' + (move10 - 'z') - 1
            }
            move10
        }.joinToString("")
    }
}

fun main() {
    println(Move10.moveTen("aa"))
    assertEquals("docdmkco", Move10.moveTen("testcase"))
}