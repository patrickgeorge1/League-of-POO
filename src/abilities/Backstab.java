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

public class Backstab implements Ability {
    public final float getLandMofifier(final Champion me, final Map map) {
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

    /**
     * Rogue vs Rogue.
     * @param me
     * @param rogue
     * @param map
     */
    @Override
    public final void between(final Champion me, final Rogue rogue, final Map map) {
        int baseDamage = NumberConstants.NR200 + NumberConstants.NR20 * me.getLevel();
        if (((Rogue) me).critNow(map)) {
            baseDamage = Math.round(NumberConstants.NR15 * baseDamage);
        }
        Damage damage = new Damage(baseDamage, getLandMofifier(me, map),
                RogueModifiers.backstab_rogue);
        cast(damage, rogue, me);
    }

    @Override
    public final void between(final Champion me, final Knight knight, final Map map) {
        int baseDamage = NumberConstants.NR200 + NumberConstants.NR20 * me.getLevel();
        if (((Rogue) me).critNow(map)) {
            baseDamage = Math.round(NumberConstants.NR15 * baseDamage);
        }
        Damage damage = new Damage(baseDamage, getLandMofifier(me, map),
                RogueModifiers.backstab_knight);
        cast(damage, knight, me);
    }

    @Override
    public final void between(final Champion me, final Wizard wizard, final Map map) {
        int baseDamage = NumberConstants.NR200 + NumberConstants.NR20 * me.getLevel();
        if (((Rogue) me).critNow(map)) {
            baseDamage = Math.round(NumberConstants.NR15 * baseDamage);
        }
        Damage damage = new Damage(baseDamage, getLandMofifier(me, map),
                RogueModifiers.backstab_wizard);
        cast(damage, wizard, me);
    }

    @Override
    public final void between(final Champion me, final Pyromancer pyromancer, final Map map) {
        int baseDamage = NumberConstants.NR200 + NumberConstants.NR20 * me.getLevel();
        if (((Rogue) me).critNow(map)) {
            baseDamage = Math.round(NumberConstants.NR15 * baseDamage);
        }
        Damage damage = new Damage(baseDamage, getLandMofifier(me, map),
                RogueModifiers.backstab_pyromancer);
        cast(damage, pyromancer, me);
    }

    @Override
    public final void cast(final Damage damage, final Champion enemy, final Champion me) {
        ((Rogue) me).increseCritCycle();
        damage.getDamageWithBothModifiers();
        me.addDamage(damage);
    }
}
