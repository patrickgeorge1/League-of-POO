package champions;

import abilities.Ability;
import constants.NumberConstants;
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

    public Champion(final int id) {
        this.setId(id);
    }

    // get level from XP
    public final int getLevel() {
        int currXp = getXp();
        int currentLevel = 0;
        currXp -= NumberConstants.NR250;
        while (currXp - currentLevel * NumberConstants.NR50 >= 0) {
            currentLevel++;
        }
        return currentLevel;
    }

    // tell me what xp should I get if I kill the enemy
    public final int getXpFrom(final Champion enemyChamp) {
        int levelWinner = getLevel();
        int levelLoser = enemyChamp.getLevel();
        return java.lang.Math.max(0, NumberConstants.NR200 - (levelWinner - levelLoser)
                * NumberConstants.NR40);
    }

    // root the victim and tell us if it s dead after rooting's effect
    public final boolean rot() {
        if (negativeBuff.isEmpty() || getHp() <= 0) {
            return false;
        } else {
            int nrOfRounds = negativeBuff.get(0);
            int damageGiven = negativeBuff.get(1);
            nrOfRounds--;
            negativeBuff.set(0, nrOfRounds);
            this.hp -= damageGiven;
            // daca s-a terminat bufful negativ
            if (nrOfRounds == 0) {
                this.setNegativeBuff(new ArrayList<>());
            }
        }
        if (this.hp <= 0) {
            return true;
        }
        return false;
    }

    // tell me where from where to where should a champ move
    public final ArrayList<TupleInt> whereShouldHeMove(final char direction) {
        ArrayList<TupleInt> result = new ArrayList<>();
        int x = position.getX();
        int xOld = x;
        int y = position.getY();
        int yOld = y;
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
        result.add(new TupleInt(xOld, yOld));
        result.add(new TupleInt(x, y));
        return result;
    }

    public final char getTerrain(final Map map) {
        return map.getTerrain(position.getX(), position.getY());
    }

    public final void deleteMeFromMap(final Map map) {
        int x = position.getX();
        int y = position.getY();
        map.getMap().get(x).get(y).getPlayers().remove(Integer.valueOf(getId()));
    }

    public abstract void resetHP();
    public abstract int maxHP();

    public final int getId() {
        return id;
    }

    public final void setId(final int id) {
        this.id = id;
    }

    public final int getXp() {
        return xp;
    }

    public final void setXp(final int xp) {
        this.xp = xp;
    }

    public final int getHp() {
        return hp;
    }

    public final void setHp(final int hp) {
        this.hp = hp;
    }

    public final ArrayList<Integer> getNegativeNegativeBuff() {
        return negativeBuff;
    }

    public final void setNegativeBuff(final ArrayList<Integer> negativeBuff) {
        this.negativeBuff = negativeBuff;
    }

    public final ArrayList<Damage> getDamage() {
        return damage;
    }

    public final void setDamage(final ArrayList<Damage> damage) {
        this.damage = damage;
    }

    public final void addDamage(final Damage damageGiven) {
        this.damage.add(damageGiven);
    }

    public final void clearDamage() {
        this.setDamage(new ArrayList<Damage>());
    }

    public final int summAllTheDamage() {
        int res = 0;
        for (Damage damageGiven:damage) {
            res += damageGiven.getDamageWithBonuses();
        }
        return res;
    }

    public final void decreaseIncapacity() {
        if (!incapacity.isEmpty()) {
            int numberOfIncapacity = incapacity.get(0);
            incapacity.remove(0);
            numberOfIncapacity--;
            if (numberOfIncapacity > 0) {
                incapacity.add(numberOfIncapacity);
            }
        }
    }

    public final Position getPosition() {
        return position;
    }

    public final void setPosition(final Position position) {
        this.position = position;
    }

    public final Champion getEnemy() {
        return enemy;
    }

    public final void setEnemy(final Champion enemy) {
        this.enemy = enemy;
    }

    public final int getPriorityToAttck() {
        return priorityToAttck;
    }

    public final ArrayList<Integer> getIncapacity() {
        return incapacity;
    }

    public final void setIncapacity(final ArrayList<Integer> incapacity) {
        this.incapacity = incapacity;
    }

    public final void setPriorityToAttck(final int priorityToAttck) {
        this.priorityToAttck = priorityToAttck;
    }

    public abstract String toString();
    public abstract void fight(Champion enemyChamp, Map map);

    public abstract void accept(Ability ability, Map map);
}
