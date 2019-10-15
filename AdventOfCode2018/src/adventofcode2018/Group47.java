package adventofcode2018;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author Lukas Thöni
 */
public class Group47 {

    protected static final String IMMUNE = "immune", INFECTION = "infection",
            BLUDGEONING = "bludgeoning", SLASHING = "slashing", FIRE = "fire",
            COLD = "cold", RADIATION = "radiation";
    private static final List<String> damageTypes = new ArrayList<>(
            Stream.of(BLUDGEONING, SLASHING, FIRE, COLD, RADIATION).collect(Collectors.toList()));

    private final int unitHitPoints, attackDamage, initiative, ID;
    private int numberOfUnits, effectivePower;
    private final String attackType, faction;
    private final Set<String> weaknesses, immunities;
    private Group47 target;

    public Group47(int unitHitPoints, int attackDamage,
            int initiative, int ID, int numberOfUnits, String attackType,
            String faction, Set<String> weaknesses, Set<String> immunities) {
        this.unitHitPoints = unitHitPoints;
        this.attackDamage = attackDamage;
        this.initiative = initiative;
        this.ID = ID;
        this.numberOfUnits = numberOfUnits;
        this.attackType = attackType;
        this.faction = faction;
        this.weaknesses = weaknesses;
        this.immunities = immunities;
        updateEffectivePower();
    }

    public boolean isWeakTo(String type) {
        return weaknesses.contains(type);
    }

    public boolean isImmuneTo(String type) {
        return immunities.contains(type);
    }

    public static boolean isDamageType(String type) {
        return damageTypes.contains(type);
    }

    /**
     * Attack this unit's stored target and, if it dies, return it.
     *
     * @return If the target is killed by the attack, returns it; Otherwise
     * null;
     */
    public Group47 attackTarget() {
        if (null != target) {
            return target.takeDamage(getDamagePossibleTo(target)) ? target : null;
        } else {
            return null;
        }
    }

    /**
     * Cause an appropriate number of units in this group to die, based on the
     * nearest whole multiple of unit health.
     *
     * @param damage The damage dealt to this group.
     * @return True iff the group becomes empty as a result, false otherwise.
     */
    public boolean takeDamage(int damage) {
        numberOfUnits -= damage / unitHitPoints;
        updateEffectivePower();
        return !containsLiveUnits();
    }

    public boolean containsLiveUnits() {
        return numberOfUnits > 0;
    }

    public int getDamagePossibleTo(Group47 target) {
        int damage;
        if (target.isImmuneTo(attackType)) {
            damage = 0;
        } else {
            damage = effectivePower;
            damage = target.isWeakTo(attackType) ? damage * 2 : damage;
        }
        return damage;
    }

    public int getInitiative() {
        return initiative;
    }

    public int getNumberOfUnits() {
        return numberOfUnits;
    }

    public int getEffectivePower() {
        return effectivePower;
    }

    public String getFaction() {
        return faction;
    }

    public int getID() {
        return ID;
    }

    public Group47 getTarget() {
        return target;
    }

    public void setTarget(Group47 target) {
        this.target = target;
    }

    private void updateEffectivePower() {
        effectivePower = numberOfUnits * attackDamage;
    }

}
