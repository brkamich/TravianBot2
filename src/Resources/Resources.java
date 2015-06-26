/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Resources;

/**
 *
 * @author michalbrka
 */
public class Resources {
    private int res[]=new int[5];
    private int total;
    public Resources(int[] res){
        this.res=res;
        total=res[0]+res[1]+res[2]+res[3];
    }
    public Resources(){
    }
    
    public void setWood(int wood){
        res[0]=wood;
    }
    public void setClay(int clay){
        res[1]=clay;
    }
    public void setIron(int iron){
        res[2]=iron;
    }
    public void setCrop(int crop){
        res[3]=crop;
    }
    public void setConsumer(int consumer){
        res[4]=consumer;
    }
    public void reloadTotal(){
        total=res[0]+res[1]+res[2]+res[3];
    }
    
    public int getWood(){
        return res[0];
    }
    public int getClay(){
        return res[1];
    }
    public int getIron(){
        return res[2];
    }
    public int getCrop(){
        return res[3];
    }
    public int getConsumer(){
        return res[4];
    }
    public int getTotal(){
        return total;
    }
    
   
    
    public int lowestResources(){
        if(res[0]<=res[1]&&res[0]<=res[2]&&res[0]<=res[3])
            return 0;
        if(res[1]<=res[0]&&res[1]<=res[2]&&res[1]<=res[3])
            return 1;
        if(res[2]<=res[1]&&res[2]<=res[0]&&res[2]<=res[3])
            return 2;
        if(res[3]<=res[1]&&res[3]<=res[2]&&res[3]<=res[0])
            return 3;
        return -1;
    }
    
    public int highestResources(){
        if(res[0]>=res[1]&&res[0]>=res[2]&&res[0]>=res[3])
            return 0;
        if(res[1]>=res[0]&&res[1]>=res[2]&&res[1]>=res[3])
            return 1;
        if(res[2]>=res[1]&&res[2]>=res[0]&&res[2]>=res[3])
            return 2;
        if(res[3]>=res[1]&&res[3]>=res[2]&&res[3]>=res[0])
            return 3;
        return -1;
    }
    
    public double ratio(){
        return res[this.highestResources()]/(res[this.lowestResources()]*1.0);
    }
}
