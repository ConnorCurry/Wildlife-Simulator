package edu.ithaca.dragon.wildlife;
import java.util.Random;

public class WildBattle extends Battle {
    Boolean hasRun;
    
    public WildBattle(Player player, Trainer trainer, Climate climate) {
        super(player, trainer, climate);
        hasRun = false;
    }

    //ends battle without fighting
    //this will be a bit different for initial demo, as there is only the demo battle, and no greater map / changelocation
    public void run() {
        hasRun = true;
        System.out.println("You ran away!");
        winner = currOpponent;
    }

    public boolean captureAnimal(){
        //~10% of chance determined from health
        //25% chance determined from level
        Random rand = new Random();
        int odds = 60;
        odds -= (this.oppAnimal.getCurrentHP()/this.oppAnimal.getMaxHP())*10;
        odds -= (this.oppAnimal.getLevel()/4);
        int selection = rand.nextInt(100);
        if (selection > odds){
            System.out.println("The animal was not successfully captured");
            return false;
        }
        else{
            System.out.println("You successfully caught the " + oppAnimal.getName() + ".");
            winner = currPlayer;
            currPlayer.addToParty(oppAnimal);
            return true;
        }
    }
}
