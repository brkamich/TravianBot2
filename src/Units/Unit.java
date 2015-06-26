package Units;

import Data.Data;
import Resources.Resources;


public class Unit {
    private int id;
    private int count;
    private int nation;
    private Resources resources;
    private int speed;
    private int trainTime;
    private int bounty;
    private int attack;
    private int deffCavalery;
    private int deffInfantry;
    private boolean Cavalery;
    
    public Unit(int id, int count, int nation, Resources resources,int trainTime,int speed) {
        this.id = id;
        this.count = count;
        this.nation=nation;
        this.resources=resources;
        this.trainTime=trainTime;
        this.speed=speed;
    }

    public int getId() {
        return id;
    }

    public int getCount() {
        return count;
    }

    public String getName() {
        return Data.unitNames[id - 1];
    }
    
    public void setCount(int count){
        this.count=count;
    }
    
    public Resources getResources(){
        return resources;
    }
}
