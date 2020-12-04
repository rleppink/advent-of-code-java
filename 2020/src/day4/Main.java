package day4;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lib.Lib;

public class Main {
  
  public static void main(String[] args) throws IOException {
    Lib.runDay(4, Main::parseFile, Main::part1, Main::part2);
  }
  
  public static List<String> parseFile(String input) {
    return Arrays.asList(input.split("\n\n"));
  }
  
  public static int part1(List<String> inputs) {
    int valid = 0;
    for (String s : inputs)
      if (containsMandatory(s))
        valid++;

    return valid;
  }
  
  public static int part2(List<String> inputs) {
    int valid = 0;
    for (String s : inputs) {
      if (!containsMandatory(s))
        continue;
      
      String[] fields = s.replace("\n", " ").trim().split(" ");
      
      boolean v = true;
      for (String field : fields) {
        String[] kv = field.split(":");
        v = validateField(kv[0].trim(), kv[1].trim());

        if (!v)
          break;
      }

      if (v)
        valid++;
    }
    
    return valid;
  }
  
  /* Helpers */
  public static String[] mandatoryFields = new String[] { "byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid" };
  public static boolean containsMandatory(String s ) {
    boolean cm = true;
    for (String mf : mandatoryFields) {
      if (s.contains(mf))
        continue;
      
      cm = false;
      break;
    }
    
    return cm;
  }
  
  public static boolean validateField(String key, String value) {
    Pattern p;
    Matcher m;

    switch (key) {
    case "byr":
      int byr = Integer.parseInt(value);
      return byr >= 1920 && byr <= 2002;
      
    case "iyr":
      int iyr = Integer.parseInt(value);
      return iyr >= 2010 && iyr <= 2020;
      
    case "eyr":
      int eyr = Integer.parseInt(value);
      return eyr >= 2020 && eyr <= 2030;
      
    case "hgt":
      p = Pattern.compile("(?<hgt>\\d+)(?<un>\\w*)");
      m = p.matcher(value);
      if (!m.matches())
        return false;
      
      int hgt = Integer.parseInt(m.group("hgt"));
      String un = m.group("un");
      
      if (un.equals("cm"))
        return hgt >= 150 && hgt <= 193;
      else if (un.equals("in"))
        return hgt >= 59 && hgt <= 76;
      else {
        return false;
      }
        
    case "hcl":
      p = Pattern.compile("#\\w{6}");
      m = p.matcher(value);
      return m.matches();
      
    case "ecl":
      return Arrays
        .asList(new String[] { "amb", "blu", "brn", "gry", "grn", "hzl", "oth" })
        .contains(value);
          
    case "pid":
      p = Pattern.compile("\\d{9}");
      m = p.matcher(value);
      return m.matches();
      
    case "cid":
      return true;
      
    default:
      return false;
    }
  }
}

