package strings.cw

/**

Given two arrays of strings a1 and a2 return a sorted array r in lexicographical order of the strings of a1 which are substrings of strings of a2.

 #Example 1: a1 = ["arp", "live", "strong"]

 a2 = ["lively", "alive", "harp", "sharp", "armstrong"]

 returns ["arp", "live", "strong"]

 #Example 2: a1 = ["tarp", "mice", "bull"]

 a2 = ["lively", "alive", "harp", "sharp", "armstrong"]

 returns []
 Notes:

     Arrays are written in "general" notation. See "Your Test Cases" for examples in your language.

     In Shell bash a1 and a2 are strings. The return is a string where words are separated by commas.

     Beware: r must be without duplicates.
     Don't mutate the inputs.


 */

// My solution: iterate over both list using for loop and compare (in a way filter)
// Things that matched were put in the result list
fun inArray(array1: Array<String>, array2: Array<String>): Array<String> {
  val result = mutableListOf<String>()
  array1.forEach { str1 ->
      array2.forEach { str2 ->
          if (str2.contains(str1)) {
              result.add(str1)
          }
      }
  }
  return result.toSet().sorted().toTypedArray()
}

// *Filter* elements from array1 that match a criteria
// Criteria is that given a str from array1 if it a substring of *any* string in array2 we filter it
// We also want all elements to be *distinct* (no dups)
fun inArray2(array1: Array<String>, array2: Array<String>): Array<String> = array1.filter { str1 -> array2.any { it.contains(str1) } }.distinct().sorted().toTypedArray()
