package maps.lc.`1`


class FourSumII {
    fun fourSumCount(A: IntArray, B: IntArray, C: IntArray, D: IntArray): Int {
        var count = 0
        val map = mutableMapOf<Int, Int>()

        // Process two arrays A & B
        for (i in A.indices) {
            for (j in B.indices) {
                val sum = A[i] + B[j]
                map[sum] = map.getOrDefault(sum, 0) + 1
            }
        }

        // Process two arrays C & D
        for (i in C.indices) {
            for (j in D.indices) {
                val sum = (C[i] + D[j]) * -1
                if (map.containsKey(sum)) count += map[sum]!!
            }
        }
        return count
    }
}