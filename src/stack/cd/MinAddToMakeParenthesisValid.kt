package stack.cd

/**
 *
 */
class MinAddToMakeParenthesisValid {
    /**
     * Time: O(N)
     * Space: O(N)
     */
    fun minAddToMakeValidEasy(S: String): Int {
        val stack = ArrayDeque<Char>()
        S.forEach {
            if(stack.isEmpty() || it == '(') {
                stack.addFirst(it)
            } else if(it == ')') {
                if(stack.first() == '(') {
                    stack.removeFirst()
                } else {
                    stack.addFirst(it)
                }
            }
        }
        return stack.size
    }

    /**
     * Time: O(N)
     * Space: O(1)
     */
    fun minAddToMakeValid(S: String): Int {
        var left = 0
        var right = 0
        S.forEach {
            if(it == ')') {
                if(left == 0) right++
                else left--
            } else {
                left++
            }
        }

        return left + right
    }
}