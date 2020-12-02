package linkedlist.lc.`21`

/**
 * https://leetcode.com/problems/insert-into-a-sorted-circular-linked-list/
 */
data class Node(val `val`: Int, var next: Node? = null)

class InsertIntoSortedCircularLinkedList {
    /**
     * Check if Null list:
     * a. True, create a single node list that points to itself, update head
     * b. False
     *  b.x Check if single node circular list
     *   b.x.i True, add the node and make the nodes point to each other to create the loop
     *   b.x.ii False
     *    b.x.ii.p Find the bounds of the loop based on values, start pointer points to lowest value
     *             and end pointer points to highest value
     *             Based on insertVal determine where to insert,
     *
     *              b.x.ii.p.l beginning if (insertval < start.val),
     *              b.x.ii.p.m end if (insertVal > end.val)
     *              b.x.ii.p.n else middle. Use the 2 pointer strategy
     *                         using current and prev pointer to mark the position of insertion.
     *
     * Time: O(N)
     * Space: O(1)
     */
    fun insert(head: Node?, insertVal: Int): Node? {
            var head: Node? = head
            val node = Node(insertVal)

            // Null list
            if (head == null) {
                head = node
                head.next = head
            } else {
                var current: Node? = head
                var prev: Node? = null
                var start: Node? = null
                var end: Node? = null

                // Single node circular list
                if (current?.next === current) {
                    node.next = current
                    current?.next = node
                } else {
                    // Multiple nodes circular list

                    // Find the starting and ending bounds of the loop based on value and not head pointer
                    while (current!!.next!!.`val` >= current.`val`) {
                        prev = current
                        if (current.next === head) break
                        current = current.next
                    }
                    end = current
                    start = current.next
                    current = start

                    // Insertion
                    when {
                        insertVal > end.`val` -> {              // Insertion at the end
                            node.next = end.next
                            end.next = node
                        }
                        insertVal <= start!!.`val` -> {    // Insertion at the front
                            node.next = start
                            start = node
                            end.next = start
                        }
                        else -> {                                // Insertion in the middle
                            current = start
                            prev = null
                            while (current!!.`val` < insertVal) {   // Find where to insert
                                prev = current
                                current = current.next
                            }
                            node.next = current
                            prev!!.next = node
                        }
                    }
                }
            }
            return head
        }
}