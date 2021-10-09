package backtracking.`39`


class Permutations_46 {

    fun permute(nums: IntArray?): List<List<Int>> {
        val result: MutableList<List<Int>> = ArrayList()
        if (nums == null || nums.isEmpty()) return result
        val permutation = mutableListOf<Int>()
        permuteHelper(nums, permutation, result, BooleanArray(nums.size))
        return result
    }

    private fun permuteHelper(nums: IntArray, permutation: MutableList<Int>, result: MutableList<List<Int>>, used: BooleanArray) {
        if (permutation.size == nums.size) {
            // Add result to the final list
            result.add(permutation.toList())
            return
        }
        for (i in nums.indices) {
            if(used[i]) continue
//            if (permutation.contains(nums[i])) continue
            // Choose
            permutation.add(nums[i])
            used[i] = true
            // Explore
            permuteHelper(nums, permutation, result, used)
            // Unchoose
            permutation.removeAt(permutation.size - 1)
            used[i] = false
        }
    }
}