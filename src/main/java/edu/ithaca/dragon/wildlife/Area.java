package edu.ithaca.dragon.wildlife;

import java.util.Random;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class Area {
    Trainer[] trainers;
    Climate climate;
    int currentTrainer;
    Trainer[] wildEncounters;
    
    @JsonCreator
    public Area(@JsonProperty("trainers") Trainer[] trainers, 
        @JsonProperty("climate") Climate climate, 
        @JsonProperty("currentTrainer") int currentTrainer,
        @JsonProperty("wildEncounters") Trainer[] wildEncounters) {
        this.trainers = trainers;
        this.climate = climate;
        this.currentTrainer = currentTrainer;
        this.wildEncounters = wildEncounters;
    }

    public Area(Trainer[] trainers, Climate climate) {
        this.trainers = trainers;
        this.climate = climate;
        this.currentTrainer = 0;
    }

    public Area(String data) {
        
    }
    
    public void setWildEncounters(Trainer[] wildEncounterTrainers) {
        this.wildEncounters = wildEncounterTrainers;
    }

    public Trainer[] getTrainers() {
        return trainers;
    }

    public Climate getClimate() {
        return climate;
    }

    public int getCurrentTrainer() {
        return currentTrainer;
    }

    public Trainer startWildEncounter() {
        Random rand = new Random();
        return wildEncounters[rand.nextInt(wildEncounters.length)];
    }

    public Trainer[] getWildEncounters(){
        return wildEncounters;
    }
}
