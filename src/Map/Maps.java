/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Map;

import Accounts.Account;
import java.awt.Point;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 *
 * @author michalbrka
 */
public class Maps {
    private Account acc;
    private String link;
    public Maps(Account acc){
        this.acc=acc;
        this.link=acc.getServer()+"/karte.php";
    }
    
    public void openMap(){
        this.acc.getDriver().get(link);
        try {
            Thread.sleep(2500);
        } catch (InterruptedException ex) {
            Logger.getLogger(Maps.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Point findFreeSpot(Point centre){
       int loc=1;
       while(true){
           for(int i=centre.x-loc;i<=centre.x+loc;i++){
               try{
               acc.getDriver().get(getSettlerLink(new Point(i,centre.y+loc)));
               if(acc.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("btn_ok"))).isDisplayed()){
                   return new Point(i,centre.y+loc);
               }
               Thread.sleep(1000);
               }catch(Exception e){
                   
               }
               try{
               acc.getDriver().get(getSettlerLink(new Point(i,centre.y-loc)));
               if(acc.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("btn_ok"))).isDisplayed()){
                   return new Point(i,centre.y-loc);
               }
               Thread.sleep(1000);
               }catch(Exception e){
                   
               }
           }
           for(int i=centre.y-loc;i<=centre.y+loc;i++){
               try{
               acc.getDriver().get(getSettlerLink(new Point(centre.x+loc,i)));
               if(acc.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("btn_ok"))).isDisplayed()){
                   return new Point(centre.x+loc,i);
               }
               Thread.sleep(1000);
               }catch(Exception e){
                   
               }
               try{
               acc.getDriver().get(getSettlerLink(new Point(centre.x-loc,i)));
               if(acc.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("btn_ok"))).isDisplayed()){
                   return new Point(centre.x-loc,i);
               }
               Thread.sleep(1000);
               }catch(Exception e){
                   
               }
           }
           
           loc++;
           if(loc>100){
               System.out.println("SOME ERROR FINDING SPOT");
               break;
           }
       }
       return null;
    }
    
    public String getSettlerLink(Point p){
        String ln=acc.getServer()+"/build.php?gid=16&tt=2&mapid="+Integer.toString(this.getFieldID(p))+"&s=1";
        return ln;
    }
    
    public int getFieldID(Point p){
        int X=p.x+400;
        int Y=p.y+400;
        int res=(800-Y)*801+X+1;
        return res;
    }
    
    
}
