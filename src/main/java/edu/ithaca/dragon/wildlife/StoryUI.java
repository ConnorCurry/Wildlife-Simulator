package edu.ithaca.dragon.wildlife;

import java.util.HashMap;
import java.util.Scanner;

public class StoryUI {
    
    public void story() {
        Scanner reader = new Scanner(System.in);
        Player neo = new Player();
        WildlifeSimulator w = new WildlifeSimulator(neo);
        w.initalLoad();
        System.out.println("Welcome to WildLife Simulator. Your name is Neo. You have just finished the initial memory wipe necessary to proceed.");
        

        //Select starter animal
        int ans = -1;
        while(ans <= 0){ //Will run as long as ans != 1, 2, or 3
            System.out.println("As you are a fresh spawn, we are blessing you with your choice of three different animals to start with.");
            System.out.println("Input the number relative to your choice of the three.");
            System.out.println("1). Cat");
            System.out.println("2). Snake");
            System.out.println("3). Hawk");
            
            ans = reader.nextInt();
            HashMap <String, Animal> starterAnimals = WildlifeSimulator.getAnimals();
            if(ans == 1) {
                neo.addToParty(starterAnimals.get("cat"));
            } else if(ans == 2) {
                neo.addToParty(starterAnimals.get("snake"));
            } else if (ans == 3) {
                neo.addToParty(starterAnimals.get("hawk"));
            } else {
                System.out.println("Invalid input. Please try again, this time either inputting 1, 2, 3");
            }
        }
        System.out.println("That's all you'll be needing in the Wilderness, now get out.");
        
        //Quest Begins, split into 4 parts (Plains , Tundra , Desert (5 trainers, 4 wild animals), Swamp (6 trainers, 5 wild animals))
            //Plains-Area 1 ; (1 trainer, 2 wild animals)
        //First Animal Battle 1/2 w
        System.out.println("You are stumbling around on the hills and run across a wild Animal!");
        w.startWildBattle();
        //Dialague; then trainer battle 1/1 t
        System.out.println("You are suddenly roasted out of nowhere by Trainer Jeff Ross who pops out of the brush");
        Trainer winner = w.startBattle();
        while(winner != neo){
            //for all neos animals, heal them; then...
            w.startBattle();
        }
        //second animal battle 2/2w
        System.out.println("As you leave the viscinity of your prior opponent, you are hit in the head out of the blue by an Animal camoflauged by its surroundings");
        System.out.println("You have successfully passed the Plains Area");
        
            //Tundra-Area 2; (2 trainers, 2 wild animals)
        //1/2 w
        System.out.println("All of a sudden, from out of the dirt, springs a wild animal!");
        //1/2 t
        System.out.println("You hear from behind you the words, My name is Toyota and this is my Tundra");
        //2/2 w
        System.out.println("You are running from a stampede when you suddenly stop hearing footsteps. As you look behind you to see no animals chasing you, you hear a deep roar from your front side");
        //2/2 t
        System.out.println("You think you are clear of the tundra when a Trainer named The Gatekeeper steps in your way ");
            //Desert-Area 3; (3 trainers, 2 wild animals)
        //1/3 t
        //1/2 w
        //2/3 t
        //2/2 w
        //3/3 t
            //Swamp-Area 4; (3 trainers, 3 wild animals)
        //1/3 w
        //1/3 t
        //2/3 w
        //2/3 t
        //3/3 w
        //3/3 t
    }

    
}
