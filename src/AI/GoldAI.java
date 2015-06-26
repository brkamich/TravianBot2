package AI;

import Accounts.Account;
import Buildings.Building;
import Buildings.Types.Residence;
import java.awt.Point;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Created by davidzaludek on 23/02/15.
 */
public class GoldAI extends AbstractAI {

    int[] BuildOrder = {1, 1, 0, 2, 1, 0, 3, 3,};
    private Scanner scaner;
    private final boolean buildplan;
    private String file;

    public GoldAI(Account acc) {
        super(acc);
        buildplan = false;
    }

    public GoldAI(Account acc, String file) {
        super(acc);
        buildplan = true;
        this.file = file;
    }

    @Override
    public void start() {
        //int time;
        //int tmpbef;
        //boolean ok=true;
        //boolean quest;
        account.getVillages().get(0).setName("PRO_SKILLER");
        if (buildplan) {

        } else {
            while (!this.allFive()) {
                
                try {
                    heroManager();
                    Thread.sleep(3000);
                    productionAllManager();
                    
                    Thread.sleep(3000);
                    heroManager();
                    
                    Thread.sleep(3000);
                    productionLowestManager();
                    
                    Thread.sleep(3000);
                    heroManager();
                    
                    Thread.sleep(3000);
                    warhouseManager();
                    
                    Thread.sleep(3000);
                    granaryManager();
                    
                    Thread.sleep(3000);
                    heroManager();
                    
                    Thread.sleep(3000);
                    mainbuildingManager();
                    
                    Thread.sleep(3000);
                    heroManager();
                    
                    Thread.sleep(3000);
                    questManager(((account.getVillages().get(0).getMaxWarehouse()+account.getVillages().get(0).getMaxGranary())/2)/(account.getVillages().get(0).getResources().getTotal()/4));
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(GoldAI.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            while(true){
                try{
                heroManager();
                Thread.sleep(2500);
                residenceManager();
                }catch(Exception e){
                    
                }
                if(account.getVillages().get(0).getBuildingById(23).getLevel()==10){
                    break;
                }
            }
            ((Residence)this.account.getVillages().get(0).getBuildingById(23)).trainSettler();
            ((Residence)this.account.getVillages().get(0).getBuildingById(23)).trainSettler();
            ((Residence)this.account.getVillages().get(0).getBuildingById(23)).trainSettler();
            try {
                Thread.sleep(12010*1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(GoldAI.class.getName()).log(Level.SEVERE, null, ex);
            }
            account.getDriver().get(account.getMap().getSettlerLink(account.getVillages().get(0).getCoor()));
            account.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("btn_ok"))).click();
        }
        System.out.println("DONE");
                

    }
    
    private boolean allFive(){
        for(Building b:this.account.getVillages().get(0).getBuildings()){
            if ((b.getId()>=0&&b.getId()<=3)&&(b.getLevel()<5)){
                return false;
            }
        }
        return true;
    }
    
    private void productionAllManager() throws InterruptedException{
        int time;
        for (int i = 0; i < 4; i++) {
                    time = this.upgradeBuildingByIdLowestLevel(i, this.account.getVillages().get(0));
                    if (time == 0) {
                        System.out.println("ERROR TIME");
                        break;
                    }
                    System.out.println("upgrading: "+account.getVillages().get(0).getBuildingById(i).getName() +" lvl: "+account.getVillages().get(0).getBuildingById(i).getLevel());
                    
                    try {
                        System.out.println("wating: "+time+"s");
                        Thread.sleep(time * 1000);
                    } catch (InterruptedException ex) {
                        System.out.println("THREAD ERRR");
                        Logger.getLogger(GoldAI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
    }
    
    private void productionLowestManager() throws InterruptedException{
        int time;
        System.out.println(account.getVillages().get(0).getResources().ratio()+":"+(account.getVillages().get(0).getBuildingById(account.getVillages().get(0).getResources().lowestResources()).getLevel()+1)+"=="+account.getVillages().get(0).getResources().ratio()/(account.getVillages().get(0).getBuildingById(account.getVillages().get(0).getResources().lowestResources()).getLevel()+1));
                
                for (int i = 0; i < account.getVillages().get(0).getResources().ratio()/(account.getVillages().get(0).getBuildingById(account.getVillages().get(0).getResources().lowestResources()).getLevel()+1); i++) {

                        System.out.println("lowest:"+account.getVillages().get(0).getResources().lowestResources());
                    time = this.upgradeBuildingByIdLowestLevel(account.getVillages().get(0).getResources().lowestResources(), this.account.getVillages().get(0));
                    if (time == 0) {
                        System.out.println("ERROR TIME");
                        break;
                    }
                    System.out.println("(low resources) upgrading: "+account.getVillages().get(0).getBuildingById(account.getVillages().get(0).getResources().lowestResources()).getName() +" lvl: "+account.getVillages().get(0).getBuildingById(account.getVillages().get(0).getResources().lowestResources()).getLevel());
                    
                    try {
                        System.out.println("wating: "+time+"s");
                        Thread.sleep(time * 1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(GoldAI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
    }
    
    private void questManager(int num){
        for(int i=0;i<num;i++){
        if(account.getTutorial().numberOfQuests()>0){
                    boolean quest=account.getTutorial().takeQuest();
                    System.out.println("Taking quest, sucess: "+quest);
                }
        }
    }
    
    private void heroManager(){
        this.account.getHero().getAdventureSet().iterator().next().sentHero();
                if (this.account.getHero().getAvailablePoints() > 0) {
                    account.getHero().setPowerPoints(2);
            try {
                Thread.sleep(2500);
            } catch (InterruptedException ex) {
                Logger.getLogger(GoldAI.class.getName()).log(Level.SEVERE, null, ex);
            }
                    account.getHero().setProductionPoints(2);
                    System.out.println("HERO NEW LEVEL, setting points.");
                }
                if(!this.account.getHero().isAlive()&&!this.account.getHero().isRegenerating()){
                    while(!this.account.getHero().isAlive()){
                        if(this.account.getHero().regenerateHero()){
                            System.out.println("HERO DEATH, regenerating");
                            break;
                        }
                        if(!account.isLoggedIn()){
                        account.login(account.getDriver());
                    }
                    }
                }
    }
    
    private void mainbuildingManager() throws InterruptedException{
        boolean ok=false;
          int      time=0;
                if (this.account.getVillages().get(0).getBuildingById(13) == null) {
                    while(!ok){
                    ok=this.account.getVillages().get(0).constructBuildingById(13);
                    if(!account.isLoggedIn()){
                        account.login(account.getDriver());
                    }
                    }
                    System.out.println("constructing: mainbuilding lvl: 0");
                    account.loadData();
            try {
                Thread.sleep(this.account.getVillages().get(0).getBuildingById(13).upgradeTime()*1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(GoldAI.class.getName()).log(Level.SEVERE, null, ex);
            }

                } else if ((this.account.getVillages().get(0).getBuildingById(13).getLevel() < this.account.getVillages().get(0).getBuildingById(0).getLevel()+this.account.getVillages().get(0).getBuildingById(0).getLevel())&&(this.account.getVillages().get(0).getBuildingById(13).getLevel()<=10)) {
                    while(time==0){
                        time=this.upgradeBuildingById(13, this.account.getVillages().get(0));
                        if(!account.isLoggedIn()){
                        account.login(account.getDriver());
                    }
                    }
                    System.out.println("upgrading: "+account.getVillages().get(0).getBuildingById(13).getName() +" lvl: "+account.getVillages().get(0).getBuildingById(13).getLevel());
            try {
                Thread.sleep(time*1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(GoldAI.class.getName()).log(Level.SEVERE, null, ex);
            }
                }
    }
    
    private void residenceManager() throws InterruptedException{
        int time=0;
                boolean ok=false;
                if (this.account.getVillages().get(0).getBuildingById(23) == null) {
                    while(!ok){
                        ok=this.account.getVillages().get(0).constructBuildingById(23);
                        if(!account.isLoggedIn()){
                        account.login(account.getDriver());
                    }
                    System.out.println("constructing: residence lvl: 0");
                        try {
                            Thread.sleep(this.account.getVillages().get(0).getBuildingById(23).upgradeTime()*1000);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(GoldAI.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } else {
                    while(time==0){
                        time=this.upgradeBuildingById(23, this.account.getVillages().get(0));
                        if(!account.isLoggedIn()){
                        account.login(account.getDriver());
                    }
                    }
                    System.out.println("upgrading: "+account.getVillages().get(0).getBuildingById(23).getName() +" lvl: "+account.getVillages().get(0).getBuildingById(9).getLevel());
            try {
                Thread.sleep(time*1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(GoldAI.class.getName()).log(Level.SEVERE, null, ex);
            }
                }
                account.loadData();
    }
    
    private void warhouseManager() throws InterruptedException{
        int time=0;
                boolean ok=false;
                if (this.account.getVillages().get(0).getBuildingById(9) == null) {
                    while(!ok){
                        ok=this.account.getVillages().get(0).constructBuildingById(9);
                        if(!account.isLoggedIn()){
                        account.login(account.getDriver());
                    }
                    System.out.println("constructing: warhouse lvl: 0");
                    account.loadData();
                        try {
                            Thread.sleep(this.account.getVillages().get(0).getBuildingById(9).upgradeTime()*1000);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(GoldAI.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } else if ((this.account.getVillages().get(0).getBuildingById(9).getLevel() < this.account.getVillages().get(0).getBuildingById(0).getLevel() * 1.3)&&(this.account.getVillages().get(0).getBuildingById(9).getLevel()<=8)) {
                    while(time==0){
                        time=this.upgradeBuildingById(9, this.account.getVillages().get(0));
                        if(!account.isLoggedIn()){
                        account.login(account.getDriver());
                    }
                    }
                    System.out.println("upgrading: "+account.getVillages().get(0).getBuildingById(9).getName() +" lvl: "+account.getVillages().get(0).getBuildingById(9).getLevel());
            try {
                Thread.sleep(time*1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(GoldAI.class.getName()).log(Level.SEVERE, null, ex);
            }
                }
    }
    
    private void granaryManager() throws InterruptedException{
        boolean ok=false;
               int  time=0;
                if (this.account.getVillages().get(0).getBuildingById(10) == null) {
                    while(!ok){
                    ok=this.account.getVillages().get(0).constructBuildingById(10);
                    if(!account.isLoggedIn()){
                        account.login(account.getDriver());
                    }
                    }
                    System.out.println("constructing: granary lvl: 0");
                    account.loadData();
            try {
                Thread.sleep(this.account.getVillages().get(0).getBuildingById(10).upgradeTime()*1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(GoldAI.class.getName()).log(Level.SEVERE, null, ex);
            }

                } else if ((this.account.getVillages().get(0).getBuildingById(10).getLevel() < this.account.getVillages().get(0).getBuildingById(0).getLevel() * 1.3)&&(this.account.getVillages().get(0).getBuildingById(10).getLevel()<=8)) {
                    while(time==0){
                        time=this.upgradeBuildingById(10, this.account.getVillages().get(0));
                        if(!account.isLoggedIn()){
                        account.login(account.getDriver());
                    }
                    }
                    System.out.println("upgrading: "+account.getVillages().get(0).getBuildingById(10).getName() +" lvl: "+account.getVillages().get(0).getBuildingById(10).getLevel());
            try {
                Thread.sleep(time*1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(GoldAI.class.getName()).log(Level.SEVERE, null, ex);
            }
                }
    }

    @Override
    public void run() {

    }
}
