package backtracking.`39`

class WordSearch_79 {
    /**
     *     Time: The DFS is initiated up to M*N times since the search starts from every cell.
     *     The search for each character of the word can lead to exploring 4 directions. However, due to the backtracking
     *     mechanism (where a path is abandoned as soon as it is determined not to lead to a solution), the actual number
     *     of cells visited for each character of the word is less than 4 to the power of the word's length.
     *     Still, for the worst-case analysis, we consider the upper bound. Therefore, in the worst-case scenario, the
     *     time complexity is O(M * N * 4^L), acknowledging that for each cell, we explore 4 directions, and this exploration
     *     could, in theory, happen for each character in the word. More accurately it would be O (M * N * 4 * 3^(L-1) because
     *     only the first char match in the board would get 4 directions to explore, all other subsequent will be explored
     *     in 3 directions, the 4th one being already visited.
     *
     *     Space: O(L), length of the word
     */
    fun exist(board: Array<CharArray>, word: String): Boolean {
        for (i in board.indices) {
            for (j in board[0].indices) {
                if (board[i][j] == word[0])
                    if (dfs(board, word, 0, i, j, mutableSetOf<Pair<Int, Int>>())) return true
            }
        }
        return false
    }


    private fun dfs(board: Array<CharArray>, word: String, index: Int, row: Int, col: Int, visited: MutableSet<Pair<Int,Int>>): Boolean {
        if (index == word.length) return true

        if (row !in board.indices || col !in board[0].indices || visited.contains(Pair(row, col)) || board[row][col] != word[index]) return false

        visited.add(Pair(row, col))

        if (dfs(board, word, index + 1, row + 1, col, visited) ||
            dfs(board, word, index + 1, row - 1, col, visited) ||
            dfs(board, word, index + 1, row, col + 1, visited) ||
            dfs(board, word, index + 1, row, col - 1, visited)) return true

        visited.remove(Pair(row, col))
        return false
    }
}