package lib;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
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
  
  public static <T, TRes> void runDay(
      int day,
      Function<String, List<T>> parser,
      Function<List<T>, TRes> part1,
      Function<List<T>, TRes> part2) throws IOException {
    String fileContent = Files.readString(Path.of("inputs/day" + day));
    List<T> parsed = parser.apply(fileContent);
    
    Lib.printResultWithExecutionTime(() -> part1.apply(parsed), "Part 1; ");
    Lib.printResultWithExecutionTime(() -> part2.apply(parsed), "Part 2; ");
  }
}