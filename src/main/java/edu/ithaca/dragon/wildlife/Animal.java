package edu.ithaca.dragon.wildlife;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Animal {
    private Move[] moves = new Move[4];
    private int maxHP; // HP = Health Points
    private int currentHP;
    private int ad; //ad = Attack Damage
    private String name;
    private int level;

    //Constructor
    public Animal(int hp,int chp, int ad) {
        this.maxHP = hp;
        this.currentHP = chp;
        this.ad = ad;
        this.level = 1;
    }

    @JsonCreator
    public Animal(@JsonProperty("maxHP") int hp, @JsonProperty("currentHP") int chp, @JsonProperty("ad") int ad, @JsonProperty("name") String name, @JsonProperty("level") int level) {
        this.maxHP = hp;
        this.currentHP = chp;
        this.ad = ad;
        this.level = level;
        this.name = name;
    }

    public Animal(int hp, int chp, int ad, String name) {
        this.maxHP = hp;
        this.currentHP = chp;
        this.ad = ad;
        this.level = 1;
        this.name = name;
    }

    public void receiveDamage(int dmg) {
        this.currentHP -= dmg;
    }

    public void healMove(int healAmt){
        if(currentHP >= maxHP){
            System.out.println("You are already full health!");
        }
        else{
            currentHP+=healAmt;
        }
    }


    //getters
    public int getAD() {
        return(this.ad);
    }

    public Move[] getMoves() {
        return(this.moves);
    }

    public int getCurrentHP() {
        return(this.currentHP);
    }
    public int getMaxHP(){
        return(this.maxHP);
    }
    public int getLevel(){
        return(this.level);
    }

    //setters
    public void setMoves(Move[] ms) {
        this.moves = ms;
    }

    public void setLevel(int levelToBe){
        this.level = levelToBe;
    }

    public void forgetMove(int moveIndex){
        moves[moveIndex] = null;
    }

    public String getName(){
        return name;
    }
}
