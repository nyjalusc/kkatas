# Description
The Sliding Window pattern is used to perform a required operation on a specific window size of a given array or linked list, such as finding the longest subarray containing all 1s. Sliding Windows start from the 1st element and keep shifting right by one element and adjust the length of the window according to the problem that you are solving. In some cases, the window size remains constant and in other cases the sizes grows or shrinks.

#Types
1. Fixed window size 
2. Variable window size - Widen & shrink the window depending on the given condition

## How to identify
- The problem input is a linear data structure such as a linked list, array, or string
- You’re asked to find the longest/shortest substring, subarray, or a desired value 
- Window size may or may not be given [usually a case of variable window size] 
- If window size is given its a fixed window size problem, if its not given but a condition is given then its variable type and you have to find the window
- In variable window size problems result of an operation is given and you have to find the smallest/largest window of elements that will produce the given result.
  - The question will be reverse of a Fixed window problem
  - eg find the largest subarray with sum = 5, arr = [2, 4, 5, 1, 1, 1, 1, 1]
  - Some problems might need combination of list & maps to work with variable window size problems 

## Bruteforce
At every index compute the operation over window of size k. The computation over window elements
will be repeated

## Template for Fixed window problems
```kotlin
var i = 0 // window end boundary
var j = 0 // window start boundary

while (j < size) { // iterate over input
    // Process element at j 
    j++ // expand window 

    // Check if window size reached 
    if(j - i == k) {
        // Compute result
        
        // Slide the window
        i++
    }
}
return result
```
