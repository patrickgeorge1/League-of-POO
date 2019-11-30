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

import java.util.ArrayList;

public class Ignite implements Ability {
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
        int baseDamage = NumberConstants.NR150 + NumberConstants.NR20 * me.getLevel();
        Damage damage = new Damage(baseDamage, getLandMofifier(me, map),
                PyromancerModifiers.IGNITEROG);
        cast(damage, rogue, me);

        int rotDamage = NumberConstants.NR50 + NumberConstants.NR30 * me.getLevel();
        Damage rot = new Damage(rotDamage, getLandMofifier(me, map),
                PyromancerModifiers.IGNITEROG);
        int duration = 2;
        rogue.setNegativeBuff(new ArrayList<Integer>() {{ add(duration);
        add(rot.getDamageWithBothModifiers()); }});
    }

    @Override
    public final void between(final Champion me, final Knight knight, final Map map) {
        int baseDamage = NumberConstants.NR150 + NumberConstants.NR20 * me.getLevel();
        Damage damage = new Damage(baseDamage, getLandMofifier(me, map),
                PyromancerModifiers.IGNITEKNI);
        cast(damage, knight, me);

        int rotDamage = NumberConstants.NR50 + NumberConstants.NR30 * me.getLevel();
        Damage rot = new Damage(rotDamage, getLandMofifier(me, map),
                PyromancerModifiers.IGNITEKNI);
        int duration = 2;
        knight.setNegativeBuff(new ArrayList<Integer>() {{ add(duration);
        add(rot.getDamageWithBothModifiers()); }});
    }

    @Override
    public final void between(final Champion me, final Wizard wizard, final Map map) {
        int baseDamage = NumberConstants.NR150 + NumberConstants.NR20 * me.getLevel();
        Damage damage = new Damage(baseDamage, getLandMofifier(me, map),
                PyromancerModifiers.IGNITEWIZ);
        cast(damage, wizard, me);

        int rotDamage = NumberConstants.NR50 + NumberConstants.NR30 * me.getLevel();
        Damage rot = new Damage(rotDamage, getLandMofifier(me, map),
                PyromancerModifiers.IGNITEWIZ);
        int duration = 2;
        wizard.setNegativeBuff(new ArrayList<Integer>() {{ add(duration);
        add(rot.getDamageWithBothModifiers()); }});
    }

    @Override
    public final void between(final Champion me, final Pyromancer pyromancer, final Map map) {
        int baseDamage = NumberConstants.NR150 + NumberConstants.NR20 * me.getLevel();
        Damage damage = new Damage(baseDamage, getLandMofifier(me, map),
                PyromancerModifiers.IGNITEPYRO);
        cast(damage, pyromancer, me);

        int rotDamage = NumberConstants.NR50 + NumberConstants.NR30 * me.getLevel();
        Damage rot = new Damage(rotDamage, getLandMofifier(me, map),
                PyromancerModifiers.IGNITEPYRO);
        int duration = 2;
        pyromancer.setNegativeBuff(new ArrayList<Integer>() {{ add(duration);
        add(rot.getDamageWithBothModifiers()); }});
    }

    @Override
    public final void cast(final Damage damage, final Champion enemy, final Champion me) {
        damage.getDamageWithBothModifiers();
        me.addDamage(damage);
    }
}
