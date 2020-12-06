package day6;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lib.Lib;

public class Main {
  
  public static void main(String[] args) throws IOException {
    Lib.runDay(6, Main::parseInput, Main::part1, Main::part2);
  }
  
  public static List<String> parseInput(String input) {
    return Arrays.asList(input.split("\n\n"));
  }
  
  public static int part1(List<String> inputs) {
    int sum = 0;
    for (String group : inputs) {
      Set<Character> as = new HashSet<Character>();
      as.addAll(toCharacterSet(group));
      as.remove('\n');
      sum += as.size();
    }

    return sum;
  }
  
  public static int part2(List<String> inputs) {
    int sum = 0;
    for (String group : inputs) {
      Set<Character> as = new HashSet<Character>();
      String[] persons = group.split("\n");
      as.addAll(toCharacterSet(persons[0]));

      for (int i = 1; i < persons.length; i++) {
        as.retainAll(toCharacterSet(persons[i]));
      }
      
      sum += as.size();
    }
    
    return sum;
  }
  
  public static Set<Character> toCharacterSet(String s) {
    Set<Character> cs = new HashSet<Character>();
    for (int i = 0; i < s.length(); i++) {
      cs.add(s.charAt(i));
    }
    
    return cs;
  }
}

