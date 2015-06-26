package Buildings.Types;

import Buildings.Building;
import Resources.Resources;
import Tribes.Village;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Woodcutter extends Building {

    public Woodcutter(String name, String link, int level, int id, Village village) {
        super(name, link, level, id, village);
    }
    
    public boolean construct(){
        village.getAcc().getDriver().get(village.getAcc().getServer()+"/build.php?id="+this.getBuilPosition());
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
                        System.out.println("PROBLEM");
                        village.getAcc().getDriver().get(village.getAcc().getServer()+"/build.php?id="+this.getBuilPosition());
                        Thread.sleep(3000);
                        if(village.getAcc().getDriver().findElement(By.className("contractLink")).findElement(By.tagName("button")).getAttribute("class").contains("green build")){
            village.getAcc().getDriver().findElement(By.className("contractLink")).findElement(By.tagName("button")).click();
            return true;
        }
                        return true;
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Building_Site.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
        
        return false;
    }
}
