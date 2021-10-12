package backtracking.`39`

class PartitionToKEqualSumSubsets {
    /**
     * Create an array called buckets with k indices.
     * These are the k buckets in which we will place elements one at a time and backtrack if required.
     * Need to implement isValid to check if selected element can fit the bucket.
     * If we find even one solution we do early termination or else we continue.
     * If current bucket is full start looking for elements from the beginning. Use boolean flag array
     *
     * Hard Backtracking
     */
    fun canPartitionKSubsets(nums: IntArray, k: Int): Boolean {
        if(nums.isEmpty()) return false

        // A solution is possible only if sum of all elements is perfectly divisible by k
        val sum = nums.sum()
        if(sum % k != 0) return false

        val subsetSum = sum / k
        val buckets = IntArray(k) { 0 }
        return helper(nums, k, subsetSum, buckets, BooleanArray(nums.size), 0)
    }

    private fun helper(nums: IntArray, k: Int, subsetSum: Int, buckets: IntArray, used: BooleanArray, current: Int): Boolean {
        // GOAL
        // Last element in the bucket is also full
        if(buckets.last() == subsetSum) {
            // If all values in the bucket are same as k we found our result
            return buckets.all { it == k }
        }

        for (i in current..nums.lastIndex) {
            // IS VALID
            val bucketIndexToBeUpdated = isValid(nums, subsetSum, buckets, used, i)
            if(bucketIndexToBeUpdated == -1) continue // Couldn't find a bucket to put nums[i]

            // We found a bucket
            // CHOOSE
            buckets[bucketIndexToBeUpdated] += nums[i]
            used[i] = true

            // EXPLORE
            val result = if (buckets[bucketIndexToBeUpdated] == subsetSum) {
                // Start looking for elements from the beginning
                helper(nums, k, subsetSum, buckets, used, 0)
            } else {
                // Continue looking for elements further
                helper(nums, k, subsetSum, buckets, used, i + 1)
            }

            if (result) return true // If we find even one result thats good enough to terminate
            else {
                // UNCHOOSE
                buckets[bucketIndexToBeUpdated] -= nums[i]
                used[i] = false
            }
        }
        return false
    }

    private fun isValid(nums: IntArray, subsetSum: Int, buckets: IntArray, used: BooleanArray, i: Int): Int {
        if(used[i]) return -1
        return buckets.indexOfFirst { bucketSum -> bucketSum + nums[i] <= subsetSum }
    }
}