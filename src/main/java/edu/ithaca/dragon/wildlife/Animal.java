package edu.ithaca.dragon.wildlife;

public class Animal {
    private Move[] moves = new Move[4];
    private int maxHP; // HP = Health Points
    private int currentHP;
    private int ad; //ad = Attack Damage

    //Constructor
    public Animal(int hp, int ad) {
        this.maxHP = hp;
        this.currentHP = hp;
        this.ad = ad;
    }


}
