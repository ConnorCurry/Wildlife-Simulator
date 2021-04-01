package edu.ithaca.dragon.wildlife;

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
    }

    public boolean captureAnimal() {
        return false;
    }
}
