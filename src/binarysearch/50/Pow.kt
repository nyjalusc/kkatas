package binarysearch.`50`

/**
 *
 */
class Pow {
    // Bruteforce
    fun myPowBrute(x: Double, n: Int): Double {
        if(x == 0.toDouble()) return 0.toDouble()
        if(x == 1.toDouble()) return 1.toDouble()
        if(x == (-1).toDouble() && n < 0) return 1.toDouble()
        if(x == (-1).toDouble() && n >= 0) return (-1).toDouble()
        if(n == 0) return 1.toDouble()

        val N: Int = if (n < 0) -n else n
        val X: Double = if(n < 0) (1.toDouble() / x) else x

        var result: Double = X
        repeat(N - 1) {
            result *= X
            if(String.format("%.5f", result).toDouble() == 0.toDouble()) return 0.toDouble()
        }

        return result
    }

    /**
     * Recursive: Solution uses the power property: x^2n = x^(n + n) = x^n * x^n
     * similarly x^n = x^(n/2) * x^(n/2). To simplify if n is odd x^(n+1) = x * x^n (here n + 1 is odd)
     * and now for solving x^n we can use the same formula.
     *
     * Time: O(log N)
     * Space: O(log N)
     */
    fun myPowRecur(x: Double, n: Int): Double {
        var X = x
        var N: Long = n.toLong()
        if (n < 0) {
            X = 1/x
            N = -n.toLong()
        }
        return recursivePow(X, N)
    }

    private fun recursivePow(x: Double, n: Long): Double {
        if (n == 0L) return 1.0
        val half: Double = recursivePow(x, n / 2)
        return if (n % 2 == 0L) half * half else half * half * x
    }

    /**
     * Iterative:
     * Time: O(log N)
     * Space: O(1)
     */
    fun myPow(x: Double, n: Int): Double {
        if(n == 0) return 1.0

        var X = x
        var N: Long = n.toLong()
        if (n < 0) {
            X = 1/x
            N = -n.toLong()
        }

        var result = 1.0
        var i = N
        var currentProduct = X
        while(i > 0) {
            if(i%2 != 0L) {
                // result will get assigned when i = 1, and as you are halving i every iteration it'll reach 1 eventually
                result *= currentProduct
            }
            currentProduct *= currentProduct
            i /= 2
        }
        return result
    }
}