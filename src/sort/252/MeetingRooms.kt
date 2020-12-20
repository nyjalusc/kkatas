package sort.`252`

/**
 *
 */
class MeetingRooms {
    /**
     * Time: O(N log N)
     * Space: O(1)
     */
    fun canAttendMeetings(intervals: Array<IntArray>): Boolean {
        if(intervals.size <= 1) return true
        intervals.sortBy{ it.first() }
        for (i in 0 until intervals.lastIndex) {
            if(intervals[i].last() > intervals[i + 1].first()) return false
        }
        return true
    }
}