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

import java.util.ArrayList;

public class Slam implements Ability {
    public final float getLandMofifier(final Champion me, final Map map) {
        float modifier = 0;
        switch (me.getTerrain(map)) {
            case 'L':
                modifier = KnightModifiers.land;
                break;
            default:
                modifier = 1.0f;
                break;
        }
        return modifier;
    }

    @Override
    public final void between(final Champion me, final Pyromancer pyromancer, final Map map) {
        int baseDamage = NumberConstants.NR100 + NumberConstants.NR40 * me.getLevel();
        Damage damage = new Damage(baseDamage, getLandMofifier(me, map),
                KnightModifiers.slam_pyromancer);
        cast(damage, pyromancer, me);

        pyromancer.setNegativeBuff(new ArrayList<Integer>());
        pyromancer.setIncapacity(new ArrayList<Integer>() {{ add(1); }});
    }

    @Override
    public final void between(final Champion me, final Wizard wizard, final Map map) {
        int baseDamage = NumberConstants.NR100 + NumberConstants.NR40 * me.getLevel();
        Damage damage = new Damage(baseDamage, getLandMofifier(me, map),
                KnightModifiers.slam_wizard);
        cast(damage, wizard, me);

        wizard.setNegativeBuff(new ArrayList<Integer>());
        wizard.setIncapacity(new ArrayList<Integer>() {{ add(1); }});
    }

    @Override
    public final void between(final Champion me, final Knight knight, final Map map) {
        int baseDamage = NumberConstants.NR100 + NumberConstants.NR40 * me.getLevel();
        Damage damage = new Damage(baseDamage, getLandMofifier(me, map),
                KnightModifiers.slam_knight);
        cast(damage, knight, me);

        knight.setNegativeBuff(new ArrayList<Integer>());
        knight.setIncapacity(new ArrayList<Integer>() {{ add(1); }});
    }

    @Override
    public final void between(final Champion me, final Rogue rogue, final Map map) {
        int baseDamage = NumberConstants.NR100 + NumberConstants.NR40 * me.getLevel();
        Damage damage = new Damage(baseDamage, getLandMofifier(me, map),
                KnightModifiers.slam_rogue);
        cast(damage, rogue, me);

        rogue.setNegativeBuff(new ArrayList<Integer>());
        rogue.setIncapacity(new ArrayList<Integer>() {{ add(1); }});
    }

    @Override
    public final void cast(final Damage damage, final Champion enemy, final Champion me) {
        damage.getDamageWithBothModifiers();
        me.addDamage(damage);
    }
}
