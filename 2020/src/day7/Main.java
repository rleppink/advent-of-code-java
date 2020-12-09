package day7;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lib.Lib;

public class Main {
  
  public static void main(String[] args) throws IOException {
    Lib.runDay(7, Main::parseInput, Main::part1, Main::part2);
  }
  
  public static Map<String, Map<String, Integer>> parseInput(String input) {
    var cs = new HashMap<String, Map<String, Integer>>();
    for (String line : input.split("\n")) {
      parseLine(line).stream()
        .forEach(c -> {
          // this gets called a bunch of times, but it should be O(1) right?
          cs.putIfAbsent(c.containerName, new HashMap<String, Integer>());
          
          var val = cs.get(c.name);
          if (val != null) {
            val.put(c.containerName, c.amount);
          } else {
            var h = new HashMap<String, Integer>();
            h.put(c.containerName, c.amount);
            cs.put(c.name, h);
          }
        }); 
    }
    
    return cs;
  }
  
  public static int part1(Map<String, Map<String, Integer>> inputs) {
    return listContainers(inputs, new HashSet<String>(), "shiny gold").size();
  }
  
  public static Set<String> listContainers(Map<String, Map<String, Integer>> cs, Set<String> posBags, String bagName) {
    var v = cs.get(bagName);
    for (String p : v.keySet()) {
      posBags.add(p);
      listContainers(cs, posBags, p);
    }
    
    return posBags;
  }
  
  public static int part2(Map<String, Map<String, Integer>> inputs) {
    // Hacky. Counts the shiny gold bag as a bag.
    return countContainees(inputs, "shiny gold") - 1;
  }
  
  /**
   * The inverted data structure was apparently a GREAT idea.
   */
  public static int countContainees(Map<String, Map<String, Integer>> cs, String name) {
    int bags = 1;  // need to subtract 1 at the end. nice.
    for (String c : cs.keySet()) {
      if (!cs.get(c).containsKey(name)) {
        continue;
      }

      int num = cs.get(c).get(name);
      bags += num * countContainees(cs, c);
    }
    
    return bags;
  }
  
  private static final Pattern containerPattern = 
      Pattern.compile("(?<container>\\w+ \\w+) bags contain (?<containees>.*)\\.");
  private static final Pattern containeePattern =
      Pattern.compile("(?<num>\\d) (?<name>\\w+ \\w+)");
  public static List<Containee> parseLine(String l) {
    Matcher containerMatcher = containerPattern.matcher(l);
    containerMatcher.matches();

    String containerName = containerMatcher.group("container");
    String containeeGroups = containerMatcher.group("containees");

    List<Containee> cs = new ArrayList<Containee>();
    Matcher containeeMatcher = containeePattern.matcher(containeeGroups);
    while (containeeMatcher.find()) {
      int num = Integer.parseInt(containeeMatcher.group("num"));
      String containeeName = containeeMatcher.group("name");
      cs.add(new Containee(containeeName, containerName, num));
    }
    
    return cs;
  }
  
  static class Containee {
    public String name;
    public String containerName;
    public int amount;

    public Containee(String name, String containerName, int amount) {
      super();
      this.name = name;
      this.containerName = containerName;
      this.amount = amount;
    }
  }
}

