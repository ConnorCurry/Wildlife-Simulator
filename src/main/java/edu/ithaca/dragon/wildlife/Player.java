package edu.ithaca.dragon.wildlife;

import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;


public class Player extends Trainer{
 
    /**
     * Saves party info to a csv file with a default path
     */
    public void savePartyInfo() {
        ObjectMapper mapper = new ObjectMapper();

        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File("src/main/java/edu/ithaca/dragon/wildlife/Player.csv"), this);
        }
        catch(Exception e) {
            System.out.println("Error Writing:\n" + e);
        }
    }

    /**
     * Saves party info to a csv file with a custom path
     */
    public void savePartyInfo(String path) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(path), this);
        }
        catch(Exception e) {
            System.out.println("Error Writing:\n" + e);
        }
    }

    /**
     * reads and returns a Player from a default path
     */
    public static Trainer readPartyInfo() {
        ObjectMapper mapper = new ObjectMapper();

        Player p = null;
        try {
            p = mapper.readValue(new File("src/test/java/edu/ithaca/dragon/wildlife/PlayerTest.csv"), new TypeReference<Player>(){});
        }
        catch(Exception e) {
            System.out.println("Error Reading:\n" + e);
        }
        return p;
    }

    /**
     * reads and returns a Player from a custom path
     */
    public static Player readPartyInfo(String path) {
        ObjectMapper mapper = new ObjectMapper();

        Player p = null;
        try {
            p = mapper.readValue(new File(path), new TypeReference<Player>(){});
        }
        catch(Exception e) {
            System.out.println("Error Reading:\n" + e);
        }
        return p;
    }
}
