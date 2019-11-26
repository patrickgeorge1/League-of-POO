package champions;

import abilities.Ability;
import map.Map;

public class Rogue extends Champion {
    public Rogue(int id) {
        super(id);
        setPriorityToAttck(1);
        resetHP();
    }

    @Override
    public void resetHP() {
        this.setHp(600 + 40 * this.getLevel());
    }

    @Override
    public int maxHP() {
        return 600 + 40 * this.getLevel();
    }

    @Override
    public void fight(Champion enemy, Map map) {

    }

    public void accept(Ability ability, Map map) {
        ability.between(getEnemy(), this, map);
    }

    @Override
    public String toString() {
        return "R = " + "<ID:" + Integer.toString(this.getId()) + " HP:" + Integer.toString(this.getHp()) + " pos (" + this.getPosition().getX() + ", " + this.getPosition().getY() + ")>";
    }
}
