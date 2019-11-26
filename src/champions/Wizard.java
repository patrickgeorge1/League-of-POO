package champions;

import abilities.Ability;
import map.Map;

public class Wizard extends Champion {
    public Wizard(int id) {
        super(id);
        setPriorityToAttck(0);
        resetHP();
    }

    @Override
    public void resetHP() {
        this.setHp(400 + 30 * this.getLevel());
    }

    @Override
    public int maxHP() {
        return 400 + 30 * this.getLevel();
    }

    @Override
    public void fight(Champion enemy, Map map) {

    }

    public void accept(Ability ability, Map map) {
        ability.between(getEnemy(), this, map);
    }

    @Override
    public String toString() {
        return "W = " + "<ID:" + Integer.toString(this.getId()) + " HP:" + Integer.toString(this.getHp()) + " pos (" + this.getPosition().getX() + ", " + this.getPosition().getY() + ")>";
    }
}
