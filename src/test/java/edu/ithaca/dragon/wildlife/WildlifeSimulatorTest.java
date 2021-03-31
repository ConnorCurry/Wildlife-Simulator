package edu.ithaca.dragon.wildlife;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
}
