package graphs.`1197`

class RottingOranges_994 {
    /*
    * Function to find the minimum time required for all oranges to rot in a given grid.
    * Time Complexity: O(N) - where N is the number of cells in the grid.
    * Space Complexity: O(N) - as the queue and visited set can store each cell.
    */
    fun orangesRotting(grid: Array<IntArray>): Int {
        val queue = ArrayDeque<Pair<Int, Int>>()
        var freshOranges = 0

        // Initialize the queue with positions of all rotten oranges and count fresh oranges.
        for (i in grid.indices) {
            for (j in grid[i].indices) {
                when (grid[i][j]) {
                    2 -> queue.add(Pair(i, j))
                    1 -> freshOranges++
                }
            }
        }

        // Early exit if there are no fresh oranges.
        if (freshOranges == 0) return 0

        val directions = arrayOf(Pair(1, 0), Pair(-1, 0), Pair(0, 1), Pair(0, -1))
        var minutes = 0

        // BFS to rot all reachable fresh oranges.
        while (queue.isNotEmpty() && freshOranges > 0) {
            minutes++
            for (k in queue.indices) {
                val (x, y) = queue.removeFirst()

                directions.forEach { (dx, dy) ->
                    val nx = x + dx
                    val ny = y + dy
                    if (nx in grid.indices && ny in grid[0].indices && grid[nx][ny] == 1) {
                        grid[nx][ny] = 2
                        freshOranges--
                        queue.add(Pair(nx, ny))
                    }
                }
            }
        }

        return if (freshOranges == 0) minutes else -1
    }
}