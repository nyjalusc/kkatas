package greedy.`605`

/**
 *
 */
class CanPlaceFlowers {
    fun canPlaceFlowers(flowerbed: IntArray, n: Int): Boolean {
        if(n == 0) return true
        var n = n
        var i = 0
        while(i < flowerbed.size) {
            if(flowerbed[i] == 1) {
                i += 2
            } else {
                if((i == flowerbed.lastIndex && flowerbed[i] == 0) || (i + 1 < flowerbed.size && flowerbed[i + 1] == 0)) {
                    flowerbed[i] = 1
                    n--
                    if(n == 0) return true
                    i += 2
                } else {
                    i += 3
                }
            }

        }

        return false
    }

    /**
     * Check one element before and after if it is 0. Keep check of the start and end bounds.
     * Time: O(N)
     * Space: O(1)
     */
    fun canPlaceFlowersEasy(flowerbed: IntArray, n: Int): Boolean {
        if(n == 0) return true
        if(flowerbed.size < n) return false
        var n = n
        flowerbed.forEachIndexed { index, value ->
            if(value == 1) return@forEachIndexed // this is like continue

            when(index) {
                // First element
                0 -> {
                    if((flowerbed.size > 1 && flowerbed[1] == 0) || flowerbed.size == 1) {
                        flowerbed[index] = 1
                        n--
                    }
                }
                // Last element
                flowerbed.lastIndex -> {
                    if((flowerbed.size > 1 && flowerbed[index - 1] == 0) || flowerbed.size == 1) {
                        flowerbed[index] = 1
                        n--
                    }
                }
                // Middle elements
                else -> {
                    if (index - 1 >= 0 && index + 1 < flowerbed.size && flowerbed[index - 1] == 0 && flowerbed[index + 1] == 0) {
                        flowerbed[index] = 1
                        n--
                    }
                }
            }

            if(n == 0) return true
        }
        return false
    }
}