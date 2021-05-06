package edu.ithaca.dragon.wildlife;

import java.util.HashMap;
import java.util.Scanner;

public class StoryUI {
    
    public void story() {
        Scanner reader = new Scanner(System.in);
        Player neo = new Player();
        WildlifeSimulator w = new WildlifeSimulator(neo);
        w.initalLoad();
        System.out.println("\nWelcome to WildLife Simulator. Your name is Neo. You have just finished the initial memory wipe necessary to proceed.");
        

        //Select starter animal
        int ans = -1;
        while(ans <= 0){ //Will run as long as ans != 1, 2, or 3
            System.out.println("As you are a fresh spawn, we are blessing you with your choice of three different animals to start with.\n");
            System.out.println("Input the number relative to your choice of the three.");
            System.out.println("1). Cat");
            System.out.println("2). Snake");
            System.out.println("3). Hawk");
            
            ans = reader.nextInt();
            HashMap <String, Animal> starterAnimals = WildlifeSimulator.getAnimals();
            if(ans == 1) {
                neo.addToParty(starterAnimals.get("Cat"));
            } else if(ans == 2) {
                neo.addToParty(starterAnimals.get("Snake"));
            } else if (ans == 3) {
                neo.addToParty(starterAnimals.get("Hawk"));
            } else {
                System.out.println("Invalid input. Please try again, this time either inputting 1, 2, 3");
            }
        }
        System.out.println("\nThat's all you'll be needing in the Wilderness, now get out.\n");
        
        //Quest Begins, split into 4 parts (Plains , Tundra , Desert (5 trainers, 4 wild animals), Swamp (6 trainers, 5 wild animals))
            //Plains-Area 1 ; (1 trainer, 2 wild animals)
        //First Animal Battle 1/2 w
        System.out.println("You are stumbling around on the hills and run across a wild Animal!\n");
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
        w.startWildBattle();
        System.out.println("You have successfully passed the Plains Area");

            //Tundra-Area 2; (2 trainers, 2 wild animals)
        //1/2 w
        System.out.println("All of a sudden, from out of the dirt, springs a wild animal!");
        w.startWildBattle();
        //1/2 t
        System.out.println("You hear from behind you the words, My name is Toyota and this is my Tundra");
        while(winner != neo){
            //for all neos animals, heal them; then...
            w.startBattle();
        }
        //2/2 w
        System.out.println("You are running from a stampede when you suddenly stop hearing footsteps. As you look behind you to see no animals chasing you, you hear a deep roar from your front side");
        w.startWildBattle();
        //2/2 t
        System.out.println("You think you are clear of the tundra when a Trainer named The Gatekeeper steps in your way ");
        while(winner != neo){
            //for all neos animals, heal them; then...
            w.startBattle();
        }

            //Desert-Area 3; (3 trainers, 2 wild animals)
        System.out.println("You have reached the Hot Desert Biome, watch out!");
        //1/3 t
        System.out.println("A Trainer camoflauged in clay jumps out of a sand hole");
        while(winner != neo){
            //for all neos animals, heal them; then...
            w.startBattle();
        }
        //1/2 w
        System.out.println("As the sun blinds your vision, a nearby animal uses its opportunity to attack");
        w.startWildBattle();
        //2/3 t
        System.out.println("You hear from across the terrain, a salty desert trainer");
        while(winner != neo){
            //for all neos animals, heal them; then...
            w.startBattle();
        }
        //2/2 w
        System.out.println("You feel an attack coming from underneath the sand...");
        w.startWildBattle();
        //3/3 t
        System.out.println("The sand guru blocks your path to the final biome and must be defeated for passage.");
        while(winner != neo){
            //for all neos animals, heal them; then...
            w.startBattle();
        }
        System.out.println("You successfully beat the Desert");
            //Swamp-Area 4; (3 trainers, 3 wild animals)
        //1/3 w 
        System.out.println("You suddenly hear a loud splash and see an animal pouncing at you");
        w.startWildBattle();
        //1/3 t
        System.out.println("A swampy monster walks out of the bog when you realize it is a trainer!");
        while(winner != neo){
            //for all neos animals, heal them; then...
            w.startBattle();
        }
        //2/3 w
        System.out.println("Scary Animal Attack!");
        w.startWildBattle();
        //2/3 t
        System.out.println("You hear a trainer nearby saying that there might be a nile crocodile in florida. You look and see it is the main character on Swamp People on the History channel");
        while(winner != neo){
            //for all neos animals, heal them; then...
            w.startBattle();
        }
        //3/3 w
        System.out.println("A cherubim asks you a riddle which you fail miserably");
        w.startWildBattle();
        //3/3 t
        System.out.println("You approach the final trainer of the final area, The Demiurgus (Jehovah, Osiris, Uranus, Shiva)");
        while(winner != neo){
            //for all neos animals, heal them; then...
            w.startBattle();
        }
        System.out.println("As The Demiurgus falls, he smiles and whispers, I am the demiurgus of your mind, and am merely a reflection of your concept of the true ineffible Demiurge. As you have now overcome the gross, material image of god in which you held, you have in turn become the new Primordial Creator and Generator of all to come within your true self's dream called reality.");
        System.out.println("GAME OVER: YOU WIN!");
    }

    
}
