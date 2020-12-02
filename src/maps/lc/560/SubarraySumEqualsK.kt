package maps.lc.`560`

/**
 * https://leetcode.com/problems/subarray-sum-equals-k/
 */
class SubarraySumEqualsK {
    /**
     * Can't use sliding window here because there can be negative integers here.
     * We generate all N subarrays starting from i to lastIndex and compute a running sum.
     * Any time running sum equals k we increment our result.
     *
     * Time: O(N*N) due to our requirement for generating all possible subarrays
     * Space: O(1)
     */
    fun subarraySumTwoPointer(nums: IntArray, k: Int): Int {
        var count = 0
        for(i in nums.indices) {
            var sum = 0
            for (j in i..nums.lastIndex) {
                sum += nums[j]
                if (sum == k) count++
            }
        }
        return count
    }

    /**
     * Algo: If we are looking for sum[i, j] such that it equals to k then we can create the following formula
     * sum[0, i - 1] + sum[i, j] = sum[0, j], where j > 1
     * By keeping a running sum what we have in fact is sum[0, j], using this we can say
     * sum[0, j] - k = sum[0, i - 1], this is true if sum[i, j] == k
     * So during our computation we check this assumption, we assume we have sum[i, j] == k and we verify
     * it by looking for sum[0, i - 1], if we find it we add it to the result. There could be multiple times that
     * we have seen sum[0, i - 1], all the "array values" starting from index i to j(current) will sum up to k.
     * We use a map to store these oldSum keys and its count.
     *
     * If you have an array of running sum, distance between any two indices is the sum of elements between them
     * eg. arr [5, 10, -5] will have sum arr [5, 15, 10], distance between index 0 and 2 is 10 - 5 = 5, which means
     * this is the same as sum of array values arr[1] + arr[2] = 10 + (-5) = 5
     *
     * At every iteration, we subtract k from running sum and check if we had encountered map[sum - k] in the past,
     * if yes then all the array values between (not including) starting from index i - 1 will sum up to k
     *
     * eg.
     * index       ->      0   1   2    3      4    5    6
     * Given array ->      3   4   7    2     -3    1    7         k = 7
     * Running sum -> 0    3   7   14   16    13    14   21       // Important to have sum start from 0
     * Map content -> 0:1 3:1 7:1  14:1 16:1  13:1 14:1 21:1
     *
     * Question: what does it mean sum - k has appeared multiple times? why do we add number of its occurences to
     * the result?
     * Answer: In the example above, there are two Pairs 14:1, ideally it should be saved as 14:2 but for the sake of explanation
     * I am keeping it this way. Lets assume we are processing index 6, running sum[6] = 21, If we have seen sum - k
     * ie 21 - 7 = 14 in the past that means all the elements between current position until the index with sum 14 will sum
     * up to k.
     *
     * map[14] = 2, this means we have encountered 14 twice previously.
     * First occurence of 14 at index 2: Sum of values between (2, 6] = 2 + -3 + 1 + 7 = 7
     * Second occurence of 14 at index 5: Sum of values between (5, 6] = 7 = 7
     * This is the reason why we add value of key 14 which is 2 to our result.
     *
     * https://www.youtube.com/watch?v=bqN9yB0vF08
     */
    fun subarraySum(nums: IntArray, k: Int): Int {
        var count = 0
        val map = mutableMapOf<Int, Int>() // preSum -> count

        // map[0] = 1 Required if skipping line 70
        var sum = 0
        for(num in nums) {
            sum += num
            if (sum - k == 0) count++ // you can also skip this step by simply initializing the map with 0:1 pair
            // By looking for key [sum - k], we are checking if we ever encountered sum[0, i - 1]
            // in the past, if yes add the number of its occurrences to the result
            count += map.getOrDefault(sum - k, 0)
            map[sum] = map.getOrDefault(sum, 0) + 1
        }
        return count
    }
}