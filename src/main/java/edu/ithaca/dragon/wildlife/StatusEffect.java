package edu.ithaca.dragon.wildlife;



public class StatusEffect {
    
    private Animal host;
    private String label;
    private int life;
    private int dmg;


    public StatusEffect(Animal host, String label, int duration,int damage){
        if(host != null) {
            this.host = host;
        } else {
            this.host = null;
        }
        
        this.label = label;
        this.life = duration;
        this.dmg = damage;
    }

    public void applyEffect() {
        this.host.receiveDamage(this.dmg);
        this.life--;
    }


    public void setHost(Animal host) {
        this.host = host;
    }
}