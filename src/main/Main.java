package main;

import champions.Champion;
import factories.ChampFactory;
import game.Game;
import game.GameInputLoader;

import java.io.IOException;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws IOException {

	// write your code here
        String path = args[0];
        String pathToPrint = args[1];
        GameInputLoader gameInputLoader = new GameInputLoader(path, " ");
        Game game = gameInputLoader.getGame();
        game.play(path, pathToPrint);
    }
}