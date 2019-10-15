package adventofcode2018;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lukas Thöni
 */
public class BranchNode39 {

    private List<String> data;
    private BranchNode39 parent;
    private List<BranchNode39> children;
    private int numberOfPossibilities;

    public BranchNode39(String data, BranchNode39 parent) {
        this.data = new ArrayList<>();
        this.parent = parent;
        this.children = new ArrayList<>();
        int start = 0;
        if (data.charAt(0) == '(') {
            data = data.substring(1, data.length() - 1);
        }
        for (int stringPointer = 0; stringPointer < data.length(); stringPointer++) {
            if (data.charAt(stringPointer) == '(') {

            }
        }

    }

    private String extractBracket(String data, int stringPointer) {
        int openBrackets = 0, start = stringPointer;
        do {
            switch (data.charAt(stringPointer)) {
                case '(':
                    openBrackets++;
                    break;
                case ')':
                    openBrackets--;
                    break;
            }
            stringPointer++;
        } while (openBrackets > 0);
        return data.substring(start, stringPointer);
    }

    private int getDataLength() {
        return data.size();
    }

}
