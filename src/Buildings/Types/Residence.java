package Buildings.Types;

import Buildings.Building;
import Resources.Resources;
import Tribes.Village;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Residence extends Building {
    private String trainSettlers;
    private String expansion;
    private String culturePoints;
    public Residence(String name, String link, int level, int id, Village village) {
        super(name, link, level, id, village);
        trainSettlers=link.substring(0,link.indexOf('?')+1)+"s=1&"+link.substring(link.indexOf('?')+1);
        expansion=link.substring(0,link.indexOf('?')+1)+"s=4&"+link.substring(link.indexOf('?')+1);
        culturePoints=link.substring(0,link.indexOf('?')+1)+"s=2&"+link.substring(link.indexOf('?')+1);
    }

    public String getTrainSettlers() {
        return trainSettlers;
    }

    public String getExpansion() {
        return expansion;
    }

    public String getCulturePoints() {
        return culturePoints;
    }
    
    public boolean trainSettler(){
        try {
            this.village.getAcc().loadData();
        } catch (InterruptedException ex) {
            Logger.getLogger(Residence.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.village.getAcc().getDriver().get(trainSettlers);
        int []arr = new int[5];
        int tmp=0;
        try{
        for(WebElement e:this.village.getAcc().getWait().until(ExpectedConditions.visibilityOfElementLocated(By.className("showCosts"))).findElements(By.tagName("span"))){
            arr[tmp] = Integer.parseInt(e.getText().replaceAll("[\\)\\(]", "").replaceAll("[^0-9]", ""));
            tmp++;
            if(tmp==5){break;}
        }
        }catch(Exception e){
            System.out.println("LOW LEVEL OR SETTLER ALREADY TRAINED");
            return false;
        }
        Resources res = new Resources(arr);
        int time = village.getProduction().whenReady(village.getResources(), res, village);
        if (time < 0) {
                    System.out.println("ERROR SMALL WARHOUSE/GRANARY/NOT ENOUHT CONSUMERS/NEGATIVE PRODUCTION");
                    return false;
                }else{
            try{
                System.out.println("Sleeping "+time+"s. then training settler");
                Thread.sleep((time + 8) * 1000);
                village.getAcc().getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form/div/div"))).click();
                village.getAcc().getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[2]/input"))).clear();
                village.getAcc().getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[2]/input"))).sendKeys("1");
                village.getAcc().getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form/button"))).click();
                try {
                    Thread.sleep(2500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Residence.class.getName()).log(Level.SEVERE, null, ex);
                }
                return true;
            }catch(Exception e){
                return false;
            }
        }
    }
    public boolean construct(){
        return false;
    }
    
}
