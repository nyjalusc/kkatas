package sort.`252`

/**
 *
 */
class IntervalListIntersections_986 {
    /**
     * Two pointer solution: Think of all the possible scenarios. Arrange them in the if-else or when clause correctly.
     * Full intersection checks should happen first to avoid false classification. Every iteration
     * move the pointer that points to the smaller interval.
     *
     * Time: O(M + N)
     * Space: O(M + N) required to creating the result array
     */
    fun intervalIntersection(A: Array<IntArray>, B: Array<IntArray>): Array<IntArray> {
        if(A.isEmpty() || B.isEmpty()) return emptyArray()
        var i = 0
        var j = 0
        val result = mutableListOf<IntArray>()
        while(i < A.size && j < B.size) {
            if (A[i][0] in B[j][0]..B[j][1] && A[i][1] in B[j][0]..B[j][1]) {
                // A is subset of B
                result.add(A[i])
            } else if(B[j][0] in A[i][0]..A[i][1] && B[j][1] in A[i][0]..A[i][1]) {
                // B is subset of A
                result.add(B[j])
            } else if(A[i][0] in B[j][0]..B[j][1]) {
                // Start of A overlaps
                result.add(intArrayOf(A[i][0], B[j][1]))
            } else if (A[i][1] in B[j][0]..B[j][1]) {
                // End of B overlaps
                result.add(intArrayOf(B[j][0], A[i][1]))
            }
            // Whether there is overlap or not we move the pointer which represents the
            // smallest interval. There could be more intervals that could intersect.
            if(A[i][1] < B[j][1]) i++ else j++
        }

        return result.toTypedArray()
    }
}