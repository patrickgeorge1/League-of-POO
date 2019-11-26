package champions;

import abilities.Ability;
import abilities.Execute;
import abilities.Slam;
import map.Map;

public class Knight extends Champion {
    public Knight(int id) {
        super(id);
        setPriorityToAttck(1);
        resetHP();
    }

    @Override
    public void resetHP() {
        this.setHp(900 + 80 * this.getLevel());
    }

    @Override
    public int maxHP() {
        return 900 + 80 * this.getLevel();
    }

    @Override
    public void fight(Champion enemy, Map map) {
        Slam slam = new Slam();
        Execute execute = new Execute();
        enemy.accept(execute, map);
        enemy.accept(slam, map);
    }

    public void accept(Ability ability, Map map) {
        ability.between(getEnemy(), this, map);
    }

    @Override
    public String toString() {
        return "K = " + "<ID:" + Integer.toString(this.getId()) + " HP:" + Integer.toString(this.getHp()) + " pos (" + this.getPosition().getX() + ", " + this.getPosition().getY() + ")>";
    }
}
