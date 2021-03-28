package edu.ithaca.dragon.wildlife;

public class Trainer {
    private Animal[] animals = new Animal[6];

   /*  public Trainer() {

    } */

    // returns first animal in party
    public Animal getFirstAnimal() {
        
        return this.animals[0];
    }

    public void setAnimals(Animal [] as) {
        this.animals = as;
    }
}
