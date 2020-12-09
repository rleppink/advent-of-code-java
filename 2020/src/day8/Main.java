package day8;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lib.Lib;

public class Main {
  
  public static void main(String[] args) throws IOException {
    Lib.runDay(8, Main::parseInput, Main::part1, Main::part2);
  }
  
  public static List<GameConsole.Instruction> parseInput(String input) {
    return Arrays.asList(input.split("\n")).stream()
      .map(Main::parseLine)
      .collect(Collectors.toList());
  }
  
  public static int part1(List<GameConsole.Instruction> inputs) {
    return GameConsole.detectLoopSimple(inputs);
  }
  
  public static Optional<Integer> part2(List<GameConsole.Instruction> inputs) {
    // Just brute force check every possible flip
    // Currently no idea what a better way would be
    for (int i = 0; i < inputs.size(); i++) {
      var curInst = inputs.get(i);
      if (curInst.op == GameConsole.Op.acc)
        continue;
      
      var curOp = curInst.op;
      if (curInst.op == GameConsole.Op.nop) {
        curInst.op = GameConsole.Op.jmp;
      } else if (curInst.op == GameConsole.Op.jmp) {
        curInst.op = GameConsole.Op.nop;
      } else {
        throw new RuntimeException("Yo wtf");
      }

      var ret = GameConsole.detectFixedLoop(inputs);
      if (ret.isPresent())
        return ret;
      curInst.op = curOp;
    }

    return Optional.empty();
  }
  
  public static GameConsole.Instruction parseLine(String line) {
    var words = line.split(" ");
    return new GameConsole.Instruction(
      GameConsole.Op.valueOf(words[0]),
      Integer.parseInt(words[1]));
  }
}

