package edu.ithaca.dragon.wildlife;



public class StatusEffect {
    
    private String label;
    private int life;
    private int dmg;


    public StatusEffect(String label, int duration,int damage){

        this.label = label;
        this.life = duration;
        this.dmg = damage;
    }

    public void decrementLife() {
        this.life--;
    }

    //Getters

    public int getLife() {
        return(this.life);
    }

    public int getDamage() {
        return(this.dmg);
    }

    
}