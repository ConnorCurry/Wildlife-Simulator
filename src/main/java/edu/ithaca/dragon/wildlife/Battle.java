package edu.ithaca.dragon.wildlife;

public class Battle {

    private Player currPlayer;
    private Trainer currOpponent;
    private Climate climate;
    private Animal playerAnimal;
    private Animal oppAnimal;

    public Battle(Player player, Trainer trainer, Climate climate) {
        currPlayer = player;
        currOpponent = trainer;
        this.climate = climate;
        playerAnimal = player.getFirstAnimal();
        oppAnimal = trainer.getFirstAnimal();
    }

    // takes move input from player and attacks opponent
    public void playerAttack(Move selectedMove) {
        int dmg = selectedMove.getDamage() + playerAnimal.getAD(); //amount to apply

    }

    // program selects a move from the opponent's moveset and attacks player
    public void opponentAttack() {
        //decide on best move
    }
}
