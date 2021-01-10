package linkedlist.lc.`430`

/**
 * https://leetcode.com/problems/flatten-a-multilevel-doubly-linked-list/
 */

class Node(var `val`: Int) {
    var prev: Node? = null
    var next: Node? = null
    var child: Node? = null
}

class FlattenMultiLevelDoublyLinkedList {

    /**
     * I was able to identify this is a stack problem but couldn't figure out how to traverse it.
     *
     * Algo:
     * 1. Use a stack to store the next node provided current node has a non-null child. This will be used
     * to resume the processing.
     * 2. Use a previous pointer to make connections in reverse. previous pointer is always one place behind current
     * 3. If child ptr is non-null. Add the next node to stack, current.next = child (brings the child to same level),
     * current.next.prev = current (make connections backwards), current.child = null
     * 4. If current is null, meaning it hit the end of nodes on some level, check if there are unprocessed nodes in stack.
     * If yes current = stack.pop() and current.prev = previous (bug) i forgot this.
     * 5. After processing the current node, increment previous to current and current to next node.
     *
     * Time: O(1), Space: O(k), where k = number of nodes with child pointers
     */
    fun flatten(root: Node?): Node? {
        val stack = ArrayDeque<Node>()
        var current = root
        var previous: Node? = root

        stack.isEm

        // Got this condition wrong in first attempt
        while(current != null || !stack.isEmpty()) {
            if (current == null) {
                current = stack.removeFirst()
                previous!!.next = current
                current.prev = previous // (bug)
            }

            if (current?.child != null) {
                current.next?.let { stack.addFirst(it) }
                current.next = current.child
                current.next!!.prev = current
                current.child = null
            }

            previous = current
            current = current!!.next
        }

        return root
    }
}