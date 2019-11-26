package abilities;

import champions.*;
import map.Map;
import utils.Damage;

import java.util.ArrayList;

public interface Ability {
    public void between(Champion me, Wizard wizard, Map map);
    public void between(Champion me, Knight knight, Map map);
    public void between(Champion me, Pyromancer pyromancer, Map map);
    public void between(Champion me, Rogue rogue, Map map);
    public void cast(Damage damage, Champion enemy, Champion me);
}
