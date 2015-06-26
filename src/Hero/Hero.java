package Hero;

import Accounts.Account;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Created by Michal Brka on 12. 2. 2015.
 * nation nation1 !!!!
 */

public class Hero {
    private final String link, heroAdventures, heroAuction;
    private String production;
    public Account acc;
    public Set<Adventure> AdventureSet = new HashSet<Adventure>();
    public int health, level, experience, speed, productionPerHour, powerPoints, offPoints, deffPoints, productionPoints, availablePoints;
    public boolean heroHide, isAlive,regenerating;


    /**
     * @param link link to the hero profile
     * @param acc  account on which is the hero
     */
    public Hero(String link, Account acc) throws InterruptedException {
        heroAdventures = acc.getServer() + "/hero_adventure.php";
        heroAuction = acc.getServer() + "/hero_auction.php";
        regenerating=false;
        this.acc = acc;
        this.link = link;
        getHeroData();
    }

    /**
     * function which call all other functions for gethering information about hero (speed,points,adventures....)
     */
    public void getHeroData() throws InterruptedException {
        try{
        acc.getDriver().get(link);
        acc.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.className("openCloseSwitchBar"))).click();
        heroHide = acc.getDriver().findElement(By.id("attackBehaviourHide")).isSelected();
        loadHeroStatus();
        loadHeroProduction();
        loadHeroLevel();
        //reloadBasicFeatures();
        loadHeroPoints();
        loadAdventures();
        }catch(Exception e){
            e.printStackTrace();
            Thread.sleep(2000);
            this.getHeroData();
        }
    }

    /**
     * @return true if the hero is alive, false if the hero is dead
     */

    public boolean regenerateHero() {
        try {
            if (acc.getDriver().findElement(By.id("heroRegeneration")) != null) {
                acc.getDriver().findElement(By.id("heroRegeneration")).click();
                regenerating=true;
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    public boolean isAlive() {
        return isAlive;
    }

    /**
     * load actual hero status (alive or dead)
     */
    public void loadHeroStatus() {
        acc.getDriver().findElement(By.className("title")).click();
        try {
            isAlive = true;
            regenerating=false;
            if (acc.getDriver().findElement(By.className("heroStatusMessage")).findElement(By.className("heroStatus101")) != null) {
                isAlive = false;
            }
        } catch (Exception e) {
        }
        try {
            if ((acc.getDriver().findElement(By.className("heroStatusMessage")).findElement(By.className("heroStatus101Regenerate")) != null)) {
                isAlive = false;
            }
        } catch (Exception e) {
        }
    }

    /**
     * @return the number of available hero points (which can be set to strength,off,deff,production bonus)
     */
    public int getAvailablePoints() {
        return availablePoints;
    }

    /**
     * @return the number of points in hero production
     */
    public int getProductionPoints() {
        return productionPoints;
    }

    /**
     * @param numberOfPoints set the hero points to production base on numberofpoints arg
     */
    public void setProductionPoints(int numberOfPoints) {
        try {
            acc.getDriver().get(link);
            acc.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.className("title"))).click();
            for (int i = 0; i < numberOfPoints; i++) {
                acc.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[4]/td[6]/a"))).click();
            }
            acc.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("saveHeroAttributes"))).click();
        } catch (Exception e) {
        }
    }

    /**
     * @return the number of points in hero deff bonus
     */
    public int getDeffPoints() {
        return deffPoints;
    }

    /**
     * @param numberOfPoints set the hero points to deff bonus base on numberofpoints arg
     */
    public void setDeffPoints(int numberOfPoints) {
        try {
            acc.getDriver().get(link);
            acc.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.className("title"))).click();
            for (int i = 0; i < numberOfPoints; i++) {
                acc.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[3]/td[6]/a"))).click();
            }
            acc.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("saveHeroAttributes"))).click();
        } catch (Exception e) {
        }
    }

    /**
     * @return the number of points in hero off bonus
     */
    public int getOffPoints() {
        return offPoints;
    }

    /**
     * @param numberOfPoints set hero points to off bonus base on numberofpoints arg
     */
    public void setOffPoints(int numberOfPoints) {
        try {
            acc.getDriver().get(link);
            acc.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.className("title"))).click();
            for (int i = 0; i < numberOfPoints; i++) {
                acc.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[2]/td[6]/a"))).click();
            }
            acc.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("saveHeroAttributes"))).click();
        } catch (Exception e) {
        }
    }

    /**
     * @return the number of points in hero strength
     */
    public int getPowerPoints() {
        return powerPoints;
    }

    /**
     * @param numberOfPoints set hero points to strength base on numberofpoints arg
     */
    public void setPowerPoints(int numberOfPoints) {
        try {
            acc.getDriver().get(link);
            acc.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.className("title"))).click();
            for (int i = 0; i < numberOfPoints; i++) {
                acc.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[6]/a"))).click();
            }
            acc.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("saveHeroAttributes"))).click();
        } catch (Exception e) {
        }
    }

    /**
     * @return production per hour
     */
    public int getProductionPerHour() {
        return productionPerHour;
    }

    /**
     * @return hero speed
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * @return hero experience
     */
    public int getExperience() {
        return experience;
    }
    
    public boolean isRegenerating(){
        return regenerating;
    }

    /**
     * @return hero health
     */
    public int getHealth() {
        return health;
    }

    /**
     * @return set of all possible adventures
     */
    public Set<Adventure> getAdventureSet() {
        return AdventureSet;
    }

    /**
     * @return account on this hero
     */
    public Account getAcc() {
        return acc;
    }

    /**
     * @return name of the actual production (all,wood...)
     */
    public String getProduction() {
        return production;
    }

    /**
     * load hero points (available, points in strength,off,deff bonus,production
     */
    public void loadHeroPoints() {
        acc.getDriver().findElement(By.className("title")).click();
        String tmp = acc.getDriver().findElement(By.id("availablePoints")).getText().replaceAll("\\D+", "");
        availablePoints = 0;
        if (!tmp.isEmpty()) {
            availablePoints = Integer.parseInt(tmp);
        }
        tmp = acc.getDriver().findElement(By.id("attributepower")).findElement(By.className("value")).getText().replaceAll("\\D+", "");
        powerPoints = 0;
        if (!tmp.isEmpty()) {
            powerPoints = Integer.parseInt(tmp);
        }
        offPoints = 0;
        tmp = acc.getDriver().findElement(By.id("attributeoffBonus")).findElement(By.className("value")).getText().replaceAll("\\D+", "");
        if (!tmp.isEmpty()) {
            offPoints = Integer.parseInt(tmp);
        }
        deffPoints = 0;
        tmp = acc.getDriver().findElement(By.id("attributedefBonus")).findElement(By.className("value")).getText().replaceAll("\\D+", "");
        if (!tmp.isEmpty()) {
            deffPoints = Integer.parseInt(tmp);
        }
        productionPoints = 0;
        tmp = acc.getDriver().findElement(By.id("attributeproductionPoints")).findElement(By.className("value")).getText().replaceAll("\\D+", "");
        if (!tmp.isEmpty()) {
            productionPoints = Integer.parseInt(tmp);
        }
    }

    /**
     * load the current hero level
     */
    public void loadHeroLevel() {
        acc.getDriver().get(link);
        String tmp = "", tmp2 = "", s;
        s = acc.getDriver().findElement(By.className("titleInHeader")).getText();
        for (int i = s.length() - 1; i > 0; i--) {
            tmp += s.charAt(i);
            if (s.charAt(i - 1) == ' ') {
                break;
            }
        }
        for (int i = tmp.length() - 1; i >= 0; i--) {
            tmp2 += tmp.charAt(i);
        }
        level = Integer.parseInt(tmp2);
    }


    /**
     * load all adventrues to set AdventureSet
     */
    public void loadAdventures() {
        AdventureSet.clear();
        acc.getDriver().get(heroAdventures);
        for (WebElement e : acc.getDriver().findElements(By.className("goTo"))) {
            if (!e.getAttribute("id").isEmpty()) {
                Adventure tmp = new Adventure(acc);
                tmp.setLinkToAdventure(acc.getServer()+"/start_adventure.php?from=list&kid="+e.getAttribute("id").replaceAll("[^0-9]+", ""));
                AdventureSet.add(tmp);
            }
        }

        Iterator<Adventure> AdSetIterator = AdventureSet.iterator();
        for (WebElement e : acc.getDriver().findElements(By.className("timeLeft"))) {
            if (!e.getAttribute("id").isEmpty()) {
                AdSetIterator.next().setTimeLeft(e.getText());
            }
        }

        Iterator<Adventure> AdSetIterator2 = AdventureSet.iterator();
        for (WebElement e : acc.getDriver().findElements(By.className("moveTime"))) {
            if (!e.getAttribute("id").isEmpty()) {
                AdSetIterator2.next().setMoveTime(e.getText());
            }
        }

        Iterator<Adventure> AdSetIterator3 = AdventureSet.iterator();
        for (WebElement e : acc.getDriver().findElements(By.className("difficulty"))) {
            if (!e.getAttribute("id").isEmpty()) {
                AdSetIterator3.next().setDifficulty(e.getText());
            }
        }
        Iterator<Adventure> AdSetIterator4 = AdventureSet.iterator();
        for (WebElement e : acc.getDriver().findElements(By.className("coords"))) {
            AdSetIterator4.next().setCoordinates(e.getAttribute("href"));

        }
    }


    /**
     * @return hero level
     */
    public int getHeroLevel() {
        return this.level;
    }


    /**
     * @return true if hero will hide during attack/false if hero will stay and fight
     */
    public boolean getHeroHide() {
        return heroHide;
    }


    /**
     * reload basic features (speed,experience,health,production per hour
     */
    public void reloadBasicFeatures() {
        if (isAlive()) {
            String s = acc.getDriver().findElement(By.id("attributes")).findElement(By.className("element current powervalue")).getText();
            String s2 = "";
            for (int i = 0; i < s.length(); i++) {
                if (Character.isDigit(s.charAt(i))) {
                    s2 += s.charAt(i);
                }
            }
            health = Integer.parseInt(s2);
            s = acc.getDriver().findElement(By.id("attributes")).findElement(By.className("element current powervalue experience points")).getText();
            experience = Integer.parseInt(s);

            s = acc.getDriver().findElement(By.id("attributes")).findElement(By.className("speed tooltip")).getText();
            s2 = "";
            for (int i = 0; i < s.length(); i++) {
                if (Character.isDigit(s.charAt(i))) {
                    s2 += s.charAt(i);
                }
            }
            speed = Integer.parseInt(s2);

            s = acc.getDriver().findElement(By.id("attributes")).findElement(By.className("production tooltip")).getText();
            s2 = "";
            for (int i = 0; i < s.length() - 1; i++) {
                if (Character.isDigit(s.charAt(i))) {
                    s2 += s.charAt(i);
                }
            }
            productionPerHour = Integer.parseInt(s2);
        }
    }

    /**
     * set hero to stay with the troops
     */
    public void setHeroFight() {
        try {
            acc.getDriver().get(link);
            acc.getDriver().findElement(By.className("title")).click();
            if (acc.getDriver().findElement(By.id("attackBehaviourFight")).isEnabled()) {
                acc.getDriver().findElement(By.id("attackBehaviourFight")).click();
                if (acc.getDriver().findElement(By.id("saveHeroAttributes")).isEnabled()) {
                    acc.getDriver().findElement(By.id("saveHeroAttributes")).click();
                }
            }
        } catch (Exception e) {
        }
    }


    /**
     * set hero to hide during attack
     */
    public void setHeroHide() {
        try {
            acc.getDriver().get(link);
            acc.getDriver().findElement(By.className("title")).click();
            if (acc.getDriver().findElement(By.id("attackBehaviourHide")).isEnabled()) {
                acc.getDriver().findElement(By.id("attackBehaviourHide")).click();
                if (acc.getDriver().findElement(By.id("saveHeroAttributes")).isEnabled()) {
                    acc.getDriver().findElement(By.id("saveHeroAttributes")).click();
                }
            }
        } catch (Exception e) {
        }
    }

    /**
     * reload heroProduction (clay,wood...)
     */
    public void loadHeroProduction() {
        acc.getDriver().findElement(By.className("title")).click();
        if (acc.getDriver().findElement(By.id("resourceHero0")).isSelected()) {
            production = "All";
        } else if (acc.getDriver().findElement(By.id("resourceHero4")).isSelected()) {
            production = "Crop";
        } else if (acc.getDriver().findElement(By.id("resourceHero3")).isSelected()) {
            production = "Iron";
        } else if (acc.getDriver().findElement(By.id("resourceHero2")).isSelected()) {
            production = "Clay";
        } else if (acc.getDriver().findElement(By.id("resourceHero1")).isSelected()) {
            production = "Wood";
        }
    }


    /**
     * @param s Set production to String S (clay,wood...)
     */
    public void setHeroProduction(String s) {
        try {
            acc.getDriver().get(link);
            acc.getDriver().findElement(By.className("title")).click();
            if (s.equals("All")) {
                acc.getDriver().findElement(By.id("resourceHero0")).click();
            } else if (s.equals("Wood")) {
                acc.getDriver().findElement(By.id("resourceHero1")).click();
            } else if (s.equals("Clay")) {
                acc.getDriver().findElement(By.id("resourceHero2")).click();
            } else if (s.equals("Iron")) {
                acc.getDriver().findElement(By.id("resourceHero3")).click();
            } else if (s.equals("Crop")) {
                acc.getDriver().findElement(By.id("resourceHero4")).click();
            }
            acc.getDriver().findElement(By.id("saveHeroAttributes")).click();
        } catch (Exception e) {
        }

    }


};
