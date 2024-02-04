package stack.`232`

/**
 * In my original solution
 * push: Added all elements to stack 1
 * pop: Moved all elements in reverse order to stack 2, that way oldest element is on top of Stack 2 which is popped
 * but then it required moving all the elements back to Stack 1 which is optimized in the following solution
 * peek: Maintained a property to keep track of next element to be popped
 * empty: Check if stack1 is empty
 *
 * Optimal Solution
 * push: Same as original solution
 * pop: Pop element from stack 2 if its non-empty, otherwise move all elements from stack 1 in reverse order but DO NOT
 * move them back like I did in original solution
 * peek: First element in Stack 2, if empty move all elements from Stack 1 to 2 and get the top one
 * empty: check if both stacks are empty
 */
class QueueUsingStacks {
    private val stack1 = ArrayDeque<Int>()
    private val stack2 = ArrayDeque<Int>()
    // Not required with more optimal solution
    private var first: Int? = null

    /**
     * Always push to Stack 1
     */
    fun push(x: Int) {
        if(first == null) first = x
        stack1.addFirst(x)
    }

    /**
     * At the end of operation transferring all elements back from Stack 2 to 1 can be avoided
     */
    fun popSubOptimal(): Int {
        while(!stack1.isEmpty()) stack2.addFirst(stack1.removeFirst())
        val poppedElement = stack2.removeFirst()
        first = if(!stack2.isEmpty()) stack2.first() else null
        while(!stack2.isEmpty()) stack1.addFirst(stack2.removeFirst())
        return poppedElement
    }

    /**
     * The whole queue is split between Stack 2 and Stack 1
     */
    fun pop(): Int {
        if (stack2.isEmpty()) {
            // Transfer all elements from Stack 1 to Stack 2
            while (stack1.isNotEmpty()) stack2.addFirst(stack1.removeFirst())
        }
        return stack2.removeFirst()
    }

    fun peekSubOptimal(): Int {
        return if(first == null) -1 else first!!
    }

    fun peek(): Int {
        if (stack2.isEmpty()) {
            // Transfer all elements from Stack 1 to Stack 2
            while (stack1.isNotEmpty()) stack2.addFirst(stack1.removeFirst())
        }
        return stack2.first()
    }

    fun emptySubOptimal(): Boolean {
        return stack1.isEmpty()
    }

    fun empty(): Boolean {
        return stack1.isEmpty() && stack2.isEmpty()
    }

}