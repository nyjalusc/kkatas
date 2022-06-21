package slidingwindow.`567`

class PermutationInString {
    /**
     * Similar to 438 Find all Anagrams problem
     *
     * Fixed window Sliding window problem.
     * Window size is deduced from s1 input string. Prepare a char count map. HashMap impl fails for large tests
     * hence using an array[26] for char count map.
     *
     * Leading j pointer computes the current char by reducing its count from the map.
     * Once window size is hit check if all map elements are 0 if not then we have one or more unwanted chars in the window.
     * If we use fixed sliding window algo the unwanted chars will get eventually pushed out of the window. The condition
     * we check if if all map char counts are 0 and window size is s1.length then we have the answer.
     *
     * Time: O(l1 + l2)
     * Space: O(1)
     */
    fun checkInclusion(s1: String, s2: String): Boolean {
        if(s1.isEmpty()) return true
        if(s1.length > s2.length) return false

        val charMap = IntArray(26) { 0 }
        s1.forEach {
            charMap[it - 'a']++
        }

        var i = 0
        val windowSize = s1.length
        for (j in s2.indices) {
            charMap[s2[j] - 'a']--

            if (j - i + 1 == windowSize) {
                if(charMap.all { it == 0 }) return true

                charMap[s2[i] - 'a']++
                i++
            }
        }
        return false
    }

    /**
     * (Alternative)Variable window size problem:
     * You can optimize it further by shrinking the window until we find the first unwanted char in the window i.e
     * as you shrink the window find the first char s2[i] with count < 0.
     *
     * Time: O(l1 + l2)
     * Space: O(1)
     */
    fun checkInclusionOpt1(s1: String, s2: String): Boolean {
        if(s1.isEmpty()) return true
        if(s1.length > s2.length) return false

        val charMap = IntArray(26) { 0 }
        s1.forEach {
            charMap[it - 'a']++
        }

        var i = 0
        val windowSize = s1.length
        for (j in s2.indices) {
            charMap[s2[j] - 'a']--

            if (j - i + 1 == windowSize) {
                if(charMap.all { it == 0 }) return true

                while (charMap[s2[i] - 'a'] >= 0) {
                    charMap[s2[i] - 'a']++
                    i++
                }
                charMap[s2[i] - 'a']++
                i++
            }
        }
        return false
    }

    /**
     * Further optimize by using a charCounter to avoid repeatedly checking if all elements in the array are 0
     * Time: O(l1 + l2)
     * Space: O(1)
     */
    fun checkInclusionOpt2(s1: String, s2: String): Boolean {
        if(s1.isEmpty()) return true
        if(s1.length > s2.length) return false

        val charMap = IntArray(26) { 0 }
        s1.forEach {
            charMap[it - 'a']++
        }

        var i = 0
        val windowSize = s1.length
        var count = charMap.count { it > 0 }
        for (j in s2.indices) {
            charMap[s2[j] - 'a']--

            if(charMap[s2[j] - 'a'] == 0) count--

            if (j - i + 1 == windowSize) {
                if(count == 0) return true

                if(charMap[s2[i] - 'a'] == 0) count++
                charMap[s2[i] - 'a']++
                i++
            }
        }
        return false
    }
}