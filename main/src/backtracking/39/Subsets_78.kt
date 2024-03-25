package backtracking.`39`

import java.lang.Character.isDigit

class Subsets_78 {
    fun subsets(nums: IntArray): List<List<Int>> {
        val result = mutableListOf<List<Int>>()
        val subset = mutableListOf<Int>()
        helper(nums, result, subset, 0)
        return result
    }

    private fun helper(nums: IntArray, result: MutableList<List<Int>>, subset: MutableList<Int>, index: Int) {
        result.add(subset.toList())

        var i = index
        while (i < nums.size) {
            // Pick
            subset.add(nums[i])

            // Explore
            helper(nums, result, subset, i + 1)

            // Retrace
            subset.removeLast()
            i++
        }
    }

    fun myAtoi(s: String): Int {
        var acc = 0L
        var sign = 1
        var whitespace = false

        for (c in s) {
            c.isDigit()
            when {
                c == '-' -> sign = -1
                (c.isDigit()) -> acc = acc * 10 + c.code - '0'.code
                c == ' ' -> whitespace = true
                else -> break
            }
        }

        return acc.toInt() * sign


    }
}