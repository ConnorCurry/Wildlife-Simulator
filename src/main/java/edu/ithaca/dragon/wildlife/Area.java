package edu.ithaca.dragon.wildlife;

public class Area {
    Trainer[] trainers;
    Climate climate;
    
    public Area(Trainer[] trainers, Climate climate) {
        this.trainers = trainers;
        this.climate = climate;
    }

    public Trainer[] getTrainers() {
        return trainers;
    }

    public Climate getClimate() {
        return climate;
    }
}
