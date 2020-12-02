package maps.lc.`242`

/**
 * https://leetcode.com/problems/valid-anagram/
 */
class ValidAnagram {
    fun isAnagram(s: String, t: String): Boolean {
        if (s.length != t.length) return false

        val map = mutableMapOf<Char, Int>()
        t.forEach {
            map[it] = map.getOrDefault(it, 0) + 1
        }

        s.forEach{
            map[it] = map.getOrDefault(it, 0) - 1
        }

        return map.values.all{ it == 0 }
    }

    fun isAnagramFromUnicodeChars(s: String, t: String): Boolean {
        if (s.length != t.length) return false
        val map1 = populateMap(s)
        val map2 = populateMap(t)
        return map1 == map2
    }

    /**
     * Unicode chars are a superset of regular US-Ascii charset. In java (UTF-16) emoji can be represented as a supplementary pair.
     * Also called as surrogate pair (High Surrogate and Low surrogate). At this point we cannot use a map of Char -> Int, instead
     * we convert the char to Int. So the map is Int -> Int (count). We use the check char.isHighSurrogate() to see if its
     * a special Unicode char, if yes we check the next char to see if its the lower surrogate pair, if yes, we have to
     * treat both of these chars as one unicode char, manage the pointer correctly in that case.
     */
    private fun populateMap(s: String): Map<Int, Int> {
        val map = mutableMapOf<Int, Int>()
        var i = 0
        while (i < s.length) {
            val c = s[i]
            var charInt = c.toInt()
            if (c.isHighSurrogate() && i < s.lastIndex) {
                val potentialSurrogate = s[i + 1]
                if (potentialSurrogate.isLowSurrogate()) {
                    charInt = Character.toCodePoint(potentialSurrogate, c)
                    i++
                }
            }
            map[charInt] = map.getOrDefault(charInt, 0) + 1
            i++
        }
        return map
    }
}

fun main() {
    val solution = ValidAnagram()
    solution.isAnagramFromUnicodeChars("abc", "def")
}