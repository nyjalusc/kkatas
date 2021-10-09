package greedy.`277`

class FindTheCelebrity {

    fun findCelebrityBrute(n: Int): Int {
        var celebrityCandidate = 0
        for (i in 0 until n) {
            if (knows(celebrityCandidate, i)) {
                celebrityCandidate = i
            }
        }
        return if (isCelebrity(celebrityCandidate, n)) {
            celebrityCandidate
        } else -1
    }

    /**
     * Logical deduction:
     * Part 1:
     * i. If A knows B = true, A can't be celebrity
     * ii. If A knows B = false, B can't be celebrity
     * We start looping by considering 0th person as a potential celebrity candidate and if (celebCandidate knows i)
     * is true then we change the celebCandidate to i
     *
     * Part 2:
     * This is still not enough as we still need to find if candidates all n - 1 members know celebCandidate,
     * We use isCelebrity() for that
     *
     * Time: O(N) + O(N)
     * Space: O(1)
     */
    fun findCelebrity(n: Int): Int {
        var celebCandidate = 0
        for (i in 0 until n) {
            if (knows(celebCandidate, i)) {
                celebCandidate = i
            }
        }
        return if (isCelebrity(celebCandidate, n)) {
            celebCandidate
        } else -1
    }

    private fun isCelebrity(i: Int, numberOfPeople: Int): Boolean {
        for (j in 0 until numberOfPeople) {
            if (i == j) continue  // Don't ask if they know themselves.
            if (knows(i, j) || !knows(j, i)) {
                return false
            }
        }
        return true
    }

    private fun knows(i: Int, j: Int): Boolean {
        TODO("Not yet implemented")
    }
}