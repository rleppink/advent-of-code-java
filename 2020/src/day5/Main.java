package day5;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lib.Lib;

public class Main {
  
  public static void main(String[] args) throws IOException {
    Lib.runDay(5, Main::parseInput, Main::part1, Main::part2);
  }
  
  public static List<String> parseInput(String input) {
    return Arrays.asList(input.split("\n"));
  }
  
  public static Optional<Integer> part1(List<String> inputs) {
    return inputs.stream()
      .map(Main::parsePass)
      .max((a, b) -> a.seatID >= b.seatID ? 1 : -1)
      .map(c -> c.seatID);
  }
  
  public static Optional<Integer> part2(List<String> inputs) {
    List<Integer> is = inputs.stream()
      .map(Main::parsePass)
      .map(p -> p.seatID)
      .sorted()
      .collect(Collectors.toList());
    
    // There are more fancy algo's for this, but a simple loop is fine.
    int start = is.get(0);
    for (int i = 0; i < is.size(); i++)
      if (i + start != is.get(i)) 
        return Optional.of(i + start);
    
    return Optional.empty();
  }
  
  static class Pass {
    public String pass;
    public int row;
    public int column;
    public int seatID;

    public Pass(String pass, int row, int column) {
      super();
      this.pass = pass;
      this.row = row;
      this.column = column;
      this.seatID = row * 8 + column;
    }
  }
  
  public static Pass parsePass(String s) {
    double min = 0, max = 127;
    for (int i = 0; i < 6; i++) {
      if (s.charAt(i) == 'F') {
        max = Math.floor((min + max) / 2);
      } else {
        min = Math.ceil((min + max) / 2);
      }
    }
    
    int row = s.charAt(6) == 'F'
      ? (int) min
      : (int) max;
      
    min = 0; max = 7;
    for (int i = 7; i < 9; i++) {
      if (s.charAt(i) == 'L') {
        max = Math.floor((min + max) / 2);
      } else {
        min = Math.ceil((min + max) / 2);
      }
    }
    
    int col = s.charAt(9) == 'L'
      ? (int) min
      : (int) max;
    
    return new Pass(s, row, col);
  }
}
