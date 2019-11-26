package map;

import champions.Champion;

import java.util.ArrayList;

public class MapCell {
    private char type;
    private ArrayList<Integer> players = new ArrayList<>();

    public MapCell() {
    }

    public MapCell(char type) {
        this.type = type;
        players = new ArrayList<>();
    }

    public ArrayList<Integer> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Integer> players) {
        this.players = players;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    public void addPlayer(Integer champ) {
        this.players.add(champ);
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        String insider = "( " + this.type + " - { ";
        output.append(insider);
        for (Integer x:this.players) {
            output.append(x.toString());
            output.append(" ");

        }
        output.append("})");
        return output.toString();
    }
}
