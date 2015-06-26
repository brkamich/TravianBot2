

package Tutorial;

import Accounts.Account;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
/**
 *
 * @author michalbrka
 */
public class Tutorial {
    
    private Account acc;
    public Tutorial(Account acc) throws InterruptedException{
        this.acc=acc;
    }
    
    
    public boolean takeQuest(String category,int id){
        acc.getDriver().get(acc.getServer()+"/dorf1.php");
        for(WebElement e:acc.getDriver().findElement(By.id("mentorTaskList")).findElements(By.className("quest"))){
            if(e.getAttribute("data-category").contains(category)&&Integer.parseInt(e.getAttribute("data-questid").substring(e.getAttribute("data-category").length()+1))==id){
        try {
            e.click();
            acc.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[5]/button[2]"))).click();
            acc.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form/div[3]"))).click();
            return true;
        } catch (Exception ex) {
            return false;
        }
        
        }
    }
        return false;
    }
    
   public boolean takeQuest(){
       acc.getDriver().get(acc.getServer()+"/dorf1.php");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Tutorial.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(WebElement e:acc.getDriver().findElement(By.id("mentorTaskList")).findElements(By.className("quest"))){
        try {
            e.click();
            acc.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[5]/button[2]"))).click();
            try{
            acc.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form/div[3]"))).click();
            }catch(Exception ei){
                return true;
            }
            return true;
        } catch (Exception ex) {
        }
        }
        return false;
   }
   
   public int numberOfQuests(){
       acc.getDriver().get(acc.getServer()+"/dorf1.php");
       return acc.getDriver().findElement(By.id("mentorTaskList")).findElements(By.className("quest")).size();
   }
   
    
}
