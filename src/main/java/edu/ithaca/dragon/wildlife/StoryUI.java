package edu.ithaca.dragon.wildlife;

import java.util.HashMap;
import java.util.Scanner;

public class StoryUI {
    
    public void story() {
        Scanner reader = new Scanner(System.in);
        //Hashmap <Integer, Area> areas = ... Load from Areas.csv
        Player neo = new Player();
        WildlifeSimulator w = new WildlifeSimulator(areas, neo);
        System.out.println("Welcome to WildLife Simulator. Your name is Neo. You have just finished the initial memory wipe necessary to proceed.");
        

        //Select starter animal
        int ans = -1;
        while(ans <= 0){ //Will run as long as ans != 1, 2, or 3
            System.out.println("As you are a fresh spawn, we are blessing you with your choice of three different animals to start with.")
            System.out.println("Input the number relative to your choice of the three.")
            System.out.println("1). Cat");
            System.out.println("2). Snake");
            System.out.println("3). Hawk");
            
            ans = reader.nextInt();
            HashMap <String, Animal> starterAnimals = WildlifeSimulator.getAnimals();
            if(ans == 1) {
                neo.addToParty(starterAnimals.get("cat"));
            } else if(ans == 2) {
                neo.addToParty(starterAnimals.get("snake"));
            } else if ans == 3) {
                neo.addToParty(starterAnimals.get("hawk"));
            } else {
                System.out.println("Invalid input. Please try again, this time either inputting 1, 2, or 3");
            }
        }
        System.out.println("That's all you'll be needing in the Wilderness, now get out.")
        
        //Quest Begins, split into 4 parts (Plains , Tundra (3 trainers, 3 wild animals), Desert (5 trainers, 4 wild animals), Swamp (6 trainers, 5 wild animals))
            //Plains-Area 1 ; (1 trainer, 2 wild animals)
        
    }

    
}
