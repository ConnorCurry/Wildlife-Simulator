package edu.ithaca.dragon.wildlife;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BattleTest {

    @Test
    void opponentAttackGetBestMoveTest() {
        Animal pa = new Animal(100, 100, 10);
        Move pm = new Move("Player Move", 20, 2);
        Animal[] pas = new Animal[1];
        pas[0] = pa;

        Animal oa = new Animal(100,100,10);
        Move om1 = new Move("Opponent Move 1", 30, 1);
        Move om2 = new Move("Opponent Move 2", 20, 1);
        Animal[] oas = new Animal[1];
        oas[0] = oa;
        
        Player p = new Player();
        Battle b = new Battle();
    }
    
}
