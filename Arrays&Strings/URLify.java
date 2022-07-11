/*
Modify the string from end to the beginning because the end of the string has spaces.
1. Scan first to find the number of spaces. Multiply by 3- we know now how many extra characters are needed.
2. Second scan - reverse order, edit the string. whenever we see space, replace with %20.
*/class Main {
  public static String URLify(char[] str, int trueLen){
    int spaces = 0;
    for(int i = 0; i < trueLen; i++){
      if(str[i] == ' ') spaces++;
    }
    int index = trueLen + (spaces*2); //last index
    if(trueLen < str.length)
      str[index] = '\0'; //ending the array if no spaces
    for(int i = trueLen-1; i >= 0; i--){
      if(str[i] == ' '){
        str[index - 1] = '0';
        str[index - 2] = '2';
        str[index - 3] = '%';
        index = index - 3;
      }
      else{
        str[index - 1] = str[i];
        index--;
      }
    }
    return new String(str);
    
  }
  public static void main(String[] args) {
    System.out.println("URLify(\"Mr John Smith    \",13): "+URLify(("Mr John Smith      ").toCharArray(), 13));
  }
}
