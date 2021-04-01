package edu.ithaca.dragon.wildlife;

public class Animal {
    private Move[] moves = new Move[4];
    private int maxHP; // HP = Health Points
    private int currentHP;
    private int ad; //ad = Attack Damage

    //Constructor
    public Animal(int hp,int chp, int ad) {
        this.maxHP = hp;
        this.currentHP = chp;
        this.ad = ad;
    }

    public void receiveDamage(int dmg) {
        this.currentHP -= dmg;
    }

    public void healMove(int healAmt){
        
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

    //setters
    public void setMoves(Move[] ms) {
        this.moves = ms;
    }

    public void forgetMove(int moveIndex){
        moves[moveIndex] = null;
    }

}
