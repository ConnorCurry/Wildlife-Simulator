package edu.ithaca.dragon.wildlife;

public class Move {
    private String title;
    private int damage;
    private int amountLeft;

    public Move(String title, int dmg, int amt) {
        this.title = title;
        this.damage = dmg;
        this.amountLeft = amt;
    }

    public int getDamage() {
        return(this.damage);
    }

    public int getAmountLeft() {
        return(this.amountLeft);
    }
}
