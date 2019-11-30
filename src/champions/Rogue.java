package champions;

import abilities.Ability;
import abilities.Backstab;
import abilities.Paralysis;
import constants.NumberConstants;
import map.Map;

public class Rogue extends Champion {
    private int critCycle;
    public Rogue(final int id) {
        super(id);
        setPriorityToAttck(1);
        resetHP();
        critCycle = 0;
    }

    public final void increseCritCycle() {
        this.critCycle++;
    }

    public final boolean critNow(final Map map) {
        return (critCycle % NumberConstants.NR3 == 0 && this.getTerrain(map) == 'W');
    }

    @Override
    public final void resetHP() {
        this.setHp(NumberConstants.NR600 + NumberConstants.NR40 * this.getLevel());
    }

    @Override
    public final int maxHP() {
        return NumberConstants.NR600 + NumberConstants.NR40 * this.getLevel();
    }

    @Override
    public final void fight(final Champion enemy, final Map map) {
        Backstab backstab = new Backstab();
        Paralysis paralysis = new Paralysis();
        enemy.accept(backstab, map);
        enemy.accept(paralysis, map);
    }

    public final int getCritCycle() {
        return critCycle;
    }

    public final void setCritCycle(final int critCycle) {
        this.critCycle = critCycle;
    }

    public final void accept(final Ability ability, final Map map) {
        ability.between(getEnemy(), this, map);
    }

    @Override
    public final String toString() {
        return "R = " + "<ID:" + Integer.toString(this.getId())
                + " HP:" + Integer.toString(this.getHp()) + " pos (" + this.getPosition().getX()
                + ", " + this.getPosition().getY() + ")>";
    }
}
