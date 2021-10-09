package design.`432`


private class Node(var freq: Int) {
    var prev: Node? = null
    var next: Node? = null
    var keys: MutableSet<String> = HashSet()
}

/**
 * The solution involves maintaining two DS: a doubly LL and a hashmap
 * The DLL consists of nodes that contain frequency (count of strings) and set of all strings with the same count.
 * It is arranged in sorted manner in such a way that head points to node containing set of strings with lowest frequency
 * and tail the highest.
 *
 * Keeping the DLL sorted means that every inc() and dec() operation will alter the position of strings in the nodes.
 * Since insert and delete operations are O(1) for DLL this is a great choice.
 *
 * Getting the correct Node given a string is difficult when using a DLL alone, because it will take O(N) time.
 * A better alternative is to maintain a map of <string, Node>, this will quickly give us the reference, and since the
 * operations inc() and dec() can alter the frequency count by only +/- 1, its relative position in the DLL can shift
 * by only one. This means it will be moved to a neighboring node, this can be done in O(1) in a DLL.
 *
 * We overcome the limitations of DLL lookup by combining it with HashMap
 *
 * All Operations are O(1)
 *
 * For Graphical Explanation checkout: https://leetcode.com/problems/all-oone-data-structure/discuss/731468/HashMap-%2B-DoublyLinkedList-strategy
 */
class AllOne {
    private var head: Node? = null
    private var tail: Node? = null
    private var map: MutableMap<String, Node?> = HashMap()

    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. </Key> */
    fun inc(key: String) {
        if (map.containsKey(key)) {
            val node = map[key]
            val freq = node!!.freq
            node.keys.remove(key)
            if (node.next == null) {
                val newNode = Node(freq + 1)
                node.next = newNode
                newNode.prev = node
                newNode.keys.add(key)
                map[key] = newNode
                tail = newNode
            } else {
                val next = node.next
                if (next!!.freq - freq > 1) {
                    val newNode = Node(freq + 1)
                    newNode.keys.add(key)
                    node.next = newNode
                    newNode.prev = node
                    newNode.next = next
                    next.prev = newNode
                    map[key] = newNode
                } else {
                    next.keys.add(key)
                    map[key] = next
                }
            }
            if (node.keys.size == 0) {
                removeNode(node)
            }
        } else { // map does not contains the key
            if (head == null) {
                head = Node(1)
                head!!.keys.add(key)
                tail = head
            } else {
                if (head!!.freq == 1) {
                    head!!.keys.add(key)
                } else {
                    val newNode = Node(1)
                    newNode.keys.add(key)
                    newNode.next = head
                    head!!.prev = newNode
                    head = newNode
                }
            }
            map[key] = head
        }
    }

    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure.  */
    fun dec(key: String) {
        if (!map.containsKey(key)) {
            return
        }
        val node = map[key]
        node!!.keys.remove(key)
        val freq = node.freq
        if (freq == 1) {
            map.remove(key)
        } else if (node === head) {
            val newNode = Node(freq - 1)
            newNode.keys.add(key)
            newNode.next = head
            head!!.prev = newNode
            head = newNode
            map[key] = head
        } else {
            val prev = node.prev
            if (freq - prev!!.freq == 1) {
                prev.keys.add(key)
                map[key] = prev
            } else {
                val newNode = Node(freq - 1)
                prev.next = newNode
                newNode.prev = prev
                newNode.next = node
                node.prev = newNode
                newNode.keys.add(key)
                map[key] = newNode
            }
        }
        if (node.keys.size == 0) {
            removeNode(node)
        }
    }

    /** Returns one of the key with Max frequency **/
    fun getMaxKey(): String {
        return if (head == null) "" else tail!!.keys.first()
    }

    /** Returns one of the key with Min frequency **/
    fun getMinKey(): String {
        return if (head == null) "" else head!!.keys.first()
    }

    private fun removeNode(node: Node?) {
        if (node === head) {
            head = head!!.next
            node!!.next = null
            if (head != null) {
                head!!.prev = null
            }
        } else if (node === tail) {
            tail = tail!!.prev
            node!!.prev = null
            if (tail != null) {
                tail!!.next = null
            }
        } else {
            node!!.prev!!.next = node.next
            node.next!!.prev = node.prev
            node.prev = null
            node.next = null
        }
    }
}