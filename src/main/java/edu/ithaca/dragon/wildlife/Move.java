package edu.ithaca.dragon.wildlife;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Move {
    private String title;
    private int damage;
    private int amountLeft;

    @JsonCreator
    public Move(@JsonProperty("title") String title, @JsonProperty("damage") int dmg, @JsonProperty("amountLeft") int amt) {
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
