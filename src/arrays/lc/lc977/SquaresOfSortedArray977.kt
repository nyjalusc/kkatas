package arrays.lc.lc977

/**
 * https://leetcode.com/problems/squares-of-a-sorted-array/
 */
class SquaresOfSortedArray977 {
     fun sortedSquares1(A: IntArray): IntArray {
         return A.map { it * it }.toIntArray().also { it.sort() }
     }

     // map returns a List. sorted() is the function on List sort() works on IntArray
     fun sortedSquares2(A: IntArray): IntArray {
         return A.map { it * it }.sorted().toIntArray()
     }

    fun sortedSquares3(A: IntArray): IntArray {
            val result = IntArray(A.size)
            A.forEachIndexed { index, num ->
                result[index] = num * num
            }

            return result.sortedArray()
    }
}
