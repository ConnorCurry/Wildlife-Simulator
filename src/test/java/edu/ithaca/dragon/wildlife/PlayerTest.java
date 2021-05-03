package edu.ithaca.dragon.wildlife;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.type.TypeReference;

public class PlayerTest {

    @Test
    void swapAnimalTest() {
        Player p = new Player();
        Animal a1, a2, a3, a4;
        a1 = new Animal(1, 1, 100);
        a2 = new Animal(2, 2, 200);
        a3 = new Animal(3, 3, 300);
        a4 = new Animal(4, 0, 400);  // fainted

        Animal[] as = {a1, a2, a3, a4};
        p.setAnimals(as);

        assertArrayEquals(new Animal[]{a1, a2, a3}, p.swappableAnimals());


        p = new Player();
        a1 = new Animal(1, 1, 100);
        a2 = new Animal(2, 2, 200);
        a3 = new Animal(3, 0, 300);  // fainted
        a4 = new Animal(4, 0, 400);  // fainted

        as = new Animal[]{a1, a2, a3, a4};
        p.setAnimals(as);

        assertArrayEquals(new Animal[]{a1, a2}, p.swappableAnimals());


        p = new Player();
        a1 = new Animal(1, 1, 100);
        a2 = new Animal(2, 0, 200);  // fainted
        a3 = new Animal(3, 0, 300);  // fainted
        a4 = new Animal(4, 0, 400);  // fainted

        as = new Animal[]{a1, a2, a3, a4};
        p.setAnimals(as);

        assertArrayEquals(new Animal[]{a1}, p.swappableAnimals());


        p = new Player();
        a1 = new Animal(1, 0, 100);  // fainted
        a2 = new Animal(2, 0, 200);  // fainted
        a3 = new Animal(3, 0, 300);  // fainted
        a4 = new Animal(4, 0, 400);  // fainted

        as = new Animal[]{a1, a2, a3, a4};
        p.setAnimals(as);

        assertArrayEquals(new Animal[]{}, p.swappableAnimals());
    }
    @Test
    void viewPartyTest() {
        Trainer t = new Trainer();
        Animal a1, a2, a3, a4;
        a1 = new Animal(100, 1, 1, "Bear");
        a2 = new Animal(200, 2, 2, "Dog");
        a3 = new Animal(300, 3, 3, "Snake");
        a4 = new Animal(400, 0, 4, "Lion"); 

        Animal[] as = {a1, a2, a3, a4};
        t.setAnimals(as);
        String answer1 = "Animal 1: Bear, HP: 1, Max HP: 100, Attack Damage: 1 %nAnimal 2: Dog, HP: 2, Max HP: 200, Attack Damage: 2 %nAnimal 3: Snake, HP: 3, Max HP: 300, Attack Damage: 3 %nAnimal 4: Lion, HP: 0, Max HP: 400, Attack Damage: 4 %n";
        System.out.printf(answer1);
        assertEquals(answer1, t.viewParty());
    }

    @Test
    void addToPartyTest(){
        Trainer t = new Trainer();
        Animal a1, a2, a3, a4, a5, a6;
        a1 = new Animal(100, 1, 1, "Bear");
        a2 = new Animal(200, 2, 2, "Dog");
        a3 = new Animal(300, 3, 3, "Snake");
        a4 = new Animal(400, 0, 4, "Lion"); 
        a5 = new Animal(500, 5, 5, "Oh My");
        a6 = new Animal(600, 6, 6, "Rat");

        Animal[] as = {a1, a2, a3, a4, a5, null};
        t.setAnimals(as);
        t.addToParty(a6);
        assertEquals(a6, t.getAnimalsArray()[5]);
        t.addToParty(a5);   //test to see if there is a print    

    }

    @Test
    void removeFromPartyTest(){
        Trainer t = new Trainer();
        Animal a1, a2, a3, a4, a5, a6;
        a1 = new Animal(100, 1, 1, "Bear");
        a2 = new Animal(200, 2, 2, "Dog");
        a3 = new Animal(300, 3, 3, "Snake");
        a4 = new Animal(400, 0, 4, "Lion"); 
        a5 = new Animal(500, 5, 5, "Oh My");
        a6 = new Animal(600, 6, 6, "Rat");
        Animal[] as = {a1, a2, a3, a4, a5, a6};
        t.setAnimals(as);
        t.removeFromParty(6);
        assertNull(t.getAnimalsArray()[5]);
        Animal[] as2 = {null, null, null, null, null, null};
        t.setAnimals(as2);
        t.removeFromParty(1); //test to see if there is a print
    }

    @Test
    void savePartyInfoTest() {
        ObjectMapper mapper = new ObjectMapper();

        Move dash = new Move("Dash", 3, 20);
        Move bite = new Move("Bite", 5, 10);
        Move scratch = new Move("Scratch", 3, 15);

        Trainer p = new Trainer();
        Animal a1, a2;

        p = new Trainer();
        a1 = new Animal(2, 1, 3, "Animal1");
        a1.setMoves(new Move[]{dash, null, null, null});
        p.addToParty(a1);


        // Write to file
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File("src/test/java/edu/ithaca/dragon/wildlife/PlayerTest.csv"), p);
        }
        catch(Exception e) {
            System.out.println("Error Writing:\n" + e);
        }

        Trainer readPlayer = null;
        // Read from file
        try {
            readPlayer = mapper.readValue(new File("src/test/java/edu/ithaca/dragon/wildlife/PlayerTest.csv"), new TypeReference<Trainer>(){});
        }
        catch(Exception e) {
            System.out.println("Error Reading:\n" + e);
        }

        Animal readAnimal = readPlayer.firstAnimal();
        assertEquals("Animal1", readAnimal.getName());
        assertEquals(2, readAnimal.getMaxHP());
        assertEquals(1, readAnimal.getCurrentHP());
        assertEquals(3, readAnimal.getAD());
        assertEquals("Dash", readAnimal.getMoves()[0].getTitle());


        p = new Trainer();
        a1 = new Animal(8, 8, 8, "Animal1");
        a1.setMoves(new Move[]{dash, null, null, null});
        a2 = new Animal(5, 4, 6, "Animal2");
        a2.setMoves(new Move[]{bite, scratch, null, null});
        p.setAnimals(new Animal[]{a1, a2});

        // Write to file
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File("src/test/java/edu/ithaca/dragon/wildlife/PlayerTest.csv"), p);
        }
        catch(Exception e) {
            System.out.println("Error Writing:\n" + e);
        }

        // Read from file
        try {
            readPlayer = mapper.readValue(new File("src/test/java/edu/ithaca/dragon/wildlife/PlayerTest.csv"), new TypeReference<Trainer>(){});
        }
        catch(Exception e) {
            System.out.println("Error Reading:\n" + e);
        }

        Animal readAnimal1 = readPlayer.getAnimalsArray()[0];
        assertEquals("Animal1", readAnimal1.getName());
        assertEquals(8, readAnimal1.getMaxHP());
        assertEquals(8, readAnimal1.getCurrentHP());
        assertEquals(8, readAnimal1.getAD());
        assertEquals("Dash", readAnimal1.getMoves()[0].getTitle());

        Animal readAnimal2 = readPlayer.getAnimalsArray()[1];
        assertEquals("Animal2", readAnimal2.getName());
        assertEquals(5, readAnimal2.getMaxHP());
        assertEquals(4, readAnimal2.getCurrentHP());
        assertEquals(6, readAnimal2.getAD());
        assertEquals("Bite", readAnimal2.getMoves()[0].getTitle());
        assertEquals("Scratch", readAnimal2.getMoves()[1].getTitle());
    }
}
