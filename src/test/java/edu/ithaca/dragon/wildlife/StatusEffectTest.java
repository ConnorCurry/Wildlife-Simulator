
package edu.ithaca.dragon.wildlife;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StatusEffectTest {

    @Test
    void GetStatusEffectTest(){
        GetStatusEffect gse = new GetStatusEffect();
        StatusEffect p = gse.getPoison();
        assertEquals(6, actual);
    }
    
}
