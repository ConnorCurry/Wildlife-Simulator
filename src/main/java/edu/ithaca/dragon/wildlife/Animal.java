package edu.ithaca.dragon.wildlife;
import java.util.ArrayList;
import java.util.HashMap;


public class Animal {
    private Move[] moves = new Move[4];
    private HashMap<Integer, ArrayList<String>> learnableMoves = new HashMap<>();//just include move names, must call cross-check function to get move info
    private int maxHP; // HP = Health Points
    private int currentHP;
    private int ad; //ad = Attack Damage
    private String name;
    private int level;
    private int ExP; //Every three victories a level up should occur

    //Constructor
    public Animal(int hp,int chp, int ad) {
        this.maxHP = hp;
        this.currentHP = chp;
        this.ad = ad;
        this.level = 1;
        this.ExP = 0;
    }

    public Animal(int hp,int chp, int ad, String name) {
        this.maxHP = hp;
        this.currentHP = chp;
        this.ad = ad;
        this.level = 1;
        this.name = name;
        this.ExP = 0;
    }

    public Animal(int hp,int chp, int ad, String name, HashMap<Integer, ArrayList<String>> possibleMoves) {
        this.maxHP = hp;
        this.currentHP = chp;
        this.ad = ad;
        this.level = 1;
        this.name = name;
        this.ExP = 0;
        this.learnableMoves = possibleMoves;
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
    public void moveIs(String Move){
        /*
        This function will check what a move is from a string and print it out since the hashmap in these properties just have the move names and level requirements
        */
    }
    /**
     * Adds one to experience points, prints that fact
     * If after addition exp equals 3, reset exp and initiate level up
     */
    public void addExp(){
        this.ExP += 1;
        System.out.println("One ExP Gained!");
        if (this.ExP == 3){
            this.ExP = 0;
            this.levelUp();
        }
    }
    /**
     * adds one to level (up to a max of 10)
     * increases stats based off level up (5% health boost, 2% attack damage boost (turns into int and loses decimals)) 
     * learns new move if able (will add later, once file scan in is ready, will make master list of animals and cross check moves)
     */
    public void levelUp(){
        if(this.level < 10){
            this.level += 1;
            double dHP = this.maxHP * 1.05;
            this.maxHP = (int)dHP;
            double dAd = this.ad * 1.02;
            this.ad = (int)dAd;
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

    public int getExp(){
        return ExP;
    }
    
}
