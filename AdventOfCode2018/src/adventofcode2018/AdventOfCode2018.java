package adventofcode2018;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import static java.lang.Math.abs;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author Lukas Thöni
 */
public class AdventOfCode2018 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        long result = 0;
        String resultString = "";
        System.out.println("Enter the number of the challenge you want me to solve.");
        Scanner keyboard = new Scanner(System.in);
        int challenge = keyboard.nextInt();
        List<String> input = ((challenge % 2) == 1)
                ? Files.readAllLines(Paths.get("input" + challenge + ".txt"))
                : Files.readAllLines(Paths.get("input" + (challenge - 1) + ".txt"));
        switch (challenge) {
            case 1:
                result = challengeOne(input);
                break;
            case 2:
                result = challengeTwo(input);
                break;
            case 3:
                result = challengeThree(input);
                break;
            case 4:
                resultString = challengeFour(input);
                break;
            case 5:
                result = challengeFive(input);
                break;
            case 6:
                result = challengeSix(input);
                break;
            case 7:
                result = challengeSeven(input);
                break;
            case 8:
                result = challengeEight(input);
                break;
            case 9:
                result = challengeNine(input);
                break;
            case 10:
                result = challengeTen(input);
                break;
            case 11:
                result = challengeEleven(input);
                break;
            case 12:
                result = challengeTwelve(input);
                break;
            case 13:
                resultString = challengeThirteen(input);
                break;
            case 14:
                result = challengeFourteen(input);
                break;
            case 15:
                result = challengeFifteen(input);
                break;
            case 16:
                result = challengeSixteen(input);
                break;
            case 17:
                result = challengeSeventeen(input);
                break;
            case 18:
                result = challengeEighteen(input);
                break;
            case 19:
                result = challengeNineteen(input);
                break;
            case 20:
                result = challengeTwenty(input);
                break;
            case 21:
                result = challengeTwentyOne(9110);
                break;
            case 22:
                result = challengeTwentyTwo(9110);
                break;
            case 23:
                result = challengeTwentyThree(input);
                break;
            case 24:
                result = challengeTwentyFour(input);
                break;
            case 25:
                resultString = challengeTwentyFive(input);
                break;
            case 26:
                resultString = challengeTwentySix(input);
                break;
            case 27:
                result = challengeTwentySeven(824501);
                break;
            case 28:
                result = challengeTwentyEight(824501);
                break;
            case 29:
                result = challengeTwentyNine(input);
                break;
            case 30:
                result = challengeThirty(input);
                break;
            case 31:
                result = challengeThirtyOne(input);
                break;
            case 32:
                result = challengeThirtyTwo(input);
                break;
            case 33:
                result = challengeThirtyThree(input);
                break;
            case 34:
                result = challengeThirtyFour(input);
                break;
            case 35:
                result = challengeThirtyFive(input);
                break;
            case 36:
                result = challengeThirtySix(input);
                break;
            case 37:
                result = challengeThirtySeven(input);
                break;
            case 38:
                result = challengeThirtyEight(input);
                break;
            case 39:
                result = challengeThirtyNine(input);
                break;
            case 41:
                result = challengeFortyOne(input);
                break;
            case 42:
                result = challengeFortyTwo(1);
                break;
            case 43:
                result = challengeFortyThree(input);
                break;
            case 44:
                result = challengeFortyFour(input);
                break;
            case 45:
                result = challengeFortyFive(input);
                break;
            case 46:
                result = challengeFortySix(input);
                break;
            case 47:
                result = challengeFortySeven(input);
                break;
            case 48:
                result = challengeFortyEight(input);
                break;
            case 49:
                result = challengeFortyNine(input);
                break;
            default:
                System.out.println("lolno");
        }
        System.out.println(result + " " + resultString);
    }

    private static int challengeOne(List<String> input) {
        int result = 0;
        for (String change : input) {
            int changeInt = Integer.parseInt(change.substring(1));
            if (change.charAt(0) == '+') {
                result += changeInt;
            } else {
                result -= changeInt;
            }
        }
        return result;
    }

    private static int challengeTwo(List<String> input) {
        int result = 0, index = 0;
        ArrayList<Integer> reachedFrequencies = new ArrayList<>(input.size());
        ArrayList<Integer> changes = new ArrayList<>(input.size());
        for (String changeString : input) {
            changes.add(Integer.parseInt(changeString));
        }
        int changesSize = changes.size();
        while (!reachedFrequencies.contains(result += changes.get(index))) {
            reachedFrequencies.add(result);
            index = ++index % changesSize;
        }
        return result;
    }

    private static int challengeThree(List<String> input) {
        int result = 0, doubleIDs = 0, tripleIDs = 0;
        Integer[] occurences;
        List occurencesList;
        for (String ID : input) {
            occurences = new Integer[26];
            for (int i = 0; i < 26; i++) {
                occurences[i] = 0;
            }
            for (char character : ID.toCharArray()) {
                occurences[character - 97]++;
            }
            occurencesList = Arrays.asList(occurences);
            if (occurencesList.contains(2)) {
                doubleIDs++;
            }
            if (occurencesList.contains(3)) {
                tripleIDs++;
            }
        }
        result = doubleIDs * tripleIDs;
        return result;
    }

    private static String challengeFour(List<String> input) {
        String result = "";
        for (String ID : input) {
            for (String otherID : input) {
                for (int i = 0; i < ID.length(); i++) {
                    if (ID.charAt(i) != otherID.charAt(i) && ID.substring(0, i).equals(otherID.substring(0, i))) {
                        if (i == ID.length() - 1) {
                            result = ID.substring(0, i);
                        } else if (ID.substring(i + 1, ID.length()).equals(otherID.substring(i + 1, otherID.length()))) {
                            result = ID.substring(0, i).concat(ID.substring(i + 1, ID.length()));
                        }
                        break;
                    }
                }
            }
            if (!result.equals("")) {
                break;
            }
        }
        return result;
    }

    private static int challengeFive(List<String> input) {
        int result = 0, x = 0, y = 0;
        int[][] fabric = new int[1000][1000];
        ArrayList<Claim> claims = new ArrayList<>(input.size());
        for (String claimSpec : input) {
            claims.add(new Claim(claimSpec));
        }
        for (Claim claim : claims) {
            for (x = claim.getxPos(); x < claim.getxPos() + claim.getxSize(); x++) {
                for (y = claim.getyPos(); y < claim.getyPos() + claim.getySize(); y++) {
                    fabric[x][y]++;
                }
            }
        }
        for (x = 0; x < 1000; x++) {
            for (y = 0; y < 1000; y++) {
                if (fabric[x][y] > 1) {
                    result++;
                }
            }
        }
        return result;
    }

    private static int challengeSix(List<String> input) {
        int ID = 0;
        int x = 0, y = 0;
        int[][] fabric = new int[1000][1000];
        boolean claimIsResult;
        ArrayList<Claim> claims = new ArrayList<>(input.size());
        for (String claimSpec : input) {
            claims.add(new Claim(claimSpec));
        }
        for (Claim claim : claims) {
            for (x = claim.getxPos(); x < claim.getxPos() + claim.getxSize(); x++) {
                for (y = claim.getyPos(); y < claim.getyPos() + claim.getySize(); y++) {
                    fabric[x][y]++;
                }
            }
        }
        for (Claim claim : claims) {
            claimIsResult = true;
            for (x = claim.getxPos(); x < claim.getxPos() + claim.getxSize(); x++) {
                for (y = claim.getyPos(); y < claim.getyPos() + claim.getySize(); y++) {
                    claimIsResult = (fabric[x][y] == 1) ? claimIsResult : false;
                }
            }
            if (claimIsResult) {
                ID = claim.getID();
                break;
            }
        }
        return ID;
    }

    private static int challengeSeven(List<String> input) throws Exception {
        int result = 0, startMinute = 0, endMinute = 0, maxMinute = 0, maxValue = 0;
        Integer ID = 0, timeAsleep = 0;
        String currentEntry;
        ArrayList<Date> entries = new ArrayList<>(input.size());
        Map<Date, String> strings = new HashMap<>(input.size());
        Map<Integer, Integer> sleepTimes = new HashMap<>();
        Map<Integer, int[]> sleepMinutesByID = new HashMap<>();
        Map.Entry<Integer, Integer> maxTimeAsleep = null;
        SimpleDateFormat format = new SimpleDateFormat("[yyyy-MM-dd HH:mm]");
        Calendar calendar = Calendar.getInstance();

        for (String entry : input) {
            Date date = format.parse(entry.substring(0, 18));
            entries.add(date);
            strings.put(date, entry);
        }
        entries.sort(null);

        for (Date date : entries) {
            currentEntry = strings.get(date);
            switch (currentEntry.charAt(19)) {
                case 'G':
                    ID = Integer.parseInt(currentEntry.substring(26, 30).trim());
                    if (!sleepMinutesByID.containsKey(ID)) {
                        sleepMinutesByID.put(ID, new int[60]);
                    }
                    break;
                case 'f':
                    calendar.setTime(date);
                    startMinute = calendar.get(Calendar.MINUTE);
                    break;
                case 'w':
                    calendar.setTime(date);
                    endMinute = calendar.get(Calendar.MINUTE);
                    timeAsleep = endMinute - startMinute;
                    if (sleepTimes.containsKey(ID)) {
                        sleepTimes.put(ID, timeAsleep + sleepTimes.get(ID));
                    } else {
                        sleepTimes.put(ID, timeAsleep);
                    }
                    if (maxTimeAsleep == null || maxTimeAsleep.getValue() < sleepTimes.get(ID)) {
                        maxTimeAsleep = new SimpleEntry<>(ID, sleepTimes.get(ID));
                    }
                    int[] sleepMinutes = sleepMinutesByID.get(ID);
                    for (int i = startMinute; i < endMinute; i++) {
                        sleepMinutes[i]++;
                    }
                    sleepMinutesByID.put(ID, sleepMinutes);
                    break;
            }
        }
        int[] sleepMinutes = sleepMinutesByID.get(maxTimeAsleep.getKey());
        for (int i = 0; i < 60; i++) {
            if (sleepMinutes[i] > maxValue) {
                maxMinute = i;
                maxValue = sleepMinutes[i];
            }
        }
        result = maxTimeAsleep.getKey() * maxMinute;
        return result;
    }

    private static int challengeEight(List<String> input) throws Exception {
        int result = 0, startMinute = 0, endMinute = 0, maxMinute = 0, maxValue = 0;
        Integer ID = 0;
        int[] adventCalendar = new int[60], currentCalendar;
        String currentEntry;
        ArrayList<Date> entries = new ArrayList<>(input.size());
        Map<Date, String> strings = new HashMap<>(input.size());
        Map<Integer, int[]> adventCalendarsByID = new HashMap<>();
        Map.Entry<Integer, Integer> employeeOfTheMonth = null;
        SimpleDateFormat format = new SimpleDateFormat("[yyyy-MM-dd HH:mm]");
        Calendar calendar = Calendar.getInstance();

        for (String entry : input) {
            Date date = format.parse(entry.substring(0, 18));
            entries.add(date);
            strings.put(date, entry);
        }
        entries.sort(null);

        for (Date date : entries) {
            currentEntry = strings.get(date);
            switch (currentEntry.charAt(19)) {
                case 'G':
                    ID = Integer.parseInt(currentEntry.substring(26, 30).trim());
                    if (!adventCalendarsByID.containsKey(ID)) {
                        adventCalendarsByID.put(ID, new int[60]);
                    }
                    break;
                case 'f':
                    calendar.setTime(date);
                    startMinute = calendar.get(Calendar.MINUTE);
                    break;
                case 'w':
                    calendar.setTime(date);
                    endMinute = calendar.get(Calendar.MINUTE);
                    currentCalendar = adventCalendarsByID.get(ID);
                    for (int i = startMinute; i < endMinute; i++) {
                        currentCalendar[i]++;
                        if (employeeOfTheMonth == null || currentCalendar[i] > employeeOfTheMonth.getValue()) {
                            employeeOfTheMonth = new SimpleEntry<>(ID, currentCalendar[i]);
                        }
                        if (currentCalendar[i] > adventCalendar[i]) {
                            adventCalendar[i] = currentCalendar[i];
                        }
                    }
                    break;
            }
        }
        currentCalendar = adventCalendarsByID.get(employeeOfTheMonth.getKey());
        maxValue = employeeOfTheMonth.getValue();
        for (int i = 0; i < 60; i++) {
            if (currentCalendar[i] == maxValue) {
                maxMinute = i;
                break;
            }
        }
        result = employeeOfTheMonth.getKey() * maxMinute;
        return result;
    }

    private static int challengeNine(List<String> input) {
        String polymer = input.get(0);
        polymer = reactPolymer(polymer);
        return polymer.length();
    }

    private static int challengeTen(List<String> input) {
        String polymer = input.get(0);
        String regex = "", excisedPolymer = "";
        int shortestPolymerLength = 100000;
        polymer = reactPolymer(polymer);
        for (int i = 65; i < 91; i++) {
            regex = "[" + (char) i;
            regex += (char) (i + 32);
            regex += "]+";
            excisedPolymer = polymer.replaceAll(regex, "");
            excisedPolymer = reactPolymer(excisedPolymer);
            if (shortestPolymerLength > excisedPolymer.length()) {
                shortestPolymerLength = excisedPolymer.length();
            }
        }
        return shortestPolymerLength;
    }

    private static String reactPolymer(String polymer) {
        int readPosition = 0;
        String charAtPosition, charAfterPosition;
        while (true) {
            charAtPosition = polymer.substring(readPosition, readPosition + 1);
            charAfterPosition = polymer.substring(readPosition + 1, readPosition + 2);
            if (!charAtPosition.equals(charAfterPosition) && charAtPosition.equalsIgnoreCase(charAfterPosition)) {
                polymer = polymer.substring(0, readPosition) + polymer.substring(readPosition + 2);
                if (readPosition > 0) {
                    readPosition--;
                }
            } else {
                readPosition++;
            }
            if (readPosition == polymer.length() - 1) {
                break;
            }
        }
        return polymer;
    }

    private static int challengeEleven(List<String> input) {
        int n = 1, numberOfChanges = 0, i = 0, j = 0, pointNumber = 0, highestArea = 0;
        List<int[]> points = new ArrayList<>(input.size());
        List<Integer> nearbyNumbers;
        Map<Integer, Integer> resultCandidates = new HashMap<>(input.size());
        int[] maxCoordinates = {0, 0};
        int[][] field, newField;
        String[] stringData;
        int[] intData;
        for (String point : input) {
            stringData = point.split(", ");
            intData = new int[stringData.length];
            for (i = 0; i < stringData.length; i++) {
                if (!stringData[i].equals("")) {
                    intData[i] = Integer.parseInt(stringData[i]);
                    if (maxCoordinates[i] < intData[i]) {
                        maxCoordinates[i] = intData[i];
                    }
                }
            }
            points.add(intData);
        }
        maxCoordinates[0]++;
        maxCoordinates[1]++;
        field = new int[maxCoordinates[0]][maxCoordinates[1]];
        for (int[] point : points) {
            field[point[0]][point[1]] = n;
            resultCandidates.put(n, 0);
            n++;
        }
        do {
            newField = new int[maxCoordinates[0]][maxCoordinates[1]];
            numberOfChanges = 0;
            for (i = 0; i < maxCoordinates[0]; i++) {
                for (j = 0; j < maxCoordinates[1]; j++) {
                    if (field[i][j] == 0) {
                        numberOfChanges++;
                        nearbyNumbers = findNearbyNumbers(field, i, j);
                        if (nearbyNumbers.size() >= 1) {
                            for (int number : nearbyNumbers) {
                                if (newField[i][j] == 0 || newField[i][j] == number) {
                                    newField[i][j] = number;
                                } else {
                                    newField[i][j] = -1;
                                    break;
                                }
                            }
                        }
                    } else {
                        newField[i][j] = field[i][j];
                    }
                }
            }
            field = newField;
        } while (numberOfChanges > 0);

        for (i = 0; i < field.length; i++) {
            resultCandidates.remove(field[i][0]);
            resultCandidates.remove(field[i][field[0].length - 1]);
        }
        for (j = 0; j < field[0].length; j++) {
            resultCandidates.remove(field[0][j]);
            resultCandidates.remove(field[field.length - 1][j]);
        }

        for (i = 1; i < field.length - 1; i++) {
            for (j = 1; j < field[0].length - 1; j++) {
                pointNumber = field[i][j];
                if (resultCandidates.containsKey(pointNumber)) {
                    resultCandidates.put(pointNumber, resultCandidates.get(pointNumber) + 1);
                    if (highestArea < resultCandidates.get(pointNumber)) {
                        highestArea = resultCandidates.get(pointNumber);
                    }
                }
            }
        }
        return highestArea;
    }

    private static List<Integer> findNearbyNumbers(int[][] field, int i, int j) {
        List<Integer> nearbyNumbers = new ArrayList<>(4);
        if (i > 0 && field[i - 1][j] > 0) {
            nearbyNumbers.add(field[i - 1][j]);
        }
        if (i < field.length - 1 && field[i + 1][j] > 0) {
            nearbyNumbers.add(field[i + 1][j]);
        }
        if (j > 0 && field[i][j - 1] > 0) {
            nearbyNumbers.add(field[i][j - 1]);
        }
        if (j < field[0].length - 1 && field[i][j + 1] > 0) {
            nearbyNumbers.add(field[i][j + 1]);
        }
        return nearbyNumbers;
    }

    private static int challengeTwelve(List<String> input) {
        int n = 1, numberOfChanges = 0, i = 0, j = 0, result = 0;
        List<int[]> points = new ArrayList<>(input.size());
        List<Integer> nearbyNumbers;
        Map<Integer, Integer> resultCandidates = new HashMap<>(input.size());
        int[] maxCoordinates = {0, 0};
        int[][] field, newField;
        String[] stringData;
        int[] intData;
        for (String point : input) {
            stringData = point.split(", ");
            intData = new int[stringData.length];
            for (i = 0; i < stringData.length; i++) {
                if (!stringData[i].equals("")) {
                    intData[i] = Integer.parseInt(stringData[i]);
                    if (maxCoordinates[i] < intData[i]) {
                        maxCoordinates[i] = intData[i];
                    }
                }
            }
            points.add(intData);
        }
        maxCoordinates[0]++;
        maxCoordinates[1]++;
        for (i = 0; i < maxCoordinates[0]; i++) {
            for (j = 0; j < maxCoordinates[1]; j++) {
                if (distanceToAllPoints(i, j, points) < 10000) {
                    result++;
                }
            }
        }
        return result;
    }

    private static int distanceToAllPoints(int i, int j, List<int[]> points) {
        int result = 0;
        for (int[] point : points) {
            result += abs(i - point[0]) + abs(j - point[1]);
        }
        return result;
    }

    private static String challengeThirteen(List<String> input) {
        String result = "";
        List<Character> theAlphabet = new ArrayList<>(input.size());
        for (char letter = 'A'; letter <= 'Z'; letter++) {
            theAlphabet.add(letter);
        }
        List<Character> requiredSteps;
        Map<Character, List<Character>> requirements = new HashMap<>(input.size());
        for (String instruction : input) {
            requiredSteps = requirements.getOrDefault(instruction.charAt(36), new ArrayList<>());
            requiredSteps.add(instruction.charAt(5));
            requirements.put(instruction.charAt(36), requiredSteps);
        }
        Iterator it;
        Character nextLetter;
        while (!theAlphabet.isEmpty()) {
            it = theAlphabet.iterator();
            while (it.hasNext()) {
                nextLetter = (Character) it.next();
                requiredSteps = requirements.get(nextLetter);
                if (requiredSteps == null || requiredSteps.isEmpty()) {
                    it.remove();
                    for (List<Character> requirement : requirements.values()) {
                        requirement.remove(nextLetter);
                    }
                    result += nextLetter;
                    break;
                }
            }
        }

        return result;
    }

    private static int challengeFourteen(List<String> input) {
        String order = "";
        int result = 0;
        List<Character> theAlphabet = new ArrayList<>(input.size());
        for (char letter = 'A'; letter <= 'Z'; letter++) {
            theAlphabet.add(letter);
        }
        int[] timeToComplete = new int[26];
        for (int i = 0; i < 26; i++) {
            timeToComplete[i] = i + 61;
        }
        List<Character> requiredSteps;
        List<Character> workInProgress = new ArrayList<>(5);
        Map<Character, List<Character>> requirements = new HashMap<>(input.size());
        for (String instruction : input) {
            requiredSteps = requirements.getOrDefault(instruction.charAt(36), new ArrayList<>());
            requiredSteps.add(instruction.charAt(5));
            requirements.put(instruction.charAt(36), requiredSteps);
        }
        Iterator it;
        Character taskInProgress;
        while (!theAlphabet.isEmpty() || !workInProgress.isEmpty()) {
            for (Character nextLetter : theAlphabet) {
                requiredSteps = requirements.get(nextLetter);
                if ((requiredSteps == null || requiredSteps.isEmpty()) && workInProgress.size() < 5 && !workInProgress.contains(nextLetter)) {
                    workInProgress.add(nextLetter);
                }
                if (workInProgress.contains(nextLetter)) {
                    timeToComplete[nextLetter - 65]--;
                }
            }
            it = workInProgress.iterator();
            while (it.hasNext()) {
                taskInProgress = (Character) it.next();
                if (timeToComplete[taskInProgress - 65] == 0) {
                    it.remove();
                    theAlphabet.remove(taskInProgress);
                    for (List<Character> requirement : requirements.values()) {
                        requirement.remove(taskInProgress);
                    }
                }
            }
            result++;
        }

        return result;
    }

    private static int challengeFifteen(List<String> input) {
        String[] specStrings = input.get(0).split(" ");
        int[] specs = new int[specStrings.length];
        int[] metadata;
        int i = 0, result = 0;
        for (i = 0; i < specs.length; i++) {
            specs[i] = Integer.parseInt(specStrings[i]);
        }
        List<Node> tree = new ArrayList<Node>(2000);
        Node.hangNodesFromSpecs(specs, 0, 0, tree);
        for (Node node : tree) {
            metadata = node.getMetadata();
            for (i = 0; i < metadata.length; i++) {
                result += metadata[i];
            }
        }
        return result;
    }

    private static int challengeSixteen(List<String> input) {
        String[] specStrings = input.get(0).split(" ");
        int[] specs = new int[specStrings.length];
        int[] metadata;
        int i = 0, result = 0;
        for (i = 0; i < specs.length; i++) {
            specs[i] = Integer.parseInt(specStrings[i]);
        }
        List<Node> tree = new ArrayList<Node>(2000);
        Node.hangNodesFromSpecs(specs, 0, 0, tree);
        tree.sort(null);
        result = tree.get(0).getValue(tree);
        return result;
    }

    private static int challengeSeventeen(List<String> input) {
        int result = 0, operationIndex;
        int noOfPlayers = Integer.parseInt(input.get(0).substring(0, 3));
        int[] scores = new int[noOfPlayers];
        int currentPlayer = 0;
        int highestMarble = Integer.parseInt(input.get(0).substring(34, 39));
        int indexOfCurrentMarble = 0;
        List<Integer> circle = new ArrayList<>();
        circle.add(0);
        circle.add(1);
        currentPlayer = (currentPlayer + 2) % noOfPlayers;
        //Setting up the first two elements allows for a simpler insertion algorithm.
        for (int i = 2; i <= highestMarble; i++) {
            if (i % 23 == 0) {
                operationIndex = indexOfCurrentMarble >= 7 ? indexOfCurrentMarble - 7 : indexOfCurrentMarble + circle.size() - 7;
                scores[currentPlayer] += i + circle.get(operationIndex);
                circle.remove(operationIndex);
            } else {
                operationIndex = indexOfCurrentMarble < (circle.size() - 2) ? indexOfCurrentMarble + 2 : indexOfCurrentMarble + 2 - circle.size();
                circle.add(operationIndex, i);
            }
            indexOfCurrentMarble = operationIndex;
            currentPlayer = ++currentPlayer % noOfPlayers;
        }
        for (int i = 0; i < noOfPlayers; i++) {
            result = result < scores[i] ? scores[i] : result;
        }
        return result;
    }

    private static long challengeEighteen(List<String> input) {
        long result = 0;
        int operationIndex, circleSize;
        int noOfPlayers = Integer.parseInt(input.get(0).substring(0, 3));
        long[] scores = new long[noOfPlayers];
        int currentPlayer = 0;
        int highestMarble = Integer.parseInt(input.get(0).substring(34, 39)) * 100;
        int indexOfCurrentMarble = 0;
        List<Integer> circle = new ArrayList<>(highestMarble);
        circle.add(0);
        circle.add(1);
        currentPlayer = (currentPlayer + 2) % noOfPlayers;
        //Setting up the first two elements allows for a simpler insertion algorithm.
        for (int i = 2; i <= highestMarble; i++) {
            circleSize = circle.size();
            if (i % 23 == 0) {
                operationIndex = indexOfCurrentMarble >= 7 ? indexOfCurrentMarble - 7 : indexOfCurrentMarble + circleSize - 7;
                scores[currentPlayer] += i + circle.get(operationIndex);
                circle.remove(operationIndex);
            } else {
                operationIndex = indexOfCurrentMarble < (circleSize - 2) ? indexOfCurrentMarble + 2 : indexOfCurrentMarble + 2 - circleSize;
                circle.add(operationIndex, i);
            }
            indexOfCurrentMarble = operationIndex;
            currentPlayer = ++currentPlayer == noOfPlayers ? 0 : currentPlayer;
        }
        for (int i = 0; i < noOfPlayers; i++) {
            result = result < scores[i] ? scores[i] : result;
        }
        return result;
    }

    private static int challengeNineteen(List<String> input) {
        int result = 0, nearbyPoints = 0, minX = 100000, maxX = -100000, minY = 100000, maxY = -100000;
        List<int[]> nodes = new ArrayList<>(input.size());
        int[] nodeDetails;
        for (String node : input) {
            nodeDetails = new int[4];
            nodeDetails[0] = Integer.parseInt(node.substring(10, 16).trim());
            nodeDetails[1] = Integer.parseInt(node.substring(18, 24).trim());
            nodeDetails[2] = Integer.parseInt(node.substring(36, 38).trim());
            nodeDetails[3] = Integer.parseInt(node.substring(40, 42).trim());
            nodes.add(nodeDetails);
        }
        while (true) {
            for (int[] node : nodes) {
                nearbyPoints = 0;
                for (int[] othernode : nodes) {
                    if (!othernode.equals(node)
                            && (othernode[0] <= node[0] + 1 && othernode[0] >= node[0] - 1)
                            && (othernode[1] <= node[1] + 1 && othernode[1] >= node[1] - 1)) {
                        nearbyPoints++;
                    }
                }
                if (nearbyPoints == 0) {
                    break;
                }
            }
            if (nearbyPoints > 0) {
                //Meaning all points have a point next to them.
                break;
            } else {
                for (int[] node : nodes) {
                    node[0] += node[2];
                    node[1] += node[3];
                }
            }
        }
        for (int[] node : nodes) {
            minX = minX > node[0] ? node[0] : minX;
            maxX = maxX < node[0] ? node[0] : maxX;
            minY = minY > node[1] ? node[1] : minY;
            maxY = maxY < node[1] ? node[1] : maxY;
        }
        int[][] field = new int[maxY - minY + 1][maxX - minX + 1];
        for (int[] node : nodes) {
            field[node[1] - minY][node[0] - minX] = 4;
        }
        result = nodes.get(0)[0];
        BufferedWriter writer = null;
        try {
            File resultFile = new File("output19.txt");
            System.out.println(resultFile.getCanonicalPath());
            writer = new BufferedWriter(new FileWriter(resultFile));
            for (int i = 0; i < field.length; i++) {
                for (int j = 0; j < field[i].length; j++) {
                    writer.write(field[i][j] + "");
                }
                writer.write("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (Exception e) {

            }
        }
        return result;
    }

    private static int challengeTwenty(List<String> input) {
        int result = 0;
        result = (41710 - 210) / 4;
        //Since my IQ equals what this returns, I am NOT running the simulation again.
        //Instead, recognise that node index 0 has travelled from 41710 to 210
        //(which is what challengeNineteen returns!) in steps of 4.
        //I predict this challenge has made many people feel very dumb.
        return result;
    }

    private static int challengeTwentyOne(int input) {
        int result = 0, rackID = 0, powerLevel = 0, i, j, k, l;
        int[] bestField = new int[3];
        int[][] field = new int[300][300];
        for (i = 1; i <= 300; i++) {
            rackID = i + 10;
            for (j = 1; j <= 300; j++) {
                powerLevel = rackID * j;
                powerLevel += input;
                powerLevel *= rackID;
                powerLevel /= 100;
                powerLevel %= 10;
                powerLevel -= 5;
                field[i - 1][j - 1] = powerLevel;
            }
        }
        for (i = 0; i < 298; i++) {
            for (j = 0; j < 298; j++) {
                powerLevel = 0;
                for (k = 0; k < 3; k++) {
                    for (l = 0; l < 3; l++) {
                        powerLevel += field[i + k][j + l];
                    }
                }
                if (powerLevel > bestField[0]) {
                    bestField[0] = powerLevel;
                    bestField[1] = i;
                    bestField[2] = j;
                }
            }
        }
        System.out.println((bestField[1] + 1) + ", " + (bestField[2] + 1));
        return result;
    }

    private static int challengeTwentyTwo(int input) {
        int result = 0, rackID = 0, powerLevel = 0, i, j, k, l, size;
        int[] bestField = new int[4];
        int[][] field = new int[300][300];
        for (i = 1; i <= 300; i++) {
            rackID = i + 10;
            for (j = 1; j <= 300; j++) {
                powerLevel = rackID * j;
                powerLevel += input;
                powerLevel *= rackID;
                powerLevel /= 100;
                powerLevel %= 10;
                powerLevel -= 5;
                field[i - 1][j - 1] = powerLevel;
            }
        }
        for (size = 1; size <= 300; size++) {
            for (i = 0; i <= 300 - size; i++) {
                for (j = 0; j <= 300 - size; j++) {
                    powerLevel = 0;
                    for (k = 0; k < size; k++) {
                        for (l = 0; l < size; l++) {
                            powerLevel += field[i + k][j + l];
                        }
                    }
                    if (powerLevel > bestField[0]) {
                        bestField[0] = powerLevel;
                        bestField[1] = i;
                        bestField[2] = j;
                        bestField[3] = size;
                    }
                }
            }
        }
        System.out.println((bestField[1] + 1) + ", " + (bestField[2] + 1) + ", " + bestField[3]);
        return result;
    }

    private static int challengeTwentyThree(List<String> input) {
        int result = 0, i, j, k;
        String initialState = input.get(0).replaceAll("[^#.]", "");
        String surroundings;
        char[] line = new char[initialState.length() + 42 * 2];
        char[] newLine;
        for (i = 0; i < initialState.length(); i++) {
            line[i + 42] = initialState.charAt(i);
        }
        for (i = 0; i < 42; i++) {
            line[i] = '.';
            line[i + 42 + initialState.length()] = '.';
        }
        Map<String, Character> rules = new HashMap<>(32);
        for (i = 2; i < 34; i++) {
            String rule = input.get(i);
            rules.put(rule.substring(0, 5), rule.charAt(9));
        }
        for (i = 0; i < 20; i++) {
            newLine = line.clone();
            for (j = 2; j < line.length - 2; j++) {
                surroundings = "";
                for (k = 0; k < 5; k++) {
                    surroundings += line[j + k - 2];
                }
                if (rules.get(surroundings) == null) {
                    System.out.println(rules.get(surroundings));
                }
                newLine[j] = rules.get(surroundings);
            }
            line = newLine;
        }
        for (i = 0; i < line.length; i++) {
            if (line[i] == '#') {
                result += i - 42;
            }
        }
        return result;
    }

    private static int challengeTwentyFour(List<String> input) {
        int result = 0, i, j, k, previousResult;
        String initialState = input.get(0).replaceAll("[^#.]", "");
        String surroundings;
        char[] line = new char[initialState.length() + 20002 * 2];
        char[] newLine;
        for (i = 0; i < initialState.length(); i++) {
            line[i + 20002] = initialState.charAt(i);
        }
        for (i = 0; i < 20002; i++) {
            line[i] = '.';
            line[i + 20002 + initialState.length()] = '.';
        }
        Map<String, Character> rules = new HashMap<>(32);
        for (i = 2; i < 34; i++) {
            String rule = input.get(i);
            rules.put(rule.substring(0, 5), rule.charAt(9));
        }
        for (i = 0; i < 10000; i++) {
            previousResult = result;
            result = 0;
            newLine = line.clone();
            for (j = 2; j < line.length - 2; j++) {
                surroundings = "";
                for (k = 0; k < 5; k++) {
                    surroundings += line[j + k - 2];
                }
                try {
                    newLine[j] = rules.get(surroundings);
                } catch (Exception e) {
                    System.out.println(i + " " + surroundings);
                    System.exit(1);
                }
            }
            line = newLine;
            for (j = 0; j < line.length; j++) {
                if (line[j] == '#') {
                    result += j - 20002;
                }
            }
            if (result - previousResult < 100) {
                System.out.println((result - previousResult) + " " + i + " " + result);
            }
        }
        return result;
    }

    private static String challengeTwentyFive(List<String> input) {
        String result = "";
        int x, y, nextX, nextY;
        char currentSquare, nextSquare;
        char[][] tracks = new char[input.get(0).length()][input.size()];
        List<Cart> carts = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            tracks[i] = input.get(i).toCharArray();
        }
        for (y = 0; y < tracks.length; y++) {
            for (x = 0; x < tracks[y].length; x++) {
                currentSquare = tracks[y][x];
                if (currentSquare == '^' || currentSquare == 'v') {
                    tracks[y][x] = '|';
                    carts.add(new Cart(x, y, currentSquare));
                } else if (currentSquare == '<' || currentSquare == '>') {
                    tracks[y][x] = '-';
                    carts.add(new Cart(x, y, currentSquare));
                }
            }
        }
        while (true) {
            for (Cart cart : carts) {
                nextX = cart.getX();
                nextY = cart.getY();
                switch (cart.getCurrentDirection()) {
                    case Cart.UP:
                        nextY--;
                        break;
                    case Cart.DOWN:
                        nextY++;
                        break;
                    case Cart.LEFT:
                        nextX--;
                        break;
                    case Cart.RIGHT:
                        nextX++;
                        break;
                }
                nextSquare = tracks[nextY][nextX];
                cart.executeStep(nextSquare);
                for (Cart otherCart : carts) {
                    if (cart != otherCart && cart.getX() == otherCart.getX() && cart.getY() == otherCart.getY()) {
                        result = cart.getX() + "," + cart.getY();
                        break;
                    }
                }
            }
            if (!result.equals("")) {
                break;
            }
        }
        return result;
    }

    private static String challengeTwentySix(List<String> input) {
        String result = "";
        int x, y, nextX, nextY;
        Cart cart = null, otherCart = null;
        List<Cart> cartsToBeRemoved = new ArrayList<>();
        char currentSquare, nextSquare;
        char[][] tracks = new char[input.get(0).length()][input.size()];
        List<Cart> carts = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            tracks[i] = input.get(i).toCharArray();
        }
        for (y = 0; y < tracks.length; y++) {
            for (x = 0; x < tracks[y].length; x++) {
                currentSquare = tracks[y][x];
                if (currentSquare == '^' || currentSquare == 'v') {
                    tracks[y][x] = '|';
                    carts.add(new Cart(x, y, currentSquare));
                } else if (currentSquare == '<' || currentSquare == '>') {
                    tracks[y][x] = '-';
                    carts.add(new Cart(x, y, currentSquare));
                }
            }
        }
        while (true) {
            for (int i = 0; i < carts.size(); i++) {
                cart = carts.get(i);
                nextX = cart.getX();
                nextY = cart.getY();
                switch (cart.getCurrentDirection()) {
                    case Cart.UP:
                        nextY--;
                        break;
                    case Cart.DOWN:
                        nextY++;
                        break;
                    case Cart.LEFT:
                        nextX--;
                        break;
                    case Cart.RIGHT:
                        nextX++;
                        break;
                }
                nextSquare = tracks[nextY][nextX];
                cart.executeStep(nextSquare);
                for (int j = 0; j < carts.size(); j++) {
                    otherCart = carts.get(j);
                    if (cart != otherCart && cart.getX() == otherCart.getX() && cart.getY() == otherCart.getY()
                            && !cartsToBeRemoved.contains(cart) && !cartsToBeRemoved.contains(otherCart)) {
                        cartsToBeRemoved.add(cart);
                        cartsToBeRemoved.add(otherCart);
                        break;
                    }
                }
            }
            for (Cart removedCart : cartsToBeRemoved) {
                carts.remove(removedCart);
            }
            cartsToBeRemoved.clear();
            if (!result.equals("")) {
                break;
            }
            carts.sort(null);
            if (carts.size() == 1) {
                cart = carts.get(0);
                result = cart.getX() + "," + cart.getY();
            }
        }
        return result;
    }

    private static long challengeTwentySeven(int input) {
        long result = 0;
        int firstElfPointer = 0, secondElfPointer = 1, newRecipe = 0, firstRecipe, secondRecipe;
        List<Integer> recipes = new ArrayList<>((int) (input * 1.45) + 2 + 10);
        recipes.add(3);
        recipes.add(7);
        while (recipes.size() < input + 10) {
            firstRecipe = recipes.get(firstElfPointer);
            secondRecipe = recipes.get(secondElfPointer);
            newRecipe = firstRecipe + secondRecipe;
            if (newRecipe >= 10) {
                recipes.add(1); //as there are no two digits that add up to more than 18.
            }
            recipes.add(newRecipe % 10);
            firstElfPointer += firstRecipe + 1;
            firstElfPointer = firstElfPointer >= recipes.size() ? firstElfPointer % recipes.size() : firstElfPointer;
            secondElfPointer += secondRecipe + 1;
            secondElfPointer = secondElfPointer >= recipes.size() ? secondElfPointer % recipes.size() : secondElfPointer;
        }
        for (int i = 0; i < 10; i++) {
            result *= 10;
            result += recipes.get(input + i);
        }
        return result;
    }

    private static int challengeTwentyEight(int input) {
        int result = 0, maxValue = 10;
        int firstElfPointer = 0, secondElfPointer = 1, newRecipe = 0, firstRecipe, secondRecipe;
        List<Integer> recipes = null;
        List<Integer> desiredSequence = new ArrayList<>();
        while (input > 0) {
            desiredSequence.add(0, input % 10);
            input /= 10;
        }
        while (result == 0) {
            recipes = new ArrayList<>(maxValue);
            recipes.add(3);
            recipes.add(7);
            firstElfPointer = 0;
            secondElfPointer = 1;
            for (int i = 0; i < maxValue; i++) {
                firstRecipe = recipes.get(firstElfPointer);
                secondRecipe = recipes.get(secondElfPointer);
                newRecipe = firstRecipe + secondRecipe;
                if (newRecipe >= 10) {
                    recipes.add(1); //as there are no two digits that add up to more than 18.
                }
                recipes.add(newRecipe % 10);
                firstElfPointer += firstRecipe + 1;
                firstElfPointer = firstElfPointer >= recipes.size() ? firstElfPointer % recipes.size() : firstElfPointer;
                secondElfPointer += secondRecipe + 1;
                secondElfPointer = secondElfPointer >= recipes.size() ? secondElfPointer % recipes.size() : secondElfPointer;
            }
            for (int i = 0; i < maxValue - desiredSequence.size(); i++) {
                if (recipes.subList(i, i + desiredSequence.size()).equals(desiredSequence)) {
                    result = i;
                    break;
                }
            }
            maxValue *= 10;
        }
        return result;
    }

    private static int challengeTwentyNine(List<String> input) {
        int result = 0;
        char[][] field = new char[input.size()][];
        for (int i = 0; i < input.size(); i++) {
            field[i] = input.get(i).toCharArray();
        }
        BattleController controller = new BattleController(field);
        result = controller.executeBattleOne();
        return result;
    }

    private static int challengeThirty(List<String> input) {
        int result = 1000000, previousResult = -1, elfAttackPower = 15;
        char[][] field;
        BattleController controller;
        do {
            previousResult = result;
            field = new char[input.size()][];
            for (int i = 0; i < input.size(); i++) {
                field[i] = input.get(i).toCharArray();
            }
            controller = new BattleController(field);
            result = controller.executeBattleTwo(elfAttackPower);
            System.out.println(elfAttackPower + " " + result);
            elfAttackPower++;
        } while (result == -1);
        return result;
    }

    private static int challengeThirtyOne(List<String> input) {
        int result = 0;
        long[] before = new long[4], after = new long[4];
        int[] operation = new int[4];
        String[] beforeSplit, operationSplit, afterSplit;
        Map<String, Integer> lookupTable = new HashMap<>();
        for (Integer i = 0; i < 16; i++) {
            lookupTable.put(i.toString(), i);
        }
        Set<String> possibleOperations;
        for (int i = 0; i < input.size(); i += 4) {
            beforeSplit = input.get(i).replaceAll("[^\\d ]", "").split(" ");
            operationSplit = input.get(i + 1).replaceAll("[^\\d ]", "").split(" ");
            afterSplit = input.get(i + 2).replaceAll("[^\\d ]", "").split(" ");

            for (int j = 0; j < 4; j++) {
                before[j] = lookupTable.get(beforeSplit[j + 1]);
                operation[j] = lookupTable.get(operationSplit[j]);
                after[j] = lookupTable.get(afterSplit[j + 2]);
            }
            possibleOperations = OperationsManager31.checkAllOperations(before, operation, after);
            if (possibleOperations.size() >= 3) {
                result++;
            }
        }
        return result;
    }

    private static long challengeThirtyTwo(List<String> input) {
        long result = 0;
        int mostPossibleOperations;
        long[] before = new long[4], after = new long[4];
        int[] operation = new int[4];
        String[] beforeSplit, operationSplit, afterSplit;
        Map<String, Integer> lookupTable = new HashMap<>();
        Map<Integer, Set<String>> operationsPerOpCode = new HashMap<>();
        for (Integer i = 0; i < 16; i++) {
            lookupTable.put(i.toString(), i);
            operationsPerOpCode.put(i, new HashSet<>());
        }
        Set<String> possibleOperations;
        int i = 0;
        while (!input.get(i).equals("")) {
            beforeSplit = input.get(i).replaceAll("[^\\d ]", "").split(" ");
            operationSplit = input.get(i + 1).replaceAll("[^\\d ]", "").split(" ");
            afterSplit = input.get(i + 2).replaceAll("[^\\d ]", "").split(" ");

            for (int j = 0; j < 4; j++) {
                before[j] = lookupTable.get(beforeSplit[j + 1]);
                operation[j] = lookupTable.get(operationSplit[j]);
                after[j] = lookupTable.get(afterSplit[j + 2]);
            }

            possibleOperations = OperationsManager31.checkAllOperations(before, operation, after);
            operationsPerOpCode.get(operation[0]).addAll(possibleOperations);

            i += 4;
        }
        do {
            mostPossibleOperations = 0;
            for (Set<String> operations : operationsPerOpCode.values()) {
                if (operations.size() == 1) {
                    for (Set<String> otherOperations : operationsPerOpCode.values()) {
                        if (otherOperations != operations) {
                            otherOperations.remove(operations.iterator().next());
                        }
                    }
                }
                mostPossibleOperations = mostPossibleOperations < operations.size() ? operations.size() : mostPossibleOperations;
            }
        } while (mostPossibleOperations > 1);
        long[] registers = new long[4];
        for (i = i + 2; i < input.size(); i++) {
            operationSplit = input.get(i).split(" ");
            for (int j = 0; j < 4; j++) {
                operation[j] = lookupTable.get(operationSplit[j]);
            }
            registers = OperationsManager31.executeOperation(registers, operation, operationsPerOpCode.get(operation[0]).iterator().next());
        }
        for (Map.Entry<Integer, Set<String>> entry : operationsPerOpCode.entrySet()) {
            System.out.println(entry);
        }
        return registers[0];
    }

    private static int challengeThirtyThree(List<String> input) {
        int result = 0;
        WaterManager33 manager = new WaterManager33(input);
        result = manager.locateWater();
        return result;
    }

    private static int challengeThirtyFour(List<String> input) {
        int result = 0;
        WaterManager33 manager = new WaterManager33(input);
        result = manager.locateRestingWater();
        return result;
    }

    private static int challengeThirtyFive(List<String> input) {
        int result = 0;
        TreeManager35 manager = new TreeManager35(input);
        result = manager.manageTrees();
        return result;
    }

    private static int challengeThirtySix(List<String> input) {
        int result = 0;
        return result;
    }

    private static long challengeThirtySeven(List<String> input) {
        long result = 0;
        int instructionPointerRegister = input.get(0).charAt(4) - '0';
        result = OperationsManager31.runProgram37(input.subList(1, input.size()), new long[6], instructionPointerRegister);
        return result;
    }

    private static long challengeThirtyEight(List<String> input) {
        int result = 0;
        float boundary = 10551408;
        for (float i = 1; i * i <= boundary; i++) {
            if ((boundary / i) % 1 == 0) {
                result += i;
                result += boundary / i;
            }
        }
        /*
        A little commentary: What the program in the input effectively does is to
         calculate the sum of all numbers between 1 and the boundary, inclusive,
        where boundary / number is a whole number. The above algorithm does this
        a little more elegently. Rather than counting up one by one from 1, check
        only to the square root of the number and add both factors instead of only
        the one currently stored in i.

        This can be done even more efficiently (as an exercise to the reader, hurr
        hurr) by breaking the number into its factors and adding together all
        multiplicatives of one or more of those factors. For instance, the first
        part had me do this with 1008. 1008 is 2^4 * 3^2 * 7^1, so the result would
        be the sum of all 2^i * 3^j * 7^k, wherein i is 0-4, j is 0-2, and k is 0-1.

        The boundary can be extracted from register 3 after the initial setup is
        complete. Specifically, when the IP is 35 or 36, which will be the case
        exactly once.*/
        return result;
    }

    private static int challengeThirtyNine(List<String> input) {
        MapBuilder39 builder = new MapBuilder39(input.get(0));
        int result = builder.findFarthestRoom();
        return result;
    }

    private static long challengeFortyOne(List<String> input) {
        long result = 0;
        int instructionPointerRegister = input.get(0).charAt(4) - '0';
        result = OperationsManager31.runProgram41(input.subList(1, input.size()), new long[]{15615244, 0, 0, 0, 0, 0}, instructionPointerRegister);
        return result;
    }

    private static long challengeFortyTwo(int input) {
        int commands = 0;
        long result = 0;
        List<Long> visitedNumbers = new ArrayList<>();
        commands = 5;
        long[] registers = new long[]{input, 0, 0, 0, 65536, 15466939};
        while (commands < Integer.MAX_VALUE) {
            while (registers[4] > 0) {
                registers[5] += registers[4] & 255;
                registers[5] &= 16777215;
                registers[5] *= 65899;
                registers[5] &= 16777215;
                registers[4] = registers[4] >> 8;
            }
            if (registers[0] == registers[5]) {
                break;
            } else {
                if (visitedNumbers.contains(registers[5])) {
                    break;
                }
                visitedNumbers.add(registers[5]);
                registers[4] = registers[5] | 65536;
                registers[5] = 15466939;
            }
            commands++;
        }
        return visitedNumbers.get(visitedNumbers.size() - 1);
    }

    private static int challengeFortyThree(List<String> input) {
        int result = 0, x, y;
        int depth = Integer.parseInt(input.get(0).replaceAll("[^\\d]", ""));
        String[] targetCoordinates = input.get(1).replaceAll("[^\\d,]", "").split(",");
        int targetX = Integer.parseInt(targetCoordinates[0]);
        int targetY = Integer.parseInt(targetCoordinates[1]);
        int[][] cave = new int[targetY + 1][targetX + 1];
        for (y = 0; y < cave.length; y++) {
            cave[y][0] = (y * 7905 + depth) % 20183;
        }
        for (x = 0; x < cave[0].length; x++) {
            cave[0][x] = (x * 16807 + depth) % 20183;
        }
        for (y = 1; y < cave.length; y++) {
            for (x = 1; x < cave[y].length; x++) {
                cave[y][x] = (cave[y - 1][x] * cave[y][x - 1] + depth) % 20183;
            }
        }
        cave[0][0] = 0;
        cave[targetY][targetX] = 0;
        for (y = 0; y < cave.length; y++) {
            for (x = 0; x < cave[y].length; x++) {
                result += cave[y][x] % 3;
            }
        }
        return result;
    }

    private static int challengeFortyFour(List<String> input) {
        int result = 0, x, y, numberOfChanges, quickestPath, tool;
        int depth = Integer.parseInt(input.get(0).replaceAll("[^\\d]", ""));
        String[] targetCoordinates = input.get(1).replaceAll("[^\\d,]", "").split(",");
        int targetX = Integer.parseInt(targetCoordinates[0]);
        int targetY = Integer.parseInt(targetCoordinates[1]);
        int worstCaseSize = (targetY + targetX + 1) * 4;
        /*Why this number? Suppose the worst case, that a tool swap was necessary
        on EVERY step. This would mean that moving into every square would have
        a cost of eight minutes. This is the most time any detours can take. An
        alternative direct path which goes off target for a bit before going back
        while never switching tools would need to take at most this long.
        However, we don't need space to go in the wrong direction for the worst
        case time, as that would leave no time to reach the target. Instead, we
        need to allocate 2 minutes to step the wrong way and step back towards
        the target eventually.
        E.g. if the target is at 10,10 then it would take 167 minutes to reach
        it with brute force. Simply walking towards it takes 20 minutes, leaving
        us with 147 minutes for detours. We can walk past the target on the
        X-axis by 73 squares before needing to turn back. As such, the maximum
        capacity needed for the worst-case optimal path is 83 spaces on each axis.

        All that being said, this is not exactly an efficient algorithm (oops).
        For performance, try lower values first (casting to int if need be for
        fractions) and see if the output changes.*/
        int[][] cave = new int[worstCaseSize][worstCaseSize];
        boolean[][][] canTraverse = new boolean[worstCaseSize][worstCaseSize][3];
        int[][][] caveWithTool = new int[worstCaseSize][worstCaseSize][3];
        for (y = 0; y < canTraverse.length; y++) {
            for (x = 0; x < canTraverse.length; x++) {
                Arrays.fill(canTraverse[y][x], true);
            }
        }
        for (y = 0; y < cave.length; y++) {
            cave[y][0] = (y * 7905 + depth) % 20183;
        }
        for (x = 0; x < cave[0].length; x++) {
            cave[0][x] = (x * 16807 + depth) % 20183;
        }
        for (y = 1; y < cave.length; y++) {
            for (x = 1; x < cave[y].length; x++) {
                cave[y][x] = (cave[y - 1][x] * cave[y][x - 1] + depth) % 20183;
            }
        }
        cave[0][0] = 0;
        cave[targetY][targetX] = 0;
        for (y = 0; y < cave.length; y++) {
            for (x = 0; x < cave[y].length; x++) {
                canTraverse[y][x][cave[y][x] % 3] = false;
                cave[y][x] %= 3;
                Arrays.fill(caveWithTool[y][x], Integer.MAX_VALUE - 10);
            }
        }
        caveWithTool[0][0][1] = 0;
        //0: Neither; 1: Torch; 2: Climbing gear.
        do {
            numberOfChanges = 0;
            for (tool = 0; tool < 3; tool++) {
                for (y = 0; y < cave.length; y++) {
                    for (x = 0; x < cave[y].length; x++) {
                        if (canTraverse[y][x][tool]) {
                            quickestPath = findQuickestWayTo(caveWithTool, y, x, tool);
                            if (quickestPath < caveWithTool[y][x][tool]) {
                                caveWithTool[y][x][tool] = quickestPath;
                                numberOfChanges++;
                            }
                        }
                    }
                }
            }
        } while (numberOfChanges > 0);
        result = caveWithTool[targetY][targetX][1];
        return result;
    }

    private static int findQuickestWayTo(int[][][] caveWithTool, int y, int x, int tool) {
        int quickestPath = caveWithTool[y][x][tool], i;
        for (i = -1; i <= 1; i += 2) {
            if (0 <= y + i && y + i < caveWithTool.length
                    && caveWithTool[y + i][x][tool] + 1 < quickestPath) {
                quickestPath = caveWithTool[y + i][x][tool] + 1;
            }
            if (0 <= x + i && x + i < caveWithTool[y].length
                    && caveWithTool[y][x + i][tool] + 1 < quickestPath) {
                quickestPath = caveWithTool[y][x + i][tool] + 1;
            }
        }
        for (i = 1; i <= 2; i++) {
            if (caveWithTool[y][x][(tool + i) % 3] + 7 < quickestPath) {
                quickestPath = caveWithTool[y][x][(tool + i) % 3] + 7;
            }
        }
        return quickestPath;
    }

    private static int challengeFortyFive(List<String> input) {
        int result = 0, radius, longestRadius = 0, i;
        List<int[]> nanobots = new ArrayList<>();
        String[] lineSplit;
        int[] nanobot, strongestBot = new int[3];
        for (String line : input) {
            nanobot = new int[3];
            lineSplit = line.replaceAll("[^\\d-,]", "").split(",");
            for (i = 0; i < 3; i++) {
                nanobot[i] = Integer.parseInt(lineSplit[i]);
            }
            nanobots.add(nanobot);
            radius = Integer.parseInt(lineSplit[3]);
            if (radius > longestRadius) {
                longestRadius = radius;
                strongestBot = nanobot;
            }
        }
        for (int[] bot : nanobots) {
            /*if (getManhattanDistance(bot, strongestBot) <= longestRadius) {
                result++;
            }*/
        }
        return result;
    }

    private static int challengeFortySix(List<String> input) {
        int range, x, y, z, i, currentConnections, currentDistance, remainingBots,
                result = 0, mostConnections = 0, leastDistance = Integer.MAX_VALUE;
        int[] origin = new int[]{0, 0, 0}, bestPoint = new int[]{0, 0, 0};
        int[][] points;
        boolean distanceUpdated = false, pointIsInRange;
        long startTime = System.currentTimeMillis(), timeAtLastCheckpoint = 0;
        List<Nanobot46> nanobots = new ArrayList<>(input.size()),
                includedBots = new ArrayList<>(input.size()),
                bestGroup = new ArrayList<>(input.size());
        List<int[]> surroundingPoints = new ArrayList<>(3);
        String[] lineSplit;
        System.out.println("Ready to go! Reading input...");
        timeAtLastCheckpoint = System.currentTimeMillis();
        //Parse int
        for (String line : input) {
            lineSplit = line.replaceAll("[^\\d-,]", "").split(",");
            x = Integer.parseInt(lineSplit[0]);
            y = Integer.parseInt(lineSplit[1]);
            z = Integer.parseInt(lineSplit[2]);
            range = Integer.parseInt(lineSplit[3]);
            nanobots.add(new Nanobot46(x, y, z, range));
        }
        System.out.println("Finished reading " + nanobots.size() + " nanobots in "
                + (System.currentTimeMillis() - timeAtLastCheckpoint) + "ms. Comparing points...");
        timeAtLastCheckpoint = System.currentTimeMillis();
        //Search 
        for (Nanobot46 currentBot : nanobots) {
            points = currentBot.getCorners();
            for (int[] currentPoint : points) {
                currentConnections = 0;
                currentDistance = getManhattanDistanceBetweenPoints(origin, currentPoint);
                remainingBots = nanobots.size();
                includedBots.clear();
                for (Nanobot46 otherBot : nanobots) {
                    if (mostConnections - currentConnections > remainingBots
                            || (mostConnections - currentConnections == remainingBots && currentDistance >= leastDistance)) {
                        break;
                    }
                    if (otherBot.pointIsInRange(currentPoint)) {
                        currentConnections++;
                        includedBots.add(otherBot);
                    }
                    remainingBots--;
                }
                if (currentConnections >= mostConnections) {
                    mostConnections = currentConnections;
                    leastDistance = getManhattanDistanceBetweenPoints(origin, currentPoint);
                    bestPoint = currentPoint;
                    bestGroup.clear();
                    bestGroup.addAll(includedBots);
                    System.out.println("Found better point [" + bestPoint[0] + ", "
                            + bestPoint[1] + ", " + bestPoint[2] + "] which has "
                            + "a distance of " + leastDistance + " and " + mostConnections
                            + " connections after " + (System.currentTimeMillis() - timeAtLastCheckpoint)
                            + "ms since the last one. This is the one, I'm sure!");
                    timeAtLastCheckpoint = System.currentTimeMillis();
                }
            }
        }
        System.out.println("That's all points processed at "
                + (System.currentTimeMillis() - startTime) + "ms since start. "
                + "See previous line for the readout. However, I'm sure I can do"
                + " better still! Starting crawl towards origin...");
        timeAtLastCheckpoint = System.currentTimeMillis();
        do {
            distanceUpdated = false;
            surroundingPoints.clear();
            for (i = 0; i < bestPoint.length; i++) {
                if (bestPoint[i] != 0) {
                    int[] closerPoint = bestPoint.clone();
                    closerPoint[i] += -1 * Math.signum(bestPoint[i]);
                    surroundingPoints.add(closerPoint);
                }
            }
            for (int[] currentPoint : surroundingPoints) {
                pointIsInRange = true;
                for (Nanobot46 currentBot : bestGroup) {
                    if (!currentBot.pointIsInRange(currentPoint)) {
                        pointIsInRange = false;
                        break;
                    }
                }
                if (pointIsInRange) {
                    bestPoint = currentPoint;
                    distanceUpdated = true;
                    break;
                }
            }
        } while (distanceUpdated);
        result = getManhattanDistanceBetweenPoints(origin, bestPoint);
        System.out.print("All finished after " + (System.currentTimeMillis() - startTime)
                + "ms. It took me " + (System.currentTimeMillis() - timeAtLastCheckpoint)
                + "ms to optimise from the best corner. (Maybe I did no optimising "
                + "at all. It is only necessary in an edge case.) \n"
                + "The best point I could find is ["
                + bestPoint[0] + ", " + bestPoint[1] + ", " + bestPoint[2]
                + "], with a distance from the origin of ");
        return result;
    }

    private static int getManhattanDistanceBetweenPoints(int[] firstPoint, int[] secondPoint) {
        int distance = 0;
        for (int i = 0; i < firstPoint.length; i++) {
            distance += Math.abs(firstPoint[i] - secondPoint[i]);
        } //Should firstPoint be longer than secondPoint, this will throw an exception.
        //I am choosing to disregard this as differently long points are uncomparable
        //anyway and I know for certain they will be the same length in this use case.
        return distance;
    }

    private static int challengeFortySeven(List<String> input) {
        int result = 0;
        ImmunoController47 controller = new ImmunoController47(input);
        result = controller.runBattle();
        return result;
    }

    private static int challengeFortyEight(List<String> input) {
        int result = 0, boost = 36;
        ImmunoController47 controller;
        do {
            System.out.println(boost);
            controller = new ImmunoController47(input, boost);
            result = controller.runBattle2();
            boost++;
        } while (result < 0);
        /*
        It ought to be noted, which is probably the point of the challenge, that
        it is possible for groups to get into a stalemate wherein neither side
        deals enough damage to kill any units, and thus the program loops infinitely.

        It's not difficult to detect that - one must simply implement a unitsKilled
        variable which is incremented whenever a unit dies, and abort the battle
        if it should ever be 0 after a round. This is left as an exercise to the
        reader as it's rather late and my body demands hot chocolate and video
        games now.

        I chose to leave that case as a possibility as executing a battle is very
        fast, and thus it's not hard to detect (with your eyeholes) when it's
        looping on a given boost number for too long.
         */
        return result;
    }

    private static int challengeFortyNine(List<String> input) {
        int result = 0, i, numberOfChanges;
        String[] lineSplit;
        int[] newNode;
        boolean newConstellation, join;
        Set<int[]> existingConstellation = null;
        Iterator it;
        Set<int[]> currentConstellation;
        Set<int[]> openNodes = new HashSet<>(input.size());
        List<Set<int[]>> constellations = new ArrayList<>();
        /*for (String line : input) {
            newNode = new int[4];
            lineSplit = line.split(",");
            for (i = 0; i < 4; i++) {
                newNode[i] = Integer.parseInt(lineSplit[i]);
            }
            openNodes.add(newNode);
        }
        for (int[] currentNode : openNodes) {
            newConstellation = true;
            for (i = 0; i < constellations.size(); i++) {
                currentConstellation = constellations.get(i);
                for (int[] node : currentConstellation) {
                    if (getManhattanDistance(node, currentNode) <= 3) {
                        existingConstellation = currentConstellation;
                        newConstellation = false;
                    }
                }
            }
            if (newConstellation) {
                currentConstellation = new HashSet<>();
                currentConstellation.add(currentNode);
                constellations.add(currentConstellation);
            } else {
                existingConstellation.add(currentNode);
            }
        }
        do {
            numberOfChanges = 0;
            it = constellations.iterator();
            while (it.hasNext()) {
                join = false;
                currentConstellation = (Set<int[]>) it.next();
                for (Set<int[]> constellation : constellations) {
                    if (constellation != currentConstellation
                            && getShortestManhattanDistanceBetween(constellation, currentConstellation) <= 3) {
                        join = true;
                        existingConstellation = constellation;
                    }
                }
                if (join) {
                    it.remove();
                    existingConstellation.addAll(currentConstellation);
                    numberOfChanges++;
                }
            }
        } while (numberOfChanges > 0);*/
        return constellations.size();
    }

}
