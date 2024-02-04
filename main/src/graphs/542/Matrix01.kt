package graphs.`542`

import kotlin.math.abs
import kotlin.math.min

class Matrix01 {
    /**
     * Idea: For every cell with value 1, compute its distance from every cell with value 0. Take the min
     * Create a Result matrix and init with MAX. For every 0 cell, insert 0 as the min distance. For every 1 cell,
     * call [computeMinDistance] which iterates over entire matrix, computes distance of 0 cell from the given cell and
     * returns the min.
     *
     * Exceeds time limit
     * Time: O((r * c) ^ 2)
     * Space: O(r * c)
     */
    fun updateMatrixBrute(mat: Array<IntArray>): Array<IntArray> {
        val result = Array<IntArray> (mat.size) { IntArray(mat[0].size) {Integer.MAX_VALUE} }
        for(i in mat.indices) {
            for(j in mat[0].indices) {
                if(mat[i][j] == 0) result[i][j] = 0
                else result[i][j] = computeMinDistance(mat, i, j)
            }
        }
        return result
    }

    private fun computeMinDistance(mat: Array<IntArray>, row: Int, col: Int): Int {
        var minDistance = Int.MAX_VALUE
        for(i in mat.indices) {
            for(j in mat[0].indices) {
                if(mat[i][j] == 0) {
                    minDistance = min(minDistance, abs(i - row) + abs(j - col))
                }
            }
        }
        return minDistance
    }



    private data class Cell(val r: Int, val c: Int)

    /**
     * BFS: Add all zero cells to the queue. While processing a cell check if it can improve the minDistance score of
     * its 4 neighbors. If yes, then that neighbor could do the same for its own neighbors. To process it further we
     * add this neighbor cell to the queue.
     *
     * This problem cannot be solved by DFS
     * Time: O(r * c)
     * Space: O(r * c)
     */
    fun updateMatrixBFS(mat: Array<IntArray>): Array<IntArray> {
        val result = Array<IntArray> (mat.size) { IntArray(mat[0].size) {Integer.MAX_VALUE} }
        val queue = ArrayDeque<Cell>()

        // Mark result cell for all 0 cells with minDistance = 0
        // Add all 0 cells to the queue
        for(i in mat.indices) {
            for(j in mat[0].indices) {
                if(mat[i][j] == 0) {
                    result[i][j] = 0
                    queue.addLast(Cell(i, j))
                }
            }
        }

        val directions = arrayOf(intArrayOf(-1, 0), intArrayOf(1, 0), intArrayOf(0, -1), intArrayOf(0, 1))

        while(queue.isNotEmpty()) {
            val cell = queue.removeFirst()
            for (direction in directions) {
                // row and cell are coordinates for neighbors
                val row = cell.r + direction[0]
                val col = cell.c + direction[1]
                if(row !in 0..mat.lastIndex ||
                    col !in 0..mat[0].lastIndex ||
                    result[row][col] <= result[cell.r][cell.c] + 1
                ) continue

                // found a neighbor whose minDistance could be improved by current cell
                result[row][col] = result[cell.r][cell.c] + 1
                // update neighbor has potential to also improve score of its own neighbors
                // Add it to the queue for further processing
                queue.addLast(Cell(row, col))
            }
        }
        return result
    }

    /**
     * Optimal Solution using DP: Create a DP result array. For every 0 cell put 0 as the distance.
     * We make two passes:
     * 1. From top left to bottom right (natural iteration): We use the info in left cell and cell above the current one
     * to see if we can get a better (min) result for the current cell
     * 2. From bottom right to top left (reverse): Same as above but we use info of neighbor cell on the right and the one
     * below to see if we get a better result
     * The reason why we split it in two iterations is because for DP you can only use results or older computation to
     * deduce result of current operation. In a regular iteration, you wouldn't have info about the cells on the right and
     * below because they are not computed yet.
     *
     * Time: O(r * c)
     * Space: O(r * c)
     */
    fun updateMatrix(mat: Array<IntArray>): Array<IntArray> {
        val dp = Array(mat.size) { IntArray(mat[0].size) { Int.MAX_VALUE } }

        // Look for computed values for top and left neighbors when iterating top left to bottom right
        for(i in mat.indices) {
            for(j in mat[0].indices) {
                if(mat[i][j] == 0) dp[i][j] = 0
                else {
                    // Value is 1
                    val leftNeighbor = if(j > 0 && dp[i][j - 1] != Int.MAX_VALUE) dp[i][j - 1] else Int.MAX_VALUE - 1
                    val topNeighbor = if (i > 0 && dp[i - 1][j] != Int.MAX_VALUE) dp[i - 1][j] else Int.MAX_VALUE - 1
                    // Doing - 1 above to avoid Int overflow in the step below
                    dp[i][j] = min(dp[i][j], min(leftNeighbor, topNeighbor) + 1)
                }
            }
        }

        // Look for computed values for bottom and right neighbors when iterating bottom right to left top
        for(i in mat.lastIndex downTo 0) {
            for(j in mat[0].lastIndex downTo 0) {
                if(mat[i][j] == 0) dp[i][j] = 0
                else {
                    // Value is 1
                    val rightNeighbor = if(j < mat[0].lastIndex && dp[i][j + 1] != Int.MAX_VALUE) dp[i][j + 1] else Int.MAX_VALUE - 1
                    val bottomNeighbor = if (i < mat.lastIndex && dp[i + 1][j] != Int.MAX_VALUE) dp[i + 1][j] else Int.MAX_VALUE - 1
                    // Doing - 1 above to avoid Int overflow in the step below
                    dp[i][j] = min(dp[i][j], min(rightNeighbor, bottomNeighbor) + 1)
                }
            }
        }
        return dp
    }
}