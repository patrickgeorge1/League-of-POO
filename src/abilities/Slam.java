package abilities;

import champions.*;
import constants.KnightModifiers;
import constants.PyromancerModifiers;
import map.Map;
import utils.Damage;

import java.util.ArrayList;

public class Slam implements Ability {
    public float getLandMofifier (Champion me, Map map) {
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
    public void between(Champion me, Pyromancer pyromancer, Map map) {
        int baseDamage = 100 + 40 * me.getLevel();
        Damage damage = new Damage(baseDamage, getLandMofifier(me, map), KnightModifiers.slam_pyromancer);
        cast(damage, pyromancer, me);

        pyromancer.setNegativeBuff(new ArrayList<Integer>());
        pyromancer.setIncapacity(new ArrayList<Integer>() {{ add(1); }});
    }

    @Override
    public void between(Champion me, Wizard wizard, Map map) {
        int baseDamage = 100 + 40 * me.getLevel();
        Damage damage = new Damage(baseDamage, getLandMofifier(me, map), KnightModifiers.slam_wizard);
        cast(damage, wizard, me);

        wizard.setNegativeBuff(new ArrayList<Integer>());
        wizard.setIncapacity(new ArrayList<Integer>() {{ add(1); }});
    }

    @Override
    public void between(Champion me, Knight knight, Map map) {
        int baseDamage = 100 + 40 * me.getLevel();
        Damage damage = new Damage(baseDamage, getLandMofifier(me, map), KnightModifiers.slam_knight);
        cast(damage, knight, me);

        knight.setNegativeBuff(new ArrayList<Integer>());
        knight.setIncapacity(new ArrayList<Integer>() {{ add(1); }});
    }

    @Override
    public void between(Champion me, Rogue rogue, Map map) {
        int baseDamage = 100 + 40 * me.getLevel();
        Damage damage = new Damage(baseDamage, getLandMofifier(me, map), KnightModifiers.slam_rogue);
        cast(damage, rogue, me);

        rogue.setNegativeBuff(new ArrayList<Integer>());
        rogue.setIncapacity(new ArrayList<Integer>() {{ add(1); }});
    }

    @Override
    public void cast(Damage damage, Champion enemy, Champion me) {
        damage.getDamageWithBothModifiers();
        me.addDamage(damage);
    }
}
