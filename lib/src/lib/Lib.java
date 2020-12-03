package lib;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Function;
import java.util.function.Supplier;

public class Lib {
  /**
   * Execute and time the given function f, print its result, and the execution
   * time.
   * 
   * @param <T> The type of result that is produced by the given function `f`
   * @param f A function that produces a result when called
   * @param prefix A prefix to print in front of the result for identification
   */
  public static <T> T printResultWithExecutionTime(Supplier<T> f, String prefix) {
    long startTime = System.nanoTime();
    T result = f.get();
    long endTime = System.nanoTime();
    
    System.out.println(prefix + result);
    System.out.println("\t...in " + ((endTime - startTime) / (float)1000000) + "ms\n");
    
    return result;
  }
  
  /**
   * Count the amount of chars c in string s.
   * 
   * @param s The string to count chars in
   * @param c The char to count
   * @return The amount of times the char c was found in string s
   */
  public static int countChars(String s, char c) {
    int count = 0;
    for (int i = 0; i < s.length(); i++)
      if (s.charAt(i) == c) 
        count += 1;
    
    return count;
  }
  
  /**
   * Run a generalized advent of code day. Every day has the same characteristics:
   * 1. Read in that day's file
   * 2. Parse the file to a workable data structure
   * 3. Pass that data structure to parts 1 and 2
   * 4. Print their results
   * 
   * @param <T> The type of the parsed data structure
   * @param <TRes> The type of the puzzle answers
   * @param day The day number
   * @param parser A function to parse an input string to a data structure
   * @param part1 A function to solve part 1
   * @param part2 A function to solve part 2
   * @throws IOException When the file cannot be read
   */
  public static <T, TRes> void runDay(
      int day,
      Function<String, T> parser,
      Function<T, TRes> part1,
      Function<T, TRes> part2) throws IOException {
    String fileContent = Files.readString(Path.of("inputs/day" + day));
    T parsed = parser.apply(fileContent);
    
    Lib.printResultWithExecutionTime(() -> part1.apply(parsed), "Part 1; ");
    Lib.printResultWithExecutionTime(() -> part2.apply(parsed), "Part 2; ");
  }
  
  /**
   * Print and then return the given value T. Quick peek, useful for debugging,
   * can be used in call chains.
   * 
   * @param <T> The type of the given value
   * @param t The value to print
   * @return The original value
   */
  public static <T> T inspect(T t) {
    System.out.println(t);
    return t;
  }
}