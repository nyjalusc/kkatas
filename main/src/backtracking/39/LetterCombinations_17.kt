package backtracking.`39`

class LetterCombinations_17 {
    /**
     * Time: O(4 ^ N * N) : 4 is the longest string combination, N is the length of digits string
     * Space: O(N): N is the length of digits string. It will be depth of the call stack
     */
    fun letterCombinations(digits: String): List<String> {
        if (digits.isEmpty()) return emptyList()
        val result = mutableListOf<String>()
        val dialPadMap = mapOf(
            "2" to "abc",
            "3" to "def",
            "4" to "ghi",
            "5" to "jkl",
            "6" to "mno",
            "7" to "pqrs",
            "8" to "tuv",
            "9" to "wxyz"
        )
        helper(digits, dialPadMap, result, StringBuilder(), 0)
        return result
    }

    private fun helper(
        digits: String,
        dialPadMap: Map<String, String>,
        result: MutableList<String>,
        strBuilder: StringBuilder,
        index: Int
    ) {
        if (strBuilder.length == digits.length) {
            result.add(strBuilder.toString())
            return
        }

        dialPadMap[digits[index].toString()]!!.forEach {
            strBuilder.append(it)
            helper(digits, dialPadMap, result, strBuilder, index + 1)
            strBuilder.deleteCharAt(strBuilder.lastIndex)
        }
    }
}