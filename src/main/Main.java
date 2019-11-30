package main;


import game.Game;
import game.GameInputLoader;
import java.io.IOException;


public final class Main {
    private Main() {
    }

    public static void main(final String[] args) throws IOException {

	// write your code here
        String path = args[0];
        String pathToPrint = args[1];
        GameInputLoader gameInputLoader = new GameInputLoader(path, " ");
        Game game = gameInputLoader.getGame();
        game.play(path, pathToPrint);
    }
}
