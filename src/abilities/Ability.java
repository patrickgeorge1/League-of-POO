package abilities;

import champions.Champion;
import champions.Pyromancer;
import champions.Wizard;
import champions.Rogue;
import champions.Knight;
import map.Map;
import utils.Damage;

public interface Ability {
     void between(Champion me, Wizard wizard, Map map);
     void between(Champion me, Knight knight, Map map);
     void between(Champion me, Pyromancer pyromancer, Map map);
     void between(Champion me, Rogue rogue, Map map);
     void cast(Damage damage, Champion enemy, Champion me);
}
