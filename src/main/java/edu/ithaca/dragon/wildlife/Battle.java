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
        selectedMove.decrementAmountLeft(); //move gets -1 amtLeft
        this.oppAnimal.receiveDamage(dmg); //apply damage

    }

    // program selects a move from the opponent's moveset and attacks player
    public void opponentAttack() {
        //decide on best move
        Move[] moves = oppAnimal.getMoves();
        Move move = null;
        for(int i = 0; i < moves.length; i++) { //for all animal moves
            if(moves[i] != null) { //if the move at index i of moves isn't null
                if(moves[i].getAmountLeft() > 0) { //If still can use move at index i
                    if(move == null) { //first valid move gets caught here
                        move = moves[i];
                    } else { 
                        if(moves[i].getDamage() > move.getDamage() && moves[i].getAmountLeft() > 0) { //test if this move is better than move
                            move = moves[i];
                        } //if not better do nothing
                    }
                }
            }
        }
        
        
        
        //move is set to highest damage move that the opponent animal has left
        int dmg;
        if(move != null) {
            dmg = move.getDamage() + oppAnimal.getAD(); //amount to apply
            move.decrementAmountLeft(); //move gets 1 less amtLeft
        } else {
            dmg = oppAnimal.getAD(); //amount to apply
        }

        //apply damage
        this.playerAnimal.receiveDamage(dmg); //apply damage
        

       
        
    }
}
