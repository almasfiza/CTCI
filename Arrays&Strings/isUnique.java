
/*
First question to ask - Is the string ASCII or Unicode?
ASCII is 128 characters in length and Unicode is 144697 characters.
ASCII represents 128 characters while Unicode represents 154 written scripts.
ASCII is a subset of Unicode or UTF-8 since the first 128 characters of Unicode point to ASCII characters.

Solution 1 - creating an array of boolean values - the flag at index i indicates if i is in the string. Return false whenever you encounter an element twice.

Also return false if the string is greater than the number of unique characters in the alphabet (128-characters).

Time - O(n) - can also argue that it is O(1) since it loops through 128 character boolean flags once.
Space - O(1)

Solution 2 - Reduce the space by factor of 8, by using a bit vector.
if((checker & (1 << val)) > 0). Assume that the string only contains the lower letters (26). By using the int (32) checker, storing the occurence of the element in each 26 out of 32 byte.
A single left shift multiplies a binary number by 2.
a = 0 i.e. `0000000000000000000000`
b = 1 i.e. `0000000000000000000001`
c = 2 i.e. `0000000000000000000010`
d = 4 i.e. `0000000000000000000100`
Now as seen above, a is 32 zeros, but rest of the characters have a single 1 and 31 zeros. So when we use these characters, we left shift each of them by 1, i.e. (1 << val), so each of them have a single 1 bit, and 31 zero bits:

Solution 3 - No additional data structures
a. Compare each character of the string to the other character - O(n^2) time complexity
b. If modification is allowed, sort the string and the check for neighbouring characters. O(nlog(n)) time complexity. However many sorting algos take extra space.

*/

import java.util.*;

class Main {
  public static boolean isUnique1(String str){
    if(str.length() > 128) return false; //if the length of the string is greater than the max number of distinct characters in alphabet.

    boolean[] char_set = new boolean[128];//boolean flags
    for(int i = 0; i < str.length(); i++){
      int val = str.charAt(i);//ascii value
      if(char_set[val]) return false;//if the flag is already true, return false
      char_set[val] = true; //set the flag to true
    }
    return true;//the control won't reach here if the characters are not distinct
  }
  public static boolean isUnique2(String str){
    int checker = 0; //checker of 32 bits to store the 0 or 1 for the occurence of a character in the 26 lower case letters.
    str = str.toLowerCase();
    for(int i = 0; i < str.length(); i++){
      int val = str.charAt(i) - 'a';//to fit the a-z in the 32 bits
      if((checker & (1 << val)) > 0) return false;
      checker |= (1 << val);
    }
    return true;
  }
  public static boolean isUnique3(String str){
    //to sort a string - convert to the charArray
    char[] ar = str.toCharArray();
    Arrays.sort(ar);
    str = new String(ar);
    //O(n^2) for loops nested
    for(int i = 0; i < str.length(); i++){
      for(int j = i+1; j < str.length(); j++){
        if(str.charAt(i) == str.charAt(j)) return false;
      }
    }
    return true;
    
  }
  public static void main(String[] args) {
    System.out.println("isUnique1(\"banana\"):"+isUnique1("banana"));
    System.out.println("isUnique2(\"banana\"):"+isUnique2("banana"));
    System.out.println("isUnique3(\"banana\"):"+isUnique3("banana"));
    System.out.println("isUnique1(\"abcdef\"):"+isUnique1("abcdef"));
    System.out.println("isUnique2(\"abcdef\"):"+isUnique2("abcdef"));
    System.out.println("isUnique3(\"abcdef\"):"+isUnique3("abcdef"));
  }
}
