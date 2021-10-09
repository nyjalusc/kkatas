package backtracking.`39`


class CombinationSum {
    // https://leetcode.com/problems/combination-sum/discuss/16502/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partitioning)
    fun combinationSum(candidates: IntArray, target: Int): List<List<Int>> {
        val result = mutableListOf<List<Int>>()
        helper(candidates, target, result, mutableListOf<Int>(), 0)
        return result
    }

    private fun helper(candidates: IntArray, target: Int, result: MutableList<List<Int>>, solution: MutableList<Int>, index: Int) {
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


    fun combinationSum(candidates: IntArray, target: Int): List<List<Int>>? {
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