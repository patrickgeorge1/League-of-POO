package abilities;

import champions.*;
import constants.KnightModifiers;
import constants.PyromancerModifiers;
import map.Map;
import utils.Damage;

public class Execute implements Ability {
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
    public void between(Champion me, Rogue rogue, Map map) {
        int baseDamage = 200 + 30 * me.getLevel();
        int hpLimit = 20 + me.getLevel();
        if (hpLimit > 40) hpLimit = 40;
        int victimMaxHp = rogue.maxHP();
        int victimCurrentHp = rogue.getHp();
        int victimTreshHoldHp = Math.round(hpLimit * victimMaxHp / 100.0f);
        if (victimCurrentHp <= victimTreshHoldHp) {
            baseDamage = victimCurrentHp;
        }
        Damage damage = new Damage(baseDamage, getLandMofifier(me, map), KnightModifiers.execute_rogue);
        cast(damage, rogue, me);
    }

    @Override
    public void between(Champion me, Knight knight, Map map) {
        int baseDamage = 200 + 30 * me.getLevel();
        int hpLimit = 20 + me.getLevel();
        if (hpLimit > 40) hpLimit = 40;
        int victimMaxHp = knight.maxHP();
        int victimCurrentHp = knight.getHp();
        int victimTreshHoldHp = Math.round(hpLimit * victimMaxHp / 100.0f);
        if (victimCurrentHp <= victimTreshHoldHp) {
            System.out.println("EXXXECCECUUTTEE !!! victimCurrent: " + victimCurrentHp + " victim treshHold: " + victimTreshHoldHp);
            baseDamage = victimCurrentHp;
        }
        Damage damage = new Damage(baseDamage, getLandMofifier(me, map), KnightModifiers.execute_knight);
        cast(damage, knight, me);
    }

    @Override
    public void between(Champion me, Wizard wizard, Map map) {
        int baseDamage = 200 + 30 * me.getLevel();
        int hpLimit = 20 + me.getLevel();
        if (hpLimit > 40) hpLimit = 40;
        int victimMaxHp = wizard.maxHP();
        int victimCurrentHp = wizard.getHp();
        int victimTreshHoldHp = Math.round(hpLimit * victimMaxHp / 100.0f);
        if (victimCurrentHp <= victimTreshHoldHp) {
            baseDamage = victimCurrentHp;
        }
        Damage damage = new Damage(baseDamage, getLandMofifier(me, map), KnightModifiers.execute_wizard);
        cast(damage, wizard, me);
    }

    @Override
    public void between(Champion me, Pyromancer pyromancer, Map map) {
        int baseDamage = 200 + 30 * me.getLevel();
        int hpLimit = 20 + me.getLevel();
        if (hpLimit > 40) hpLimit = 40;
        int victimMaxHp = pyromancer.maxHP();
        int victimCurrentHp = pyromancer.getHp();
        int victimTreshHoldHp = Math.round(hpLimit * victimMaxHp / 100.0f);
        if (victimCurrentHp <= victimTreshHoldHp) {
            baseDamage = victimCurrentHp;
        }
        System.out.println("                        vs pyro " + baseDamage + " " + getLandMofifier(me, map) + " " + KnightModifiers.execute_pyromancer);
        Damage damage = new Damage(baseDamage, getLandMofifier(me, map), KnightModifiers.execute_pyromancer);
        cast(damage, pyromancer, me);
    }

    @Override
    public void cast(Damage damage, Champion enemy, Champion me) {
        damage.getDamageWithBothModifiers();
        System.out.println("Execute : " + damage.getDamageWithBothModifiers());
        me.addDamage(damage);
    }
}
