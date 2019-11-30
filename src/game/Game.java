package game;

import champions.Champion;
import factories.ChampFactory;
import map.Map;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Game {
    private int roundNumber = 0;
    private int playerNumber = 0;
    private ArrayList<String> movements;
    private Map world;

    public Game(final int roundNumber, final int playerNumber, final int numLines,
                final int numColumns,  final ArrayList<String> terrain,
                final ArrayList<String> players, final ArrayList<String> movements) {
        this.roundNumber = roundNumber;
        this.playerNumber = playerNumber;
        this.movements = movements;
        this.world = new Map(numLines, numColumns);
        this.world.constructMap(terrain, players);
    }

    public final void playRound(final String movement) {
        // fac mutarile
        for (int i = 0; i < movement.length(); i++) {
            Champion player = ChampFactory.getInstance().getChampById(i);

            // DOT damage
            player.rot();
            char letter = movement.charAt(i);
            world.move(i, player.whereShouldHeMove(letter));
            // reduc incapacitatea
            player.decreaseIncapacity();
        }

        Set<Integer> fought = new HashSet<>();
        for (int i = 0; i < playerNumber; i++) {
            Champion player = ChampFactory.getInstance().getChampById(i);
            player.clearDamage();

            // traiesc, nu am luptat, am inamic in casuta
            if (player.getHp() > 0 && (!fought.contains(i)) && player.getEnemy() != null) {
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

            }

        }
    }

    public final void combat2Champs(final Champion player, final Champion enemy) {
        // in caz ca erau inamici dar i a omorat root ul
        if (player.getHp() <= 0 || enemy.getHp() <= 0) {
            if (player.getHp() <= 0) {
                player.deleteMeFromMap(world);
            }
            if (enemy.getHp() <= 0) {
                enemy.deleteMeFromMap(world);
            }
            player.setEnemy(null);
            enemy.setEnemy(null);
            return;
        }
        player.fight(enemy, world);
        enemy.fight(player, world);
        enemy.setHp(enemy.getHp() - player.summAllTheDamage());
        player.setHp(player.getHp() - enemy.summAllTheDamage());


        player.clearDamage();
        enemy.clearDamage();
        //  dau xp la winner si resetez HP
        if (player.getHp() <= 0 && enemy.getHp() > 0) {
            enemy.setEnemy(null);
            player.setEnemy(null);
            player.deleteMeFromMap(world);

            int enemyOldLevel = enemy.getLevel();
            enemy.setXp(enemy.getXp() + enemy.getXpFrom(player));
            if (enemyOldLevel < enemy.getLevel()) {
                enemy.resetHP();
            }
        }

        if (enemy.getHp() <= 0 && player.getHp() > 0) {
            enemy.setEnemy(null);
            player.setEnemy(null);
            enemy.deleteMeFromMap(world);

            int playerOldLevel = player.getLevel();
            player.setXp(player.getXp() + player.getXpFrom(enemy));
            if (playerOldLevel < player.getLevel()) {
                player.resetHP();
            }
        }

        if (enemy.getHp() <= 0 && player.getHp() <= 0) {
            enemy.setEnemy(null);
            player.setEnemy(null);
            enemy.deleteMeFromMap(world);
            player.deleteMeFromMap(world);
        }
    }

    public final void play(final String path, final String pathToPrint) throws IOException {
        int roundNumberNow = 0;
        for (String movement : movements) {
            playRound(movement);
            roundNumberNow++;
        }
        String content = "";
        for (int i = 0; i < playerNumber; i++) {
            Champion player = ChampFactory.getInstance().getChampById(i);
            if (player.getHp() <= 0) {
                content += ChampFactory.getInstance().getChampionForOutput(i) + " dead\n";
            } else {
                content += ChampFactory.getInstance().getChampionForOutput(i)
                        + " " + player.getLevel() + " " + player.getXp() + " " + player.getHp()
                        + " " + player.getPosition().getX()
                        + " " + player.getPosition().getY() + "\n";
            }
        }
        PrintWriter writer = new PrintWriter(pathToPrint, "UTF-8");
        writer.println(content);
        writer.close();


    }



    public final int getRoundNumber() {
        return roundNumber;
    }

    public final void setRoundNumber(final int roundNumber) {
        this.roundNumber = roundNumber;
    }

    public final int getPlayerNumber() {
        return playerNumber;
    }

    public final void setPlayerNumber(final int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public final Map getWorld() {
        return world;
    }

    public final void setWorld(final Map world) {
        this.world = world;
    }

    @Override
    public final String toString() {
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
