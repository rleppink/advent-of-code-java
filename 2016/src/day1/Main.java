package day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lib.Lib;

public class Main {

  public static void main(String[] args) throws IOException {
    Lib.runDay(1, Main::parser, Main::part1, Main::part2);
  }
  
  enum Direction {
    L,
    R
  }
  
  static class Instruction {
    public Direction direction;
    public int distance;
    
    public Instruction(Direction direction, int distance) {
      super();
      this.direction = direction;
      this.distance = distance;
    }
  }
  
  public static List<Instruction> parser(String input) {
    List<Instruction> is = new ArrayList<Instruction>();
    Pattern p = Pattern.compile("(?<direction>R)(?<distance>\\d+)");
    for (String s : input.split(", ")) {
      System.out.println("'" + s + "'");
      Matcher m = p.matcher(s);
      
      Direction dir = m.group("direction") == "L" ? Direction.L : Direction.R;
      int dist = Integer.parseInt(m.group("distance"));
      
      is.add(new Instruction(dir, dist));
    }
    
    return is;
  }

  public static int part1(List<Instruction> inputs) {
    return 0;
  }

  public static int part2(List<Instruction> inputs) {
    return 0;
  }
  
  
  
}
