
package edu.ithaca.dragon.wildlife;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StatusEffectTest {

    @Test
    void getStatusEffectTest(){
        GetStatusEffect gse = new GetStatusEffect();
        //Test Poison
        StatusEffect p = gse.getPoison();
        assertNotNull(p);
        assertEquals(6, p.getLife());
        assertEquals(5, p.getDamage());
        //Test Bleed
        StatusEffect bl = gse.getBleed();
        assertNotNull(bl);
        assertEquals(9, bl.getLife());
        assertEquals(3, bl.getDamage());
        //Test Burn
        StatusEffect b = gse.getBurn();
        assertNotNull(b);
        assertEquals(4, b.getLife());
        assertEquals(7, b.getDamage());
    }

    @Test
    void moveWithStatusEffectTest() {
        Move m = new Move("demo", 10, 10, "poison");
        assertEquals(6, m.getEffect().getLife());
        assertEquals(5, m.getEffect().getDamage());

        m = new Move("demo2", 10, 10, "bleed");
        assertEquals(9, m.getEffect().getLife());
        assertEquals(3, m.getEffect().getDamage());

        m = new Move("demo3", 10, 10, "burn");
        assertEquals(4, m.getEffect().getLife());
        assertEquals(7, m.getEffect().getDamage());
    }
    
}
