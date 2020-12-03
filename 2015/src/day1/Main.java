package day1;

import java.io.IOException;
import java.util.Optional;
import java.util.function.Function;

import lib.Lib;

public class Main {
  
  public static void main(String[] args) throws IOException {
    Lib.runDay(1, Function.identity(), Main::part1, Main::part2);
  }
  
  public static int part1(String inputs) {
    int floor = 0;
    for (int i = 0; i < inputs.length(); i++) {
      floor += inputs.charAt(i) == "(".charAt(0) ? 1 : -1;
    }
    
    return floor;
  }
  
  public static Optional<Integer> part2(String inputs) {
    int floor = 0;
    for (int i = 0; i < inputs.length(); i++) {
      floor += inputs.charAt(i) == "(".charAt(0) ? 1 : -1;
      if (floor <= -1)
        return Optional.of(i + 1);
    }
    
    return Optional.empty();
  }
}
