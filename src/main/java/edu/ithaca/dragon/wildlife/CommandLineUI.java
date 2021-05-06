package edu.ithaca.dragon.wildlife;

import java.util.HashMap;
import java.util.Scanner;

public class CommandLineUI {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String in;
        // Create player and demo party
        do { //repeats until either "load" or "demo" is input (upper/lowercases covered)
            System.out.println("Which player do you choose:\nload\nloadstory\nstory\ndemo (overwrites save)");
            in = scan.nextLine().toLowerCase();
        } while(!in.equals("load") && !in.equals("demo") && !in.equals("loadstory") && !in.equals("story"));

        Player player = null;
        if (in.equals("load")) {
            player = Player.readPartyInfo();
        } else if (in.equals("loadstory")){
            //TODO: Load Story
        } else if(in.equals("story")) {
            StoryUI story = new StoryUI();
            StoryUI.story();
            return;
        }

        Move bite = new Move("Bite", 5, 10);
        Move scratch = new Move("Scratch", 3, 15);
        if (player == null) {
            player = new Player();
            Animal a1 = new Animal(40, 40, 7, "Wolf");
            a1.setMoves(new Move[]{bite, scratch, null, null});
    
            Animal a2 = new Animal(50, 50, 8, "Bear");
            a2.setMoves(new Move[]{bite, null, null, null});
    
            Animal a3 = new Animal(25, 25, 3, "Squirrel");
            a3.setMoves(new Move[]{null, null, null, null});
            Animal[] playerAnimalSet = {a1, a2, a3, null, null, null};
            player.setAnimals(playerAnimalSet);
            player.savePartyInfo();
        }

        // Create demo wild animal w/ trainer
        Trainer wildTrainer = new Trainer();
        Animal wildAnimal = new Animal(30, 30, 4, "Fox");
        wildAnimal.setMoves(new Move[]{scratch, null, null, null});
        Animal[] wildAnimalSet= {wildAnimal, null, null, null, null, null};
        wildTrainer.setAnimals(wildAnimalSet);

        // Create demo opponent trainer
        Trainer oppTrainer = new Trainer();
        Animal oppA1 = new Animal(30, 30, 5, "Deer");
        Move dash = new Move("Dash", 3, 20);
        oppA1.setMoves(new Move[]{dash, null, null, null});
        Animal oppA2 = new Animal(40, 40, 5, "Wolf");
        oppA2.setMoves(new Move[]{bite, scratch, null, null});

        Animal[] oppSet = {oppA1, oppA2, null, null, null, null};
        oppTrainer.setAnimals(oppSet);

        Trainer demoTrainer;
        String demoString;
        do{
            System.out.println("Would you like to fight:\nWild\nTrainer");
            demoString = scan.nextLine().toLowerCase();
        } while (!demoString.equals("wild") && !demoString.equals("trainer"));
        if (demoString.equals("wild")) {
            demoTrainer = wildTrainer;
        }
        else demoTrainer = oppTrainer;

        Trainer[] trainerArray = {demoTrainer};
        Area area1 = new Area(trainerArray, Climate.PLAINS);
        HashMap<Integer, Area> areas = new HashMap<>();
        areas.put(1, area1);

        WildlifeSimulator simulator = new WildlifeSimulator(areas, player);

        if (demoString.equals("wild")) {
            simulator.startWildBattle();
        }
        else simulator.startBattle();

        player.savePartyInfo();
    }
}
