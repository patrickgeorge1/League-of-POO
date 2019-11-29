package abilities;

import champions.*;
import constants.PyromancerModifiers;
import constants.WizardModifiers;
import map.Map;
import utils.Damage;

public class Drain implements Ability {
    public float getLandMofifier (Champion me, Map map) {
        float modifier = 0;
        switch (me.getTerrain(map)) {
            case 'D':
                modifier = WizardModifiers.desert;
                break;
            default:
                modifier = 1.0f;
                break;
        }
        return modifier;
    }

    @Override
    public void between(Champion me, Rogue rogue, Map map) {
        float procent = 0.2f + 0.05f * me.getLevel();
        float landModifier = this.getLandMofifier(me, map);
        float raceMofifier = WizardModifiers.drain_rogue;
        int currentHp = rogue.getHp();
        int maxHp = rogue.maxHP();
        Damage damage = new Damage(1, 0,0);
        damage.getDamageForDrain(procent, raceMofifier, landModifier, currentHp, maxHp);
        cast(damage, rogue, me);
    }

    @Override
    public void between(Champion me, Knight knight, Map map) {
        float procent = 0.2f + 0.05f * me.getLevel();
        float landModifier = this.getLandMofifier(me, map);
        float raceMofifier = WizardModifiers.drain_knight;
        int currentHp = knight.getHp();
        int maxHp = knight.maxHP();
        Damage damage = new Damage(1, 0,0);
        damage.getDamageForDrain(procent, raceMofifier, landModifier, currentHp, maxHp);
        cast(damage, knight, me);
    }

    @Override
    public void between(Champion me, Wizard wizard, Map map) {
        float procent = 0.2f + 0.05f * me.getLevel();
        float landModifier = this.getLandMofifier(me, map);
        float raceMofifier = WizardModifiers.drain_wizard;
        int currentHp = wizard.getHp();
        int maxHp = wizard.maxHP();
        Damage damage = new Damage(1, 0,0);
        damage.getDamageForDrain(procent, raceMofifier, landModifier, currentHp, maxHp);
        cast(damage, wizard, me);
    }

    @Override
    public void between(Champion me, Pyromancer pyromancer, Map map) {
        float procent = 0.2f + 0.05f * me.getLevel();
        float landModifier = this.getLandMofifier(me, map);
        float raceMofifier = WizardModifiers.drain_pyromancer;
        int currentHp = pyromancer.getHp();
        int maxHp = pyromancer.maxHP();
        Damage damage = new Damage(1, 0,0);
        damage.getDamageForDrain(procent, raceMofifier, landModifier, currentHp, maxHp);
        cast(damage, pyromancer, me);
    }

    @Override
    public void cast(Damage damage, Champion enemy, Champion me) {
        damage.getDamageWithBonuses();
//        System.out.println("drain - " + damage.getDamageWithBothModifiers());
        me.addDamage(damage);
    }
}
