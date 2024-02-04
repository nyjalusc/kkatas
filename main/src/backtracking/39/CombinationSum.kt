package backtracking.`39`


class CombinationSum {
    // https://leetcode.com/problems/combination-sum/discuss/16502/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partitioning)

    /**
     * Time Complexity: O(N ^ depth), where depth = target / min(candidates), for the worst case
     *
     * Space Complexity:
     *  Call Stack Depth: O(depth), where depth could be approximated as target / min(candidates) in a general sense, considering the recursion nature.
     *  Result Storage: O(N * K), where N is the number of combinations stored, and K is the average length of each combination, worst case of K is (target / min candidate) = Max depth of tree
     */
    fun combinationSum(candidates: IntArray, target: Int): List<List<Int>> {
        val result = mutableListOf<List<Int>>()
        // Sorting will help us skip elements
        candidates.sort()
        helper(candidates, target, 0, result, mutableListOf())
        return result
    }

    private fun helper(candidates: IntArray, target: Int, index: Int, result: MutableList<List<Int>>, solution: MutableList<Int>) {
        if (target == 0) {
            result.add(solution.toList())
            return
        }

        var i = index
        while (i < candidates.size) {
            if (candidates[i] <= target) {
                // Choose
                solution.add(candidates[i])
                // Explore
                helper(candidates, target - candidates[i], i, result, solution)
                // Unchoose
                solution.removeLast()
                i++
            } else {
                return // We can skip elements if remaining elements are going to be larger than target
            }
        }
    }


    fun combinationSum1(candidates: IntArray, target: Int): List<List<Int>> {
        val result = mutableListOf<List<Int>>()
        helper1(candidates, target, result, mutableListOf<Int>(), 0)
        return result
    }

    private fun helper1(candidates: IntArray, target: Int, result: MutableList<List<Int>>, solution: MutableList<Int>, index: Int) {
        if (target == 0) {
            result.add(solution.toList()) // Creates a copy
        }

        var i = index
        while (i < candidates.size) {
            if (candidates[i] <= target) { // Constraint
                solution.add(candidates[i]) // Select
                helper(candidates, target - candidates[i], result, solution, i) // index not incremented because dups are allowed
                solution.removeAt(solution.lastIndex) // Unselect, because we have explored all paths for candidates[i]
            }
            i++
        }
    }


    fun combinationSum2(candidates: IntArray, target: Int): List<List<Int>>? {
        val list: MutableList<Int> = ArrayList()
        val solution: MutableList<List<Int>> = ArrayList()
        solve(candidates, target, 0, list, solution)
        return solution
    }

    fun solve(
        candidates: IntArray,
        target: Int,
        index: Int,
        currentSolution: MutableList<Int>,
        globalSolution: MutableList<List<Int>>
    ) {
        if (target == 0) {
            globalSolution.add(ArrayList(currentSolution))
            return
        }
        for (i in index until candidates.size) {
            if (candidates[i] <= target) {
                currentSolution.add(candidates[i])
                solve(
                    candidates,
                    target - candidates[i],
                    i,
                    currentSolution,
                    globalSolution
                ) // not i + 1 because we can reuse same elements
                currentSolution.removeAt(currentSolution.size - 1)
            }
        }
    }
}