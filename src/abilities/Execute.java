package abilities;

import champions.Rogue;
import champions.Champion;
import champions.Knight;
import champions.Wizard;
import champions.Pyromancer;
import constants.KnightModifiers;
import constants.NumberConstants;
import map.Map;
import utils.Damage;

public class Execute implements Ability {
    public final float getLandMofifier(final Champion me, final Map map) {
        float modifier = 0;
        switch (me.getTerrain(map)) {
            case 'L':
                modifier = KnightModifiers.LAND;
                break;
            default:
                modifier = 1.0f;
                break;
        }
        return modifier;
    }

    @Override
    public final void between(final Champion me, final Rogue rogue, final Map map) {
        int baseDamage = NumberConstants.NR200 + NumberConstants.NR30 * me.getLevel();
        int hpLimit = NumberConstants.NR20 + me.getLevel();
        if (hpLimit > NumberConstants.NR40) {
            hpLimit = NumberConstants.NR40;
        }
        int victimMaxHp = rogue.maxHP();
        int victimCurrentHp = rogue.getHp();
        int victimTreshHoldHp = Math.round(hpLimit * victimMaxHp / NumberConstants.NR100F);
        if (victimCurrentHp <= victimTreshHoldHp) {
            baseDamage = victimCurrentHp;
        }
        Damage damage = new Damage(baseDamage, getLandMofifier(me, map),
                KnightModifiers.EXECUTEROGUE);
        cast(damage, rogue, me);
    }

    @Override
    public final void between(final Champion me, final Knight knight, final Map map) {
        int baseDamage = NumberConstants.NR200 + NumberConstants.NR30 * me.getLevel();
        int hpLimit = NumberConstants.NR20 + me.getLevel();
        if (hpLimit > NumberConstants.NR40) {
            hpLimit = NumberConstants.NR40;
        }
        int victimMaxHp = knight.maxHP();
        int victimCurrentHp = knight.getHp();
        int victimTreshHoldHp = Math.round(hpLimit * victimMaxHp / NumberConstants.NR100F);
        if (victimCurrentHp <= victimTreshHoldHp) {
            baseDamage = victimCurrentHp;
        }
        Damage damage = new Damage(baseDamage, getLandMofifier(me, map),
                KnightModifiers.EXECUTEKNIGHT);
        cast(damage, knight, me);
    }

    @Override
    public final void between(final Champion me, final Wizard wizard, final Map map) {
        int baseDamage = NumberConstants.NR200 + NumberConstants.NR30 * me.getLevel();
        int hpLimit = NumberConstants.NR20 + me.getLevel();
        if (hpLimit > NumberConstants.NR40) {
            hpLimit = NumberConstants.NR40;
        }
        int victimMaxHp = wizard.maxHP();
        int victimCurrentHp = wizard.getHp();
        int victimTreshHoldHp = Math.round(hpLimit * victimMaxHp / NumberConstants.NR100F);
        if (victimCurrentHp <= victimTreshHoldHp) {
            baseDamage = victimCurrentHp;
        }
        Damage damage = new Damage(baseDamage, getLandMofifier(me, map),
                KnightModifiers.EXECUTEWIZ);
        cast(damage, wizard, me);
    }

    @Override
    public final void between(final Champion me, final Pyromancer pyromancer, final Map map) {
        int baseDamage = NumberConstants.NR200 + NumberConstants.NR30 * me.getLevel();
        int hpLimit = NumberConstants.NR20 + me.getLevel();
        if (hpLimit > NumberConstants.NR40) {
            hpLimit = NumberConstants.NR40;
        }
        int victimMaxHp = pyromancer.maxHP();
        int victimCurrentHp = pyromancer.getHp();
        int victimTreshHoldHp = Math.round(hpLimit * victimMaxHp / NumberConstants.NR100F);
        if (victimCurrentHp <= victimTreshHoldHp) {
            baseDamage = victimCurrentHp;
        }
        Damage damage = new Damage(baseDamage, getLandMofifier(me, map),
                KnightModifiers.EXECUTEPYRO);
        cast(damage, pyromancer, me);
    }

    @Override
    public final void cast(final Damage damage, final Champion enemy, final Champion me) {
        damage.getDamageWithBothModifiers();
        me.addDamage(damage);
    }
}
