package champions;

import abilities.Ability;
import abilities.Execute;
import abilities.Slam;
import constants.NumberConstants;
import map.Map;

public class Knight extends Champion {
    public Knight(final int id) {
        super(id);
        setPriorityToAttck(1);
        resetHP();
    }

    @Override
    public final void resetHP() {
        this.setHp(NumberConstants.NR900 + NumberConstants.NR80 * this.getLevel());
    }

    @Override
    public final int maxHP() {
        return NumberConstants.NR900 + NumberConstants.NR80 * this.getLevel();
    }

    @Override
    public final void fight(final Champion enemy, final Map map) {
        Slam slam = new Slam();
        Execute execute = new Execute();
        enemy.accept(execute, map);
        enemy.accept(slam, map);
    }

    public final void accept(final Ability ability, final Map map) {
        ability.between(getEnemy(), this, map);
    }

    @Override
    public final String toString() {
        return "K = " + "<ID:" + Integer.toString(this.getId()) + " HP:"
                + Integer.toString(this.getHp()) + " pos (" + this.getPosition().getX() + ", "
                + this.getPosition().getY() + ")>";
    }
}
