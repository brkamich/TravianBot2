package Buildings.Types;

import Buildings.Building;
import Tribes.Village;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * @author David Zaludek
 */
public class Academy extends Building {
    public Academy(String name, String link, int level, int id, Village village) {
        super(name, link, level, id, village);
    }

    public void researchUnit(int id) {
        village.getAcc().getDriver().get(village.getLink());
        village.getAcc().getDriver().get(link);
        for (WebElement e : village.getAcc().getDriver().findElements(By.className("research"))) {
            if (Integer.parseInt(e.findElement(By.tagName("img")).getAttribute("class").replaceAll("\\D+", "")) == id) {
                e.findElement(By.tagName("button")).click();
                return;
            }
        }
    }

    //returns currently researched unit if nothing in production then returns -1
    public int curentResearch() {
        village.getAcc().getDriver().get(village.getLink());
        village.getAcc().getDriver().get(link);

        try {
            return Integer.parseInt(village.getAcc().getDriver().findElement(By.className("under_progress")).findElement(By.tagName("img")).getAttribute("class").replaceAll("\\D+", ""));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return -1;
    }
    
    public boolean construct(){
        return false;
    }



}