package utils;

import champions.Champion;

import java.util.ArrayList;

public class Damage {
    private int baseDamage;
    private float land_modifier;
    private float race_modifier;
    private int damageWithBonuses;
    private int damageJustWithLand;
    private ArrayList<Float> extra_modifier = new ArrayList<>();

    public Damage(int baseDamage, float land_modifier, float race_modifier) {
        this.baseDamage = baseDamage;
        this.land_modifier = land_modifier;
        this.race_modifier = race_modifier;
    }


    public int getDamageWithLandModifier() {
        damageWithBonuses = Math.round(baseDamage * land_modifier);
        damageJustWithLand = damageWithBonuses;
        return damageWithBonuses;
    }

    public int getDamageWithRaceModifier() {
        damageWithBonuses = Math.round(baseDamage * race_modifier);
        return damageWithBonuses;
    }

    public int getDamageWithBothModifiers() {
        damageWithBonuses = Math.round(getDamageWithLandModifier() * race_modifier);
        return damageWithBonuses;
    }

    public int getDamageWithBothModifiersAndCrit() {
        damageWithBonuses = Math.round(getDamageWithBothModifiers() * extra_modifier.get(0));
        return damageWithBonuses;
    }

    public int getDamageForDrain(float procent, float race_modifier, float land_modifier, int currentHp, int maxHp) {
        float procentOfRaceAndLevel = procent * land_modifier * race_modifier;
        float minimum = java.lang.Math.min(0.3f * maxHp, currentHp);
        this.baseDamage = Math.round(procent  * minimum);
        this.land_modifier = land_modifier;
        damageWithBonuses = Math.round(procentOfRaceAndLevel * minimum);
        return damageWithBonuses;
    }

    public int getDamageJustWithLand() {
        return damageJustWithLand;
    }

    public void setDamageJustWithLand(int damageJustWithLand) {
        this.damageJustWithLand = damageJustWithLand;
    }
    public int getBaseDamage() {
        return baseDamage;
    }

    public void setBaseDamage(int baseDamage) {
        this.baseDamage = baseDamage;
    }

    public float getLand_modifier() {
        return land_modifier;
    }

    public void setLand_modifier(float land_modifier) {
        this.land_modifier = land_modifier;
    }

    public float getRace_modifier() {
        return race_modifier;
    }

    public void setRace_modifier(float race_modifier) {
        this.race_modifier = race_modifier;
    }

    public ArrayList<Float> getExtra_modifier() {
        return extra_modifier;
    }

    public int getDamageWithBonuses() {
        return damageWithBonuses;
    }

    public void setDamageWithBonuses(int damageWithBonuses) {
        this.damageWithBonuses = damageWithBonuses;
    }

    public void setExtra_modifier(ArrayList<Float> extra_modifier) {
        this.extra_modifier = extra_modifier;
    }
}
