package Buildings;

import Buildings.Types.Building_Site;
import Resources.Resources;
import Tribes.Village;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author David Zaludek
 */
public abstract class Building {
    
    public int buildPosition;
    public final String link, name;
    public final Village village;
    public final int level;
    public final int id;

    public Building(String name, String link, int level, int id, Village village) {
        this.link = link;
        this.name = name;
        this.level = level;
        this.village = village;
        this.id = id;
        this.buildPosition=Integer.parseInt(link.substring(link.indexOf('=')+1));
    }

    public int[] upgradeResources() {

        int resources[] = new int[5];

        village.getAcc().getDriver().get(village.getLink());
        village.getAcc().getDriver().get(link);

        try {

            WebElement e = village.getAcc().getDriver().findElement(By.className("showCosts"));

            resources[0] = Integer.parseInt(e.findElement(By.cssSelector("span[class='resources r1']")).getText());
            resources[1] = Integer.parseInt(e.findElement(By.cssSelector("span[class='resources r2']")).getText());
            resources[2] = Integer.parseInt(e.findElement(By.cssSelector("span[class='resources r3']")).getText());
            resources[3] = Integer.parseInt(e.findElement(By.cssSelector("span[class='resources r4']")).getText());
            resources[4] = Integer.parseInt(e.findElement(By.cssSelector("span[class='resources r5']")).getText());

            return resources;

        } catch (Exception ex) {

        }

        return null;
    }

    public int upgradeTime() {
        int time = 0;
        village.getAcc().getDriver().get(village.getLink());
        village.getAcc().getDriver().get(link);
        WebElement e = village.getAcc().getDriver().findElement(By.className("showCosts"));
        String s = e.findElement(By.cssSelector("span[class='clocks']")).getText();
        String[] values = s.split(":");

        time += Integer.parseInt(values[0]) * 3600;
        time += Integer.parseInt(values[1]) * 60;
        time += Integer.parseInt(values[2]);
        return time;
    }

    public boolean upgrade() {

        village.getAcc().getDriver().get(village.getLink());
        village.getAcc().getDriver().get(link);
        int []tmp = new int[5];
        Resources res;
       int num=0;
        for(WebElement we:village.getAcc().getDriver().findElement(By.className("showCosts")).findElements(By.tagName("span"))){
        tmp[num]=Integer.parseInt(we.getText().replaceAll("[\\)\\(]", "").replaceAll("[^0-9]", ""));
        num++;
        if(num==5){
            break;
        }
        }
                res=new Resources(tmp);
                int time=village.getProduction().whenReady(village.getResources(), res,village);
                if(time<0){
                    System.out.println("ERROR SMALL WARHOUSE/GRANARY/NOT ENOUHT CONSUMERS/NEGATIVE PRODUCTION");
                    return false;
                }else{
                    try {
                        Thread.sleep((time+8)*1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Building_Site.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
        
        try {
            WebElement element = village.getAcc().getDriver().findElement(By.id("contract")).findElement(By.cssSelector("button[class='green build']"));
            element.click();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public abstract boolean construct();
    
    public int getId() {
        return id;
    }

    public int getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }
    
    public String getLink() {
        return link;
    }
    
    public int getBuilPosition(){
        return buildPosition;
    }

}
