package champions;

import abilities.Ability;
import factories.ChampFactory;
import map.Map;
import utils.Damage;
import utils.Position;
import utils.TupleInt;

import java.util.ArrayList;

public abstract class Champion {
    private int id;
    private int xp = 0;
    private int hp = 0;
    private Position position;
    private ArrayList<Integer> negativeBuff = new ArrayList<>();
    private ArrayList<Integer> incapacity = new ArrayList<>();
    private ArrayList<Damage> damage = new ArrayList<Damage>();
    private int priorityToAttck = 0;
    private Champion enemy = null;

    public Champion(int id) {
        this.setId(id);
    }

    // get level from XP
    public int getLevel() {
        int curr_xp = getXp();
        int currentLevel = 0;
        curr_xp -= 250;
        while (curr_xp - currentLevel * 50 >= 0) {
            currentLevel++;
        }
        return currentLevel;
    }

    // tell me what xp should I get if I kill the enemy
    public int getXpFrom (Champion enemy) {
        int level_winner = getLevel();
        int level_loser = enemy.getLevel();
        return java.lang.Math.max(0, 200 - (level_winner - level_loser) * 40);
    }

    // root the victim and tell us if it s dead after rooting's effect
    public boolean rot() {
        if(negativeBuff.isEmpty() || getHp() <= 0) return false;
        else {
            int nr_of_rounds = negativeBuff.get(0);
            int damage = negativeBuff.get(1);
            nr_of_rounds--;
            negativeBuff.set(0, nr_of_rounds);
            this.hp -= damage;
            // daca s-a terminat bufful negativ
            if (nr_of_rounds == 0) this.setNegativeBuff(new ArrayList<>());
        }
        if (this.hp <= 0) return true;
        return false;
    }

    // tell me where from where to where should a champ move
    public ArrayList<TupleInt> whereShouldHeMove(char direction) {
        ArrayList<TupleInt> result = new ArrayList<>();
        int x = position.getX();
        int x_old = x;
        int y = position.getY();
        int y_old = y;
        switch (direction) {
            case 'L':
                 y--;
                break;
            case 'R':
                y++;
                break;
            case 'U':
                x--;
                break;
            case 'D':
                x++;
                break;
            default:
                break;
        }
        String symbol = ChampFactory.getInstance().getChampionForOutput(getId());
        result.add(new TupleInt(x_old, y_old));
        result.add(new TupleInt(x, y));
        return result;
    }

    public char getTerrain(Map map) {
        return map.getTerrain(position.getX(), position.getY());
    }

    public void deleteMeFromMap(Map map) {
        int x = position.getX();
        int y = position.getY();
        map.getMap().get(x).get(y).getPlayers().remove(Integer.valueOf(getId()));
    }

    abstract public void resetHP();
    abstract public int maxHP();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public ArrayList<Integer> getNegativeNegativeBuff() {
        return negativeBuff;
    }

    public void setNegativeBuff(ArrayList<Integer> negativeBuff) {
        this.negativeBuff = negativeBuff;
    }

    public ArrayList<Damage> getDamage() {
        return damage;
    }

    public void setDamage(ArrayList<Damage> damage) {
        this.damage = damage;
    }

    public void addDamage(Damage damage) { this.damage.add(damage); }

    public void clearDamage() { this.setDamage(new ArrayList<Damage>()); }

    public int summAllTheDamage() {
        int res = 0;
        for (Damage damage:damage) {
            res += damage.getDamageWithBonuses();
        }
        return res;
    }

    public void decreaseIncapacity() {
        if (!incapacity.isEmpty()) {
            int number_of_incapacity = incapacity.get(0);
            incapacity.remove(0);
            number_of_incapacity--;
            if (number_of_incapacity > 0) incapacity.add(number_of_incapacity);
        }
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Champion getEnemy() {
        return enemy;
    }

    public void setEnemy(Champion enemy) {
        this.enemy = enemy;
    }

    public int getPriorityToAttck() {
        return priorityToAttck;
    }

    public ArrayList<Integer> getIncapacity() {
        return incapacity;
    }

    public void setIncapacity(ArrayList<Integer> incapacity) {
        this.incapacity = incapacity;
    }

    public void setPriorityToAttck(int priorityToAttck) {
        this.priorityToAttck = priorityToAttck;
    }

    abstract public String toString();
    abstract public void fight(Champion enemy, Map map);

    abstract public void accept(Ability ability, Map map);
}
