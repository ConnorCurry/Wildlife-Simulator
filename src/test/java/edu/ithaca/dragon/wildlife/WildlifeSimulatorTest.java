package edu.ithaca.dragon.wildlife;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.HashMap;

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
            mapper.writeValue(new File("target/areas.json"), areas);
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
        assertEquals(3, sim.getCurrArea().getTrainers()[0].getAnimalsArray()[0].getMoves()[0].getDamage()); // Check damage of trainer in area 1 on first animals first move
        assertEquals(20, sim.getCurrArea().getTrainers()[0].getAnimalsArray()[0].getMoves()[0].getAmountLeft()); // Check amount of moves left of trainer in area 1 on first animals first move
        assertTrue(sim.getCurrArea().getTrainers()[0].getAnimalsArray()[0].getMoves()[0].getTitle().equals("Dash")); // Check name of first animal of trainer 1 in area 1
        assertTrue(sim.getCurrArea().getTrainers()[0].getAnimalsArray()[0].getName().equals("Deer")); // Check name of first animal
        assertEquals(30, sim.getCurrArea().getTrainers()[0].getAnimalsArray()[0].getMaxHP()); // Check max HP of animal
        assertEquals(30, sim.getCurrArea().getTrainers()[0].getAnimalsArray()[0].getCurrentHP()); // Check current HP of animal
        assertEquals(5, sim.getCurrArea().getTrainers()[0].getAnimalsArray()[0].getAD()); // Check attack damage of animal
        assertEquals(1, sim.getCurrArea().getTrainers()[0].getAnimalsArray()[0].getLevel()); // Check level of animal
        assertNull(sim.getCurrArea().getTrainers()[0].getAnimalsArray()[0].getMoves()[0].getStatusString());
    }
}
