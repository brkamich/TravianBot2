package Accounts;

import Gold.GoldMenu;
import Hero.Hero;
import Map.Maps;
import Tribes.Village;
import Tutorial.Tutorial;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.util.ArrayList;

public class Account {

    private final WebDriver driver;
    private ArrayList<String> buildingNames = new ArrayList<>();
    private ArrayList<Village> villages = new ArrayList<>();
    private Hero hero;
    private String server, name, pass;
    private WebDriverWait wait;
    private Tutorial tutorial;
    private int nation;
    private GoldMenu goldMenu;
    private Maps map;


    public Account(WebDriver driver, String name, String pass, String server) throws InterruptedException {
        this.driver = driver;
        this.name = name;
        this.pass = pass;
        this.server = server;
        this.hero=null;
        wait = new WebDriverWait(driver, 8);
        getLanguage();
        tutorial=new Tutorial(this);
        goldMenu=new GoldMenu(this);
        map=new Maps(this);
        this.login(driver);
        this.skipTutorial();
        if(!wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[3]/div[2]/div[2]/div/button"))).isSelected()){
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[3]/div[2]/div[2]/div/button"))).click();
        }
        loadData();
    }

    private void skipTutorial(){
        try{
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[5]/button[2]"))).click();
            Thread.sleep(2500);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[5]/button"))).click();
            Thread.sleep(2500);
        }catch(Exception e){
            
        }
    }
    
    
    
    /**
     * loading building names in server language
     */
    private void getLanguage() {
        int l = 0;
        try {
            WebDriver tmpDriver = new HtmlUnitDriver();

            String tmpServer = server.substring(19);

            tmpDriver.get("http://t4.answers.travian." + tmpServer + "/?view=answers&action=answer&aid=213#go2answer");

            for (WebElement e : tmpDriver.findElement(By.className("answer_result")).findElements(By.tagName("tr"))) {
                for (WebElement ee : e.findElements(By.tagName("td"))) {
                    for (WebElement eee : ee.findElements(By.className("rbg f8"))) {
                        buildingNames.add(eee.getText());
                        //System.out.println(l++ + " " + eee.getText());
                    }
                }
            }
        } catch (Exception e) {

        }

    }

    public void login(WebDriver driver) {
        try {
            driver.get(server);
            driver.findElement(By.name("name")).clear();
            driver.findElement(By.name("name")).sendKeys(name);
            driver.findElement(By.name("password")).clear();
            driver.findElement(By.name("password")).sendKeys(pass);
            driver.findElement(By.id("s1")).click();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void loadData() throws InterruptedException {
        villages.clear();
        if (!this.isLoggedIn()) {
            System.out.println("LOGIN");
            login(driver);
        }
        if(hero==null)
            hero = loadHero();
        else{
            hero.getHeroData();
        }
        driver.get(server + "/dorf1.php");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sidebarAfterContent")));
        nation=Integer.parseInt(driver.findElement(By.className("playerName")).findElement(By.tagName("img")).getAttribute("class").replaceAll("[^0-9]", ""));
        WebElement e = driver.findElement(By.id("sidebarAfterContent")).findElement(By.id("sidebarBoxVillagelist")).findElement(By.className("sidebarBoxInnerBox")).findElement(By.tagName("ul"));

        for (WebElement ele : e.findElements(By.tagName("a"))) {
          /*System.out.println(ele.getText());
            System.out.println(ele.getAttribute("href"));
            System.out.println(ele.findElement(By.tagName("div")).getText());*/
            villages.add(new Village(ele.findElement(By.tagName("div")).getText(), ele.getAttribute("href"), new Point(Integer.parseInt(ele.findElement(By.className("coordinateX")).getText().replaceAll("[^0-9-]", "")), Integer.parseInt(ele.findElement(By.className("coordinateY")).getText().replaceAll("[^0-9.]", "") )), this));
        }
    }

    
    private Hero loadHero() throws InterruptedException {

        if (!this.isLoggedIn()) {
            login(driver);
        }

        return new Hero(server + "/hero_inventory.php", this);
    }

    public int getBuildingId(String buildingName) {
        int i;

        for (i = 0; i < buildingNames.size(); i++) {
            if (buildingNames.get(i).compareToIgnoreCase(buildingName) == 0) {
                return i;
            }
        }
        return -1;
    }

    public String getBuildingName(int id) {
        if (id == -1) return "Building_Site";
        return buildingNames.get(id);
    }

    public ArrayList<String> getBuildingNames() {
        return buildingNames;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public ArrayList<Village> getVillages() {
        return villages;
    }

    public Village getVillageByCoords(int X,int Y) {
        for (Village v : villages) {
            if (v.getCoor().getX() == X && v.getCoor().getY()==Y) {
                return v;
            }
        }
        return null;
    }

    public boolean isLoggedIn() {
        try{
        if(driver.findElement(By.id("heroImageButton"))!=null){
            return true;
        }
        }catch(Exception e){
            return false;
        }
        return false;
    }

    public String getServer() {
        return server;
    }

    public String getName() {
        return name;
    }
    
    public Hero getHero(){
        return hero;
    }


    public WebDriverWait getWait() {
        return wait;
    }
    
    public Tutorial getTutorial(){
        return tutorial;
    }

    public boolean prolongProtection(){
        try{
            this.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[5]/button"))).click();
            this.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[6]/button"))).click();
            return true;
        }catch(Exception e){
            return false;
        }
    }
    
    public Maps getMap(){
        return map;
    }
    

}
