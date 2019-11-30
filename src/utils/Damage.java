package utils;

import constants.NumberConstants;

import java.util.ArrayList;

public class Damage {
    private int baseDamage;
    private float landModifierr;
    private float raceModifierr;
    private int damageWithBonuses;
    private int damageJustWithLand;
    private ArrayList<Float> extraModifierr = new ArrayList<>();

    public Damage(final int baseDamage, final float landModifier, final float raceModifier) {
        this.baseDamage = baseDamage;
        this.landModifierr = landModifier;
        this.raceModifierr = raceModifier;
    }


    public final int getDamageWithLandModifier() {
        damageWithBonuses = Math.round(baseDamage * landModifierr);
        damageJustWithLand = damageWithBonuses;
        return damageWithBonuses;
    }

    public final int getDamageWithRaceModifier() {
        damageWithBonuses = Math.round(baseDamage * raceModifierr);
        return damageWithBonuses;
    }

    public final int getDamageWithBothModifiers() {
        damageWithBonuses = Math.round(getDamageWithLandModifier() * raceModifierr);
        return damageWithBonuses;
    }

    public final int getDamageWithBothModifiersUpgraded() {
        damageWithBonuses = Math.round(baseDamage * landModifierr * raceModifierr);
        return damageWithBonuses;
    }

    public final int getDamageWithBothModifiersAndCrit() {
        damageWithBonuses = Math.round(getDamageWithBothModifiers() * extraModifierr.get(0));
        return damageWithBonuses;
    }

    public final int getDamageForDrain(final float procent, final float raceModifier,
                                       final float landModifier,
                                       final int currentHp, final int maxHp) {
        float procentOfRaceAndLevel = procent * landModifier * raceModifier;
        float minimum = java.lang.Math.min(NumberConstants.NR3F * maxHp, currentHp);
        this.baseDamage = Math.round(procent  * minimum);
        this.landModifierr = landModifier;
        damageWithBonuses = Math.round(procentOfRaceAndLevel * minimum);
        return damageWithBonuses;
    }

    public final int getDamageJustWithLand() {
        return damageJustWithLand;
    }

    public final void setDamageJustWithLand(final int damageJustWithLand) {
        this.damageJustWithLand = damageJustWithLand;
    }
    public final int getBaseDamage() {
        return baseDamage;
    }

    public final void setBaseDamage(final int baseDamage) {
        this.baseDamage = baseDamage;
    }

    public final float getLandModifier() {
        return landModifierr;
    }

    public final void setLandMdifier(final float landModifier) {
        this.landModifierr = landModifier;
    }

    public final float getRaceModifier() {
        return raceModifierr;
    }

    public final void setRaceModifier(final float raceModifier) {
        this.raceModifierr = raceModifier;
    }

    public final ArrayList<Float> getExtraModifier() {
        return extraModifierr;
    }

    public final int getDamageWithBonuses() {
        return damageWithBonuses;
    }

    public final void setDamageWithBonuses(final int damageWithBonuses) {
        this.damageWithBonuses = damageWithBonuses;
    }

    public final void setExtraModifier(final ArrayList<Float> extraModifier) {
        this.extraModifierr = extraModifier;
    }
}
