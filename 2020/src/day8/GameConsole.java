package day8;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import lib.Pair;

public class GameConsole {
  enum Op {
    acc,
    jmp,
    nop,
  }
  
  static class Instruction {
    public Op op;
    public int num;

    public Instruction(Op op, int num) {
      super();
      this.op = op;
      this.num = num;
    }
  }
  
  public static Pair<Integer, Integer> runInstruction(int instNum, Instruction ins) {
    int accMod = 0, nextInstNum;
    switch (ins.op) {
    case acc:
      accMod = ins.num;
      nextInstNum = instNum + 1;
      break;
      
    case jmp:
      nextInstNum = instNum + ins.num;
      break;
      
    case nop:
      nextInstNum = instNum + 1;
      break;
      
    default:
      throw new RuntimeException("Unknown instruction opcode");
    }
    
    return new Pair<Integer, Integer>(accMod, nextInstNum);
  }

  public static int detectLoopSimple(List<Instruction> is) {
    Set<Integer> visited = new HashSet<Integer>();
    int n = 0, acc = 0;
    while (!visited.contains(n)) {
      visited.add(n);
      var ai = runInstruction(n, is.get(n));
      
      acc += ai.item1;
      n = ai.item2;
    }

    return acc;
  }
  
  public static Optional<Integer> detectFixedLoop(List<Instruction> is) {
    Set<Integer> visited = new HashSet<Integer>();
    int n = 0, acc = 0;
    while (!visited.contains(n)) {
      visited.add(n);
      var ai = runInstruction(n, is.get(n));
      if (ai.item2 >= is.size())
        return Optional.of(acc += ai.item1);
      
      acc += ai.item1;
      n = ai.item2;
    }

    return Optional.empty();
  }
}