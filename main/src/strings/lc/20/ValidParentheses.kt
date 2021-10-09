package strings.lc.`20`

class ValidParentheses {

    /**
     * Time: O(N)
     * Space: O(N)
     */
    fun isValid(s: String): Boolean {
        if(s.length <= 1) return false

        val stack = ArrayDeque<Char>(s.length)
        s.forEach { c ->
            try {
                when (c) {
                    '{' -> stack.addFirst(c)
                    '[' -> stack.addFirst(c)
                    '(' -> stack.addFirst(c)
                    '}' -> {
                        val top = stack.removeFirst()
                        if(top != '{') return false
                    }
                    ']' -> {
                        val top = stack.removeFirst()
                        if(top != '[') return false
                    }
                    ')' -> {
                        val top = stack.removeFirst()
                        if(top != '(') return false
                    }
                }
            } catch (e: NoSuchElementException) {
                return false
            }

        }

        return stack.isEmpty()
    }
}