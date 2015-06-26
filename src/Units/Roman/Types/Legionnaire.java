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
public class Legionnaire {
    private Resources res;
    private int speed;
    private int trainTime;
    private int bounty;
    private int attack;
    private int deffCavalery;
    private int deffInfantry;
    public Legionnaire(){
        int[] arr={120,100,150,30,1};
        res=new Resources(arr);
        speed=6;
        bounty=50;
        trainTime=1600;
        attack=40;
        deffCavalery=50;
        deffInfantry=35;
    }
}