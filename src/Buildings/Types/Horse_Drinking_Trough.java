/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Buildings.Types;

import Buildings.Building;
import Tribes.Village;

public class Horse_Drinking_Trough extends Building {

    public Horse_Drinking_Trough(String name, String link, int level, int id, Village village) {
        super(name, link, level, id, village);
    }
    public boolean construct(){
        return false;
    }
}
