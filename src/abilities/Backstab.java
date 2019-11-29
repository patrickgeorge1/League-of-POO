package abilities;

import champions.*;
import constants.PyromancerModifiers;
import constants.RogueModifiers;
import map.Map;
import utils.Damage;

public class Backstab implements Ability {
    public float getLandMofifier (Champion me, Map map) {
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

    @Override
    public void between(Champion me, Rogue rogue, Map map) {
        int baseDamage = 200 + 20 * me.getLevel();
        if (((Rogue) me).critNow(map)) baseDamage = Math.round(1.5f * baseDamage);
        Damage damage = new Damage(baseDamage, getLandMofifier(me, map), RogueModifiers.backstab_rogue);
        cast(damage, rogue, me);
    }

    @Override
    public void between(Champion me, Knight knight, Map map) {
        int baseDamage = 200 + 20 * me.getLevel();
        if (((Rogue) me).critNow(map)) baseDamage = Math.round(1.5f * baseDamage);
        Damage damage = new Damage(baseDamage, getLandMofifier(me, map), RogueModifiers.backstab_knight);
        cast(damage, knight, me);
    }

    @Override
    public void between(Champion me, Wizard wizard, Map map) {
        int baseDamage = 200 + 20 * me.getLevel();
        if (((Rogue) me).critNow(map)) baseDamage = Math.round(1.5f * baseDamage);
        Damage damage = new Damage(baseDamage, getLandMofifier(me, map), RogueModifiers.backstab_wizard);
        cast(damage, wizard, me);
    }

    @Override
    public void between(Champion me, Pyromancer pyromancer, Map map) {
        int baseDamage = 200 + 20 * me.getLevel();
        if (((Rogue) me).critNow(map)) baseDamage = Math.round(1.5f * baseDamage);
        Damage damage = new Damage(baseDamage, getLandMofifier(me, map), RogueModifiers.backstab_pyromancer);
        cast(damage, pyromancer, me);
    }

    @Override
    public void cast(Damage damage, Champion enemy, Champion me) {
        ((Rogue) me).increseCritCycle();
        damage.getDamageWithBothModifiers();
        me.addDamage(damage);
    }
}
