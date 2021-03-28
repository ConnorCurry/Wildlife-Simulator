package edu.ithaca.dragon.wildlife;

public class Trainer {
    private Animal[] animals = new Animal[6];

   /*  public Trainer() {

    } */

    // returns first animal in party
    public Animal getFirstAnimal() {
        
        return(this.animals[0]);
    }

    //returns first valid animal in party - if none returns null
    public Animal getNextAnimal() {
        Animal a;
        for(int i = 0; i < this.animals.length; i++) {
            if(this.animals[i] != null) {
                if(this.animals[i].getCurrentHP() > 0) {
                    return(this.animals[i]);
                }
                
            }
        }
        return(null);
    }

    public void setAnimals(Animal [] as) {
        this.animals = as;
    }
}
