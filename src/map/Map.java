package map;

import champions.Champion;
import factories.ChampFactory;
import utils.Position;
import utils.TupleInt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Map {
    private ArrayList<ArrayList<MapCell>> map = new ArrayList<>();
    private int numLines;
    private int numColumns;

    public Map(final int numLines, final int numColumns) {
        this.numLines = numLines;
        this.numColumns = numColumns;

        for (int i = 0; i < numLines; i++) {
            map.add(new ArrayList<MapCell>());
        }
        for (int i = 0; i < numLines; i++) {
            for (int j = 0; j < numColumns; j++) {
                map.get(i).add(new MapCell());
            }
        }
    }

    // Construct map of terrains and players
    public final void constructMap(final ArrayList<String> lines, final ArrayList<String> players) {
        int i = 0;
        for (String line:lines) {
            for (int j = 0; j < line.length(); j++) {
                char terrain = line.charAt(j);
                this.map.get(i).get(j).setType(terrain);
            }
            i++;
        }
        i = 0;
        for (String player:players) {
            String[] data = player.split(" ");
            List<String> dataToString = Arrays.asList(data);
            char playerType = dataToString.get(0).charAt(0);
            int line = Integer.parseInt(dataToString.get(1));
            int col = Integer.parseInt(dataToString.get(2));
            Position position = new Position(line, col);
            ChampFactory.getInstance().createChampion(i, playerType, position);
            this.map.get(line).get(col).addPlayer(i);
            i++;
        }
    }

    // apelez move(id, champ.whereShouldImove("w"))
    public final void move(final int id, final ArrayList<TupleInt> coord) {
        Champion me = ChampFactory.getInstance().getChampById(id);
        // doar daca nu s mort ma pot misca
        if (me.getHp() > 0) {
            // daca pot sa ma misc
            if (me.getIncapacity().isEmpty()) {
                int x = coord.get(0).getFirst();
                int y = coord.get(0).getSecond();
                int xx = coord.get(1).getFirst();
                int yy = coord.get(1).getSecond();

                map.get(x).get(y).getPlayers().remove(Integer.valueOf(id));
                // daca aveam inamic si l am abandonat, nu o sa i mai fiu inamic
                if (me.getEnemy() != null) {
                    me.getEnemy().setEnemy(null);
                }
                me.setEnemy(null);

                // ajunge langa un inamic
                if (!map.get(xx).get(yy).getPlayers().isEmpty()) {
                    int enemyId = map.get(xx).get(yy).getPlayers().get(0);
                    Champion enemy = ChampFactory.getInstance().getChampById(enemyId);
                    me.setEnemy(enemy);
                    enemy.setEnemy(me);
                }
                map.get(xx).get(yy).getPlayers().add(id);
                Position newPositon = new Position(xx, yy);
                me.setPosition(newPositon);
            }
        }
    }

    // remove the champion from map
    public final void removeChampionFromMap(final int id) {
        Champion me = ChampFactory.getInstance().getChampById(id);
        int x = me.getPosition().getX();
        int y = me.getPosition().getY();
        map.get(x).get(y).getPlayers().remove(Integer.valueOf(id));
    }

    public final char getTerrain(final int x, final int y) {
        return map.get(x).get(y).getType();
    }

    public final ArrayList<ArrayList<MapCell>> getMap() {
        return map;
    }

    public final void setMap(final ArrayList<ArrayList<MapCell>> map) {
        this.map = map;
    }

    public final int getNumLines() {
        return numLines;
    }

    public final void setNumLines(final int numLines) {
        this.numLines = numLines;
    }

    public final int getNumColumns() {
        return numColumns;
    }

    public final void setNumColumns(final int numColumns) {
        this.numColumns = numColumns;
    }

    @Override
    public final String toString() {
        StringBuilder output = new StringBuilder();
        for (ArrayList<MapCell> line:this.map) {
            for (MapCell cell:line) {
                String insider = cell.toString() + " ";
                output.append(insider);
            }
            output.append("\n");
        }
        return output.toString();
    }
}
