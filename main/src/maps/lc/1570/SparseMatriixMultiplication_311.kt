package maps.lc.`1570`

/**
 *
 */
class SparseMatriixMultiplication_311 {
    /**
     * Bruteforce
     */
    fun multiply(A: Array<IntArray>, B: Array<IntArray>): Array<IntArray> {
        val rows = A.size
        val cols = B[0].size
        val aCols = A[0].size
        val result = Array(rows) { IntArray(cols) { 0 } }

        for (i in 0 until rows) {
            for (j in 0 until cols) {
                for (k in 0 until aCols) {
                    result[i][j] += A[i][k] * B[k][j]
                }
            }
        }

        return result
    }

    /**
     * Optimized solution:
     * Turn this problem into a DotProductOfTwoSparseVectors problem. This can be done by doing a transponse of matrix B.
     * And then operating on one row at a time for each matrix.
     *
     * Follow up: How would you efficiently store the matrix? Matrix compression by creating a List<IndexedValue<Int>>
     *     which will contain nonzero element's index and value.
     *  Other follow up questions described in the solution of DotProductOfTwoSparseVectors
     */

}