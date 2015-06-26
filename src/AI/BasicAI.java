package AI;

import Accounts.Account;
import Buildings.Building;
import Tribes.Village;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by davidzaludek on 15/02/15.
 */
public class BasicAI extends AbstractAI {


    public BasicAI(Account account) {
        super(account);
    }

    static boolean hasEnought(int[] l, int[] p) {
        try {
            for (int i = 0; i < 5; i++) {
                if (!(l[i] > p[i])) {
                    return false;
                }
            }
        } catch (Exception e) {
            return false;

        }

        return true;

    }

    public void start() {



        Building k = null;
        
        int time = 600;
        int id=0;
        while(true){
            try {
                account.loadData();
            } catch (InterruptedException ex) {
                Logger.getLogger(BasicAI.class.getName()).log(Level.SEVERE, null, ex);
            }
        ArrayList<Building> buildings;
        Village v = account.getVillages().get(0);
           buildings=v.getBuildings();
       
           System.out.println("buildings size:"+buildings.size());
           for(Building b:buildings){
               System.out.println(b.getName()+" "+b.getId()+" "+b.getLink());
               
           }
            try {
                System.out.println("time!!!!:" +this.upgradeBuildingById(16, v));
            } catch (InterruptedException ex) {
                Logger.getLogger(BasicAI.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("drevo:"+v.getResources().getWood()+" hlina:"+v.getResources().getClay()+" crop:"+v.getResources().getCrop()+" zelezo:"+v.getResources().getIron());
        /*if (v.getBuildingById(id%40) == null) {
            v.constructBuildingById(id%40);
        }else{*/
           // System.out.println("!!!link:"+this.upgradeBuildingByIdAndLevel(3,0,v));
        //
        id++;
        if(id>555555){break;}
        
            System.out.println("id="+id);
        }
    }




    @Override
    public void run() {

    }
}
