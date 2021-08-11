package linkedlist.lc.`707`

/**
 *
 */
class MyLinkedList() {

    /** Initialize your data structure here. */
    data class Node(val value: Int, var next: Node? = null)

    // head always points to the dummy node
    private var head: Node = Node(-1)

    // tail always points to the last element
    private var tail = head

    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    fun get(index: Int): Int {
        var curr = head.next
        repeat(index) {
            curr = curr?.next
        }
        return curr?.value ?: -1
    }

    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    fun addAtHead(`val`: Int) {
        val curr = Node(`val`)
        curr.next = head.next
        if (head == tail) tail = curr
        head.next = curr
    }

    /** Append a node of value val to the last element of the linked list. */
    fun addAtTail(`val`: Int) {
        val curr = Node(`val`)
        tail.next = curr
        tail = curr
    }

    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    fun addAtIndex(index: Int, `val`: Int) {
        var prev: Node? = head
        val curr = Node(`val`)
        repeat(index) {
            prev = prev?.next
        }
        curr.next = prev?.next
        prev?.next = curr

        if (prev == tail) tail = curr
    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    fun deleteAtIndex(index: Int) {
        var prev: Node? = head
        repeat(index) {
            prev = prev?.next
        }
        prev?.next = prev?.next?.next
        if (prev != null && prev!!.next == null) tail = prev!!
    }

}
