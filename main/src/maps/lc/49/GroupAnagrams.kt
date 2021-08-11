package maps.lc.`49`

/**
 * https://leetcode.com/problems/group-anagrams/
 * Sort the given string to create a key for the hashmap
 *
 * Time: O(N * K log k), where N is the total number of words, and each word has K characters.
 * It takes K log K time to sort chars of one word and there are N words.
 *
 * Space: O(N * K)
 */
class GroupAnagrams {
    fun groupAnagrams(strs: Array<String>): List<List<String>> {
        val map = mutableMapOf<String, List<String>>()

        strs.forEach {
            // sorted() returns a list
            val key = it.toCharArray().sorted().joinToString("")
            map[key] = map.getOrDefault(key, mutableListOf()) + it
        }

        return map.values.toList()
    }
}