package edu.ithaca.dragon.wildlife;

public class GetStatusEffect {

    
    public StatusEffect getPoison() {
        StatusEffect poison = new StatusEffect(null, "poison", 6 , 5);
        return(poison);
    }

    public StatusEffect getBleed() {
        StatusEffect bleed = new StatusEffect(null, "bleed", 9 , 3);
        return(bleed);
    }

    public StatusEffect getBurn() {
        StatusEffect burn = new StatusEffect(null, "burn", 4 , 7);
        return(burn);
    }
}
