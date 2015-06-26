package Buildings.Types;

import Buildings.Building;
import Resources.Resources;
import Tribes.Village;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by davidzaludek on 12/02/15.
 */
public class Building_Site extends Building {

    public Building_Site(String name, String link, int level, int id, Village village) {
        super(name, link, level, id, village);
    }

    public boolean build(int id) {
        Resources res;
        int[] tmp = new int[5];
        for (int i = 1; i < 4; i++) {
            village.getAcc().getDriver().get(link + "&category=" + String.valueOf(i));
            try {
                Thread.sleep(2500);
            } catch (InterruptedException ex) {
                Logger.getLogger(Building_Site.class.getName()).log(Level.SEVERE, null, ex);
            }
            for (WebElement e : village.getAcc().getDriver().findElements(By.className("buildingWrapper"))) {
                int num = 0;
                for (WebElement we : e.findElement(By.className("showCosts")).findElements(By.tagName("span"))) {
                    tmp[num] = Integer.parseInt(we.getText().replaceAll("[\\)\\(]", "").replaceAll("[^0-9]", ""));
                    num++;
                    if (num == 5) {
                        break;
                    }
                }
                res = new Resources(tmp);
                int time = village.getProduction().whenReady(village.getResources(), res, village);
                if (time < 0) {
                    System.out.println("ERROR SMALL WARHOUSE/GRANARY/NOT ENOUHT CONSUMERS/NEGATIVE PRODUCTION");
                    return false;
                } else {
                    try {
                        Thread.sleep((time + 8) * 1000);
                        village.getAcc().getDriver().get(link + "&category=" + String.valueOf(i));
                        Thread.sleep(2500);
                        for (WebElement e2 : village.getAcc().getDriver().findElements(By.className("buildingWrapper"))) {
                            if (village.getAcc().getBuildingName(id).toLowerCase().contains(e2.findElement(By.tagName("H2")).getText().toLowerCase())) {
                                //press build if available
                                try {
                                    e2.findElement(By.className("contractLink")).findElement(By.tagName("button")).click();
                                    System.out.println("BUILDING");
                                    return true;

                                } catch (Exception ex) {
                                    System.err.println("not enought resources/full queue/u suck");
                                    System.err.println(ex.getMessage());
                                }
                            }
                        }
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Building_Site.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
            }
        }
        return false;
    }

    public boolean construct() {
        return false;
    }
}
