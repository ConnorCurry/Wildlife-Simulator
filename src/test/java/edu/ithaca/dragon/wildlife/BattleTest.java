package edu.ithaca.dragon.wildlife;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BattleTest {

    @Test
    void opponentAttackGetBestMoveTest() {
        Animal pa = new Animal(100, 100, 10); //Player Animal
        Move pm = new Move("Player Move", 20, 2);
        Animal[] pas = new Animal[1]; 
        pas[0] = pa; //Player animals set to one animal
        Move[] pms = new Move[1];
        pms[0] = pm; 

        Animal oa = new Animal(100,100,10);
        Move om1 = new Move("Opponent Move 1", 30, 1);
        Move om2 = new Move("Opponent Move 2", 20, 1);
        Animal[] oas = new Animal[1];
        oas[0] = oa;
        Move[] oms = new Move[2];
        oms[0] = om1;
        oms[1] = om2;

        Player p = new Player();
        p.setAnimals(pas);
        Trainer t = new Trainer();
        t.setAnimals(oas);

        Climate c = Climate.DESERT;

        Battle b = new Battle(p, t, c);

        
        b.opponentAttack();
        assertEquals(80, pa.getCurrentHP());
        b.opponentAttack();

        //2 moves, better one has 1
        assertEquals(50, oa.getAD());
    }
    
}
