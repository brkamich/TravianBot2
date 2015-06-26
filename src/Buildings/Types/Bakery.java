package Buildings.Types;

import Buildings.Building;
import Tribes.Village;

public class Bakery extends Building {
    public Bakery(String name, String link, int level, int id, Village village) {
        super(name, link, level, id, village);
    }
    public boolean construct(){
        return false;
    }

}
