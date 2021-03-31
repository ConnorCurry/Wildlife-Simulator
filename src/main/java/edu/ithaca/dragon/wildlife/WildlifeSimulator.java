package edu.ithaca.dragon.wildlife;

import java.util.HashMap;
import java.util.Scanner;

public class WildlifeSimulator {
    private HashMap<Integer, Area> areas = new HashMap<>();
    private Area currArea;
    private Battle currBattle;
    private Player player;
    
    public WildlifeSimulator() {
        Trainer[] trainers = {new Trainer()};
        Area area1 = new Area(trainers, Climate.PLAINS);
        areas.put(1, area1);
        this.currArea = areas.get(1);
    }

    public Trainer startBattle(){
        currBattle = new Battle(player, currArea.getTrainers()[0], currArea.getClimate());
        // while no winner exists, run turns
        // in future we need to add a speed stat to animals to check who goes first
        Scanner scan = new Scanner(System.in);
        do {
            String selectedMove;
            Move[] pMoves = currBattle.getPlayerAnimal().getMoves();
            do{
                System.out.println("Choose a move: " + "/n" + 
                pMoves[0].getTitle() + "/n" + 
                pMoves[1].getTitle() + "/n" + 
                pMoves[2].getTitle() + "/n" + 
                pMoves[3].getTitle());
                
                selectedMove = scan.nextLine().toLowerCase();
            } while (!pMoves[0].getTitle().toLowerCase().equals(selectedMove) && !pMoves[1].getTitle().toLowerCase().equals(selectedMove) && !pMoves[2].getTitle().toLowerCase().equals(selectedMove) && !pMoves[3].getTitle().toLowerCase().equals(selectedMove));
            
            // currBattle.playerAttack(selectedMove);
        
        } while (currBattle.getWinner() == null);
        scan.close();
        return null;
    }
    
    static Move getMoveFromTitle(Animal animal, String title) {
        return null;
    }
}
