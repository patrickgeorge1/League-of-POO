package abilities;

import champions.*;
import constants.WizardModifiers;
import map.Map;
import utils.Damage;

import java.util.ArrayList;

public class Deflect implements Ability {
    public float getLandMofifier (Champion me, Map map) {
        float modifier = 0;
        switch (me.getTerrain(map)) {
            case 'D':
                modifier = WizardModifiers.desert;
                break;
            default:
                modifier = 1.0f;
                break;
        }
        return modifier;
    }

    @Override
    public void between(Champion me, Pyromancer pyromancer, Map map) {
        float baseProcent = 35 + 2 * me.getLevel();
        if (baseProcent > 70) baseProcent = 70;
        baseProcent = baseProcent / 100.0f;
        ArrayList<Damage> totalDamage = pyromancer.getDamage();
        int deflectDamage = 0;
        for (Damage damage:totalDamage) {
            deflectDamage += damage.getDamageJustWithLand();
        }
        int baseDamage = Math.round(deflectDamage * baseProcent);
        Damage damage = new Damage(baseDamage, getLandMofifier(me ,map), WizardModifiers.deflect_pyromancer);
        cast(damage, pyromancer, me);
    }

    @Override
    public void between(Champion me, Wizard wizard, Map map) {
        Damage damage = new Damage(0, 1.0f, 1.0f);
        cast(damage, wizard, me);
    }

    @Override
    public void between(Champion me, Knight knight, Map map) {
        float baseProcent = 35 + 2 * me.getLevel();
        if (baseProcent > 70) baseProcent = 70;
        baseProcent = baseProcent / 100.0f;
        ArrayList<Damage> totalDamage = knight.getDamage();
        int deflectDamage = 0;
        for (Damage damage:totalDamage) {
            deflectDamage += damage.getDamageJustWithLand();
        }
        int baseDamage = Math.round(deflectDamage * baseProcent);
        Damage damage = new Damage(baseDamage, getLandMofifier(me ,map), WizardModifiers.deflect_knight);
        cast(damage, knight, me);
    }

    @Override
    public void between(Champion me, Rogue rogue, Map map) {
        float baseProcent = 35 + 2 * me.getLevel();
        if (baseProcent > 70) baseProcent = 70;
        baseProcent = baseProcent / 100.0f;
        ArrayList<Damage> totalDamage = rogue.getDamage();
        int deflectDamage = 0;
        for (Damage damage:totalDamage) {
            deflectDamage += damage.getDamageJustWithLand();
        }
        int baseDamage = Math.round(deflectDamage * baseProcent);
        Damage damage = new Damage(baseDamage, getLandMofifier(me ,map), WizardModifiers.deflect_rogue);
        cast(damage, rogue, me);
    }

    @Override
    public void cast(Damage damage, Champion enemy, Champion me) {
        damage.getDamageWithBothModifiers();
        System.out.println("Deflect : " + damage.getDamageWithBothModifiers());
        me.addDamage(damage);
    }
}
