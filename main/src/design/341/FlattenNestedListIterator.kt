package design.`341`

import java.util.*


/**
 * Queue + DFS to pre-process the input
 */
class NestedIteratorMy(nestedList: List<NestedInteger>) {
    val queue = ArrayDeque<Int>()

    init {
        init(nestedList)
    }

    private fun init(nestedList: List<NestedInteger>) {
        nestedList.forEach {
            if(it.isInteger()) queue.addFirst(it.getInteger()!!)
            else init(it.getList()!!)
        }
    }

    fun next(): Int {
        return queue.removeLast()
    }

    fun hasNext() = queue.isNotEmpty()
}

/**
 * This is the smartest one and easiest of them all.
 * We basically process the given list and store all the values;
 *
 *
 * Advantages:
 * 1. Its fast
 * 2. this is fail safe iterator, means it won't throw concurrent modification exception
 * 3. good choice for multi threading
 *
 *
 * Disadvantages:
 * 1. This occupy lot of extra space, and
 * 2. this is not lazy in its behaviour rather proactive
 */
class NestedIterator(nestedList: List<NestedInteger>) {
    init {
        init(nestedList)
    }

    /**
     * use to store the elements from given list
     */
    private val elements: MutableList<Int> = ArrayList()

    /**
     * to keep track the element which need to show
     */
    var index = 0

    /**
     * store them recursively
     *
     * @param nestedList
     */
    private fun init(nestedList: List<NestedInteger>) {
        if (nestedList.isEmpty()) return
        for (nst in nestedList) {
            if (!nst.isInteger()) {
                nst.getList()?.let { init(it) }
            } else nst.getInteger()?.let { elements.add(it) }
        }
    }

    fun next(): Int {
        return elements[index++]
    }

    fun hasNext(): Boolean {
        return index < elements.size
    }
}

/**
 * This is another approach, which is lazy in nature. It has advantages on memory usage.
 */
class NestedIteratorUsingStack(nestedList: List<NestedInteger>) {
    /**
     * keep a stack for which element we need to print
     */
    private val stack: Stack<NestedInteger>
    fun next(): Int {
        return stack.pop().getInteger()!!
    }

    fun hasNext(): Boolean {
        /**
         * based on top, just process
         */
        while (!stack.isEmpty() && !stack.peek().isInteger()) {
            val nst = stack.pop().getList()
            for (i in nst!!.indices.reversed()) {
                stack.push(nst[i])
            }
        }
        return !stack.isEmpty()
    }

    init {
        if (nestedList.isEmpty()) return
        stack = Stack()
        /**
         * since we need to show from left to right direction, simply push all the elements in reverse order initially
         */
        for (i in nestedList.indices.reversed()) stack.push(nestedList[i])
    }
}