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

    //Returns string of trainer's party
    public String viewParty(){
        String partyInfo = "";
        for(int i = 0; i < animals.length; i++){
            int anNum = i +1;
            partyInfo += "Animal " + anNum + ": " + animals[i].getName() + ", HP: " + animals[i].getCurrentHP() + ", Max HP: " + animals[i].getMaxHP() + ", Attack Damage: " + animals[i].getAD() + " %n";
        }
        return partyInfo;
    }

    public void addToParty(Animal newAnimal){
        Boolean i = false;
        int z = 0;
        if(animals[animals.length - 1] != null){
            System.out.println("Your party is full! Remove an animal before adding one.");
        }
        else{
            while(i == false){
                if(animals[z] == null){
                    animals[z] = newAnimal;
                    i = true;
                    String animalName = newAnimal.getName();
                    System.out.println("Added " + animalName + " to your party!");
                }
                else{
                    z++;
                }
            }
        }
    }

    public void removeFromParty(int anNumb){
        if(isPartyEmpty()){
            System.out.println("Your party is empty!");
        }
        else{
            int anIndex = anNumb - 1;
            String animalName = animals[anIndex].getName();
            animals[anIndex] = null;
            System.out.println("Removed " + animalName + " from your party!");
        }
    }

    public boolean isPartyEmpty(){
        boolean empty = true;
        for(int i = 0; i < animals.length; i++){
            if (animals[i] != null) {
                empty = false;
                break;
            }
        }
        return empty;
    }
}

