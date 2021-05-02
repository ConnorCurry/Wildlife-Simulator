package edu.ithaca.dragon.wildlife;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class AnimalTest {
    
    @Test
    public void animalConstructorTest() {
        Animal bear = new Animal(55, 55, 7, "bear", 1, 0);
        assertTrue(bear.getLearnableMoves().get(1).get(0).equals("tackle"));
        assertTrue(bear.getLearnableMoves().get(5).get(0).equals("bear hug"));

        Animal dog = new Animal(45, 45, 5, "dog", 1, 0);
        assertTrue(dog.getLearnableMoves().get(1).get(0).equals("bark"));
        assertTrue(dog.getLearnableMoves().get(1).get(1).equals("bite"));
        assertTrue(dog.getLearnableMoves().get(3).get(0).equals("fetch"));
        assertTrue(dog.getLearnableMoves().get(3).get(1).equals("sit"));
        assertTrue(dog.getLearnableMoves().get(3).get(2).equals("rollover"));

    }
}
