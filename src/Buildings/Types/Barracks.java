package Buildings.Types;

import Buildings.Building;
import Tribes.Village;

public class Barracks extends Building {

    public Barracks(String name, String link, int level, int id, Village village) {
        super(name, link, level, id, village);
    }

    public void trainUnits(int id, int amount) {

    }
    public boolean construct(){
        return false;
    }
}