package Buildings.Types;

import Buildings.Building;
import Tribes.Village;

public class Iron_Foundry extends Building {

    public Iron_Foundry(String name, String link, int level, int id, Village village) {
        super(name, link, level, id, village);
    }
    public boolean construct(){
        return false;
    }
}
