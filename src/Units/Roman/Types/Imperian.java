/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Units.Roman.Types;

import Resources.Resources;

/**
 *
 * @author michalbrka
 */
public class Imperian {
    private Resources res;
    private int speed;
    private int trainTime;
    private int bounty;
    private int attack;
    private int deffCavalery;
    private int deffInfantry;
    private boolean Cavalery;
    public Imperian(){
        int[] arr={120,100,150,30,1};
        res=new Resources(arr);
        speed=7;
        bounty=50;
        trainTime=1920;
        attack=70;
        deffInfantry=40;
        deffCavalery=25;
    }
}
