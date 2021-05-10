package edu.ithaca.dragon.wildlife;
import java.util.ArrayList;
import java.util.HashMap;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Animal {
    private Move[] moves = new Move[4];
    private HashMap<Integer, ArrayList<String>> learnableMoves = new HashMap<>();//just include move names, must call cross-check function to get move info
    private int maxHP; // HP = Health Points
    private int currentHP;
    private int ad; //ad = Attack Damage
    private String name;
    private int level;
    private StatusEffect currentEffect;
    private int exP; //Every three victories a level up should occur

    //Constructor
    public Animal(int hp,int chp, int ad) {
        this.maxHP = hp;
        this.currentHP = chp;
        this.ad = ad;
        this.level = 1;
        this.currentEffect = null;
        this.exP = 0;
    }

    @JsonCreator
    public Animal(@JsonProperty("maxHP") int hp, 
    @JsonProperty("currentHP") int chp, 
    @JsonProperty("ad") int ad, 
    @JsonProperty("name") String name, 
    @JsonProperty("level") int level,
    @JsonProperty("exP") int exP,
    @JsonProperty("moves") Move[] moves) {
        this.maxHP = hp;
        this.currentHP = chp;
        this.ad = ad;
        this.level = level;
        this.name = name;
        this.exP = 0;
        this.currentEffect = null;
        this.moves = moves;
        this.learnableMoves = WildlifeSimulator.animalLearnSet(name);
    }

    public Animal(int hp, int chp, int ad, String name) {
        this.maxHP = hp;
        this.currentHP = chp;
        this.ad = ad;
        this.level = 1;
        this.name = name;
        this.currentEffect = null;
        this.exP = 0;
    }

    public Animal(int hp,int chp, int ad, String name, HashMap<Integer, ArrayList<String>> possibleMoves) {
        this.maxHP = hp;
        this.currentHP = chp;
        this.ad = ad;
        this.level = 1;
        this.name = name;
        this.exP = 0;
        this.learnableMoves = possibleMoves;
        for(int i = 0; i < possibleMoves.get(1).size(); i++){
            this.learnMoveCon(possibleMoves.get(1).get(i));
        }
    }

    public Animal(int hp, int chp, int ad, String name, int level, int exP) {
        this.maxHP = hp;
        this.currentHP = hp;
        this.ad = ad;
        this.name = name;
        this.level = level;
        this.exP = exP;
        this.learnableMoves = WildlifeSimulator.animalLearnSet(name);
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

    public void healMax() {
        currentHP = maxHP;
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
        this.exP += 1;
        System.out.println("One ExP Gained!");
        if (this.exP == 3){
            this.exP = 0;
            this.levelUp();
        }
    }
    /**
     * adds one to level (up to a max of 10)
     * increases stats based off level up (5% health boost, 2% attack damage boost (turns into int and loses decimals)) 
     * Prints if any moves were unlocked and able to be learned
     */
    public void levelUp(){
        if(this.level < 10){
            this.level += 1;
            double dHP = this.maxHP * 1.05;
            this.maxHP = (int)dHP;
            double dAd = this.ad * 1.02;
            this.ad = (int)dAd;
            if(this.learnableMoves.containsKey(this.level)){
                ArrayList<String> mArray = this.learnableMoves.get(this.level);
                System.out.println("Newly unlocked Moves Include");
                for(int i=0; i < mArray.size(); i++){
                    System.out.println(mArray.get(i));
                }
            }
        }
    }
    /*
        can only learn moves that have been unlocked
        can only know up to 4 moves
        can't learn a previously known move
    */
    public boolean learnMove(String moveToLearn){
        int size =0;
        for (int i=0; i < 4; i++){
            if(moves[i] != null){
                size++;
            }
        }
        if(size < 4){
            HashMap<String, Move> exisistingMoves = WildlifeSimulator.getMoveList();
            if (exisistingMoves.containsKey(moveToLearn)){
                for (int i = 1; i <= this.level; i++){
                    if(this.learnableMoves.containsKey(i)){
                        boolean ifEq = false;
                        for (int j = 0; j < this.learnableMoves.get(i).size(); j++){
                            if(learnableMoves.get(i).get(j).equals(moveToLearn)){
                                moves[size] = exisistingMoves.get(moveToLearn);
                                System.out.println(moveToLearn + " learned!");
                                ifEq = true;
                                return ifEq;
                            }
                        }
                        if(!ifEq){
                            System.out.println("Animal has not unlocked this move yet!");
                            return false;
                        }
                    }
                    else{
                        System.out.println("Animal has not unlocked this move!");
                        return false;
                    }
                }
            }
            else{
                System.out.println("Move doesn't exist!");
                return false;
            }

        } else{
            System.out.println("Already know the maximum of 4 moves!");
            return false;
        }
        return false;
    }
    /*
        Removes print statement for initial setup (level 1 moves)
    */
    public boolean learnMoveCon(String moveToLearn){
        int size =0;
        for (int i=0; i < 4; i++){
            if(moves[i] != null){
                size++;
            }
        }
        if(size < 4){
            HashMap<String, Move> exisistingMoves = WildlifeSimulator.getMoveList();
            if (exisistingMoves.containsKey(moveToLearn)){
                for (int i = 1; i <= this.level; i++){
                    if(this.learnableMoves.containsKey(i)){
                        boolean ifEq = false;
                        for (int j = 0; j < this.learnableMoves.get(i).size(); j++){
                            if(learnableMoves.get(i).get(j).equals(moveToLearn)){
                                moves[size] = exisistingMoves.get(moveToLearn);
                                ifEq = true;
                                return ifEq;
                            }
                        }
                        if(!ifEq){
                            System.out.println("Animal has not unlocked this move yet!");
                            return false;
                        }
                    }
                    else{
                        System.out.println("Animal has not unlocked this move!");
                        return false;
                    }
                }
            }
            else{
                System.out.println("Move doesn't exist!");
                return false;
            }

        } else{
            System.out.println("Already know the maximum of 4 moves!");
            return false;
        }
        return false;
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
    public StatusEffect getCurrentEffect() {
        return(this.currentEffect);
    }

    //setters
    public void setMoves(Move[] ms) {
        this.moves = ms;
    }

    public void setLevel(int levelToBe){
        this.level = levelToBe;
    }

    public void resetCurrentHP() {
        this.currentHP = this.maxHP;
    }

    public void forgetMove(int moveIndex){
        moves[moveIndex] = null;
    }

    public String getName(){
        return name;
    }

    public int getExP(){
        return exP;
    }

    public HashMap<Integer, ArrayList<String>> getLearnableMoves() {
        return learnableMoves;
    }
    
}
