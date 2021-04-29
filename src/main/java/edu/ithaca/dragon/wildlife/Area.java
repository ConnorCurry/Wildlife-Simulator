package edu.ithaca.dragon.wildlife;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class Area {
    Trainer[] trainers;
    Climate climate;
    
    @JsonCreator
    public Area(@JsonProperty("trainers") Trainer[] trainers, @JsonProperty("climate") Climate climate) {
        this.trainers = trainers;
        this.climate = climate;
    }

    public Area(String data) {
        
    }

    public Trainer[] getTrainers() {
        return trainers;
    }

    public Climate getClimate() {
        return climate;
    }
}
