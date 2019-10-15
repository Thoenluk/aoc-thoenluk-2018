package adventofcode2018;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author Lukas Thöni
 */
public class ImmunoController47 {

    private final PowerComparator powerComp = new PowerComparator();
    private final InitiativeComparator initativeComp = new InitiativeComparator();
    private final IDComparator IDComp = new IDComparator();

    private List<Group47> groups;

    public ImmunoController47(List<String> input, int boost) {
        groups = new ArrayList<>(input.size() - 3);
        String faction = "", attackType = "";
        String[] lineSplit;
        int numberOfUnits = 0, unitHitPoints = 0, attackDamage = 0, initiative = 0, i, ID = 1;
        Set<String> weaknesses, immunities;
        for (String line : input) {
            switch (line) {
                case "Immune System:":
                    faction = Group47.IMMUNE;
                    break;
                case "Infection:":
                    faction = Group47.INFECTION;
                    ID = 1;
                    break;
                case "":
                    break;
                default:
                    weaknesses = new TreeSet<>();
                    immunities = new TreeSet<>();
                    line = line.replaceAll("[();,]", "");
                    lineSplit = line.split(" ");
                    for (i = 0; i < lineSplit.length; i++) {
                        switch (lineSplit[i]) {
                            case "units":
                                numberOfUnits = Integer.parseInt(lineSplit[i - 1]);
                                break;
                            case "hit":
                                unitHitPoints = Integer.parseInt(lineSplit[i - 1]);
                                break;
                            case "immune":
                                i += 2;
                                while (Group47.isDamageType(lineSplit[i])) {
                                    immunities.add(lineSplit[i]);
                                    i++;
                                }
                                i--;
                                break;
                            case "weak":
                                i += 2;
                                while (Group47.isDamageType(lineSplit[i])) {
                                    weaknesses.add(lineSplit[i]);
                                    i++;
                                }
                                i--;
                                break;
                            case "does":
                                attackDamage = Integer.parseInt(lineSplit[i + 1]);
                                attackType = lineSplit[i + 2];
                                break;
                            case "initiative":
                                initiative = Integer.parseInt(lineSplit[i + 1]);
                                break;
                        }
                    }
                    if (faction.equals(Group47.IMMUNE)) {
                        attackDamage += boost;
                    }
                    groups.add(new Group47(unitHitPoints, attackDamage, initiative, ID,
                            numberOfUnits, attackType, faction, weaknesses, immunities));
                    ID++;
                    break;
            }
        }
    }

    public ImmunoController47(List<String> input) {
        this(input, 0);
    }

    public int runBattle() {
        int result = 0, maximumDamage = 0, currentDamage, round = 0, ID;
        List<Group47> chosenTargets = new ArrayList<>(), possibleTargets = new ArrayList<>(),
                deadGroups = new ArrayList<>();
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(new File("output47.txt"), true))) {
            while (battleContinues()) {
                /*groups.sort(IDComp);
                writer.println(round++);
                writer.println("Immune system:");
                ID = 1;
                for (Group47 group : groups) {
                    if (group.getFaction().equals(Group47.IMMUNE)) {
                        writer.println("Group " + ID + " contains "
                                + group.getNumberOfUnits() + " units");
                        ID++;
                    }
                }
                ID = 1;
                writer.println("Infection:");
                for (Group47 group : groups) {
                    if (group.getFaction().equals(Group47.INFECTION)) {
                        writer.println("Group " + ID + " contains "
                                + group.getNumberOfUnits() + " units");
                        ID++;
                    }
                }*/
                groups.sort(powerComp);
                chosenTargets.clear();
                for (Group47 group : groups) {
                    possibleTargets.clear();
                    maximumDamage = 0;
                    for (Group47 possibleTarget : groups) {
                        if (!possibleTarget.getFaction().equals(group.getFaction())
                                && !chosenTargets.contains(possibleTarget)) {
                            currentDamage = group.getDamagePossibleTo(possibleTarget);
                            /*writer.println(group.getFaction() + " group initative "
                                    + group.getInitiative() + " would deal " + currentDamage
                                    + "\t to group iniative " + possibleTarget.getInitiative() + " with power " + possibleTarget.getEffectivePower());*/
                            if (currentDamage > maximumDamage) {
                                possibleTargets.clear();
                                maximumDamage = currentDamage;
                            }
                            if (currentDamage == maximumDamage && currentDamage > 0) {
                                possibleTargets.add(possibleTarget);
                            }
                        }
                    }
                    if (possibleTargets.size() > 0) {
                        if (round == 283) {
                            System.out.println("penis");
                        }
                        group.setTarget(possibleTargets.get(0));
                        chosenTargets.add(possibleTargets.get(0));
                        //writer.println("It chooses group iniative " + possibleTargets.get(0).getInitiative());
                    } else {
                        group.setTarget(null);
                    }

                }
                groups.sort(initativeComp);
                //writer.println();
                //writer.println("Attack phase!");
                for (Group47 attacker : groups) {
                    if (attacker.containsLiveUnits()) {
                        if (attacker.getTarget() != null) {
                            //priorUnits = attacker.getTarget().getNumberOfUnits();
                            deadGroups.add(attacker.attackTarget());
                            /*writer.println(attacker.getFaction() + " group iniative "
                                    + attacker.getInitiative() + " attacked group iniative "
                                    + attacker.getTarget().getInitiative() + " for " + attacker.getDamagePossibleTo(attacker.getTarget())
                                    + " and killed " + (priorUnits - attacker.getTarget().getNumberOfUnits()));
                             */
                        }
                    }
                }
                groups.removeAll(deadGroups);
                deadGroups.clear();
            }
        } catch (Exception e) {

        }
        for (Group47 group : groups) {
            result += group.getNumberOfUnits();
        }
        return result;
    }

    public int runBattle2() {
        int result = runBattle();
        if (groups.get(0).getFaction().equals(Group47.INFECTION)) {
            return -1;
        } else {
            return result;
        }
    }

    private boolean battleContinues() {
        String firstFactionEncountered = groups.get(0).getFaction();
        for (Group47 group : groups) {
            if (!firstFactionEncountered.equals(group.getFaction())) {
                return true;
            }
        }
        return false;
    }

    public class PowerComparator implements Comparator {

        @Override
        public int compare(Object o1, Object o2) {
            if (!(o1 instanceof Group47 && o2 instanceof Group47)) {
                throw new UnsupportedOperationException("Not supported.");
            }
            if (o1.equals(o2)) {
                return 0;
            }
            Group47 group1 = (Group47) o1;
            Group47 group2 = (Group47) o2;
            if (group1.getEffectivePower() == group2.getEffectivePower()) {
                return -(group1.getInitiative() - group2.getInitiative());
            } else {
                return -(group1.getEffectivePower() - group2.getEffectivePower());
            }
        }

    }

    public class InitiativeComparator implements Comparator {

        @Override
        public int compare(Object o1, Object o2) {
            if (!(o1 instanceof Group47 && o2 instanceof Group47)) {
                throw new UnsupportedOperationException("Not supported.");
            }
            if (o1.equals(o2)) {
                return 0;
            }
            Group47 group1 = (Group47) o1;
            Group47 group2 = (Group47) o2;
            return -(group1.getInitiative() - group2.getInitiative());
        }

    }

    public class IDComparator implements Comparator {

        @Override
        public int compare(Object o1, Object o2) {
            if (!(o1 instanceof Group47 && o2 instanceof Group47)) {
                throw new UnsupportedOperationException("Not supported.");
            }
            if (o1.equals(o2)) {
                return 0;
            }
            Group47 group1 = (Group47) o1;
            Group47 group2 = (Group47) o2;
            return group1.getID() - group2.getID();
        }

    }
}
