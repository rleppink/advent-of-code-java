package day3;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

import lib.Lib;

public class Main {
  
  public static void main(String[] args) throws IOException {
    Lib.runDay(3, Function.identity(), Main::part1, Main::part2);
  }
  
  public static int part1(String inputs) {
    int x = 0, y = 0;
    Set<String> hs = new HashSet<String>();
    for (int i = 0; i < inputs.length(); i++) {
      hs.add(x + "," + y);

      switch (inputs.charAt(i)) {
      case '^':
        y++;
        break;

      case '>':
        x++;
        break;
        
      case 'v':
        y--;
        break;
      
      case '<':
        x--;
        break;
        
      default:
        throw new RuntimeException("No match for: " + inputs.charAt(i));
      }
    }

    return hs.size();
  }
  
  /**
   * Well... It's not pretty. But hey, it's fast (O(N)), and it works!
   */
  public static int part2(String inputs) {
    int sx = 0, sy = 0, rx = 0, ry = 0;
    Set<String> hs = new HashSet<String>();
    for (int i = 0; i < inputs.length(); i++) {
      // Double presents don't matter
      hs.add(sx + "," + sy);
      hs.add(rx + "," + ry);

      switch (inputs.charAt(i)) {
      case '^':
        if (i % 2 == 0)
          sy++;
        else
          ry++;
        break;

      case '>':
        if (i % 2 == 0)
          sx++;
        else
          rx++;
        break;
        
      case 'v':
        if (i % 2 == 0)
          sy--;
        else
          ry--;
        break;
      
      case '<':
        if (i % 2 == 0)
          sx--;
        else
          rx--;
        break;
        
      default:
        throw new RuntimeException("No match for: " + inputs.charAt(i));
      }
    }

    return hs.size();
  }
}