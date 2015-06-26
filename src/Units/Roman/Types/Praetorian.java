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
public class Praetorian {
    private Resources res;
    private int speed;
    private int trainTime;
    private int bounty;
    private int attack;
    private int deffCavalery;
    private int deffInfantry;
    public Praetorian(){
        int[] arr={100,130,160,70,1};
        res=new Resources(arr);
        speed=5;
        bounty=20;
        trainTime=1760;
        attack=30;
        deffInfantry=65;
        deffCavalery=35;
        
    }
}
