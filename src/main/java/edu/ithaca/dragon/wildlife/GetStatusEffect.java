package edu.ithaca.dragon.wildlife;

public class GetStatusEffect {
    
    public StatusEffect getPoison(Animal host) {
        StatusEffect poison = new StatusEffect(host, "poison", 6 , 5);
        return(poison);
    }

    public StatusEffect getBleed(Animal host) {
        StatusEffect bleed = new StatusEffect(host, "bleed", 8 , 4);
        return(bleed);
    }

    public StatusEffect getBurn(Animal host) {
        StatusEffect burn = new StatusEffect(host, "burn", 8 , 4);
        return(burn);
    }
}
