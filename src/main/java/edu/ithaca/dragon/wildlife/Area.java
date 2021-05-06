package edu.ithaca.dragon.wildlife;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class Area {
    Trainer[] trainers;
    Climate climate;
    int currentTrainer;
    
    @JsonCreator
    public Area(@JsonProperty("trainers") Trainer[] trainers, @JsonProperty("climate") Climate climate, @JsonProperty("currentTrainer") int currentTrainer) {
        this.trainers = trainers;
        this.climate = climate;
        this.currentTrainer = currentTrainer;
    }

    public Area(Trainer[] trainers, Climate climate) {
        this.trainers = trainers;
        this.climate = climate;
        this.currentTrainer = 0;
    }

    public Area(String data) {
        
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
}
