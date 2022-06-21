package maps.lc.`438`
import kotlin.math.max

/**
 * Use end poninter to populate the map. If a value already exists that is a sign to start shrinking the window using
 * begin pointer. We need to continue shrinking the window until the criteria is met i.e there are no more dups in the
 * window, meaning map[s[end]] < 1 (look at the while loop, condition is inverse). I moved end++ at the "end" of the loop
 * as s[end] can be used to create the condition for while loop.
 * Time: O(N)
 * Space: O(N), if all the chars in given string are unique
 */
class LongestSubstringWithoutRepeatingChars_3 {
    fun lengthOfLongestSubstring(s: String): Int {
        val map = mutableMapOf<Char, Int>()
        var result = 0
        var begin = 0
        var end = 0

        while(end < s.length) {
            if(!map.containsKey(s[end])) {
                result = max(result, end - begin + 1)
            }
            map[s[end]] = map.getOrDefault(s[end], 0) + 1


            while(map[s[end]]!! > 1) {
                map[s[begin]] = map.getOrDefault(s[begin], 0) - 1
                if(map[s[begin]]!! <= 0) map.remove(s[begin])
                begin++
            }
            end++

        }

        return result
    }

    fun lengthOfLongestSubstringMyImpl(s: String): Int {
        val set = HashSet<Char>()
        var result = 0
        var i = 0
        for (j in s.indices) {
            if(!set.contains(s[j])) {
                set.add(s[j])
                result = result.coerceAtLeast(j - i + 1)
            } else {
                while (s[i] != s[j]) {
                    set.remove(s[i++])
                }
                i++
            }
        }
        return result
    }
}