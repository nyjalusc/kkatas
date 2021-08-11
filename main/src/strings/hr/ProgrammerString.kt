package strings.hr

/**
 *
 */
object ProgrammerString {
    fun programmerStrings(s: String): Int {
        // Create maps to keep track of valid 'programmer' substrings at the start and end of given string
        val leftMap = mutableMapOf('p' to 1, 'r' to 3, 'o' to 1, 'g' to 1, 'a' to 1, 'm' to 2, 'e' to 1)
        val rightMap = leftMap.toMutableMap() // Create a copy

        var i = 0
        var j = s.lastIndex
        while ((leftMap.values.sum() > 0 || rightMap.values.sum() > 0)) {
            if (i >= s.length && j < 0 || j < i) return -1

            if (leftMap.getOrDefault(s[i], 0) > 0) {
                leftMap[s[i]] = leftMap[s[i]]!! - 1
            }

            if (rightMap.getOrDefault(s[j], 0) > 0) {
                rightMap[s[j]] = rightMap[s[j]]!! - 1
            }
            if (leftMap.values.sum() > 0) i++
            if (rightMap.values.sum() > 0) j--
        }

        return j - i - 1
    }
}

fun main(args: Array<String>) {
    val s = readLine()!!

    val result = ProgrammerString.programmerStrings(s)

    println(result)
}
