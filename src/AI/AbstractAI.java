/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AI;

import Accounts.Account;
import Buildings.Building;
import Tribes.Village;

/**
 * @author David Zaludek
 */
public abstract class AbstractAI implements Runnable {

    public final Account account;

    AbstractAI(Account acc) {
        this.account = acc;
    }

    int upgradeBuildingById(int id, Village v) throws InterruptedException {
        int time=0;
        account.loadData();
        v.loadData();
        for (Building b : v.getBuildings()) {
            if (b.getId() == id) {
                time=b.upgradeTime();
                if(b.upgrade())
                return time;
            }
        }
        return 0;
    }

    int upgradeBuildingByIdLowestLevel(int id,Village v) throws InterruptedException {
        account.loadData();
        Building low=null;
        boolean first = true;
        boolean done;
        int time=0;
            for (Building b : v.getBuildings()) {
                if (b.getId() == id) {
                    if (first) {
                        low = b;
                        first=false;
                    } else if (low.getLevel() > b.getLevel()) {
                        low = b;
                    }
                }
            }
            System.out.println("befor time");
        time=low.upgradeTime();
        System.out.println("after time");
        if(low.getId()<4){
            System.out.println("before coonstruct");
            done=low.construct();
            System.out.println("after coonstruct");
            if(done){
            return time;
            }else{
                return 0;
            }
        }
        
        System.out.println("before upgrading");
        if(low.upgrade()){
            System.out.println("donenone");
        return time;
        }
        return 0;
    }
    
    
    
    int upgradeBuildingByIdAndLevel(int id,int level,Village v) throws InterruptedException {
        account.loadData();
        boolean first = true;
        int time=0;
            v.loadData();
            for (Building b : v.getBuildings()) {
                if (b.getId() == id&& b.getLevel()==level) {
                    time=b.upgradeTime();
                    if(b.upgrade())
                    return time;
                }
            }
        return time;
    }
    
    

    public abstract void start();

}
