package abilities;

import champions.Rogue;
import champions.Champion;
import champions.Knight;
import champions.Wizard;
import champions.Pyromancer;
import constants.NumberConstants;
import constants.PyromancerModifiers;
import map.Map;
import utils.Damage;


public class Fireblast implements Ability {
    public final float getLandMofifier(final Champion me, final Map map) {
        float modifier = 0;
        switch (me.getTerrain(map)) {
            case 'V':
                modifier = PyromancerModifiers.VOLCANIC;
                break;
            default:
                modifier = 1.0f;
                break;
        }
        return modifier;
    }

    @Override
    public final void between(final Champion me, final Rogue rogue, final Map map) {
        int baseDamage = NumberConstants.NR350 + NumberConstants.NR50 * me.getLevel();
        Damage damage = new Damage(baseDamage, getLandMofifier(me, map),
                PyromancerModifiers.FIREROGUE);
        cast(damage, rogue, me);
    }

    @Override
    public final void between(final Champion me, final Knight knight, final Map map) {
        int baseDamage = NumberConstants.NR350 + NumberConstants.NR50 * me.getLevel();
        Damage damage = new Damage(baseDamage, getLandMofifier(me, map),
                PyromancerModifiers.FIREKNIG);
        cast(damage, knight, me);
    }

    @Override
    public final void between(final Champion me, final Wizard wizard, final Map map) {
        int baseDamage = NumberConstants.NR350 + NumberConstants.NR50 * me.getLevel();
        Damage damage = new Damage(baseDamage, getLandMofifier(me, map),
                PyromancerModifiers.FIREWIZ);
        cast(damage, wizard, me);
    }

    @Override
    public final void between(final Champion me, final Pyromancer pyromancer, final Map map) {
        int baseDamage = NumberConstants.NR350 + NumberConstants.NR50 * me.getLevel();
        Damage damage = new Damage(baseDamage, getLandMofifier(me, map),
                PyromancerModifiers.FIREPYRO);
        cast(damage, pyromancer, me);
    }

    @Override
    public final void cast(final Damage damage, final Champion enemy, final Champion me) {
        damage.getDamageWithBothModifiers();
        me.addDamage(damage);
    }
}
