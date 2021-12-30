package arrays.lc.`128`

import kotlin.math.max

class LongestConsecutiveSequence {
    /**
     * Step 1: Put all the elements in a set for fast lookup
     * Step 2: Iterate over elements in set to avoid dups
     * Step 3: Check if an element is the first element of a subsequence, by checking if num - 1 is not in the set
     * Step 4: Generate next element from subsequence, check if in set and maintain a counter
     * Time: O(N), looks like N^2 because of the while loop but in most of the cases there won't be too many head of subsequnces
     * Space: O(N), input set
     */
    fun longestConsecutive(nums: IntArray): Int {
        if(nums.isEmpty()) return 0
        val inputSet = HashSet<Int>()
        for (num in nums) inputSet.add(num)

        var maxLength = 0
        // this is done to avoid dups and we don't care about the order since the input is unsorted
        for (num in inputSet) {
            // If num - 1 doesn't belong to the set then it may be the first element in a subsequence
            // We do not know if it is the longest subsequence but we know it is the head of the subsequnce
            if(!inputSet.contains(num - 1)) {
                var currentNum = num
                var lengthFromCurrent = 0
                while(inputSet.contains(currentNum)) {
                    lengthFromCurrent++
                    currentNum++
                }
                maxLength = max(maxLength, lengthFromCurrent)
            }
        }
        return maxLength
    }
}

