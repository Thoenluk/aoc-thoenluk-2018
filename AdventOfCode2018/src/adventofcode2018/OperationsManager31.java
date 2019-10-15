package adventofcode2018;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author Lukas Thöni
 */
public class OperationsManager31 {

    protected static final String ADDR = "addr", ADDI = "addi", MULR = "mulr", MULI = "muli",
            BANR = "banr", BANI = "bani", BORR = "borr", BORI = "bori", SETR = "setr",
            SETI = "seti", GTIR = "gtir", GTRI = "gtri", GTRR = "gtrr", EQIR = "eqir",
            EQRI = "eqri", EQRR = "eqrr";
    protected static final ArrayList<String> opcodes = new ArrayList<>(Stream.of(
            ADDR, ADDI, MULR, MULI, BANR, BANI, BORR, BORI, SETR, SETI, GTIR, GTRI, GTRR,
            EQIR, EQRI, EQRR).collect(Collectors.toList()));

    public static Set<String> checkAllOperations(long[] before, int[] operation, long[] after) {
        HashSet<String> possibleOperations = new HashSet<>();
        long[] beforeClone;
        for (String opcode : opcodes) {
            beforeClone = before.clone();
            if (checkOperation(beforeClone, operation, after, opcode)) {
                possibleOperations.add(opcode);
            }
        }
        return possibleOperations;
    }

    public static long[] executeOperation(long[] input, int[] operation, String opcode) {
        switch (opcode) {
            case ADDR:
                return executeAddr(input, operation);
            case ADDI:
                return executeAddi(input, operation);
            case MULR:
                return executeMulr(input, operation);
            case MULI:
                return executeMuli(input, operation);
            case BANR:
                return executeBanr(input, operation);
            case BANI:
                return executeBani(input, operation);
            case BORR:
                return executeBorr(input, operation);
            case BORI:
                return executeBori(input, operation);
            case SETR:
                return executeSetr(input, operation);
            case SETI:
                return executeSeti(input, operation);
            case GTIR:
                return executeGtir(input, operation);
            case GTRI:
                return executeGtri(input, operation);
            case GTRR:
                return executeGtrr(input, operation);
            case EQIR:
                return executeEqir(input, operation);
            case EQRI:
                return executeEqri(input, operation);
            case EQRR:
                return executeEqrr(input, operation);
            default:
                return null;
        }
    }

    public static long runProgram37(List<String> input, long[] registers, int instructionPointerRegister) {
        int i, instructionPointer = 0;
        int[] newInstruction;
        String[] splitInstruction;
        List<String> instructionOpCodes = new ArrayList<>(input.size());
        List<int[]> instructions = new ArrayList<>(input.size());
        for (String line : input) {
            splitInstruction = line.split(" ");
            instructionOpCodes.add(splitInstruction[0]);
            newInstruction = new int[4];
            for (i = 1; i < 4; i++) {
                newInstruction[i] = Integer.parseInt(splitInstruction[i]);
            }
            instructions.add(newInstruction);
        }
        do {
            renderState(instructionPointer, registers); //Logging: Beware, makes program unusably slow.
            registers[instructionPointerRegister] = instructionPointer;
            registers = executeOperation(registers, instructions.get(instructionPointer), instructionOpCodes.get(instructionPointer));
            instructionPointer = (int) registers[instructionPointerRegister];
            instructionPointer++;
        } while (0 <= instructionPointer && instructionPointer < instructions.size());
        return registers[0];
    }

    public static long runProgram41(List<String> input, long[] registers, int instructionPointerRegister) {
        int i, instructionPointer = 0;
        int instructionsDone = 0;
        int[] newInstruction;
        String[] splitInstruction;
        List<String> instructionOpCodes = new ArrayList<>(input.size());
        List<int[]> instructions = new ArrayList<>(input.size());
        for (String line : input) {
            splitInstruction = line.split(" ");
            instructionOpCodes.add(splitInstruction[0]);
            newInstruction = new int[4];
            for (i = 1; i < 4; i++) {
                newInstruction[i] = Integer.parseInt(splitInstruction[i]);
            }
            instructions.add(newInstruction);
        }
        do {
            instructionsDone++;
            renderState(instructionPointer, registers); //Logging: Beware, makes program unusably slow.
            registers[instructionPointerRegister] = instructionPointer;
            registers = executeOperation(registers, instructions.get(instructionPointer), instructionOpCodes.get(instructionPointer));
            instructionPointer = (int) registers[instructionPointerRegister];
            instructionPointer++;
        } while (0 <= instructionPointer && instructionPointer < instructions.size());
        return instructionsDone;
    }

    private static void renderState(int instructionPointer, long[] registers) {
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(new File("output41.txt"), true))) {
            writer.print(instructionPointer + "\t");
            for (int i = 0; i < registers.length; i++) {
                writer.print(" " + registers[i]);
            }
            writer.println();
        } catch (Exception e) {

        }
    }

    private static boolean checkOperation(long[] before, int[] operation, long[] after, String opcode) {
        switch (opcode) {
            case ADDR:
                return checkAddr(before, operation, after);
            case ADDI:
                return checkAddi(before, operation, after);
            case MULR:
                return checkMulr(before, operation, after);
            case MULI:
                return checkMuli(before, operation, after);
            case BANR:
                return checkBanr(before, operation, after);
            case BANI:
                return checkBani(before, operation, after);
            case BORR:
                return checkBorr(before, operation, after);
            case BORI:
                return checkBori(before, operation, after);
            case SETR:
                return checkSetr(before, operation, after);
            case SETI:
                return checkSeti(before, operation, after);
            case GTIR:
                return checkGtir(before, operation, after);
            case GTRI:
                return checkGtri(before, operation, after);
            case GTRR:
                return checkGtrr(before, operation, after);
            case EQIR:
                return checkEqir(before, operation, after);
            case EQRI:
                return checkEqri(before, operation, after);
            case EQRR:
                return checkEqrr(before, operation, after);
            default:
                return false;
        }
    }

    private static boolean checkAddr(long[] before, int[] operation, long[] after) {
        if (operation[1] < 0 || 3 < operation[1] || operation[2] < 0 || 3 < operation[2]) {
            return false;
        }
        return Arrays.equals(after, executeAddr(before, operation));
    }

    private static long[] executeAddr(long[] input, int[] operation) {
        input[operation[3]] = input[operation[1]] + input[operation[2]];
        return input;
    }

    private static boolean checkAddi(long[] before, int[] operation, long[] after) {
        if (operation[1] < 0 || 3 < operation[1]) {
            return false;
        }
        return Arrays.equals(after, executeAddi(before, operation));
    }

    private static long[] executeAddi(long[] input, int[] operation) {
        input[operation[3]] = input[operation[1]] + operation[2];
        return input;
    }

    private static boolean checkMulr(long[] before, int[] operation, long[] after) {
        if (operation[1] < 0 || 3 < operation[1] || operation[2] < 0 || 3 < operation[2]) {
            return false;
        }
        return Arrays.equals(after, executeMulr(before, operation));
    }

    private static long[] executeMulr(long[] input, int[] operation) {
        input[operation[3]] = input[operation[1]] * input[operation[2]];
        return input;
    }

    private static boolean checkMuli(long[] before, int[] operation, long[] after) {
        if (operation[1] < 0 || 3 < operation[1]) {
            return false;
        }
        return Arrays.equals(after, executeMuli(before, operation));
    }

    private static long[] executeMuli(long[] input, int[] operation) {
        input[operation[3]] = input[operation[1]] * operation[2];
        return input;
    }

    private static boolean checkBanr(long[] before, int[] operation, long[] after) {
        if (operation[1] < 0 || 3 < operation[1] || operation[2] < 0 || 3 < operation[2]) {
            return false;
        }
        return Arrays.equals(after, executeBanr(before, operation));
    }

    private static long[] executeBanr(long[] input, int[] operation) {
        input[operation[3]] = input[operation[1]] & input[operation[2]];
        return input;
    }

    private static boolean checkBani(long[] before, int[] operation, long[] after) {
        if (operation[1] < 0 || 3 < operation[1]) {
            return false;
        }
        return Arrays.equals(after, executeBani(before, operation));
    }

    private static long[] executeBani(long[] input, int[] operation) {
        input[operation[3]] = input[operation[1]] & operation[2];
        return input;
    }

    private static boolean checkBorr(long[] before, int[] operation, long[] after) {
        if (operation[1] < 0 || 3 < operation[1] || operation[2] < 0 || 3 < operation[2]) {
            return false;
        }
        return Arrays.equals(after, executeBorr(before, operation));
    }

    private static long[] executeBorr(long[] input, int[] operation) {
        input[operation[3]] = input[operation[1]] | input[operation[2]];
        return input;
    }

    private static boolean checkBori(long[] before, int[] operation, long[] after) {
        if (operation[1] < 0 || 3 < operation[1]) {
            return false;
        }
        return Arrays.equals(after, executeBori(before, operation));
    }

    private static long[] executeBori(long[] input, int[] operation) {
        input[operation[3]] = input[operation[1]] | operation[2];
        return input;
    }

    private static boolean checkSetr(long[] before, int[] operation, long[] after) {
        if (operation[1] < 0 || 3 < operation[1]) {
            return false;
        }
        return Arrays.equals(after, executeSetr(before, operation));
    }

    private static long[] executeSetr(long[] input, int[] operation) {
        input[operation[3]] = input[operation[1]];
        return input;
    }

    private static boolean checkSeti(long[] before, int[] operation, long[] after) {
        return after[operation[3]] == operation[1];
    }

    private static long[] executeSeti(long[] input, int[] operation) {
        input[operation[3]] = operation[1];
        return input;
    }

    private static boolean checkGtir(long[] before, int[] operation, long[] after) {
        if (operation[2] < 0 || 3 < operation[2]) {
            return false;
        }
        return Arrays.equals(after, executeGtir(before, operation));
    }

    private static long[] executeGtir(long[] input, int[] operation) {
        input[operation[3]] = operation[1] > input[operation[2]] ? 1 : 0;
        return input;
    }

    private static boolean checkGtri(long[] before, int[] operation, long[] after) {
        if (operation[1] < 0 || 3 < operation[1]) {
            return false;
        }
        return Arrays.equals(after, executeGtri(before, operation));
    }

    private static long[] executeGtri(long[] input, int[] operation) {
        input[operation[3]] = input[operation[1]] > operation[2] ? 1 : 0;
        return input;
    }

    private static boolean checkGtrr(long[] before, int[] operation, long[] after) {
        if (operation[1] < 0 || 3 < operation[1] || operation[2] < 0 || 3 < operation[2]) {
            return false;
        }
        return Arrays.equals(after, executeGtrr(before, operation));
    }

    private static long[] executeGtrr(long[] input, int[] operation) {
        input[operation[3]] = input[operation[1]] > input[operation[2]] ? 1 : 0;
        return input;
    }

    private static boolean checkEqir(long[] before, int[] operation, long[] after) {
        if (operation[2] < 0 || 3 < operation[2]) {
            return false;
        }
        return Arrays.equals(after, executeEqir(before, operation));
    }

    private static long[] executeEqir(long[] input, int[] operation) {
        input[operation[3]] = operation[1] == input[operation[2]] ? 1 : 0;
        return input;
    }

    private static boolean checkEqri(long[] before, int[] operation, long[] after) {
        if (operation[1] < 0 || 3 < operation[1]) {
            return false;
        }
        if (before[operation[1]] == operation[2]) {
            return after[operation[3]] == 1;
        } else {
            return after[operation[3]] == 0;
        }
    }

    private static long[] executeEqri(long[] input, int[] operation) {
        input[operation[3]] = input[operation[1]] == operation[2] ? 1 : 0;
        return input;
    }

    private static boolean checkEqrr(long[] before, int[] operation, long[] after) {
        if (operation[1] < 0 || 3 < operation[1] || operation[2] < 0 || 3 < operation[2]) {
            return false;
        }
        return Arrays.equals(after, executeEqrr(before, operation));
    }

    private static long[] executeEqrr(long[] input, int[] operation) {
        input[operation[3]] = input[operation[1]] == input[operation[2]] ? 1 : 0;
        return input;
    }
}
