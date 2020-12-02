package maps.lc.`438`

import kotlin.math.max

/**
 * Unlike find all anagrams where we initialized the map with a given substring,
 * here we populate it with end pointer. We keep updating the result if we find a better
 * result. If our window has more than 2 chars we use begin pointer to shrink the window.
 * The begin pointer is used to remove the chars from map, we need to repeat this process
 * until we meet the requirement ie. map.size <= 2
 *
 * Time: O(N)
 * Space: O(1), at most hashmap will have 3 keys hence constant space
 */
class LongestSubstringWithAtMostTwoDistinctChars_159 {
    fun lengthOfLongestSubstringTwoDistinct(s: String): Int {
            val map = mutableMapOf<Char, Int>()
            var end = 0
            var begin = 0
            var result = 0

            while(end < s.length) {
                map[s[end]] = map.getOrDefault(s[end], 0) + 1
                end++

                // Bug in first attempt: I updated result = end - begin so I lost the correct result
                if (map.size <= 2) result = max(end - begin, result)

                while(map.size > 2) {
                    map[s[begin]] = map.getOrDefault(s[begin], 0) - 1
                    if(map[s[begin]]!! <= 0) map.remove(s[begin])
                    begin++
                }
            }

            return result
        }

        // "b aacccacaca bcaabbbbc"
}