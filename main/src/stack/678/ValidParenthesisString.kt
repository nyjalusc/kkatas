package stack.`678`

import java.util.*

class ValidParenthesisString {
    /**
     * Use 2 stacks for keeping track of left parens and the star.
     * Every time we see a right paren, pop left stack, if its empty check star stack, because we could use
     * star to balance the right paren, if that is empty as well then abort!
     *
     * At the end check if remaining left parens can be balanced by the stars. If the index of left paren > star,
     * ie. the star is on the left of open paren then abort! At the end, you could have extra stars but left paren
     * stack should be empty
     *
     * Time: O(N)
     * Space: O(N)
     */
    fun checkValidString(s: String): Boolean {
        if(s.isEmpty()) return true

        val openStack = LinkedList<Int>()
        val starStack = LinkedList<Int>()

        s.forEachIndexed { index, c ->
            when (c) {
                '(' -> openStack.addFirst(index)
                ')' -> {
                    // If both stacks are empty, right paren cannot be balanced
                    if(openStack.isEmpty() && starStack.isEmpty()) return false

                    // if openStack is non-empty use it or else use starStack to balance right paren
                    if(openStack.isNotEmpty()) openStack.removeFirst()
                    else starStack.removeFirst()
                }
                else -> starStack.addFirst(index)
            }
        }

        // By now all right parens are balanced, now the unresolved left parens need to be resolved using stars
        while (openStack.isNotEmpty() && starStack.isNotEmpty()) {
            if (openStack.removeFirst() > starStack.removeFirst()) {
                // this means the most recent star is on the left of left paren -> "*(", cannot be balanced
                return false
            }
        }

        // If all left parens are balanced the openstack should be empty
        return openStack.isEmpty()
    }
}