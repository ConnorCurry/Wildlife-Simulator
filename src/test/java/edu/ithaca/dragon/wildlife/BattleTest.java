package edu.ithaca.dragon.wildlife;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BattleTest {

    @Test
    void opponentAttackTest() {
        Animal pa = new Animal(200, 200, 10); //Player Animal
        Move pm = new Move("Player Move", 20, 2);
        Animal[] pas = new Animal[1]; 
        pas[0] = pa; //Player animals set to one animal
        Move[] pms = new Move[4];
        pms[0] = pm; 
        pa.setMoves(pms);

        Animal oa = new Animal(100,100,10);
        Move om1 = new Move("Opponent Move 1", 30, 1);
        Move om2 = new Move("Opponent Move 2", 20, 1);
        Animal[] oas = new Animal[1];
        oas[0] = oa;
        Move[] oms = new Move[4];
        oms[0] = om1;
        oms[1] = om2;
        oa.setMoves(oms);

        Player p = new Player();
        p.setAnimals(pas);
        Trainer t = new Trainer();
        t.setAnimals(oas);

        Climate c = Climate.DESERT;

        Battle b = new Battle(p, t, c);

        //Equivalence Class: x moves are null where 0<x<=4
        b.opponentAttack();
        assertEquals(160, pa.getCurrentHP()); //Chose better move when better move is first


        b.opponentAttack();
        assertEquals(130, pa.getCurrentHP()); //Don't have more of better attack, should only deal 20 + 10

        Animal oa2 = new Animal(100, 100, 10);
        Animal[] oas2 = new Animal[1];
        oas2[0] = oa2;
        Move[] oms2 = new Move[4];
        Move om3 = new Move("Opponent Move 1", 20, 1);
        Move om4 = new Move("Opponent Move 2", 30, 1);
        oms2[0] = om3;
        oms2[1] = om4;
        oa2.setMoves(oms2);
        t.setAnimals(oas2);

        Battle b2 = new Battle(p, t, c);

        b2.opponentAttack();
        assertEquals(90, pa.getCurrentHP()); //Chose better move when better move is not first


        b2.opponentAttack();
        assertEquals(60, pa.getCurrentHP()); //Don't have more of better attack, should only deal 20 + 10

        Animal oa3 = new Animal(100,100,10);
        Animal[] oas3 = new Animal[4];
        oas3[0] = oa3;
        t.setAnimals(oas3);

        Battle b3 = new Battle(p, t, c);
        b3.opponentAttack();
        
        assertEquals(50, pa.getCurrentHP()); //Equivalence Class: Opponents Animal has no moves
    }

    @Test
    void deadlyOpponentAttackTest() {
        Animal pa = new Animal(100, 200, 10); //Player Animal
        Move pm = new Move("Player Move", 20, 2);
        Animal[] pas = new Animal[1]; 
        pas[0] = pa; //Player animals set to one animal
        Move[] pms = new Move[4];
        pms[0] = pm; 
        pa.setMoves(pms);

        Animal oa = new Animal(100,100,10);
        Move om1 = new Move("Opponent Move 1", 90, 1);
        Animal[] oas = new Animal[1];
        oas[0] = oa;
        Move[] oms = new Move[4];
        oms[0] = om1;
        oa.setMoves(oms);

        Player p = new Player();
        p.setAnimals(pas);
        Trainer t = new Trainer();
        t.setAnimals(oas);

        Climate c = Climate.DESERT;

        Battle b = new Battle(p, t, c);

        b.opponentAttack();
        assertEquals(t, );
    }
    
}
