package champions;

import abilities.Ability;
import abilities.Deflect;
import abilities.Drain;
import constants.NumberConstants;
import map.Map;

public class Wizard extends Champion {
    public Wizard(final int id) {
        super(id);
        setPriorityToAttck(0);
        resetHP();
    }

    @Override
    public final void resetHP() {
        this.setHp(NumberConstants.NR400 + NumberConstants.NR30 * this.getLevel());
    }

    @Override
    public final int maxHP() {
        return NumberConstants.NR400 + NumberConstants.NR30 * this.getLevel();
    }

    @Override
    public final void fight(final Champion enemy, final Map map) {
        Drain drain = new Drain();
        Deflect deflect = new Deflect();
        enemy.accept(drain, map);
        enemy.accept(deflect, map);
    }

    public final void accept(final Ability ability, final Map map) {
        ability.between(getEnemy(), this, map);
    }

    @Override
    public final String toString() {
        return "W = " + "<ID:" + Integer.toString(this.getId())
                + " HP:" + Integer.toString(this.getHp()) + " pos (" + this.getPosition().getX()
                + ", " + this.getPosition().getY() + ")>";
    }
}
