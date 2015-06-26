package travian_bot;

import AI.AbstractAI;
import AI.BasicAI;
import AI.GoldAI;
import Accounts.Account;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.util.logging.Level;
import org.openqa.selenium.firefox.FirefoxDriver;


/**
 * @author David Zaludek
 */
public class Travian_bot {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */

    public static void main(String[] args) throws Exception {

        LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");
        java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.OFF);
        java.util.logging.Logger.getLogger("org.apache.commons.httpclient").setLevel(Level.OFF);

        WebDriver driver = new FirefoxDriver();
        //driver.s.setJavascriptEnabled(true);
        
        //try{
        //if(args.length>0){
        Account mainAcc = new Account(driver, "wake", "2lada2", "http://tx3.travian.sk");
        AbstractAI ai = new GoldAI(mainAcc);
        ai.start();
        /*}else{
            System.out.println("BAD INPUT: 1:name 2:password 3:server, like this: michal 456heslo http://tx3.travian.sk");
        }
        }catch(Exception e){
            System.out.println("BAD INPUT: 1:name 2:password 3:server, like this: michal 456heslo http://tx3.travian.sk");
        }*/
    }



}
