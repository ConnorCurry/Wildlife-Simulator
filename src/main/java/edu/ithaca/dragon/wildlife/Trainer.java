package edu.ithaca.dragon.wildlife;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Trainer {
    private Animal[] animals = new Animal[6];

    public Trainer() {
    }

    @JsonCreator
    public Trainer(@JsonProperty("animalsArray") Animal[] animals) {
        this.animals = animals;
    } 

    // returns first animal in party
    public Animal firstAnimal() {
        
        return(this.animals[0]);
    }

    //returns first valid animal in party - if none returns null
    public Animal nextAnimal() {
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

    //Returns string of trainer's party
    public String viewParty(){
        String partyInfo = "";
        for(int i = 0; i < animals.length; i++){
            int anNum = i +1;
            partyInfo += "Animal " + anNum + ": " + animals[i].getName() + ", HP: " + animals[i].getCurrentHP() + ", Max HP: " + animals[i].getMaxHP() + ", Attack Damage: " + animals[i].getAD() + " %n";
        }
        return partyInfo;
    }
}
