package day3;

import java.io.IOException;

import lib.Lib;

public class Main {
  
  public static void main(String[] args) throws IOException {
    Lib.runDay(3, Main::parseFile, Main::part1, Main::part2);
  }
  
  public static String[] parseFile(String input) {
    return input.split("\n");
  }
  
  public static int part1(String[] lines) {
    return traverse(lines, 3, 1);
  }
  
  public static long part2(String[] lines) {
    return
      (long)
      traverse(lines, 1, 1) *
      traverse(lines, 3, 1) *
      traverse(lines, 5, 1) *
      traverse(lines, 7, 1) *
      traverse(lines, 1, 2);
  }
  
  private static int traverse(String[] lines, int xStep, int yStep) {
    int trees = 0;
    for (int x = 0, y = 0; y < lines.length; x += xStep, y += yStep) {
      if (lines[y].charAt(x % lines[0].length()) == "#".charAt(0))
        trees++;
    }
    
    return trees;
  }
}
