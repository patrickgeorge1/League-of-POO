package champions;

import abilities.Ability;
import abilities.Fireblast;
import abilities.Ignite;
import map.Map;

public class Pyromancer extends Champion {
    public Pyromancer(int id) {
        super(id);
        setPriorityToAttck(1);
        resetHP();
    }

    @Override
    public void resetHP() {
        this.setHp(500 + 50 * this.getLevel());
    }

    @Override
    public int maxHP() {
        return 500 + 50 * this.getLevel();
    }

    @Override
    public void fight(Champion enemy, Map map) {
        Fireblast fireblast = new Fireblast();
        Ignite ignite = new Ignite();
        enemy.accept(fireblast, map);
        enemy.accept(ignite, map);
    }

    public void accept(Ability ability, Map map) {
        ability.between(getEnemy(), this, map);
    }

    @Override
    public String toString() {
        return "P = " + "<ID:" + Integer.toString(this.getId()) + " HP:" + Integer.toString(this.getHp()) + " pos (" + this.getPosition().getX() + ", " + this.getPosition().getY() + ")>";
    }
}
