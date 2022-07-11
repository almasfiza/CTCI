/*
Permuation is rearrangement of letters.
Questions to ask:
Is the permutation case sensitive?
Is white space significant?

Rule out - Note that the strings of different lengths cannot be permutations of each other. 

Solution 1: Sort the strings and compare each of them s1.equals(s2).
Time complexity is O(nlog(n)). This algorithm is clean, simple and easy to understand. However if efficiency is important, go to next solution.

Solution 2: Check if two strings have identical character counts- ascii or unicode boolean flags 128 bits. O(n) and space is O(1)

*/

import java.util.*;
class Main {
  //function to sort the string
  public static String sort(String str){
    //convert to char array first
    char[] charArr = str.toCharArray();
    //sort using inbuilt function
    Arrays.sort(charArr);
    //revert to string
    return new String(charArr);
  }
  public static boolean checkPermutation1(String s1, String s2){
    if(s1.length() != s2.length()) return false;//ruling out
    return sort(s1).equals(sort(s2));
  }
  public static boolean checkPermutation2(String s1, String s2){
    if(s1.length() != s2.length()) return false;
    int[] letters = new int[128];//assuming ascii
    //convert the s1 into char array and then modify the letters flags depending on the frequncy
    char[] charArr = s1.toCharArray();
    for(char c: charArr) 
      letters[c]++;
  
    for(int i = 0; i < s2.length(); i++){
      int c = (int)s2.charAt(i);
      letters[c]--;
      if(letters[c] < 0) return false;
    }
    return true;
    
  }
  public static void main(String[] args) {
    System.out.println("checkPermutation1(\"anna\",\"nnaa\"):"+checkPermutation1("anna", "nnaa"));
    System.out.println("checkPermutation1(\"anna\",\"hnnaa\"):"+checkPermutation1("anna", "hnnaa"));
    System.out.println("checkPermutation1(\"anna\",\"nmaa\"):"+checkPermutation1("anna", "nmaa"));
    System.out.println("checkPermutation2(\"anna\",\"nnaa\"):"+checkPermutation2("anna", "nnaa"));
    System.out.println("checkPermutation2(\"anna\",\"hnnaa\"):"+checkPermutation2("anna", "hnnaa"));
    System.out.println("checkPermutation2(\"anna\",\"nmaa\"):"+checkPermutation2("anna", "nmaa"));

  }
}
