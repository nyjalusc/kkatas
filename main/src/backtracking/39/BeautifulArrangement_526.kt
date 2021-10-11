package backtracking.`39`

class BeautifulArrangement_526 {
    /**
     * Similar to Permutations
     */
    private var count = 0
    fun countArrangement(n: Int): Int {
        if(n == 0) return count
        permute(n, 1, BooleanArray(n + 1) { false })
        return count
    }

    /**
     * In permutations we used a list to generate results, think of index here as the size of list.
     * It indicates the index of the element in the list we are going to fill next
     */
    private fun permute(n: Int, index: Int, used: BooleanArray) {
        if(index > n) {
            // If flow reaches here it means it was valid
            count++
            return
        }

        for (i in 1..n) {
            // Optimized backtracking by checking the condition first instead of checking in the base condition
            if (!used[i] && ((i % index == 0) || (index % i == 0))) {
                used[i] = true
                permute(n, index + 1, used)
                used[i] = false
            }
        }
    }
}