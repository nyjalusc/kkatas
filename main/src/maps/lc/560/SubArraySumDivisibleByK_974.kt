package maps.lc.`560`

/**
 *
 */
class SubArraySumDivisibleByK_974 {
    /**
     * Time: O(N*N) N*N computations required to generate all sub arrays
     * Space: O(1)
     */
    fun subarraysDivByKTLE(A: IntArray, K: Int): Int {
        var result = 0
        for (i in A.indices) {
            var sum = 0
            for (j in i..A.lastIndex) {
                sum += A[j]
                if (sum.rem(K) == 0) result++
            }
        }
        return result
    }

    /**
     * Algo: PreSum method
     * Lets say we have a array given with indices [0..i..j..N], where 0 < i < j < N.
     * We compute a running sum, for i its Si and for index j it is Sj.
     * If Si % K = x and Sj % K = x then it means Sj-i % K = 0
     * For instance lets say K = 5, Si = 22 and Sj = 52,
     * Si % K = 22 % 5 = 2 (meaning 2 surplus)
     * Sj % K = 52 % 5 = 2 (meaning 2 surplus)
     * This means that sum of all the elements from (i, j] % K will be 0. This remainder -> frequency
     * is what we save in map.
     *
     * remainder = -1 % 5 = -1 (this means we are deficient by 1 to make it perfectly divisible by K = 5)
     * We could also say if remainder is negative remainder = -1 (remainder) + 5 (K) = 4
     * (which means now it is surplus by 4 to make it perfectly divisible by K = 5)
     * We always maintain remainder in surplus, so in case it is negative we just add it with K to make it
     * positive.
     *
     * Modulus operation always gives us surplus count by which the number missed to be perfectly divisible.
     * This is true if num > 0, if its negative we can convert deficient interpretation to a surplus interpretation.
     * The remainder values will always lie between [0, K), hence Space complexity is O(K)
     * Time: O(N)
     * Space: O(K)
     * https://www.youtube.com/watch?v=u9m-hnlcydk
     */
    fun subarraysDivByK(A: IntArray, K: Int): Int {
        val map = mutableMapOf<Int, Int>()
        var sum = 0
        var result = 0
        var remainder = 0
        map[0] = 1
        for (num in A) {
            sum += num
            remainder = sum.rem(K)
            if(remainder < 0) remainder += K
            result += map.getOrDefault(remainder, 0)
            map[remainder] = map.getOrDefault(remainder, 0) + 1
        }
        return result
    }
}