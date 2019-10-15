package adventofcode2018;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lukas Thöni
 */
public class TreeManager35 {

    private char[][] field, newField;
    private int minute;

    private TreeManager35() {
        this.field = new char[50][50];
        this.newField = new char[50][50];
        this.minute = 0;
    }

    public TreeManager35(List<String> input) {
        this();
        for (int i = 0; i < input.size(); i++) {
            field[i] = input.get(i).toCharArray();
        }
        renderFieldToFile();
    }

    private void renderFieldToFile() {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new FileOutputStream(new File("output35.txt"), true));
            writer.println(minute);
            for (int y = 0; y < field.length; y++) {
                for (int x = 0; x < field[y].length; x++) {
                    writer.print(field[y][x]);
                }
                writer.println();
            }
        } catch (Exception e) {

        } finally {
            writer.close();
        }
    }

    public int manageTrees() {
        int y, x, noOfTrees, noOfLumberyards, result = 0;
        int[] stateOfSurroundings;
        List<Integer> previousValues = new ArrayList<>(10000);
        for (minute = 1; minute <= 10000; minute++) {
            for (y = 0; y < field.length; y++) {
                for (x = 0; x < field[y].length; x++) {
                    newField[y][x] = field[y][x];
                    stateOfSurroundings = getStateAround(y, x);
                    switch (field[y][x]) {
                        case '.':
                            if (3 <= stateOfSurroundings[0]) {
                                newField[y][x] = '|';
                            }
                            break;
                        case '|':
                            if (3 <= stateOfSurroundings[1]) {
                                newField[y][x] = '#';
                            }
                            break;
                        case '#':
                            if (stateOfSurroundings[0] == 0 || stateOfSurroundings[1] == 0) {
                                newField[y][x] = '.';
                            }
                            break;
                    }
                }
            }
            for (y = 0; y < field.length; y++) {
                for (x = 0; x < field[y].length; x++) {
                    field[y][x] = newField[y][x];
                }
            }
            noOfTrees = 0;
            noOfLumberyards = 0;
            for (y = 0; y < field.length; y++) {
                for (x = 0; x < field[y].length; x++) {
                    if (field[y][x] == '|') {
                        noOfTrees++;
                    } else if (field[y][x] == '#') {
                        noOfLumberyards++;
                    }
                }
            }
            result = noOfTrees * noOfLumberyards;
            if (previousValues.contains(result)) {
                System.out.println(result + " " + previousValues.lastIndexOf(result) + " " + (previousValues.size() - previousValues.lastIndexOf(result)));
            }
            previousValues.add(result);
            //renderFieldToFile();
        }
        return result;
    }

    private int[] getStateAround(int y, int x) {
        int i, j;
        int[] state = new int[2];
        for (i = y - 1; i <= y + 1; i++) {
            if (0 <= i && i < field.length) {
                for (j = x - 1; j <= x + 1; j++) {
                    if (0 <= j && j < field[i].length
                            && !(i == y && j == x)) {
                        if (field[i][j] == '|') {
                            state[0]++;
                        } else if (field[i][j] == '#') {
                            state[1]++;
                        }
                    }
                }
            }
        }
        return state;
    }

}
