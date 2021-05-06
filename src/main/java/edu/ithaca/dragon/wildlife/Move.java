package edu.ithaca.dragon.wildlife;

import java.io.File;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Move {
    private String title;
    private int damage;
    private int amountLeft;
    private String strEffect;
    private StatusEffect effect;
    private String statusString;

    @JsonCreator
    //Constructor with effect (effect can be null)
    public Move(@JsonProperty("title") String title, @JsonProperty("damage") int dmg, @JsonProperty("amountLeft") int amt, @JsonProperty("statusString") String effect) {
        this.title = title;
        this.damage = dmg;
        this.amountLeft = amt;
        this.statusString = effect;

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
        this.title = title;
        this.damage = dmg;
        this.amountLeft = amt;

    }


    public void decrementAmountLeft() {
        this.amountLeft -= 1;
    }


    //Getters
    public int getDamage() {
        return(this.damage);
    }

    public int getAmountLeft() {
        return(this.amountLeft);
    }

    public String getTitle(){
        return(title);
    }
    public StatusEffect getEffect() {
        return(this.effect);
    }

    public String getStatusString() {
        return statusString;
    }

    //Json

    public static Move readMoveInfo() {
        ObjectMapper mapper = new ObjectMapper();

        Move m = null;
        try {
            m = mapper.readValue(new File("src/main/java/edu/ithaca/dragon/wildlife/Moves.csv"), new TypeReference<Move>(){});
        }
        catch(Exception e) {
            // System.out.println("Error Reading:\n" + e);
        }
        return m;
    }
}
