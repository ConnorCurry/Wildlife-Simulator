package edu.ithaca.dragon.wildlife;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
        a1 = new Animal(1, 1, 100, "Bear");
        a2 = new Animal(2, 2, 200, "Dog");
        a3 = new Animal(3, 3, 300, "Snake");
        a4 = new Animal(4, 0, 400, "Lion"); 

        Animal[] as = {a1, a2, a3, a4};
        t.setAnimals(as);
        String answer1 = "Animal 1: Bear, HP: 1 %nAnimal 2: Dog, HP: 2 %nAnimal 3: Snake, HP: 3 %nAnimal 4: Lion, HP: 0 %n";
        assertEquals(answer1, t.viewParty());
    }
}
