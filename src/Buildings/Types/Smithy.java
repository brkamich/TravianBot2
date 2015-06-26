package Buildings.Types;

import Buildings.Building;
import Tribes.Village;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Smithy extends Building {

    public Smithy(String name, String link, int level, int id, Village village) {
        super(name, link, level, id, village);
    }

    public void upgradeUnit(int id) {
        village.getAcc().getDriver().get(village.getLink());
        village.getAcc().getDriver().get(link);
        for (WebElement e : village.getAcc().getDriver().findElements(By.className("research"))) {
            if (Integer.parseInt(e.findElement(By.tagName("img")).getAttribute("class").replaceAll("\\D+", "")) == id) {
                e.findElement(By.tagName("button")).click();
                return;
            }
        }
    }
    public boolean construct(){
        return false;
    }
}
