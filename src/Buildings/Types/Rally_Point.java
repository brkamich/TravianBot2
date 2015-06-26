package Buildings.Types;

import Buildings.Building;
import Tribes.Village;

public class Rally_Point extends Building {

    public Rally_Point(String name, String link, int level, int id, Village village) {
        super(name, link, level, id, village);
    }

    public int[] getArmy() {
        return null;
    }
    public boolean construct(){
        return false;
    }
}
