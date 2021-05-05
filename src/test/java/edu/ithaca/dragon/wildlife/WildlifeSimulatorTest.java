package edu.ithaca.dragon.wildlife;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import java.io.File;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

public class WildlifeSimulatorTest {
    
    @Test
    public void getMoveFromTitleTest() {
        Animal animal1 = new Animal(1, 1, 100);
        Move move1 = new Move("scratch", 1, 10);
        Move[] moves = {move1, null, null, null};
        animal1.setMoves(moves);
        assertEquals(move1, WildlifeSimulator.getMoveFromTitle(animal1, "scratch")); // base case


        Move move2 = new Move("tackle", 1, 10);
        Move move3 = new Move("bite", 1, 10);
        Move move4 = new Move("slash", 1, 10);
        moves[1] = move2;
        moves[2] = move3;
        moves[3] = move4;
        animal1.setMoves(moves);
        assertEquals(move1, WildlifeSimulator.getMoveFromTitle(animal1, "scratch"));
        assertEquals(move2, WildlifeSimulator.getMoveFromTitle(animal1, "tackle"));
        assertEquals(move3, WildlifeSimulator.getMoveFromTitle(animal1, "bite"));
        assertEquals(move4, WildlifeSimulator.getMoveFromTitle(animal1, "slash")); // full moveset test

        moves[3] = null;
        assertThrows(IllegalArgumentException.class, () -> {WildlifeSimulator.getMoveFromTitle(animal1, "slash");}); // move doesnt exist

        moves[3] = move4;
        moves[2] = null;
        assertEquals(move4, WildlifeSimulator.getMoveFromTitle(animal1, "slash")); // if earlier move is null, can stil retrieve later move
        assertThrows(IllegalArgumentException.class, () -> {WildlifeSimulator.getMoveFromTitle(animal1, "bite");}); // move shouldn't exist
    }

    @Test
    public void changeAreaTest(){
        
    }

    
    // public static void main(String[] args) {
    //     Scanner scan = new Scanner(System.in);

    //     // Create player and demo party
    //     Player player = new Player();
    //     Animal a1 = new Animal(40, 40, 7, "Wolf");
    //     Move bite = new Move("Bite", 5, 10);
    //     Move scratch = new Move("Scratch", 3, 15);
    //     a1.setMoves(new Move[]{bite, scratch, null, null});

    //     Animal a2 = new Animal(50, 50, 8, "Bear");
    //     a2.setMoves(new Move[]{bite, null, null, null});

    //     Animal a3 = new Animal(25, 25, 3, "Squirrel");
    //     a3.setMoves(new Move[]{null, null, null, null});
    //     Animal[] playerAnimalSet = {a1, a2, a3, null, null, null};
    //     player.setAnimals(playerAnimalSet);

    //     // Create demo wild animal w/ trainer
    //     Trainer wildTrainer = new Trainer();
    //     Animal wildAnimal = new Animal(30, 30, 4, "Fox");
    //     wildAnimal.setMoves(new Move[]{scratch, null, null, null});
    //     Animal[] wildAnimalSet= {wildAnimal, null, null, null, null, null};
    //     wildTrainer.setAnimals(wildAnimalSet);

    //     // Create demo opponent trainer
    //     Trainer oppTrainer = new Trainer();
    //     Animal oppA1 = new Animal(30, 30, 5, "Deer");
    //     Move dash = new Move("Dash", 3, 20);
    //     oppA1.setMoves(new Move[]{dash, null, null, null});
    //     Animal oppA2 = new Animal(40, 40, 7, "Wolf");
    //     oppA2.setMoves(new Move[]{bite, scratch, null, null});

    //     Animal[] oppSet = {oppA1, oppA2, null, null, null, null};
    //     oppTrainer.setAnimals(oppSet);

    //     Trainer demoTrainer;
    //     String demoString;
    //     do{
    //         System.out.println("Would you like to fight:\nWild\nTrainer");
    //         demoString = scan.nextLine().toLowerCase();
    //     } while (!demoString.equals("wild") && !demoString.equals("trainer"));
    //     if (demoString.equals("wild")) {
    //         demoTrainer = wildTrainer;
    //     }
    //     else demoTrainer = oppTrainer;

    //     Trainer[] trainerArray = {demoTrainer};
    //     Area area1 = new Area(trainerArray, Climate.PLAINS);
    //     HashMap<Integer, Area> areas = new HashMap<>();
    //     areas.put(1, area1);

    //     WildlifeSimulator simulator = new WildlifeSimulator(areas, player);
    //     if (simulator.getAnimals().containsKey("Bear"))){
    //         System.out.println("Pass");
    //     }
    //     assertTrue(simulator.getAnimals().containsKey("Cat"));
    //     assertTrue(simulator.getAnimals().containsKey("Deer"));
    //     assertTrue(simulator.getAnimals().containsKey("Dog"));
    //     assertTrue(simulator.getAnimals().containsKey("Fox"));
    //     assertTrue(simulator.getAnimals().containsKey("Hawk"));
    //     assertTrue(simulator.getAnimals().containsKey("Snake"));
    //     assertTrue(simulator.getAnimals().containsKey("Squirrel"));
    //     assertTrue(simulator.getAnimals().containsKey("Wolf"));
    // }
    


    @Test
    /**
     * Test function for setting up areas
     * Testing out Jackson use for loading and saving area data
     * This is not a unit test of any particular feature
     * This should be removed for the final product
     */
    public void saveLoadAreas() {
        ObjectMapper mapper = new ObjectMapper();

        Trainer oppTrainer = new Trainer();
        Animal oppA1 = new Animal(30, 30, 5, "Deer");
        Move dash = new Move("Dash", 3, 20);
        Move bite = new Move("Bite", 5, 10);
        Move scratch = new Move("Scratch", 3, 15);
        oppA1.setMoves(new Move[]{dash, null, null, null});
        Animal oppA2 = new Animal(40, 40, 7, "Wolf");
        oppA2.setMoves(new Move[]{bite, scratch, null, null});

        Animal[] oppSet = {oppA1, oppA2, null, null, null, null};
        oppTrainer.setAnimals(oppSet);

        Trainer[] trainerArray = {oppTrainer};
        Area area1 = new Area(trainerArray, Climate.PLAINS);
        HashMap<Integer, Area> areas = new HashMap<>();
        areas.put(1, area1);
        

        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File("target/areas.json"), areas);
        }
        catch(Exception e) {
            System.out.println("Error Writing:\n" + e);
        }

        TypeReference<HashMap<Integer, Area>> typeRef = new TypeReference<HashMap<Integer,Area>>(){};
        HashMap<Integer, Area> loadedAreas = new HashMap<>();
        try {
            loadedAreas = mapper.readValue(new File("target/areas.json"), typeRef);
        }
        catch(Exception e) {
            System.out.println("Error Reading:\n" + e);
        }

        Area loadedArea1 = loadedAreas.get(1);
        
        System.out.println(loadedArea1.getClimate() + "\n\n\n");
        System.out.println(area1.getClimate());
        assertTrue(area1.getClimate() == loadedArea1.getClimate());
        assertTrue(areas.get(1).getClimate() == loadedAreas.get(1).getClimate());
        
    }

    @Test
    public void initalLoadTest() {
        WildlifeSimulator sim = new WildlifeSimulator();
        sim.initalLoad();
        assertEquals(Climate.PLAINS, sim.getCurrArea().getClimate()); // Check climate in area 1 from fresh save file
        assertEquals(Climate.DESERT, sim.getAreas().get(2).getClimate()); // Check climate in area 2 from fresh save file
        assertEquals(3, sim.getCurrArea().getTrainers()[0].getAnimals()[0].getMoves()[0].getDamage()); // Check damage of trainer in area 1 on first animals first move
        assertEquals(20, sim.getCurrArea().getTrainers()[0].getAnimals()[0].getMoves()[0].getAmountLeft()); // Check amount of moves left of trainer in area 1 on first animals first move
        assertTrue(sim.getCurrArea().getTrainers()[0].getAnimals()[0].getMoves()[0].getTitle().equals("Dash")); // Check name of first animal of trainer 1 in area 1
        assertTrue(sim.getCurrArea().getTrainers()[0].getAnimals()[0].getName().equals("Deer")); // Check name of first animal
        assertEquals(30, sim.getCurrArea().getTrainers()[0].getAnimals()[0].getMaxHP()); // Check max HP of animal
        assertEquals(30, sim.getCurrArea().getTrainers()[0].getAnimals()[0].getCurrentHP()); // Check current HP of animal
        assertEquals(5, sim.getCurrArea().getTrainers()[0].getAnimals()[0].getAD()); // Check attack damage of animal
        assertEquals(1, sim.getCurrArea().getTrainers()[0].getAnimals()[0].getLevel()); // Check level of animal
        assertNull(sim.getCurrArea().getTrainers()[0].getAnimals()[0].getMoves()[0].getStatusString());
    }

    @Test
    public void animalLearnSetTest() {
        HashMap<Integer, ArrayList<String>> dogMoves;
        dogMoves = WildlifeSimulator.animalLearnSet("dog");
        assertTrue(dogMoves.get(1).get(0).equals("bark"));
        assertTrue(dogMoves.get(1).get(1).equals("bite"));
        assertTrue(dogMoves.get(3).get(0).equals("fetch"));
        assertTrue(dogMoves.get(3).get(1).equals("sit"));
        assertTrue(dogMoves.get(3).get(2).equals("rollover"));

        HashMap<Integer, ArrayList<String>> bearMoves;
        bearMoves = WildlifeSimulator.animalLearnSet("bear");
        assertTrue(bearMoves.get(1).get(0).equals("tackle"));
        assertTrue(bearMoves.get(5).get(0).equals("bear hug"));

        HashMap<Integer, ArrayList<String>> foxMoves;
        foxMoves = WildlifeSimulator.animalLearnSet("fox");
        assertTrue(foxMoves.get(1).get(0).equals("scratch"));
        assertTrue(foxMoves.get(3).get(0).equals("dash"));
        assertTrue(foxMoves.get(5).get(0).equals("bite"));

        HashMap<Integer, ArrayList<String>> hawkMoves;
        hawkMoves = WildlifeSimulator.animalLearnSet("hawk");
        assertTrue(hawkMoves.get(1).get(0).equals("peck"));
        assertTrue(hawkMoves.get(3).get(0).equals("wing attack"));
    }
}
