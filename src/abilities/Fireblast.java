package abilities;

import champions.*;
import constants.PyromancerModifiers;
import map.Map;
import utils.Damage;

import java.util.ArrayList;

public class Fireblast implements Ability {
    public float getLandMofifier (Champion me, Map map) {
        float modifier = 0;
        switch (me.getTerrain(map)) {
            case 'V':
                modifier = PyromancerModifiers.volcanic;
                break;
            default:
                modifier = 1.0f;
                break;
        }
        return modifier;
    }

    @Override
    public void between(Champion me, Rogue rogue, Map map) {
        int baseDamage = 350 + 50 * me.getLevel();
        Damage damage = new Damage(baseDamage, getLandMofifier(me, map), PyromancerModifiers.fireblast_rogue);
        cast(damage, rogue, me);
    }

    @Override
    public void between(Champion me, Knight knight, Map map) {
        int baseDamage = 350 + 50 * me.getLevel();
        Damage damage = new Damage(baseDamage, getLandMofifier(me, map), PyromancerModifiers.fireblast_knight);
        cast(damage, knight, me);
    }

    @Override
    public void between(Champion me, Wizard wizard, Map map) {
        int baseDamage = 350 + 50 * me.getLevel();
        Damage damage = new Damage(baseDamage, getLandMofifier(me, map), PyromancerModifiers.fireblast_wizard);
        cast(damage, wizard, me);
    }

    @Override
    public void between(Champion me, Pyromancer pyromancer, Map map) {
        int baseDamage = 350 + 50 * me.getLevel();
        Damage damage = new Damage(baseDamage, getLandMofifier(me, map), PyromancerModifiers.fireblast_pyromancer);
        cast(damage, pyromancer, me);
    }

    @Override
    public void cast(Damage damage, Champion enemy, Champion me) {
        damage.getDamageWithBothModifiers();
        System.out.println("Fireblast : " + damage.getDamageWithBonuses());
        me.addDamage(damage);
    }
}
