package maps.lc.`438`

/**
 * Mistake: There can be repeating chars in the given string t and also the window. I was decrementing charCount for
 * every s[end] that was in t and incrementing charCount for every s[begin] that was in t. This is a bug we should
 * be updating charCount only when the map key == 0. It can be less than zero if there are multiple chars in the same
 * window, in that case we do update the value of char in the map, it will be negative but we do not update charCount.
 * Similarly while adding chars back into the map it should be updated only once the value of key > 0.
 */
class MinimumWindowSubstring_76 {
    fun minWindow(s: String, t: String): String {
        val map = mutableMapOf<Char, Int>()
        var begin = 0
        var end = 0
        var result: String? = null

        // populate the map
        t.forEach {
            map[it] = map.getOrDefault(it, 0) + 1
        }

        var charCount = map.size

        while(end < s.length) {
            if(map.containsKey(s[end])) {
                map[s[end]] = map[s[end]]!! - 1
                if(map[s[end]]!! == 0) charCount-- ///////////////////// A
            }

            end++
            while(charCount == 0) {                  /////////////////// B I had charCount <= 0
                val potentialResult = s.substring(begin, end)
                if(result == null || potentialResult.length < result.length) {
                    result = potentialResult
                }
                if(map.containsKey(s[begin])) {
                    if(map[s[begin]]!! == 0) charCount++
                    map[s[begin]] = map.getOrDefault(s[begin], 0) + 1
                }
                begin++
            }
        }

        return result ?: ""
    }
}