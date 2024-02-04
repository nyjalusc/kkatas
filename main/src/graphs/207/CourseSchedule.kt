package graphs.`207`

import kotlin.test.assertFalse

class CourseSchedule {
    /**
     * 1. Build adjacency map that will keep track of edges/relationships, in this case prerequisites
     * 2. Iterate over each course by running dfs, which will use map to determine if the course can be completed.
     * 3. to avoid duplicate work if a course can be completed when the recursion unfolds make prerequisite list for the
     * current course as empty to indicate it can be completed. e.g if we have this relationship 0 -> 1 -> 2, when you process
     * course 0, you'll eventually do map[2] -> emptyList(), this indicates there is no prereq for 2 and hence it can be
     * completed which means so can 1 and 0. If you don't make map[1] empty, you'll process dfs(1) again which will call dfs(2)
     * 4. To catch loops keep track of visited nodes
     * TIme: O(N + P): N is the number of courses and P is the num of prereqs
     * Space: O(N + P)
     */
    fun canFinish(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
        // Init all courses with 0 prereqs
        val map = mutableMapOf<Int, MutableList<Int>>()
        for (i in 0..numCourses) map[i] = mutableListOf()

        // Process input
        for (prereq in prerequisites) map[prereq[0]]!!.add(prereq[1])

        val visitedSet = mutableSetOf<Int>()

        for (i in 0 until numCourses)
            if (!dfs(i, map, visitedSet)) return false

        return true
    }

    private fun dfs(course: Int, map: MutableMap<Int, MutableList<Int>>, visitedSet: MutableSet<Int>): Boolean {
        // This course has no prereqs
        if(map[course]!!.isEmpty()) return true
        // Check if there is a loop and we are currently exploring this course
        if(visitedSet.contains(course)) return false

        // Lets explore this course and checkout prereq conditions
        visitedSet.add(course)

        // Check if all prereqs can be completed
        for (prereq in map[course]!!) {
            if (!dfs(prereq, map, visitedSet)) return false
        }

        // We are done processing this course, we know it can be completed, since all prereqs can be completed
        visitedSet.remove(course)
        map[course]!!.clear()

        return true
    }
}