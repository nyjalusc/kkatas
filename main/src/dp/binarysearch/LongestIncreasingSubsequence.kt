package dp.binarysearch

import kotlin.math.max


/**
 *
 */
class LongestIncreasingSubsequence {
    /**
     * The DP array contains the longest subsequnce possible until a certain index i.
     * Iteration is done using two pointers i and j. Pointer j sets up bounds for i.
     * Every iteration j increases by one place and i starts from 0 and we compute the longest
     * possible subsequnce for element at index j.
     *
     * My initial idea was to calculate for every element starting from index 0 and compare it against
     * every other element but that is not feasible. Here having a bounds is must as it really simplifies
     * the problem. Otherwise it becomes a brute-force approach.
     *
     * Time: O(N^2)
     * Space: O(N)
     */
    fun lengthOfLISDP(nums: IntArray?): Int {
        if (nums == null || nums.isEmpty()) return 0
        val dp = IntArray(nums.size) { 1 }
        dp[0] = 1
        var result = 1
        for (j in 1 until nums.size) {
            for (i in 0 until j) {
                if (nums[i] < nums[j]) {
                    // Most important step
                    dp[j] = max(dp[j], dp[i] + 1)
                    result = max(dp[j], result)
                }
            }
        }
        return result
    }

    /**
     * Every iteration we try to add element in a sorted manner in the dp array.
     * The dp array may or may not contain the actual elements but the size of dp array is the result. It is a little
     * difficult to see because the elements can be overwritten in the dp array. But ideally we are trying to build
     * the longest increasing subsequence in the dp array.
     *
     * IMPORTANT: The way we build the dp array is quite unique. We perform a binary search over the dp array and try to
     * look for num[i], of course we wouldn't find it because it doesn't exist yet. But binary search result will tell us
     * where the element should have been.
     */
    fun lengthOfLIS(nums: IntArray): Int {
        val dp = IntArray(nums.size)
        var len = 0
        for (num in nums) {
            var i = dp.binarySearch(0, len, num)
            // If element is not found i will be a negative index, say -X, this is negative of one value greater than where
            // the element should have been. So the real index where element should be is X - 1
            if (i < 0) {
                i = -(i + 1)
            }
            dp[i] = num
            // if i has reached the bounds increase len
            if (i == len) {
                len++
            }
        }
        return len
    }

    /**
     * https://leetcode.com/problems/longest-increasing-subsequence/discuss/74824/JavaPython-Binary-search-O(nlogn)-time-with-explanation
     * Also checkout https://leetcode.com/problems/longest-increasing-subsequence/discuss/74824/JavaPython-Binary-search-O(nlogn)-time-with-explanation/206357
     */
    fun lengthOfLISUpsolve(nums: IntArray): Int {
        val tails = IntArray(nums.size)
        var size = 0
        for (x in nums) {
            var i = 0
            var j = size
            while (i != j) {
                val m = (i + j) / 2
                if (tails[m] < x) i = m + 1 else j = m
            }
            tails[i] = x
            if (i == size) ++size
        }
        return size
    }
}