/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tutorial;

/**
 *
 * @author michalbrka
 */
public class Quest {
    private String category;
    private int id;
    private boolean ready;
    
    public Quest(String category,int id,boolean ready){
    this.category=category;
    this.id=id;
    this.ready=ready;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }
    
    
}
