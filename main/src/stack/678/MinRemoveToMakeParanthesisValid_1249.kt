package stack.`678`

class MinRemoveToMakeParanthesisValid_1249 {
    /**
     * Using a stack keep track of balanced parenthesis. Instead of adding open paren char to the stack add the index of
     * open paren, for every closed paren pop top of the stack. If for a closed paren, stack is empty, that means we have
     * found an extra close paren. Keep track of it by adding its index to a set. If at the end of string iteration, stack
     * is not empty then it means we found extra open parens. Add these indices to the stack. Using a stringbuilder, create
     * a string out of chars at indices that do not appear in the set of parens to remove.
     *
     * Time: O(N)
     * Space: O(N)
     */
    fun minRemoveToMakeValid(s: String): String {
        val stack = ArrayDeque<Int>(s.length)
        val indicesToDelete = mutableSetOf<Int>()

        for (i in s.indices) {
            when (s[i]) {
                '(' -> stack.addFirst(i)
                ')' -> {
                    if (stack.isNotEmpty())
                        stack.removeFirst()
                    else {
                        // All additional right parens
                        indicesToDelete.add(i)
                    }
                }
            }
        }

        // All additional left parens
        indicesToDelete.addAll(stack)

        // Remove extra left and right parens
        // Cannot delete this way because the chars would shift after first deletion making the other indices incorrect
//        val sb = StringBuilder(s)
//        indicesToDelete.forEach { sb.deleteCharAt(it) }

        val sb = StringBuilder()
        s.forEachIndexed { index, c ->  if (index !in indicesToDelete) sb.append(c) }

        return sb.toString()
    }
}