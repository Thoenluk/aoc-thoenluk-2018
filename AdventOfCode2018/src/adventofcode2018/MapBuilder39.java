package adventofcode2018;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Lukas Thöni
 */
public class MapBuilder39 {

    private String directions;
    private Map<Integer, Integer> branchEndings;
    private List<int[]> resumptionPoints; //Elements: [currentIndex, x, y, closingIndex]
    private List<Integer> closingIndices;
    private List<List<Integer>> resumptionClosingIndices;

    public MapBuilder39(String input) {
        this.directions = input;
        this.branchEndings = new HashMap<>();
        this.resumptionPoints = new ArrayList<>();
        this.closingIndices = new ArrayList<>();
        this.resumptionClosingIndices = new ArrayList<>();
    }

    public int findFarthestRoom() {
        matchEndings();
        exploreBranch();
        return 1;
    }

    private void exploreBranch() {
        int currentIndex, closingIndex, x = 0, y = 0;
        boolean updated = false;
        char currentChar, previousChar = ' ';
        for (currentIndex = 0; currentIndex < directions.length() + 1; currentIndex++) {
            if (currentIndex == directions.length()) {
                //System.out.println();
                if (resumptionPoints.isEmpty()) {
                    return;
                }
                int[] resumptionPoint = resumptionPoints.get(0);
                currentIndex = resumptionPoint[0];
                x = resumptionPoint[1];
                y = resumptionPoint[2];
                closingIndices.clear();
                closingIndices.addAll(resumptionClosingIndices.get(0));
                updated = false;
            } else {
                currentChar = directions.charAt(currentIndex);
                //System.out.print(" " + currentChar);
                switch (currentChar) {
                    case '(':
                        closingIndex = branchEndings.get(currentIndex);
                        closingIndices.add(0, closingIndex);
                        resumptionPoints.add(0, new int[]{currentIndex, x, y});
                        resumptionClosingIndices.add(0, new ArrayList<>(closingIndices));
                        updated = false;
                        break;
                    case ')':
                        resumptionClosingIndices.remove(0);
                        resumptionPoints.remove(0);
                        closingIndices.remove(0);
                        break;
                    case '|':
                        if (!updated && !resumptionPoints.isEmpty()) {
                            int[] resumptionPoint = resumptionPoints.get(0);
                            resumptionPoint[0] = currentIndex;
                            updated = true;
                        }
                        currentIndex = closingIndices.get(0);
                        closingIndices.remove(0);
                        break;
                }
                previousChar = currentChar;
            }
        }
    }

    private void matchEndings() {
        List<Integer> unmatchedStarts = new ArrayList<>();
        char currentCharacter;
        int j = 1;
        for (int i = 0; i < directions.length(); i++) {
            currentCharacter = directions.charAt(i);
            if (currentCharacter == '(') {
                unmatchedStarts.add(0, i);
            } else if (currentCharacter == ')') {
                for (j = 1; i + j < directions.length() && directions.charAt(i + j) == ')'; j++) {
                }
                j--;
                branchEndings.put(unmatchedStarts.get(0), i + j);
                unmatchedStarts.remove(0);
            }
        }
    }
}
