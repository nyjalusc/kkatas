package dp.`322`

import kotlin.math.min

class CoinChange {
    /**
     * Function to find the fewest number of coins needed to make up a given amount.
     * The function iterates over each sub-amount up to the target amount, updating the dp array with the minimum coins needed for each sub-amount.
     * If the amount cannot be reached with the available coins, the function returns -1.
     * This is a classic dynamic programming problem.
     * Time complexity: O(n * m), where n is the 'amount' and m is the number of coins.
     * Space complexity: O(n), where n is the 'amount'.
     */
    fun coinChange(coins: IntArray, amount: Int): Int {
        // dp array to store the minimum number of coins needed for each amount up to 'amount'
        val dp = Array(amount + 1) { amount + 1 }

        // Base case: No coins needed to make amount 0
        dp[0] = 0

        // Iterate through each amount from 1 to 'amount'
        for (i in 1..amount) {
            // Iterate through each coin value
            for (coin in coins) {
                // Check if the coin value is less than or equal to the current amount 'i'
                if (coin <= i) {
                    // Update dp[i] to the minimum of its current value and the number of coins needed to make amount i - coin value + 1
                    dp[i] = min(dp[i], 1 + dp[i - coin])
                }
            }
        }

        // If dp[amount] is not updated, return -1, indicating it's not possible to make the amount with the given coins
        return if (dp[amount] == (amount + 1)) -1 else dp[amount]
    }
}