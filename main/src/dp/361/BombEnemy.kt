package dp.`361`

import kotlin.math.max

class BombEnemy {
    /**
     * Brute force approach is also good enough for this problem. At every cell imagine going in all 4 directions and
     * counting casualties.
     *
     * This is an optimization on top of brute force approach.
     * 1. We are going to iterate over every cell
     * 2. We pre-compute rowKills and colKills and try to reuse it, since we iterate along columns first, we need just
     * one rowCasualty variable and we hold a columnCasualty Array
     * 3. Every time we iterate along the column on row 0, we compute colCasualty, similarly everytime we go to the new row
     * i.e row[i][0], we compute row casualty
     * 4. We skip processing if cell is not empty '0'
     * 5. Every time we are past a Wall 'W' we compute row and col casualties. Because the previous count got obstructed
     * by the wall.
     */
    fun maxKilledEnemies(grid: Array<CharArray>): Int {
        var maxCasualties = 0
        val colCasualties = Array(grid[0].size) { 0 }
        var rowCasualties = 0
        for (i in grid.indices) {
            for (j in grid[0].indices) {
                // If cell is enemy or Wall continue, because you can't place bomb
                if(grid[i][j] in listOf('E', 'W')) continue

                // Compute Row casualties
                if(j == 0 || grid[i][j - 1] == 'W') {
                    rowCasualties = killedEnenmiesRow(grid, i, j)
                }

                // Compute Col casualties
                if(i == 0 || grid[i - 1][j] == 'W') {
                    colCasualties[j] = killedEnenmiesRow(grid, i, j)
                }

                if(grid[i][j] == '0') maxCasualties = max(maxCasualties, rowCasualties + colCasualties[j])
            }
        }

        return maxCasualties

    }

    private fun killedEnenmiesRow(grid: Array<CharArray>, row: Int, col: Int): Int {
        var casualties = 0
        for (i in col until grid[0].size) {
            if(grid[row][i] == 'W') break
            else if (grid[row][i] == 'E') casualties++
        }
        return casualties
    }

    private fun killedEnenmiesCol(grid: Array<CharArray>, row: Int, col: Int): Int {
        var casualties = 0
        for (i in row until grid.size) {
            if(grid[i][col] == 'W') break
            else if (grid[i][col] == 'E') casualties++
        }
        return casualties
    }
}

class Upsolve {
    fun maxKilledEnemies(grid: Array<CharArray>?): Int {
        if (grid == null || grid.isEmpty() || grid[0].isEmpty()) return 0
        var max = 0
        var row = 0
        val col = IntArray(grid[0].size)
        for (i in grid.indices) {
            for (j in 0 until grid[0].size) {
                if (grid[i][j] == 'W') continue
                if (j == 0 || grid[i][j - 1] == 'W') {
                    row = killedEnemiesRow(grid, i, j)
                }
                if (i == 0 || grid[i - 1][j] == 'W') {
                    col[j] = killedEnemiesCol(grid, i, j)
                }
                if (grid[i][j] == '0') {
                    max = if (row + col[j] > max) row + col[j] else max
                }
            }
        }
        return max
    }

    //calculate killed enemies for row i from column j
    private fun killedEnemiesRow(grid: Array<CharArray>, i: Int, j: Int): Int {
        var j = j
        var num = 0
        while (j <= grid[0].size - 1 && grid[i][j] != 'W') {
            if (grid[i][j] == 'E') num++
            j++
        }
        return num
    }

    //calculate killed enemies for  column j from row i
    private fun killedEnemiesCol(grid: Array<CharArray>, i: Int, j: Int): Int {
        var i = i
        var num = 0
        while (i <= grid.size - 1 && grid[i][j] != 'W') {
            if (grid[i][j] == 'E') num++
            i++
        }
        return num
    }
}