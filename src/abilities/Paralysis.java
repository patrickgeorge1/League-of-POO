package abilities;

import champions.*;
import constants.PyromancerModifiers;
import constants.RogueModifiers;
import map.Map;
import utils.Damage;

import java.util.ArrayList;

public class Paralysis implements Ability {
    public float getLandMofifier (Champion me, Map map) {
        float modifier = 0;
        switch (me.getTerrain(map)) {
            case 'W':
                modifier = RogueModifiers.woods;
                break;
            default:
                modifier = 1.0f;
                break;
        }
        return modifier;
    }
    // TODO incapacitatea scade si daca  nu se lupta

    @Override
    public void between(Champion me, Pyromancer pyromancer, Map map) {
        int baseDamage = 40 + 10 * me.getLevel();
        int numberOfincapacity = 3;
        if (me.getTerrain(map) == 'W') numberOfincapacity = 6;
        Damage damage = new Damage(baseDamage, getLandMofifier(me, map), RogueModifiers.paralysis_pyromancer);
        cast(damage, pyromancer, me);

        // RESETEZ buff negativ
        pyromancer.setNegativeBuff(new ArrayList<Integer>());
        ArrayList<Integer> incapacity = new ArrayList<>();
        incapacity.add(numberOfincapacity);
        pyromancer.setIncapacity(incapacity);

        int rotDamage = 40 + 10 * me.getLevel();
        Damage rot = new Damage(rotDamage, getLandMofifier(me, map), RogueModifiers.paralysis_pyromancer);
        int duration = 2;
        ArrayList<Integer> rotSpecs = new ArrayList<>();
        rotSpecs.add(numberOfincapacity);
        rotSpecs.add(rot.getDamageWithBothModifiers());
        pyromancer.setNegativeBuff(rotSpecs);
    }

    @Override
    public void between(Champion me, Wizard wizard, Map map) {
        int baseDamage = 40 + 10 * me.getLevel();
        int numberOfincapacity = 3;
        if (me.getTerrain(map) == 'W') numberOfincapacity = 6;
        Damage damage = new Damage(baseDamage, getLandMofifier(me, map), RogueModifiers.paralysis_wizard);
        cast(damage, wizard, me);

        // RESETEZ buff negativ
        wizard.setNegativeBuff(new ArrayList<Integer>());
        ArrayList<Integer> incapacity = new ArrayList<>();
        incapacity.add(numberOfincapacity);
        wizard.setIncapacity(incapacity);

        int rotDamage = 40 + 10 * me.getLevel();
        Damage rot = new Damage(rotDamage, getLandMofifier(me, map), RogueModifiers.paralysis_wizard);
        int duration = 2;
        ArrayList<Integer> rotSpecs = new ArrayList<>();
        rotSpecs.add(numberOfincapacity);
        rotSpecs.add(rot.getDamageWithBothModifiers());
        wizard.setNegativeBuff(rotSpecs);
    }

    @Override
    public void between(Champion me, Knight knight, Map map) {
        int baseDamage = 40 + 10 * me.getLevel();
        int numberOfincapacity = 3;
        if (me.getTerrain(map) == 'W') numberOfincapacity = 6;
        Damage damage = new Damage(baseDamage, getLandMofifier(me, map), RogueModifiers.paralysis_knight);
        cast(damage, knight, me);

        // RESETEZ buff negativ
        knight.setNegativeBuff(new ArrayList<Integer>());
        ArrayList<Integer> incapacity = new ArrayList<>();
        incapacity.add(numberOfincapacity);
        knight.setIncapacity(incapacity);

        int rotDamage = 40 + 10 * me.getLevel();
        Damage rot = new Damage(rotDamage, getLandMofifier(me, map), RogueModifiers.paralysis_knight);
        int duration = 2;
        ArrayList<Integer> rotSpecs = new ArrayList<>();
        rotSpecs.add(numberOfincapacity);
        rotSpecs.add(rot.getDamageWithBothModifiers());
        knight.setNegativeBuff(rotSpecs);
    }

    @Override
    public void between(Champion me, Rogue rogue, Map map) {
        int baseDamage = 40 + 10 * me.getLevel();
        int numberOfincapacity = 3;
        if (me.getTerrain(map) == 'W') numberOfincapacity = 6;
        Damage damage = new Damage(baseDamage, getLandMofifier(me, map), RogueModifiers.paralysis_rogue);
        cast(damage, rogue, me);

        // RESETEZ buff negativ
        rogue.setNegativeBuff(new ArrayList<Integer>());
        ArrayList<Integer> incapacity = new ArrayList<>();
        incapacity.add(numberOfincapacity);
        rogue.setIncapacity(incapacity);

        int rotDamage = 40 + 10 * me.getLevel();
        Damage rot = new Damage(rotDamage, getLandMofifier(me, map), RogueModifiers.paralysis_rogue);
        int duration = 2;
        ArrayList<Integer> rotSpecs = new ArrayList<>();
        rotSpecs.add(numberOfincapacity);
        rotSpecs.add(rot.getDamageWithBothModifiers());
        rogue.setNegativeBuff(rotSpecs);
    }

    @Override
    public void cast(Damage damage, Champion enemy, Champion me) {
        damage.getDamageWithBothModifiers();
        System.out.println("Paralysis : " + damage.getDamageWithBothModifiers());
        me.addDamage(damage);
    }
}
