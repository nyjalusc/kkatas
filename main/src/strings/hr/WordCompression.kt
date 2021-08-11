package strings.hr

import java.lang.StringBuilder

/**
 *
 */


fun compressWord(word: String, k: Int): String {
    var i = 0
    val sb = StringBuilder(word)
    while (i < word.length) {
        // Check if two consecutive chars are the same
        if (i + 1 < sb.length && sb[i] == sb[i + 1]) {

            // Init two pointers and counter
            var start = i
            var end = i + 1
            var counter = k - 1

            // Delete char range from StringBuilder if we find consecutive k chars
            while (end < sb.length && sb[start] == sb[end++]) {
                counter--
                if (counter == 0) {
                    sb.delete(start, end)
                    i = -1
                    break
                }
            }
        }

        i++
    }
    return sb.toString()
}

fun compressWord2(word: String, k: Int): String {
    var w = word
    val chars = word.toHashSet()
    println(chars)
    for(i in chars) {
        val seq = i.toString().repeat(k)
        if(w.indexOf(seq) != -1) {
            w = word.replace(seq, "")
            w = compressWord2(w, k)
            break
        }
    }
    return w
}


fun main() {
    println(compressWord2("abbcccb", 3))
}