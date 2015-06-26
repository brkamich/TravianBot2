package Tribes;

import Accounts.Account;
import Buildings.Building;
import Buildings.Types.*;
import Production.Production;
import Resources.Resources;
import Units.Unit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.awt.*;
import java.util.ArrayList;
import org.openqa.selenium.support.ui.ExpectedConditions;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author David Zaludek
 */

public class Village {

    private ArrayList<Building> buildings;
    private int resources[] = new int[5];
    private Resources Resources;
    private String name, link;
    private Account acc;
    private Integer MaxWarehouse;
    private Integer MaxGranary;
    private Point coor;
    private ArrayList<Unit> army;
    private boolean hasHero;
    private Production production;

    public Village(String name, String link, Point point, Account acc) {
        this.name = name;
        this.link = link;
        this.acc = acc;
        this.coor = point;
        Resources=new Resources();
        production=new Production();
        this.loadData();
    }

    public void loadCoordinates() {
        int q = 1, w = 1;
        acc.getDriver().get(acc.getServer() + "/statistiken.php?id=2");
        String x = acc.getDriver().findElement(By.className("contentContainer")).findElement(By.id("content")).findElement(By.id("villages")).findElement(By.className("hl")).findElement(By.tagName("span")).getText().replaceAll("[\\)\\(]", "").split("[|]")[0].replaceAll("[^0-9-]", "");
        String y = acc.getDriver().findElement(By.className("contentContainer")).findElement(By.id("content")).findElement(By.id("villages")).findElement(By.className("hl")).findElement(By.tagName("span")).getText().replaceAll("[\\)\\(]", "").split("[|]")[1].replaceAll("[^0-9-]", "");
        coor = new Point(Integer.parseInt(x) * q, Integer.valueOf(y) * w);
    }

    public void loadBuildings() {
        if (!acc.isLoggedIn()) {
            acc.login(acc.getDriver());
        }

        buildings = new ArrayList<>();
        
        //<editor-fold defaultstate="collapsed" desc="LoadFields">
        acc.getDriver().get(link);

        int j = 0;
        for (WebElement e : acc.getDriver().findElement(By.id("village_map")).findElements(By.tagName("div"))) {
            if (e.getAttribute("class").substring(e.getAttribute("class").lastIndexOf("gid") + 1).contains("label"))
                continue;

            Integer buildingLevel = 0;
            Integer buildingId = -1;

            String buildingLink = "/build.php?id=" + String.valueOf(++j);

            try {
                buildingLevel = Integer.parseInt(e.getText());
            } catch (Exception ex) {
                buildingLevel = 0;
            }

            try {
                buildingId = Integer.parseInt(e.getAttribute("class").substring(e.getAttribute("class").lastIndexOf("gid") + 1).substring(2, 3));
            } catch (Exception ex) {
                buildingId = 0;
            }

            //System.out.println(getBuildingName(buildingId) + " | " + buildingLevel + " | " + buildingLink);

            buildings.add(getBuilding(--buildingId, this.getAcc().getServer() + buildingLink, buildingLevel));
        }
//</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="LoadVillageCentre">
        acc.getDriver().get(link);
        acc.getDriver().get(acc.getServer() + "/dorf2.php");

        for (WebElement e : acc.getDriver().findElements(By.tagName("area"))) {

            String buildingLink = e.getAttribute("href");

            Integer buildingLevel = 0;

            try {
                String[] ar = e.getAttribute("alt").split("[<>]");
                buildingLevel = Integer.parseInt(ar[2].replaceAll("\\D+", ""));
            } catch (Exception ex) {
            }

            String buildingName = e.getAttribute("alt").replaceAll("[0-9]", "");

            for (String s : acc.getBuildingNames()) {
                if (buildingName.toLowerCase().contains(s.toLowerCase())) {
                    buildingName = s;
                }
            }

            //System.out.println(buildingName + " | " + buildingLevel + " | " + buildingLink);


            buildings.add(getBuilding(acc.getBuildingId(buildingName), buildingLink, buildingLevel));
        }
//</editor-fold>‭-‭

    }

    public void loadResources() {
        if (!acc.isLoggedIn()) {
            acc.login(acc.getDriver());
        }

        //<editor-fold defaultstate="collapsed" desc="LoadResources">
        acc.getDriver().get(link);
        WebElement e = acc.getDriver().findElement(By.id("stockBar"));
        MaxWarehouse = Integer.parseInt(e.findElement(By.id("stockBarWarehouseWrapper")).getText().replaceAll("\\D+", ""));
        resources[0] = Integer.parseInt(e.findElement(By.id("stockBarResource1")).getText().replaceAll("\\D+", ""));
        resources[1] = Integer.parseInt(e.findElement(By.id("stockBarResource2")).getText().replaceAll("\\D+", ""));
        resources[2] = Integer.parseInt(e.findElement(By.id("stockBarResource3")).getText().replaceAll("\\D+", ""));
        MaxGranary = Integer.parseInt(e.findElement(By.id("stockBarGranaryWrapper")).getText().replaceAll("\\D+", ""));
        resources[3] = Integer.parseInt(e.findElement(By.id("stockBarResource4")).getText().replaceAll("\\D+", ""));
        resources[4] = Integer.parseInt(e.findElement(By.id("stockBarFreeCropWrapper")).getText().replaceAll("\\D+", ""));
        Resources.setClay(resources[1]);
        Resources.setWood(resources[0]);
        Resources.setIron(resources[2]);
        Resources.setCrop(resources[3]);
        Resources.setConsumer(resources[4]);
        Resources.reloadTotal();
        int n=0;
        for(WebElement ele:acc.getDriver().findElement(By.id("production")).findElements(By.className("num"))){
            if(n==0)
                production.setWood(Integer.parseInt(ele.getText().replaceAll("[\\)\\(]", "").replaceAll("[^0-9-]", "")));
            if(n==1)
                production.setClay(Integer.parseInt(ele.getText().replaceAll("[\\)\\(]", "").replaceAll("[^0-9-]", "")));
            if(n==2)
                production.setIron(Integer.parseInt(ele.getText().replaceAll("[\\)\\(]", "").replaceAll("[^0-9-]", "")));
            if(n==3)
                production.setCrop(Integer.parseInt(ele.getText().replaceAll("[\\)\\(]", "").replaceAll("[^0-9-]", "")));
            
            n++;
        }
//</editor-fold>

    }

    
    /** NOT RELAIBLE
     */
    public void loadArmy() {
        if (!acc.isLoggedIn()) {
            acc.login(acc.getDriver());
        }

        if (buildings == null) {
            loadBuildings();
        }

        acc.getDriver().get(link);
        acc.getDriver().get(acc.getServer() + "/build.php?tt=1&id=39");
        army = new ArrayList<>();

        WebElement table = acc.getDriver().findElement(By.className("troop_details"));
        java.util.List<WebElement> tableElements = table.findElements(By.tagName("tbody"));
        for (int i = 0; i < 10; i++) {
            int unitId = Integer.parseInt(tableElements.get(0).findElements(By.tagName("img")).get(i).getAttribute("class").replaceAll("\\D+", ""));
            int unitCout = Integer.parseInt(tableElements.get(1).findElements(By.tagName("td")).get(i).getText().replaceAll("\\D+", ""));
            //army.add(new Unit(unitId, unitCout));
        }

        if (Integer.parseInt(tableElements.get(1).findElements(By.tagName("td")).get(10).getText().replaceAll("\\D+", "")) == 1) {
            hasHero = true;
        } else {
            hasHero = false;
        }

        
    }

    public String buildingQueue() {
        acc.getDriver().get(link);
        WebElement e = acc.getDriver().findElement(By.className("contentContainer"));
        return null;
        // implenent
    }

    public void loadData() {

        if (!acc.isLoggedIn()) {
            acc.login(acc.getDriver());
        }
        loadCoordinates();
        loadBuildings();
        loadResources();
        //loadArmy();

    }

    public boolean constructBuildingByName(String buildingName) {
        int id = acc.getBuildingId(buildingName);

        Building tmpBuilding = getBuildingById(-1);

        if (tmpBuilding == null) return false;
        return ((Building_Site) tmpBuilding).build(id);
    }

    public boolean constructBuildingById(int id) {
        Building tmpBuilding = getBuildingById(-1);
        return ((Building_Site) tmpBuilding).build(id);
    }

    
    
    private Building getBuilding(int buildingId, String buildingLink, int buildingLevel) {
        Building building = null;

        String buildingName = acc.getBuildingName(buildingId);

        switch (buildingId) {
            case 0:
                building = new Woodcutter(buildingName, buildingLink, buildingLevel, buildingId, this);
                break;
            case 1:
                building = new Clay_Pit(buildingName, buildingLink, buildingLevel, buildingId, this);
                break;
            case 2:
                building = new Iron_Mine(buildingName, buildingLink, buildingLevel, buildingId, this);
                break;
            case 3:
                building = new Cropland(buildingName, buildingLink, buildingLevel, buildingId, this);
                break;
            case 4:
                building = new Sawmill(buildingName, buildingLink, buildingLevel, buildingId, this);
                break;
            case 5:
                building = new Brickyard(buildingName, buildingLink, buildingLevel, buildingId, this);
                break;
            case 6:
                building = new Iron_Foundry(buildingName, buildingLink, buildingLevel, buildingId, this);
                break;
            case 7:
                building = new Grain_Mill(buildingName, buildingLink, buildingLevel, buildingId, this);
                break;
            case 8:
                building = new Bakery(buildingName, buildingLink, buildingLevel, buildingId, this);
                break;
            case 9:
                building = new Warehouse(buildingName, buildingLink, buildingLevel, buildingId, this);
                break;
            case 10:
                building = new Granary(buildingName, buildingLink, buildingLevel, buildingId, this);
                break;
            case 11:
                building = new Smithy(buildingName, buildingLink, buildingLevel, buildingId, this);
                break;
            case 12:
                building = new Tournament_Square(buildingName, buildingLink, buildingLevel, buildingId, this);
                break;
            case 13:
                building = new Main_Building(buildingName, buildingLink, buildingLevel, buildingId, this);
                break;
            case 14:
                building = new Rally_Point(buildingName, buildingLink, buildingLevel, buildingId, this);
                break;
            case 15:
                building = new Marketplace(buildingName, buildingLink, buildingLevel, buildingId, this);
                break;
            case 16:
                building = new Embassy(buildingName, buildingLink, buildingLevel, buildingId, this);
                break;
            case 17:
                building = new Barracks(buildingName, buildingLink, buildingLevel, buildingId, this);
                break;
            case 18:
                building = new Stable(buildingName, buildingLink, buildingLevel, buildingId, this);
                break;
            case 19:
                building = new Workshop(buildingName, buildingLink, buildingLevel, buildingId, this);
                break;
            case 20:
                building = new Academy(buildingName, buildingLink, buildingLevel, buildingId, this);
                break;
            case 21:
                building = new Cranny(buildingName, buildingLink, buildingLevel, buildingId, this);
                break;
            case 22:
                building = new Town_Hall(buildingName, buildingLink, buildingLevel, buildingId, this);
                break;
            case 23:
                building = new Residence(buildingName, buildingLink, buildingLevel, buildingId, this);
                break;
            case 24:
                building = new Palace(buildingName, buildingLink, buildingLevel, buildingId, this);
                break;
            case 25:
                building = new Treasury(buildingName, buildingLink, buildingLevel, buildingId, this);
                break;
            case 26:
                building = new Trade_Office(buildingName, buildingLink, buildingLevel, buildingId, this);
                break;
            case 27:
                building = new Great_Barracks(buildingName, buildingLink, buildingLevel, buildingId, this);
                break;
            case 28:
                building = new Great_Stable(buildingName, buildingLink, buildingLevel, buildingId, this);
                break;
            case 29:
                building = new City_Wall(buildingName, buildingLink, buildingLevel, buildingId, this);
                break;
            case 30:
                building = new Earth_Wall(buildingName, buildingLink, buildingLevel, buildingId, this);
                break;
            case 31:
                building = new Palisade(buildingName, buildingLink, buildingLevel, buildingId, this);
                break;
            case 32:
                building = new Stonemason_Lodge(buildingName, buildingLink, buildingLevel, buildingId, this);
                break;
            case 33:
                building = new Brewery(buildingName, buildingLink, buildingLevel, buildingId, this);
                break;
            case 34:
                building = new Trapper(buildingName, buildingLink, buildingLevel, buildingId, this);
                break;
            case 35:
                building = new Hero_Mansion(buildingName, buildingLink, buildingLevel, buildingId, this);
                break;
            case 36:
                building = new Great_Warehouse(buildingName, buildingLink, buildingLevel, buildingId, this);
                break;
            case 37:
                building = new Great_Granary(buildingName, buildingLink, buildingLevel, buildingId, this);
                break;
            case 38:
                building = new Wonder_of_the_World(buildingName, buildingLink, buildingLevel, buildingId, this);
                break;
            case 39:
                building = new Horse_Drinking_Trough(buildingName, buildingLink, buildingLevel, buildingId, this);
                break;
            default:
                building = new Building_Site(buildingName, buildingLink, buildingLevel, -1, this);
                break;
        }
        return building;
    }

    public ArrayList<Building> getBuildings() {
        return buildings;
    }

    public String getName() {
        return name;
    }

    public Resources getResources() {
        return Resources;
    }

    public String getLink() {
        return link;
    }

    public Account getAcc() {
        return acc;
    }

    public Building getBuildingById(int id) {
        for (Building b : buildings) {
            if (b.getId() == id) {
                return b;
            }
        }
        return null;
    }

    public Point getCoor() {
        return coor;
    }

    public double getVillageX() {
        return coor.getX();
    }

    public double getVillageY() {
        return coor.getX();
    }

    public Integer getMaxWarehouse() {
        return MaxWarehouse;
    }

    public Integer getMaxGranary() {
        return MaxGranary;
    }
    
    public Production getProduction(){
        return production;
    }
    
    public void setName(String name){
        acc.getDriver().get(link);
        acc.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[3]/div/div[2]/div[3]/button"))).click();
        acc.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input"))).clear();
        acc.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input"))).sendKeys(name);
        acc.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[6]/button"))).click();
    }
    
}
