package Buildings.Types;

import Buildings.Building;
import Tribes.Village;

public class Tournament_Square extends Building {

    public Tournament_Square(String name, String link, int level, int id, Village village) {
        super(name, link, level, id, village);
    }
    public boolean construct(){
        return false;
    }
}
