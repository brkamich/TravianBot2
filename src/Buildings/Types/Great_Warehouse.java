package Buildings.Types;

import Buildings.Building;
import Tribes.Village;

public class Great_Warehouse extends Building {

    public Great_Warehouse(String name, String link, int level, int id, Village village) {
        super(name, link, level, id, village);
    }
    public boolean construct(){
        return false;
    }
}
