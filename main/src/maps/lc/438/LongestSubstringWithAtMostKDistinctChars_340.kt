package maps.lc.`438`

import kotlin.math.max
/**
 * Same as https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters/
 * Just update with the value of k
 */
class LongestSubstringWithAtMostKDistinctChars_340 {
        fun lengthOfLongestSubstringKDistinct(s: String, k: Int): Int {
            val map = mutableMapOf<Char, Int>()
            var end = 0
            var begin = 0
            var result = 0

            while(end < s.length) {
                map[s[end]] = map.getOrDefault(s[end], 0) + 1
                end++

                if (map.size <= k) result = max(end - begin, result)

                while(map.size > k) {
                    map[s[begin]] = map.getOrDefault(s[begin], 0) - 1
                    if(map[s[begin]]!! <= 0) map.remove(s[begin])
                    begin++
                }
            }

            return result
        }
}