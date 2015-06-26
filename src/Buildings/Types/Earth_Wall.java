package Buildings.Types;

import Buildings.Building;
import Tribes.Village;


public class Earth_Wall extends Building {

    public Earth_Wall(String name, String link, int level, int id, Village village) {
        super(name, link, level, id, village);
    }
    public boolean construct(){
        return false;
    }
}
