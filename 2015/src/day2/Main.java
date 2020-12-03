package day2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lib.Lib;

public class Main {
  
  public static void main(String[] args) throws IOException {
    Lib.runDay(2, Main::parseFile, Main::part1, Main::part2);
  }
  
  public static List<Dim> parseFile(String input) {
    Pattern p = Pattern.compile("(?<l>\\d+)x(?<w>\\d+)x(?<h>\\d+)");
    List<Dim> dims = new ArrayList<Dim>();
    for (String s : input.split("\n")) {
      dims.add(parseLine(s, p));
    }
    
    return dims;
  }
  
  public static int part1(List<Dim> inputs) {
    int rt = 0;
    for (Dim d : inputs) {
      int lw = d.l * d.w;
      int wh = d.w * d.h;
      int hl = d.h * d.l;
      rt += (lw * 2) + (wh * 2) + (hl * 2) + Collections.min(Arrays.asList(lw, wh, hl));
    }
    
    return rt;
  }
  
  public static long part2(List<Dim> inputs) {
    long rt = 0;
    for (Dim d : inputs) {
      rt += 
        smallestPerimeter(d.l, d.w, d.h) + // ribbon
        Lib.inspect((d.l * d.w * d.h)); // bow
    }
    
    return rt;
  }
  
  static class Dim {
    public int l;
    public int w;
    public int h;
    
    public Dim(int l, int w, int h) {
      super();
      this.l = l;
      this.w = w;
      this.h = h;
    }
  }
  
  public static Dim parseLine(String line, Pattern p) {
    Matcher m = p.matcher(line);
    
    if (!m.matches())
      // This would be a programming bug, let me know as soon as possible.
      throw new RuntimeException("Could not parse line: " + line);
    
    return new Dim(
      Integer.parseInt(m.group("l")),
      Integer.parseInt(m.group("w")),
      Integer.parseInt(m.group("h")));
  }
  
  public static int smallestPerimeter(Integer... is) {
    List<Integer> lis = Arrays.asList(is);
    Collections.sort(lis);
    
    return (lis.get(0) * 2) + (lis.get(1) * 2);
  }
}
