package trie.`208`

class ImplementTrie {
    // Inner class representing a node in the trie
    private class TreeNode {
        // Map to hold children nodes. Each character points to a TreeNode.
        val children = HashMap<Char, TreeNode>()
        // Flag to indicate if the node represents the end of a word
        var isEndOfWord = false
    }

    // The root of the trie. It does not hold any character.
    private val root: TreeNode = TreeNode()

    // Function to insert a word into the trie
    fun insert(word: String) {
        var current = root
        // Iterate over each character in the word
        for (c in word) {
            // If the character is not already a child of the current node, add it
            current.children.computeIfAbsent(c) { TreeNode() }
            // Move to the child node
            current = current.children[c]!!
        }
        // Mark the last node as the end of a word
        current.isEndOfWord = true
    }

    // Function to search for a word in the trie
    fun search(word: String): Boolean {
        var current = root
        // Iterate over each character in the word
        for (c in word) {
            // If the character is not in the children of the current node, return false
            current = current.children[c] ?: return false
        }
        // Return true if the last node is marked as the end of a word
        return current.isEndOfWord
    }

    // Function to check if there is any word in the trie that starts with the given prefix
    fun startsWith(prefix: String): Boolean {
        var current = root
        // Iterate over each character in the prefix
        for (c in prefix) {
            // If the character is not in the children of the current node, return false
            current = current.children[c] ?: return false
        }
        // Return true if the prefix is a valid path in the trie
        return true
    }
}