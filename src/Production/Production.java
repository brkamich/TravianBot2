/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Production;

import Resources.Resources;
import Tribes.Village;

/**
 *
 * @author michalbrka
 */
public class Production {
    double wood,clay,iron,crop;
    public Production(int wood,int clay, int iron, int crop){
        this.wood=wood/3600.0;
        this.clay=clay/3600.0;
        this.iron=iron/3600.0;
        this.crop=crop/3600.0;
    }
    public Production(){
        
    }

    public void setWood(int wood) {
        this.wood = (double)wood/3600.0;
    }

    public void setClay(int clay) {
        this.clay = (double)clay/3600.0;
    }

    public void setIron(int iron) {
        this.iron = (double)iron/3600.0;
    }

    public void setCrop(int crop) {
        this.crop = (double)crop/3600.0;
    }
    
    public int whenReady(Resources vilage,Resources building,Village vil){
        if(vilage.getConsumer()<building.getConsumer()||vil.getMaxWarehouse()<building.getClay()||vil.getMaxWarehouse()<building.getWood()||vil.getMaxWarehouse()<building.getIron()||vil.getMaxGranary()<building.getCrop()){
            return -1;
        }
        int MaxTime=0;
        if(building.getWood()-vilage.getWood()>0){
            MaxTime=(int)(((double)(building.getWood()-vilage.getWood()))/wood);
        }
        if(building.getClay()-vilage.getClay()>0){
            int tmp;
            tmp=(int)(((double)(building.getClay()-vilage.getClay()))/clay);
            if(tmp>MaxTime){
                MaxTime=tmp;
            }
        }
        if(building.getIron()-vilage.getIron()>0){
            int tmp;
            tmp=(int)(((double)(building.getIron()-vilage.getIron()))/iron);
            if(tmp>MaxTime){
                MaxTime=tmp;
            }
        }
        if(building.getCrop()-vilage.getCrop()>0){
            int tmp;
            tmp=(int)(((double)(building.getCrop()-vilage.getCrop()))/crop);
            if(tmp>MaxTime){
                MaxTime=tmp;
            }
        }
        return MaxTime;
    }
    
    public void print(){
        System.out.println("wood:"+wood+" clay:"+clay+" iron:"+iron+" crop:"+crop);
    }
    
}
