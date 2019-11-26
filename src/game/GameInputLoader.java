package game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class GameInputLoader {
    private String pathIN;
    private String pathOUT;

    public GameInputLoader(String pathIN, String pathOUT) {
        this.pathIN = pathIN;
        this.pathOUT = pathOUT;
    }

    public Game getGame() {
        try {
            File file = new File(this.pathIN);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            line = br.readLine();
            List<String> mapDimAux = Arrays.asList(line.split(" "));
            int numberOfLines = Integer.parseInt(mapDimAux.get(0));
            int numberOfColumns = Integer.parseInt(mapDimAux.get(0));

            ArrayList<String> terrain = new ArrayList<>();
            for (int i = 1; i <= numberOfLines; i++) {
                line = br.readLine();
                terrain.add(line);
            }

            line = br.readLine();
            int numberOfPlayers = Integer.parseInt(line);

            ArrayList<String> players = new ArrayList<>();
            for (int i = 1; i <= numberOfPlayers; i++) {
                line = br.readLine();
                players.add(line);
            }

            line = br.readLine();
            int numberOfMovements = Integer.parseInt(line);

            ArrayList<String> movements = new ArrayList<>();
            for (int i = 1; i <= numberOfMovements; i++) {
                line = br.readLine();
                movements.add(line);
            }

            return new Game(numberOfMovements, numberOfPlayers, numberOfLines, numberOfColumns, terrain, players, movements);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
        return null;
    }

    public String getPathIN() {
        return pathIN;
    }

    public void setPathIN(String pathIN) {
        this.pathIN = pathIN;
    }

    public String getPathOUT() {
        return pathOUT;
    }

    public void setPathOUT(String pathOUT) {
        this.pathOUT = pathOUT;
    }
}
