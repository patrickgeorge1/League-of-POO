package game;

import champions.Champion;
import factories.ChampFactory;
import map.Map;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Game {
    private int roundNumber = 0;
    private int playerNumber = 0;
    private ArrayList<String> movements;
    private Map world;

    public Game(int roundNumber, int playerNumber, int numLines, int numColumns,  ArrayList<String> terrain, ArrayList<String> players, ArrayList<String> movements) {
        this.roundNumber = roundNumber;
        this.playerNumber = playerNumber;
        this.movements = movements;
        this.world = new Map(numLines, numColumns);
        this.world.constructMap(terrain, players);
    }

    public void playRound(String movement) {
        // fac mutarile
        for (int i = 0; i < movement.length(); i++) {
            Champion player = ChampFactory.getInstance().getChampById(i);
            char letter = movement.charAt(i);
            String move = String.valueOf(letter);
            world.move(i, player.whereShouldHeMove(move));
        }

        System.out.println("Incepe RUNDA : ");
        Set<Integer> fought = new HashSet<>();
        for (int i = 0; i < playerNumber; i++) {
            Champion player = ChampFactory.getInstance().getChampById(i);
            player.clearDamage();

            // se bate cu inamicul
            if((!fought.contains(i)) && player.getEnemy() != null) {
                fought.add(i);
                fought.add(player.getEnemy().getId());

                // trebuie sa se lupte
                Champion enemy = player.getEnemy();
                enemy.clearDamage();
                // nu trebuie sa atace primul
                if (player.getPriorityToAttck() == 0) {
                    Champion aux = enemy;
                    enemy = player;
                    player = aux;
                }
                combat2Champs(player, enemy);
                // daca unul moare nu o sa mai fie inamici
                if (player.getHp() <= 0 || enemy.getHp() <= 0) {
                    player.setEnemy(null);
                    enemy.setEnemy(null);
                }
            } else {
                // nu se bate si ia doar rot
                player.rot();
            }
        }
    }

    public void combat2Champs(Champion player, Champion enemy) {
        System.out.println();
        System.out.println("-------Se bate " + player.getId() + " cu " + enemy.getId() + " | hp inainte de bataie :");
        System.out.println(player.getHp());
        System.out.println(enemy.getHp());
        System.out.println("-------------------- dupa bataie :  \n");

        player.rot();
        enemy.rot();
        if (player.getHp() <= 0 || enemy.getHp() <= 0) {
            return;
        }
        player.fight(enemy, world);
        enemy.fight(player, world);
        enemy.setHp(enemy.getHp() - player.summAllTheDamage());
        player.setHp(player.getHp() - enemy.summAllTheDamage());
        System.out.println(player.getHp());
        System.out.println(enemy.getHp());
        System.out.println("\n\n");

        // TODO ai grija unde aplici rot


        player.clearDamage();
        enemy.clearDamage();
        //  dau xp la winner si resetez HP
        if (player.getHp() <= 0) {
            int enemy_old_level = enemy.getLevel();
            enemy.setXp(enemy.getXp() + enemy.getXpFrom(player));
            if (enemy_old_level < enemy.getLevel()) enemy.resetHP();
        }

        if (enemy.getHp() <= 0) {
            int player_old_level = player.getLevel();
            player.setXp(player.getXp() + player.getXpFrom(enemy));
            if (player_old_level < player.getLevel()) player.resetHP();
        }
    }

    public void play() {
        for (String movement:movements) {
            playRound(movement);
        }
        for (int i = 0; i < playerNumber; i++) {
            Champion player = ChampFactory.getInstance().getChampById(i);
            if (player.getHp() <= 0) System.out.println(ChampFactory.getInstance().getChampionForOutput(i) + " dead");
            else System.out.println(ChampFactory.getInstance().getChampionForOutput(i) + " " + player.getLevel() + " " + player.getXp() + " " + player.getHp() + " " + player.getPosition().getX() + " " + player.getPosition().getY());
        }
    }


    public int getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public Map getWorld() {
        return world;
    }

    public void setWorld(Map world) {
        this.world = world;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("num of rounds: ");
        result.append(Integer.toString(this.roundNumber));
        result.append("\n");

        result.append("num of players: ");
        result.append(Integer.toString(this.playerNumber));
        result.append("\n");

        result.append("players: ");
        result.append("\n");
        result.append(ChampFactory.getInstance().toString());
        result.append("\n");


        result.append(world.toString());
        return result.toString();
    }
}
