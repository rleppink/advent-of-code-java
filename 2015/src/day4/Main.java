package day4;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import lib.Lib;

public class Main {
  
  public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
    md = MessageDigest.getInstance("MD5"); // don't do this every time
    Lib.runDay(4, s -> s.trim(), Main::part1, Main::part2);
  }
  
  public static int part1(String input) {
    for (int i = 1;; i++) {
      byte[] md5 = md5(input + i);
      String hex = bytesToHex(md5);
      if (hex.startsWith("00000"))
        return i;
    }
  }
  
  public static int part2(String input) {
    for (int i = 1;; i++) {
      byte[] md5 = md5(input + i);
      String hex = bytesToHex(md5);
      if (hex.startsWith("000000"))
        return i;
    }
  }
  
  private static MessageDigest md;
  public static byte[] md5(String s) {
    return md.digest(s.getBytes(StandardCharsets.UTF_8));
  }
  
  private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
  public static String bytesToHex(byte[] bytes) {
      char[] hexChars = new char[bytes.length * 2];
      for (int j = 0; j < bytes.length; j++) {
          int v = bytes[j] & 0xFF;
          hexChars[j * 2] = HEX_ARRAY[v >>> 4];
          hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
      }
      return new String(hexChars);
  }
}

