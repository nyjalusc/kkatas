package binarysearch.`33`

/**
 *
 */
class FindSmallestLetterGreaterThanTarget_744 {
    /**
     * Binary search using temp result variable
     * Time: O(log N)
     * Space: O(1)
     */
    fun nextGreatestLetter(letters: CharArray, target: Char): Char {
        var start = 0
        var end = letters.lastIndex

        var result: Char = Int.MAX_VALUE.toChar()
        while(start <= end) {
            val mid = start + (end - start) / 2
            result = if(letters[mid] > target && letters[mid] < result) letters[mid] else result
            if(letters[mid] <= target) {
                start = mid + 1
            } else {
                end = mid - 1
            }
        }

        return if(start == letters.size) letters[0] else result
    }

    /**
     * Modified Binary search without temp variable.
     * To make the start pointer overflow we initialize end pointer to letters.size instead of letters.lastIndex.
     * This allows us to use the modulo operation at the end to adjust start pointer back into the valid range.
     */
    fun nextGreatestLetter2(letters: CharArray, target: Char): Char {
        var start = 0
        var end = letters.size // IMPORTANT: Required to make start overflow so that we can do modulo op to retrieve the result

        while(start < end) {
            val mid = start + (end - start) / 2
            if(letters[mid] <= target) {
                start = mid + 1
            } else {
                end = mid
            }
        }

        return letters[start % letters.size]
    }
}