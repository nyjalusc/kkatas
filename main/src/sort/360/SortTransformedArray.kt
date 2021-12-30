package sort.`360`

class SortTransformedArray {
    /**
     * When a > 0, start filling the result array from the end other from the front.
     * Maintain two pointers start and end to compare and iterate from the two ends of the array
     */
    fun sortTransformedArray(nums: IntArray, a: Int, b: Int, c: Int): IntArray {
        val n = nums.size
        var start = 0
        var end = n - 1
        val res = IntArray(n)
        if (a > 0) {
            for (i in n - 1 downTo 0) {
                val x = compute(nums[start], a, b, c)
                val y = compute(nums[end], a, b, c)
                if (x > y) {
                    res[i] = x
                    start++
                } else {
                    res[i] = y
                    end--
                }
            }
        } else {
            for (i in 0 until n) {
                val x = compute(nums[start], a, b, c)
                val y = compute(nums[end], a, b, c)
                if (x < y) {
                    res[i] = x
                    start++
                } else {
                    res[i] = y
                    end--
                }
            }
        }
        return res
    }

    /**
     * Compute ax^2 + bx + c, for every input x
     */
    fun compute(x: Int, a: Int, b: Int, c: Int): Int = a * x * x + b * x + c

}