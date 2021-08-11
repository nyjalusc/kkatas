package linkedlist.lc.`1171`

import linkedlist.lc.ListNode

/**
 * https://leetcode.com/problems/remove-zero-sum-consecutive-nodes-from-linked-list/discuss/366350/C%2B%2B-O(n)-(explained-with-pictures)
 * Time: O(N)
 * Space: O(N) for using the HashMap
 */
fun removeZeroSumSublists(head: ListNode?): ListNode? {
    val root = ListNode(0).apply { next = head }
    var current: ListNode? = root

    val map = HashMap<Int, ListNode?>() // It implements MutableMap interface
    var prefix = 0

    while (current != null) {
        prefix += current.`val`

        // NOTE: The current node is processed and we found it removes N number
        // of previous nodes, so all the nodes between map[prefix] and current (inclusive)
        // need to be deleted. Deleting current is easy as it is not saved anywhere, we do
        // this by map[prefix]?.next = current.next and skip it. Whereas we need to
        // manually delete all the nodes between map[prefix].next and current. To delete these
        // bad nodes from the map we need to reconstruct the key which we do by recreating ac (key)
        if (map.containsKey(prefix)) {
            var left = map[prefix]?.next
            map[prefix]?.next = current.next

            // Delete intermediate nodes
            var ac = prefix
            while (left != current) {
                ac += left!!.`val`
                map.remove(ac)
                left = left.next
            }
        } else {
            map[prefix] = current
        }
        current = current.next
    }

    return root.next
}