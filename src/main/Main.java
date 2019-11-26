package main;

import champions.Champion;
import factories.ChampFactory;
import game.Game;
import game.GameInputLoader;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
	// write your code here
        String path = "./src/main/test.txt";
        GameInputLoader gameInputLoader = new GameInputLoader(path, " ");
        Game game = gameInputLoader.getGame();
        game.play();
    }
}

//            K 0 0 260 0 0
//            K 0 0 260 0 0