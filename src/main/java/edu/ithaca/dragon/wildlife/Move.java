package edu.ithaca.dragon.wildlife;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Move {
    private String title;
    private int damage;
    private int amountLeft;
    private StatusEffect effect;

<<<<<<< HEAD
    @JsonCreator
    public Move(@JsonProperty("title") String title, @JsonProperty("damage") int dmg, @JsonProperty("amountLeft") int amt) {
=======
    //Constructor with effect (effect can be null)
    public Move(String title, int dmg, int amt, String effect) {
        this.title = title;
        this.damage = dmg;
        this.amountLeft = amt;

        if(effect != null) {
            GetStatusEffect gse = new GetStatusEffect();
            if(effect.toLowerCase().equals("poison")){
                this.effect = gse.getPoison();
            } else if(effect.toLowerCase().equals("bleed")){
                this.effect = gse.getBleed();
            } else {
                this.effect = gse.getBurn();
            }
        } else {
            effect = null;
        }
    }
    //Constructor without effect
    public Move(String title, int dmg, int amt) {
>>>>>>> master
        this.title = title;
        this.damage = dmg;
        this.amountLeft = amt;

    }

    public void decrementAmountLeft() {
        this.amountLeft -= 1;
    }

    public int getDamage() {
        return(this.damage);
    }

    public int getAmountLeft() {
        return(this.amountLeft);
    }

    public String getTitle(){
        return title;
    }
}
