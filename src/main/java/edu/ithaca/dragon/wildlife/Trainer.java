package edu.ithaca.dragon.wildlife;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * @return the animals that are able to be swapped to and from
     */
    public Animal[] swappableAnimals() {
        List<Animal> swappable = new ArrayList<Animal>();
        for (Animal animal : animals) {
            if (animal != null && animal.getCurrentHP() > 0) {
                swappable.add(animal);
            }
        }
        return swappable.toArray(new Animal[0]);
    }

    public Animal[] getAnimalsArray() {
        return animals;
    }
}
