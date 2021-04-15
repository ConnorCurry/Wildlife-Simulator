package edu.ithaca.dragon.wildlife;

public class Move {
    private String title;
    private int damage;
    private int amountLeft;
    private StatusEffect effect;

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
