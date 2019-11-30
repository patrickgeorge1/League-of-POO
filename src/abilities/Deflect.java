package abilities;

import champions.Rogue;
import champions.Champion;
import champions.Knight;
import champions.Wizard;
import champions.Pyromancer;
import constants.NumberConstants;
import constants.WizardModifiers;
import map.Map;
import utils.Damage;

import java.util.ArrayList;

public class Deflect implements Ability {
    public final float getLandMofifier(final Champion me, final Map map) {
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
    public final void between(final Champion me, final Pyromancer pyromancer, final Map map) {
        float baseProcent = NumberConstants.NR35 + 2 * me.getLevel();
        if (baseProcent > NumberConstants.NR70) {
            baseProcent = NumberConstants.NR70;
        }
        baseProcent = baseProcent / NumberConstants.NR100F;
        ArrayList<Damage> totalDamage = pyromancer.getDamage();
        int deflectDamage = 0;
        for (Damage damage:totalDamage) {
            deflectDamage += damage.getDamageJustWithLand();
        }
        int baseDamage = Math.round(deflectDamage * baseProcent);
        Damage damage = new Damage(baseDamage, getLandMofifier(me, map),
                WizardModifiers.deflect_pyromancer);
        cast(damage, pyromancer, me);
    }

    @Override
    public final void between(final Champion me, final Wizard wizard, final Map map) {
        Damage damage = new Damage(0, 1.0f, 1.0f);
        cast(damage, wizard, me);
    }

    @Override
    public final void between(final Champion me, final Knight knight, final Map map) {
        float baseProcent = NumberConstants.NR35 + 2 * me.getLevel();
        if (baseProcent > NumberConstants.NR70) {
            baseProcent = NumberConstants.NR70;
        }
        baseProcent = baseProcent / NumberConstants.NR100F;
        ArrayList<Damage> totalDamage = knight.getDamage();
        int deflectDamage = 0;
        for (Damage damage:totalDamage) {
            deflectDamage += damage.getDamageJustWithLand();
        }
        int baseDamage = Math.round(deflectDamage * baseProcent);
        Damage damage = new Damage(baseDamage, getLandMofifier(me, map),
                WizardModifiers.deflect_knight);
        cast(damage, knight, me);
    }

    @Override
    public final void between(final Champion me, final Rogue rogue, final Map map) {
        float baseProcent = NumberConstants.NR35 + 2 * me.getLevel();
        if (baseProcent > NumberConstants.NR70) {
            baseProcent = NumberConstants.NR70;
        }
        baseProcent = baseProcent / NumberConstants.NR100F;
        ArrayList<Damage> totalDamage = rogue.getDamage();
        int deflectDamage = 0;
        for (Damage damage:totalDamage) {
            deflectDamage += damage.getDamageJustWithLand();
        }
        int baseDamage = Math.round(deflectDamage * baseProcent);
        Damage damage = new Damage(baseDamage, getLandMofifier(me, map),
                WizardModifiers.deflect_rogue);
        cast(damage, rogue, me);
    }

    @Override
    public final void cast(final Damage damage, final Champion enemy, final Champion me) {
        damage.getDamageWithBothModifiersUpgraded();
//        System.out.println("Deflect - " + damage.getDamageWithBothModifiersUpgraded());
        me.addDamage(damage);
    }
}
