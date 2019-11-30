package abilities;

import champions.Rogue;
import champions.Champion;
import champions.Knight;
import champions.Wizard;
import champions.Pyromancer;
import constants.NumberConstants;
import constants.RogueModifiers;
import map.Map;
import utils.Damage;

import java.util.ArrayList;

public class Paralysis implements Ability {
     final float getLandMofifier(final Champion me, final Map map) {
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
    public final void between(final Champion me, final Pyromancer pyromancer, final Map map) {
        int baseDamage = NumberConstants.NR40 + NumberConstants.NR10 * me.getLevel();
        int numberOfincapacity = NumberConstants.NR3;
        if (me.getTerrain(map) == 'W') {
            numberOfincapacity = NumberConstants.NR6;
        }
        Damage damage = new Damage(baseDamage, getLandMofifier(me, map),
                RogueModifiers.paralysis_pyromancer);
        cast(damage, pyromancer, me);

        // RESETEZ buff negativ
        pyromancer.setNegativeBuff(new ArrayList<Integer>());
        ArrayList<Integer> incapacity = new ArrayList<>();
        incapacity.add(numberOfincapacity);
        pyromancer.setIncapacity(incapacity);

        int rotDamage = NumberConstants.NR40 + NumberConstants.NR10 * me.getLevel();
        Damage rot = new Damage(rotDamage, getLandMofifier(me, map),
                RogueModifiers.paralysis_pyromancer);
        int duration = 2;
        ArrayList<Integer> rotSpecs = new ArrayList<>();
        rotSpecs.add(numberOfincapacity);
        rotSpecs.add(rot.getDamageWithBothModifiers());
        pyromancer.setNegativeBuff(rotSpecs);
    }

    @Override
    public final void between(final Champion me, final Wizard wizard, final Map map) {
        int baseDamage = NumberConstants.NR40 + NumberConstants.NR10 * me.getLevel();
        int numberOfincapacity = NumberConstants.NR3;
        if (me.getTerrain(map) == 'W') {
            numberOfincapacity = NumberConstants.NR6;
        }
        Damage damage = new Damage(baseDamage, getLandMofifier(me, map),
                RogueModifiers.paralysis_wizard);
        cast(damage, wizard, me);

        // RESETEZ buff negativ
        wizard.setNegativeBuff(new ArrayList<Integer>());
        ArrayList<Integer> incapacity = new ArrayList<>();
        incapacity.add(numberOfincapacity);
        wizard.setIncapacity(incapacity);

        int rotDamage = NumberConstants.NR40 + NumberConstants.NR10 * me.getLevel();
        Damage rot = new Damage(rotDamage, getLandMofifier(me, map),
                RogueModifiers.paralysis_wizard);
        int duration = 2;
        ArrayList<Integer> rotSpecs = new ArrayList<>();
        rotSpecs.add(numberOfincapacity);
        rotSpecs.add(rot.getDamageWithBothModifiers());
        wizard.setNegativeBuff(rotSpecs);
    }

    @Override
    public final void between(final Champion me, final Knight knight, final Map map) {
        int baseDamage = NumberConstants.NR40 + NumberConstants.NR10 * me.getLevel();
        int numberOfincapacity = NumberConstants.NR3;
        if (me.getTerrain(map) == 'W') {
            numberOfincapacity = NumberConstants.NR6;
        }
        Damage damage = new Damage(baseDamage, getLandMofifier(me, map),
                RogueModifiers.paralysis_knight);
        cast(damage, knight, me);

        // RESETEZ buff negativ
        knight.setNegativeBuff(new ArrayList<Integer>());
        ArrayList<Integer> incapacity = new ArrayList<>();
        incapacity.add(numberOfincapacity);
        knight.setIncapacity(incapacity);

        int rotDamage = NumberConstants.NR40 + NumberConstants.NR10 * me.getLevel();
        Damage rot = new Damage(rotDamage, getLandMofifier(me, map),
                RogueModifiers.paralysis_knight);
        int duration = 2;
        ArrayList<Integer> rotSpecs = new ArrayList<>();
        rotSpecs.add(numberOfincapacity);
        rotSpecs.add(rot.getDamageWithBothModifiers());
        knight.setNegativeBuff(rotSpecs);
    }

    @Override
    public final void between(final Champion me, final Rogue rogue, final Map map) {
        int baseDamage = NumberConstants.NR40 + NumberConstants.NR10 * me.getLevel();
        int numberOfincapacity = NumberConstants.NR3;
        if (me.getTerrain(map) == 'W') {
            numberOfincapacity = NumberConstants.NR6;
        }
        Damage damage = new Damage(baseDamage, getLandMofifier(me, map),
                RogueModifiers.paralysis_rogue);
        cast(damage, rogue, me);

        // RESETEZ buff negativ
        rogue.setNegativeBuff(new ArrayList<Integer>());
        ArrayList<Integer> incapacity = new ArrayList<>();
        incapacity.add(numberOfincapacity);
        rogue.setIncapacity(incapacity);

        int rotDamage = NumberConstants.NR40 + NumberConstants.NR10 * me.getLevel();
        Damage rot = new Damage(rotDamage, getLandMofifier(me, map),
                RogueModifiers.paralysis_rogue);
        int duration = 2;
        ArrayList<Integer> rotSpecs = new ArrayList<>();
        rotSpecs.add(numberOfincapacity);
        rotSpecs.add(rot.getDamageWithBothModifiers());
        rogue.setNegativeBuff(rotSpecs);
    }

    @Override
    public final void cast(final Damage damage, final Champion enemy, final Champion me) {
        damage.getDamageWithBothModifiers();
        me.addDamage(damage);
    }
}
