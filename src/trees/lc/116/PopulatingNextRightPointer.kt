package trees.lc.`116`

import trees.lc.Node

/**
 * Key take away: Order in which nodes are added and removed is important.
 * In a traditional list objects are added at the end, there is no addAtFirst() but you can remove at
 * first() and last(). addAtfirst() is only available in ArrayDeque.
 *
 * To mimic exactly order of next level, right child is added at the end followed by left child.
 * While processing nodes are removed from the beginning. I used a prev pointer to assist next pointer write.
 *
 * Same solution works for Part II where the given tree is not a complete binary tree.
 */
class PopulatingNextRightPointer {
    fun connect(root: Node?): Node? {
        if (root == null) return null

        val queue = ArrayDeque<Node>()
        var current = root
        queue.addLast(current)
        var currentLevelCounter = 1

        while (!queue.isEmpty()) {
            var nextLevelCounter = 0
            var prev: Node? = null
            while (currentLevelCounter > 0) {
                current = queue.removeFirst()
                current.next = prev

                current.right?.also {
                    queue.addLast(it)
                    nextLevelCounter++
                }

                current.left?.also {
                    queue.addLast(it)
                    nextLevelCounter++
                }

                prev = current

                currentLevelCounter--
            }
            currentLevelCounter = nextLevelCounter
            println(queue.size)
        }

        return root
    }

    // Solution above is faster
    fun connectUsingLists(root: Node?): Node? {
        if (root == null) return null

        val queue = ArrayDeque<MutableList<Node>>()
        var current = root
        queue.addLast(mutableListOf(current))

        while (queue.isNotEmpty()) {
            val currentLevel = queue.removeFirst()
            var next: Node? = null
            while (currentLevel.isNotEmpty()) {
                current = currentLevel.removeAt(0) // removeAtFirstOrNull() not available in LC
                current.next = next

                var nextLevelList = queue.firstOrNull()
                if(nextLevelList == null) {
                    nextLevelList = mutableListOf()
                    queue.addLast(nextLevelList) // BUG: I was adding to the queue even if it was already existing list
                }

                current.right?.also { nextLevelList.add(it) }
                current.left?.also { nextLevelList.add(it) }
                next = current
            }
        }

        return root
    }
}