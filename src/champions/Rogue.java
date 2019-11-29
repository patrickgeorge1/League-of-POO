package champions;

import abilities.Ability;
import abilities.Backstab;
import abilities.Paralysis;
import map.Map;

public class Rogue extends Champion {
    private int critCycle;
    public Rogue(int id) {
        super(id);
        setPriorityToAttck(1);
        resetHP();
        critCycle = 0;
    }

    public void increseCritCycle() {
        this.critCycle++;
    }

    public boolean critNow(Map map) {
        return (critCycle % 3 == 0 && this.getTerrain(map) == 'W');
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
        Backstab backstab = new Backstab();
        Paralysis paralysis = new Paralysis();
        enemy.accept(backstab, map);
        enemy.accept(paralysis, map);
    }

    public int getCritCycle() {
        return critCycle;
    }

    public void setCritCycle(int critCycle) {
        this.critCycle = critCycle;
    }

    public void accept(Ability ability, Map map) {
        ability.between(getEnemy(), this, map);
    }

    @Override
    public String toString() {
        return "R = " + "<ID:" + Integer.toString(this.getId()) + " HP:" + Integer.toString(this.getHp()) + " pos (" + this.getPosition().getX() + ", " + this.getPosition().getY() + ")>";
    }
}
