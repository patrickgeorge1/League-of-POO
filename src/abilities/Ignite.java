package abilities;

import champions.*;
import constants.PyromancerModifiers;
import map.Map;
import utils.Damage;

import java.util.ArrayList;

public class Ignite implements Ability {
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
        int baseDamage = 150 + 20 * me.getLevel();
        Damage damage = new Damage(baseDamage, getLandMofifier(me, map), PyromancerModifiers.ignite_rogue);
        cast(damage, rogue, me);

        int rotDamage = 50 + 30 * me.getLevel();
        Damage rot = new Damage(rotDamage, getLandMofifier(me, map), PyromancerModifiers.ignite_rogue);
        int duration = 2;
        rogue.setNegativeBuff(new ArrayList<Integer>() {{ add(duration); add(rot.getDamageWithBothModifiers()); }});
    }

    @Override
    public void between(Champion me, Knight knight, Map map) {
        int baseDamage = 150 + 20 * me.getLevel();
        Damage damage = new Damage(baseDamage, getLandMofifier(me, map), PyromancerModifiers.ignite_knight);
        cast(damage, knight, me);

        int rotDamage = 50 + 30 * me.getLevel();
        Damage rot = new Damage(rotDamage, getLandMofifier(me, map), PyromancerModifiers.ignite_knight);
        int duration = 2;
        knight.setNegativeBuff(new ArrayList<Integer>() {{ add(duration); add(rot.getDamageWithBothModifiers()); }});
    }

    @Override
    public void between(Champion me, Wizard wizard, Map map) {
        int baseDamage = 150 + 20 * me.getLevel();
        Damage damage = new Damage(baseDamage, getLandMofifier(me, map), PyromancerModifiers.ignite_wizard);
        cast(damage, wizard, me);

        int rotDamage = 50 + 30 * me.getLevel();
        Damage rot = new Damage(rotDamage, getLandMofifier(me, map), PyromancerModifiers.ignite_wizard);
        int duration = 2;
        wizard.setNegativeBuff(new ArrayList<Integer>() {{ add(duration); add(rot.getDamageWithBothModifiers()); }});
    }

    @Override
    public void between(Champion me, Pyromancer pyromancer, Map map) {
        int baseDamage = 150 + 20 * me.getLevel();
        Damage damage = new Damage(baseDamage, getLandMofifier(me, map), PyromancerModifiers.ignite_pyromancer);
        cast(damage, pyromancer, me);

        int rotDamage = 50 + 30 * me.getLevel();
        Damage rot = new Damage(rotDamage, getLandMofifier(me, map), PyromancerModifiers.ignite_pyromancer);
        int duration = 2;
        pyromancer.setNegativeBuff(new ArrayList<Integer>() {{ add(duration); add(rot.getDamageWithBothModifiers()); }});
    }

    @Override
    public void cast(Damage damage, Champion enemy, Champion me) {
        damage.getDamageWithBothModifiers();
        System.out.println("Ignite : " + damage.getDamageWithBothModifiers());
        me.addDamage(damage);
    }
}
