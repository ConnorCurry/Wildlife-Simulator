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

        Battle b = new WildBattle(p, t, c);

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
        Animal pa = new Animal(100, 100, 10); //Player Animal
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
        assertEquals(0, pa.getCurrentHP());
        assertEquals(t, b.getWinner());
    }
    @Test
    void deadlyPlayerAttackTest() {
        Animal pa = new Animal(100, 100, 10); //Player Animal
        Move pm = new Move("Player Move", 90, 2);
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

        b.playerAttack(pm);
        assertEquals(0, oa.getCurrentHP());
        assertEquals(p, b.getWinner());
    }

    @Test
    void runTest(){
        Animal pa = new Animal(100, 100, 10); //Player Animal
        Move pm = new Move("Player Move", 50, 2);
        Animal[] pas = new Animal[1]; 
        pas[0] = pa; //Player animals set to one animal
        Move[] pms = new Move[4];
        pms[0] = pm; 
        pa.setMoves(pms);

        Animal oa = new Animal(100,100,10);
        Move om1 = new Move("Opponent Move 1", 50, 1);
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

        WildBattle wb = new WildBattle(p, t, c);

        wb.playerAttack(pm);
        assertEquals(40, oa.getCurrentHP());
        wb.run();
        //here it will set a boolean isBattle to false, which will bring player to main demo menu
    }

    @Test
    /*Animals are able to forget moves. All animals must have at least 1 move.
    given an animal with 2 moves, view the animal's moves, select to remove a move, choose move, view updated animal moves
    given an animal with 1 move, view the animals moves, observe that select to remove is visible but not accessible. */
    void forgetTest(){
        Animal testAnimal = new Animal(100,100,10);
        Move testMoveOne = new Move("Move 1", 50, 1);
        Move testMoveTwo = new Move("Move 2", 50, 1);
        Move[] testMoveSet = new Move[4];
        testMoveSet[0] = testMoveOne;
        testMoveSet[1] = testMoveTwo;
        testAnimal.setMoves(testMoveSet);
        assertNotNull(testAnimal.getMoves()[1]);
        assertNotNull(testAnimal.getMoves()[0]);
        testAnimal.forgetMove(1);
        assertNull(testAnimal.getMoves()[1]);
    }
    
    @Test
    void playerSwapAnimalTest() {
        Player p = new Player();
        Animal a1, a2, a3, a4;
        a1 = new Animal(1, 1, 100);
        a2 = new Animal(2, 2, 200);
        a3 = new Animal(3, 3, 300);
        a4 = new Animal(4, 0, 400);  // fainted

        Animal[] as = {a1, a2, a3, a4};
        p.setAnimals(as);

        assertArrayEquals(new Animal[]{a1, a2, a3}, p.swappableAnimals());

        Trainer o = new Trainer();
        o.setAnimals(new Animal[]{new Animal(1, 1, 1)});

        Battle battle = new Battle(p, o, Climate.PLAINS);
        assertEquals(a1, battle.getPlayerAnimal());

        assertThrows(IllegalArgumentException.class, () -> battle.playerSwapAnimal(a1));  // animal already out
        assertThrows(IllegalArgumentException.class, () -> battle.playerSwapAnimal(a4));  // animal fainted

        battle.playerSwapAnimal(a2);

        assertEquals(a2, battle.getPlayerAnimal());
    }

    @Test
    void captureTest(){
        boolean boolArray[] = new boolean[1000];
        int worstNumber = 0;
        int bestNumber = 0;
        for(int i=0; i<1000; i++){
            Animal pa = new Animal(200, 200, 10); //Best possible chance to capture, 1% health, min level 1
            Move pm = new Move("Player Move", 20, 2);
            Animal[] pas = new Animal[1]; 
            pas[0] = pa; //Player animals set to one animal
            Move[] pms = new Move[4];
            pms[0] = pm; 
            pa.setMoves(pms);

            Animal oa = new Animal(100,1,10);
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

            WildBattle b = new WildBattle(p, t, c);
            
            boolean captureCheck = b.captureAnimal();
            boolArray[i] = captureCheck;
     }
        for (int i=0; i<1000; i++){
            if (boolArray[i] == true){
                bestNumber+=1;
            }
        }
        assertEquals(580, bestNumber, 130);
        for(int i=0; i<1000; i++){ //Worst possible chance to capture, 100% health, max level 99
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
            oa.setLevel(99);

            Player p = new Player();
            p.setAnimals(pas);
            Trainer t = new Trainer();
            t.setAnimals(oas);

            Climate c = Climate.DESERT;

            WildBattle b = new WildBattle(p, t, c);
            
            boolean captureCheck = b.captureAnimal();
            boolArray[i] = captureCheck;
     }
     for (int i=0; i<1000; i++){
        if (boolArray[i] == true){
            worstNumber+=1;
        }
    }
    assertEquals(350, worstNumber, 130);
    }



    @Test
    void healTest(){
        Animal a1 = new Animal(100, 20, 100);
        assertEquals(20, a1.getCurrentHP());
        a1.healMove(20);
        assertEquals(40, a1.getCurrentHP());
        Animal a2 = new Animal(100, 100, 100);
        a2.healMove(20);
        assertEquals(100, a2.getCurrentHP());
    }

    @Test

    void addExpTest(){ //unit test, just tests experience features but relies on levelUp function working
        Animal a1 = new Animal(100, 20, 100);
        assertEquals(0, a1.getExP());//equivalence class, new animal with no exp
        a1.addExp();
        assertEquals(1, a1.getExP());//equivalence class, normal exp gain
        a1.addExp();
        assertEquals(2, a1.getExP());
        a1.addExp();
        assertEquals(0, a1.getExP());//equivalence class, existing animal with reset exp
        
    }

    @Test

    void levelUpTest(){
        Animal a1 = new Animal(100, 20, 100);
        assertEquals(1, a1.getLevel());//equivalence class, new animal with starting level
        a1.levelUp();
        assertEquals(2, a1.getLevel());//equivalence class, normal level up
        assertEquals(105, a1.getMaxHP());
        assertEquals(102, a1.getAD());
        a1.levelUp();
        a1.levelUp();
        a1.levelUp();
        a1.levelUp();
        a1.levelUp();
        a1.levelUp();
        a1.levelUp();
        a1.levelUp();
        assertEquals(10, a1.getLevel());//equivalence class, max level newly reached
        assertEquals(151, a1.getMaxHP());
        assertEquals(118, a1.getAD());
        a1.levelUp();
        assertEquals(10, a1.getLevel());//equivalence class, no level change because max level already reached
        assertEquals(151, a1.getMaxHP());
        assertEquals(118, a1.getAD());


    }

    @Test

    void learnMoveTest(){
        Animal oa = new Animal(100,100,10);
        Move om1 = new Move("Opponent Move 1", 30, 1);
        Move om2 = new Move("Opponent Move 2", 20, 1);
        Animal[] oas = new Animal[1];
        oas[0] = oa;
        Move[] oms = new Move[4];
        oms[0] = om1;
        oms[1] = om2;
        oa.setMoves(oms);
        assertTrue(oa.learnMove("Bite"));
        assertFalse(oa.learnMove("Bite"));//fails, edge case: already know this move
        assertFalse(oa.learnMove("Rollover"));//fails, edge case: not high enough level to learn move
        assertFalse(oa.learnMove("dasdfas"));//fails, edge case: move does not exist
        assertTrue(oa.learnMove("Scratch"));
        assertFalse(oa.learnMove("Dash"));//fails, edge case: already know four moves
    }
}
