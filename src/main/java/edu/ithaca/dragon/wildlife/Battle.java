package edu.ithaca.dragon.wildlife;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Battle {

    protected Player currPlayer;
    protected Trainer currOpponent;
    private Climate climate;
    private Animal playerAnimal;
    protected Animal oppAnimal;
    protected Trainer winner = null; //only set when battle is won

    public Battle(Player player, Trainer trainer, Climate climate) {
        currPlayer = player;
        currOpponent = trainer;
        this.climate = climate;
        playerAnimal = player.firstAnimal();
        oppAnimal = trainer.firstAnimal();
    }

    /**
     * swaps current player animal to animal if swappable and not out
     * @param animal to swap to
     */
    public void playerSwapAnimal(Animal animal) {
        List<Animal> swappable = Arrays.asList(currPlayer.swappableAnimals());
        if (!(animal.equals(playerAnimal)) && swappable.contains(animal)) {
            System.out.println("\n" + playerAnimal.getName() + " has been recalled.\n");
            playerAnimal = animal;
            System.out.println("\n" + playerAnimal.getName() + " I choose you!\n");
        } else {
            throw new IllegalArgumentException("Cannot swap to that Animal");
        }
    }

    // takes move input from player and attacks opponent
    public void playerAttack(Move selectedMove) {

        Scanner reader = new Scanner(System.in);
        boolean run = true;
        //apply status effect
        if(this.playerAnimal.getCurrentEffect() != null) {
            //apply effect damage
            this.playerAnimal.receiveDamage(this.playerAnimal.getCurrentEffect().getDamage());
            //decrement life
            this.playerAnimal.getCurrentEffect().decrementLife();
        }
        //if animal died
        if(playerAnimal.getCurrentHP() <0) {
            //Need to choose new animal for turn
            Animal[] animals = this.getPlayerAnimalsArray();
            ArrayList<Animal> validAnimals = new ArrayList<Animal>();
            //Get list of valid animals
            for(int i = 0; i < animals.length; i++) {
                if(animals[i].getCurrentHP() > 0) {
                    validAnimals.add(animals[i]);
                }
            }      
                
            int ans = -1;
            if(validAnimals.isEmpty()){ //if valid animals in party
                for(int i = 0; i < validAnimals.size(); i++) {
                    System.out.println((i+1) + " "+ validAnimals.get(i).getName() + " : HP=" + validAnimals.get(i).getCurrentHP() + "/" + validAnimals.get(i).getMaxHP()+ " ; AD = " + validAnimals.get(i).getAD());
                }
                ans = reader.nextInt();
                if(ans < 1 || ans > validAnimals.size()){
                    ans = 1;
                }
                this.playerAnimal = validAnimals.get(ans-1);
            }   
                    
        } else { //If Animal Survived Status Effect (if no effect, animal will just start turn normally here)
            int dmg = selectedMove.getDamage() + playerAnimal.getAD(); //amount to apply
            selectedMove.decrementAmountLeft(); //move gets -1 amtLeft
            this.oppAnimal.receiveDamage(dmg); //apply damage
            if(this.oppAnimal.getCurrentHP() <= 0){ //deadly attack
                System.out.println("Oponnent's " + oppAnimal.getName() + " has fainted!");
                Animal newOA = this.currOpponent.nextAnimal();
                if(newOA == null) { //no more valid animals
                    this.winner = this.currPlayer;
                } else {
                    this.oppAnimal = newOA;
                    System.out.println("You are now facing " + oppAnimal.getName() + "!");
                }   
            }
        }         
    }

    // program selects a move from the opponent's moveset and attacks player
    public void opponentAttack() {
        boolean run = true;
        //apply status effect
        if(this.oppAnimal.getCurrentEffect() != null) {
            //apply effect damage
            this.oppAnimal.receiveDamage(this.oppAnimal.getCurrentEffect().getDamage());
            //decrement life of effect
            this.oppAnimal.getCurrentEffect().decrementLife();
        }
        //if animal died
        if(oppAnimal.getCurrentHP() <0) {
            //Need to choose new animal for turn
            Animal[] animals = this.getOpponentAnimalsArray();
            ArrayList<Animal> validAnimals = new ArrayList<Animal>();
            //Get list of valid animals
            for(int i = 0; i < animals.length; i++) {
                if(animals[i].getCurrentHP() > 0) {
                    validAnimals.add(animals[i]);
                }
            }      
                
            if(validAnimals.isEmpty()){ //if no valid animals in party
                this.winner = this.currPlayer;
                return;
            } else { //swap to first valid animal
                this.oppAnimal = validAnimals.get(0);
            }
        } else {
            //decide on best move
            Move[] moves = oppAnimal.getMoves();
            Move move = null;
            for(int i = 0; i < moves.length; i++) { //for all animal moves
                // TODO this while loop is causing an infinite loop, run deadlyOpponentAttackTest or opponentAttackTest!
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
                if(move == null){
                    move = new Move("nullmove", 0, 99);
                }
            }         
            
            
            //move is set to highest damage move that the opponent animal has left
            int dmg;
            if(move != null) {
                dmg = move.getDamage() + oppAnimal.getAD();//amount to apply
                String strDMG = Integer.toString(dmg);
                System.out.println("\nOponnent's "+ oppAnimal.getName() + " used " + move.getTitle() + " which dealt " + strDMG + " to " + this.playerAnimal.getName() + "!\n");
                move.decrementAmountLeft(); //move gets 1 less amtLeft


        //apply damage
        this.playerAnimal.receiveDamage(dmg); //apply damage
        if(this.playerAnimal.getCurrentHP() <= 0) { //Deadly attack
            System.out.println("Your " + playerAnimal.getName() + " has fainted!");
            Animal newPA = this.currPlayer.getNextAnimal();
            if(newPA == null) { //No more animals in party
                this.winner = this.currOpponent; //game over
            } else {
                this.playerAnimal = newPA; //set to next animal
                System.out.println("Your next animal: " + playerAnimal.getName() + " has been sent out!");
            }
        }
        
    }

    public Trainer getWinner() {
        return(this.winner);
    }

    public Animal getPlayerAnimal() {
        return playerAnimal;
    }

    public Animal getOpponentAnimal() {
        return oppAnimal;
    }

    public Animal[] getPlayerAnimalsArray() {
        return currPlayer.getAnimalsArray();
    }

    public Animal[] getOpponentAnimalsArray() {
        return(this.currOpponent.getAnimalsArray());
    }

}
