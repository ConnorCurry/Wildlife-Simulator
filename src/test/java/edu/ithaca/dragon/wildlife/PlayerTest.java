package edu.ithaca.dragon.wildlife;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

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
        Animal a1, a2, a3, a4, a5;
        a1 = new Animal(100, 1, 1, "Bear");
        a2 = new Animal(200, 2, 2, "Dog");
        a3 = new Animal(300, 3, 3, "Snake");
        a4 = new Animal(400, 0, 4, "Lion"); 
        a5 = new Animal(500, 5, 5, "Oh My");

        Animal[] as = {a1, a2, a3, null};
        t.setAnimals(as);
        t.addToParty(a4);
        assertEquals(a4, t.getAnimalsArray()[3]);
        t.addToParty(a5);   //test to see if there is a print    

    }

    @Test
    void removeFromPartyTest(){

    }
}
