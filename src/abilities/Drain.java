package abilities;

import champions.Rogue;
import champions.Champion;
import champions.Knight;
import champions.Wizard;
import champions.Pyromancer;
import constants.NumberConstants;
import constants.WizardModifiers;
import map.Map;
import utils.Damage;

public final class Drain implements Ability {
    public float getLandMofifier(final Champion me, final Map map) {
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
    public void between(final Champion me, final Rogue rogue, final Map map) {
        float procent = NumberConstants.NR2F + NumberConstants.NR05F * me.getLevel();
        float landModifier = this.getLandMofifier(me, map);
        float raceMofifier = WizardModifiers.drain_rogue;
        int currentHp = rogue.getHp();
        int maxHp = rogue.maxHP();
        Damage damage = new Damage(1, 0, 0);
        damage.getDamageForDrain(procent, raceMofifier, landModifier, currentHp, maxHp);
        cast(damage, rogue, me);
    }

    @Override
    public void between(final Champion me, final Knight knight, final Map map) {
        float procent = NumberConstants.NR2F + NumberConstants.NR05F * me.getLevel();
        float landModifier = this.getLandMofifier(me, map);
        float raceMofifier = WizardModifiers.drain_knight;
        int currentHp = knight.getHp();
        int maxHp = knight.maxHP();
        Damage damage = new Damage(1, 0, 0);
        damage.getDamageForDrain(procent, raceMofifier, landModifier, currentHp, maxHp);
        cast(damage, knight, me);
    }

    @Override
    public void between(final Champion me, final Wizard wizard, final Map map) {
        float procent = NumberConstants.NR2F + NumberConstants.NR05F * me.getLevel();
        float landModifier = this.getLandMofifier(me, map);
        float raceMofifier = WizardModifiers.drain_wizard;
        int currentHp = wizard.getHp();
        int maxHp = wizard.maxHP();
        Damage damage = new Damage(1, 0, 0);
        damage.getDamageForDrain(procent, raceMofifier, landModifier, currentHp, maxHp);
        cast(damage, wizard, me);
    }

    @Override
    public void between(final Champion me, final Pyromancer pyromancer, final Map map) {
        float procent = NumberConstants.NR2F + NumberConstants.NR05F * me.getLevel();
        float landModifier = this.getLandMofifier(me, map);
        float raceMofifier = WizardModifiers.drain_pyromancer;
        int currentHp = pyromancer.getHp();
        int maxHp = pyromancer.maxHP();
        Damage damage = new Damage(1, 0, 0);
        damage.getDamageForDrain(procent, raceMofifier, landModifier, currentHp, maxHp);
        cast(damage, pyromancer, me);
    }

    @Override
    public void cast(final Damage damage, final Champion enemy, final Champion me) {
        damage.getDamageWithBonuses();
//        System.out.println("drain - " + damage.getDamageWithBothModifiers());
        me.addDamage(damage);
    }
}
