package graphs.`200`

class NumberOfIslands {
    fun numIslands(grid: Array<CharArray>): Int {
        if(grid.isEmpty()) return 0
        if(grid[0].isEmpty()) return 0

        val row = grid.size
        val col = grid[0].size
        val flags = Array(row) { Array(col) { false } }
        var islandCount = 0

        for (i in grid.indices) {
            for (j in grid[0].indices) {
                if (grid[i][j] == '1' && !flags[i][j]) {
                    islandCount++
                    markIslandCells(grid, flags, i, j)
                }
            }
        }
        return islandCount
    }

    private fun markIslandCells(grid: Array<CharArray>, flags: Array<Array<Boolean>>, row: Int, col: Int) {
        // Base Case
        if(row !in grid.indices ||
            col !in grid[0].indices ||
            flags[row][col] ||
            grid[row][col] == '0'
        ) return

        flags[row][col] = true

        markIslandCells(grid, flags, row + 1, col)
        markIslandCells(grid, flags, row, col + 1)
        markIslandCells(grid, flags, row - 1, col)
        markIslandCells(grid, flags, row, col - 1)
    }
}