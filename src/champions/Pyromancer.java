package champions;

import abilities.Ability;
import abilities.Fireblast;
import abilities.Ignite;
import constants.NumberConstants;
import map.Map;

public class Pyromancer extends Champion {
    public Pyromancer(final int id) {
        super(id);
        setPriorityToAttck(1);
        resetHP();
    }

    @Override
    public final void resetHP() {
        this.setHp(NumberConstants.NR500 + NumberConstants.NR50 * this.getLevel());
    }

    @Override
    public final int maxHP() {
        return NumberConstants.NR500 + NumberConstants.NR50 * this.getLevel();
    }

    @Override
    public final void fight(final Champion enemy, final Map map) {
        Fireblast fireblast = new Fireblast();
        Ignite ignite = new Ignite();
        enemy.accept(fireblast, map);
        enemy.accept(ignite, map);
    }

    public final void accept(final Ability ability, final Map map) {
        ability.between(getEnemy(), this, map);
    }

    @Override
    public final String toString() {
        return "P = " + "<ID:" + Integer.toString(this.getId())
                + " HP:" + Integer.toString(this.getHp()) + " pos (" + this.getPosition().getX()
                + ", " + this.getPosition().getY() + ")>";
    }
}
