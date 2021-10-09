package backtracking.`39`


class FactorCombinations_254 {

    fun getFactors(n: Int): List<List<Int>> {
        val result: MutableList<List<Int>> = ArrayList()
        helper(result, ArrayList(), n, 2)
        return result
    }

    fun helper(result: MutableList<List<Int>>, item: MutableList<Int>, n: Int, start: Int) {
        if (n <= 1) {
            if (item.size > 1) {
                result.add(item.toList())
            }
            return
        }
        for (i in start..n) {
            if (n % i == 0) {
                item.add(i)
                helper(result, item, n / i, i)
                item.removeAt(item.size - 1)
            }
        }
    }
}