package maps.lc.`438`

class FindAllAnagramsInAString {
    /**
     * Sliding Window + map
     * Template for such problems
     * https://leetcode.com/problems/find-all-anagrams-in-a-string/discuss/92007/Sliding-Window-algorithm-template-to-solve-all-the-Leetcode-substring-search-problem.
     *
     * Tricky part, how to formulate success criteria, and handle pointer manipulations every iteration. End pointer leads and points
     * to unprocessed char when begin starts processing chars from the start of the string and slides the window.
     *
     * Time: O(Ns + Np): Size of input strings
     * Space: O(1) Max of 26 keys can exist in the map. Hence constant space
     */
    fun findAnagrams(s: String, p: String): List<Int> {
        // Populate map with substring char -> count
        val map = mutableMapOf<Char, Int>()

        map.values.all { it == 0 }
        p.forEach{
            map[it] = map.getOrDefault(it, 0) + 1
        }

        // Initialize the two pointers end and begin
        var end = 0
        var begin = 0

        // Since chars can repeat in the given substring
        // we use map.size and not p.size to check if we have already
        // encountered all the chars from substring
        var charCounter = map.size
        val result = mutableListOf<Int>()

        // Traverse from first char up to the end
        while (end < s.length) {
            // If s[end] is there in the map reduce its count, if it goes down to 0
            // we have visited all the occurrences of this char in the given string so decrement charCounter
            if (map.contains(s[end])) {
                map[s[end]] = map[s[end]]!! - 1
                if(map[s[end]] == 0) charCounter--
            }
            end++

            // I initially used if(charCounter) == 0 && end - begin = p.length, result.add(begin)
            // It would have worked for inputs such as s = "abcxyz" p = "abc" but failed if s = "axybca"
            // We got to move begin pointer and shrink the window, if we have encountered chars from p
            // we add it back to the map and adjust charCounter if required.
            while(charCounter == 0) {
                // Success condition: if charCounter == 0 && end - begin == p.length
                if(end - begin == p.length) result.add(begin)

                // Relevant chars that fall out of the window need to be added back to the map
                if(map.contains(s[begin])) {
                    if(map[s[begin]] == 0) charCounter++
                    map[s[begin]] = map.getOrDefault(s[begin], 0) + 1
                }

                // there could be other characters in the middle so we got to move begin pointer "axybc"
                // and also allow for sequential patters such as "abcab"
                begin++
            }
        }
        return result
    }
}

fun main() {
    val solution = FindAllAnagramsInAString()
    println(solution.findAnagrams("abcabc", "abc"))
}