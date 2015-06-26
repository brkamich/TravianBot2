package Hero;

import Accounts.Account;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by Michal Brka on 15. 2. 2015.
 */
public class Adventure {
    private String linkToAdventure, timeLeft, moveTime, difficulty, coordinates;
    private Account acc;

    public Adventure(Account acc) {
        this.acc = acc;
    }


    /**
     * will sent hero to the adventure
     *
     * @return true if OK, false if not
     */
    public boolean sentHero() {
        try {
            System.out.println("ink:"+this.linkToAdventure);
            acc.getDriver().get(linkToAdventure);
            System.out.println("sending hero in adventrue!!!");
            Thread.sleep(4000);
            if (acc.getDriver().findElement(By.id("start")).isEnabled()) {
                acc.getDriver().findElement(By.id("start")).click();
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    public String getLinkToAdventure() {
        return linkToAdventure;
    }

    public void setLinkToAdventure(String linkToAdventure) {
        this.linkToAdventure = linkToAdventure;
    }

    public void setTimeLeft(String timeLeft) {
        this.timeLeft = timeLeft;
    }

    public void setMoveTime(String moveTime) {
        this.moveTime = moveTime;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public String getTimeLeft() {
        return timeLeft;
    }

    public String getMoveTime() {
        return moveTime;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getCoordinates() {
        return coordinates;
    }


}
