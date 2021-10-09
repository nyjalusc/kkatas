package backtracking.`39`


class PermutationsII_47 {
    fun permuteUnique(nums: IntArray): List<List<Int>> {
        if (nums.isEmpty()) return ArrayList()
        val result = mutableSetOf<List<Int>>()
        permuteUniqueHelper(nums, ArrayList(), result, BooleanArray(nums.size))
        return result.toList()
    }

    private fun permuteUniqueHelper(
        nums: IntArray,
        permutation: MutableList<Int>,
        result: MutableSet<List<Int>>,
        used: BooleanArray
    ) {
        // goal
        if (permutation.size == nums.size) {
            // Add result to the final list
            result.add(permutation.toList())
            return
        }
        for (i in nums.indices) {
            // Check constraint
            // if (permutation.contains(nums[i])) continue;
            if (used[i]) continue

            // choose
            permutation.add(nums[i])
            used[i] = true

            // explore
            permuteUniqueHelper(nums, permutation, result, used)

            // unchoose
            permutation.removeLast()
            used[i] = false
        }
    }
}