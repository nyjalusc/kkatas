package arrays.lc.`11`

import kotlin.math.max
import kotlin.math.min

class ContainerMostWater {
    fun maxArea(height: IntArray): Int {
        var maxArea = 0
        var i = 0
        var j = height.lastIndex

        while(i < j) {
            maxArea = max(maxArea, (j - i) * min(height[i], height[j]))
            if (height[i] < height[j]) i++ else j--
        }

        return maxArea
    }
}