package trees.lc.`366`

import trees.lc.TreeNode
import kotlin.math.max

class FindLeavesOfBinaryTree {
    /**
     * Time: O(N)
     * Space: O(N)
     */
    fun findLeaves(root: TreeNode?): List<List<Int>> {
        val result = mutableListOf<MutableList<Int>>()
        if(root == null) return result

        val map = mutableMapOf<Int, MutableList<Int>>()
        getHeight(root, map)

//        map.entries.sortedBy { it.key }.map {
//            result.add(it.value)
//        }

        // We can get away without sorting because the levels of the tree can be accessed in natural order
        for (i in 0 until map.size) {
            result.add(map[i]!!)
        }
        return result
    }

    private fun getHeight(root: TreeNode?, map: MutableMap<Int, MutableList<Int>>): Int {
        if(root == null) return -1

        val currentHeight = max(getHeight(root.left, map), getHeight(root.right, map)) + 1

        // Remove child
        root.left = null
        root.right = null

        // Add current node val to the map based on currentHeight key
        val list = map.getOrDefault(currentHeight, mutableListOf())
        list.add(root.`val`)
        map[currentHeight] = list

        return currentHeight
    }


}