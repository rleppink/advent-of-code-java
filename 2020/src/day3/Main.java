package day3;

import java.io.IOException;

import lib.Lib;

public class Main {
  
  public static void main(String[] args) throws IOException {
    Lib.runDay(3, Main::parseFile, Main::part1, Main::part2);
  }
  
  public static Square[][] parseFile(String input) {
    String[] lines = input.split("\n");
    Square[] firstLine = parseLine(lines[0]);
    Square[][] grid = new Square[lines.length][firstLine.length];
    grid[0] = firstLine;
    for (int i = 1; i < lines.length; i++) {
      grid[i] = parseLine(lines[i]);
    }
    
    return grid;
  }
  
  public static int part1(Square[][] grid) {
    return traverse(grid, 3, 1);
  }
  
  public static long part2(Square[][] grid) {
    return
      (long)
      traverse(grid, 1, 1) *
      traverse(grid, 3, 1) *
      traverse(grid, 5, 1) *
      traverse(grid, 7, 1) *
      traverse(grid, 1, 2);
  }
  
  enum Square {
    Open,
    Tree
  }
  
  private static Square[] parseLine(String line) {
    Square[] squares = new Square[line.length()];
    for (int i = 0; i < line.length(); i++) {
      squares[i] = line.charAt(i) == ".".charAt(0)
        ? Square.Open
        : Square.Tree;
    }
    
    return squares;
  }
  
  private static int traverse(Square[][] grid, int xStep, int yStep) {
    int trees = 0;
    for (int x = 0, y = 0; y < grid.length; x += xStep, y += yStep) {
      if (grid[y][x % grid[0].length] == Square.Tree) 
        trees++;
    }
    
    return trees;
  }
}
