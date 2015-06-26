/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gold;

import Accounts.Account;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 *
 * @author michalbrka
 */
public class GoldMenu {
    Account account;
    public GoldMenu(Account acc){
        this.account=acc;
    }
    
    public void getGoldTutorial(){
        account.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[7]/a"))).click();
        account.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[2]/a/div[2]"))).click();
        account.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form/div[3]"))).click();
    }
    
    
    
}
