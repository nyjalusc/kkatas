package maps.lc.`49`

/**
 * https://leetcode.com/problems/group-shifted-strings/
 *
 * This is my own solution, if its difficult to follow checkout
 * https://leetcode.com/problems/group-shifted-strings/discuss/67442/My-Concise-JAVA-Solution
 *
 * We create a String key using the formula chars[j] - chars[j - 1]
 * Strings with same key belong to the same sequence which gets added to a map
 *
 * Time: O(N * K)
 * Space: O(N * K)
 */
class GroupShiftedStrings {
    fun groupStrings(strings: Array<String>): List<List<String>> {
        val map = mutableMapOf<String, List<String>>()

        strings.forEach { str ->
            val sb = StringBuilder()

            if(str.length <= 1) {
                // Strings of length one always belong to the same sequence
                // Special key for one length words, it doesn't have to be -1, it could be anything as long as its unique
                // Do not use 0 as the else block will generate "0" key for examples "aa" and "bb"
                sb.append("-1")
            } else {
                // We create a String key using the formula chars[j] - chars[j - 1]
                // Strings with same key belong to the same sequence
                val chars = str.toCharArray()
                for (j in 1 until chars.size) {
                    var diff = chars[j] - chars[j - 1]
                    // For strs "za" and "cb", key will be "-1" but we have already used -1 as a special key
                    // Adding 26 to bring the diff value between bounds 0..25
                    if (diff < 0) diff += 26
                    sb.append(diff)
                }
            }
            val key = sb.toString()
            map[key] = map.getOrDefault(key, mutableListOf()) + str
        }

        return map.values.toList()
    }
}