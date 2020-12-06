package day5;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lib.Lib;

public class Main {
  
  public static void main(String[] args) throws IOException {
    Lib.runDay(5, Main::parseFile, Main::part1, Main::part2);
  }
  
  public static List<String> parseFile(String input) {
    return Arrays.asList(input.split("\n"));
  }
  
  public static int part1(List<String> inputs) {
    List<String> naughtyStrings = Arrays.asList(new String[] { "ab", "cd", "pq", "xy" });
    int nice = 0;
    for (String line : inputs) {
      int vowels = 0;
      char lastChar = Character.MIN_VALUE; // taking a guess this won't be in the input
      boolean twice = false;
      boolean naughty = false;
      
      for (int i = 0; i < line.length(); i++) {
        char cur = line.charAt(i);
        if ("aeiou".indexOf(cur) != -1) 
          vowels++;
        
        if (i == 0) {
          lastChar = cur;
          continue;
        }
        
        if (naughtyStrings.contains(new StringBuilder().append(lastChar).append(cur).toString())) {
          naughty = true;
          break;
        }

        if (cur == lastChar)
          twice = true;
        
        lastChar = cur;
      }
      
      if (!naughty && (vowels >= 3) && twice)
        nice++;
    }
    
    return nice;
  }
  
  public static long part2(List<String> inputs) {
    return inputs.stream()
      .filter(s -> twoLettersNonOverlapping(s))
      .filter(s -> repeatsOneLetterBetween(s))
      .count();
  }
  
  /**
   * Works, but not very elegant...
   */
  public static boolean twoLettersNonOverlapping(String s) {
    Set<String> pairs = new HashSet<String>();
    for (int i = 0; i < s.length() - 1; i++) {
      String ss = s.substring(i, i+2);
      if (pairs.contains(ss))
        return true;
      
      pairs.add(ss);
      
      if (s.length() - i <= 2) 
        continue;
      
      if (s.charAt(i) == s.charAt(i+1) && s.charAt(i+1) == s.charAt(i+2))
        i++;
    }
    
    return false;
  }
  
  public static boolean repeatsOneLetterBetween(String s) {
    for (int i = 0; i < s.length() - 2; i++) {
      if (s.charAt(i) == s.charAt(i + 2)) {
        return true;
      }
    }
    
    return false;
  }
}
