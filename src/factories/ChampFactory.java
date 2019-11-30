package factories;

import champions.Champion;
import champions.Wizard;
import champions.Rogue;
import champions.Pyromancer;
import champions.Knight;
import utils.Position;
import java.util.HashMap;

public final class ChampFactory {
    private static ChampFactory instance = null;
    private static HashMap<Integer, Champion> dictOfChamps = new HashMap<>();
    private static HashMap<Integer, String> dictOfDefinition = new HashMap<>();
    private ChampFactory() {
    }

    public static ChampFactory getInstance() {
        if (instance == null) {
            instance = new ChampFactory();
            dictOfChamps = new HashMap<>();
        }
        return instance;
    }

    public void createChampion(final int id, final char type, final Position position) {
        Champion champ;
        switch (type) {
            case 'K':
                champ = new Knight(id);
                break;
            case 'W':
                champ = new Wizard(id);
                break;
            case 'R':
                champ = new Rogue(id);
                break;
            case 'P':
                champ = new Pyromancer(id);
                break;
            default:
                champ = null;
                break;
        }
        champ.setPosition(position);
        dictOfDefinition.put(id, String.valueOf(type));
        dictOfChamps.put(id, champ);
    }

    public Champion getChampById(final int id) {
        return dictOfChamps.get(id);
    }

    public String getChampionForOutput(final int id) {
        return dictOfDefinition.get(id);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Integer key:dictOfChamps.keySet()) {
            result.append(" | ");
            result.append(dictOfChamps.get(key));
            result.append(" | ");
        }
        return result.toString();
    }


    public static HashMap<Integer, Champion> getDictOfChamps() {
        return dictOfChamps;
    }

    public static void setDictOfChamps(final HashMap<Integer, Champion> dictOfChamps) {
        ChampFactory.dictOfChamps = dictOfChamps;
    }
}
