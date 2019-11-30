package map;

import java.util.ArrayList;

public class MapCell {
    private char type;
    private ArrayList<Integer> players = new ArrayList<>();

    public MapCell() {
    }

    public MapCell(final char type) {
        this.type = type;
        players = new ArrayList<>();
    }

    public final ArrayList<Integer> getPlayers() {
        return players;
    }

    public final void setPlayers(final ArrayList<Integer> players) {
        this.players = players;
    }

    public final char getType() {
        return type;
    }

    public final void setType(final char type) {
        this.type = type;
    }

    public final void addPlayer(final Integer champ) {
        this.players.add(champ);
    }

    @Override
    public final String toString() {
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
