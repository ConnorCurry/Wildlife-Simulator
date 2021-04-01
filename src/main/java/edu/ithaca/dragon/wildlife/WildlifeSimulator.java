package edu.ithaca.dragon.wildlife;

import java.util.ArrayList;
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
            System.out.println("Player animal health: " + currBattle.getPlayerAnimal().getCurrentHP());
            System.out.println("Opponent animal health: " + currBattle.getOpponentAnimal().getCurrentHP());

            String selectedActionString;
            System.out.println("Will you: /nAttack/nSwap");
            selectedActionString = scan.nextLine().toLowerCase();

            if (selectedActionString.equals("attack")){ //TODO check for invalid input
                String selectedMoveString;
                Move selectedMove;
                Move[] pMoves = currBattle.getPlayerAnimal().getMoves();
                do{
                    System.out.println("Choose a move: " + "/n" + 
                    pMoves[0].getTitle() + "/n" + 
                    pMoves[1].getTitle() + "/n" + 
                    pMoves[2].getTitle() + "/n" + 
                    pMoves[3].getTitle());
                    
                    selectedMoveString = scan.nextLine().toLowerCase();
                } while (!pMoves[0].getTitle().toLowerCase().equals(selectedMoveString) && !pMoves[1].getTitle().toLowerCase().equals(selectedMoveString) && !pMoves[2].getTitle().toLowerCase().equals(selectedMoveString) && !pMoves[3].getTitle().toLowerCase().equals(selectedMoveString));
                selectedMove = WildlifeSimulator.getMoveFromTitle(currBattle.getPlayerAnimal(), selectedMoveString);
                currBattle.playerAttack(selectedMove);
            }
            else if (selectedActionString.equals("swap")) {
                System.out.println("Which animal will you swap to?");
                for (Animal animal : currBattle.getPlayerAnimalsArray()) {
                    System.out.println(animal.getName());
                }
                String swapString;
                ArrayList<String> namesList = new ArrayList<>();
                for (Animal animal : currBattle.getPlayerAnimalsArray()) {
                    namesList.add(animal.getName().toLowerCase());
                }
                do {
                    swapString = scan.nextLine().toLowerCase();
                } while (!namesList.contains(swapString)); // not sure contains will work here

                for (Animal animal : currBattle.getPlayerAnimalsArray()) {
                    if (animal.getName().toLowerCase().equals(swapString)) {
                        currBattle.playerSwapAnimal(animal);
                    }
                }
            }
            currBattle.opponentAttack(); // Can we make this method print the text for what move was played and how much damage it did?
        } while (currBattle.getWinner() == null);
        scan.close();
        return currBattle.getWinner();
    }

    public Trainer startWildBattle(){
        currBattle = new WildBattle(player, currArea.getTrainers()[0], currArea.getClimate());
        // while no winner exists, run turns
        // in future we need to add a speed stat to animals to check who goes first
        Scanner scan = new Scanner(System.in);
        do {
            System.out.println("Player animal health: " + currBattle.getPlayerAnimal().getCurrentHP());
            System.out.println("Opponent animal health: " + currBattle.getOpponentAnimal().getCurrentHP());

            String selectedActionString;
            System.out.println("Will you: /nAttack/nSwap");
            selectedActionString = scan.nextLine().toLowerCase();

            if (selectedActionString.equals("attack")){ //TODO check for invalid input
                String selectedMoveString;
                Move selectedMove;
                Move[] pMoves = currBattle.getPlayerAnimal().getMoves();
                do{
                    System.out.println("Choose a move: " + "/n" + 
                    pMoves[0].getTitle() + "/n" + 
                    pMoves[1].getTitle() + "/n" + 
                    pMoves[2].getTitle() + "/n" + 
                    pMoves[3].getTitle());
                    
                    selectedMoveString = scan.nextLine().toLowerCase();
                } while (!pMoves[0].getTitle().toLowerCase().equals(selectedMoveString) && !pMoves[1].getTitle().toLowerCase().equals(selectedMoveString) && !pMoves[2].getTitle().toLowerCase().equals(selectedMoveString) && !pMoves[3].getTitle().toLowerCase().equals(selectedMoveString));
                selectedMove = WildlifeSimulator.getMoveFromTitle(currBattle.getPlayerAnimal(), selectedMoveString);
                currBattle.playerAttack(selectedMove);
            }
            
            else if (selectedActionString.equals("swap")) {
                System.out.println("Which animal will you swap to?");
                for (Animal animal : currBattle.getPlayerAnimalsArray()) {
                    System.out.println(animal.getName());
                }
                String swapString;
                ArrayList<String> namesList = new ArrayList<>();
                for (Animal animal : currBattle.getPlayerAnimalsArray()) {
                    namesList.add(animal.getName().toLowerCase());
                }
                do {
                    swapString = scan.nextLine().toLowerCase();
                } while (!namesList.contains(swapString)); // not sure contains will work here

                for (Animal animal : currBattle.getPlayerAnimalsArray()) {
                    if (animal.getName().toLowerCase().equals(swapString)) {
                        currBattle.playerSwapAnimal(animal);
                    }
                }
            }

            else if (selectedActionString.equals("run")) {
                ((WildBattle)currBattle).run();
            }

            else if (selectedActionString.equals("catch")) {
                ((WildBattle)currBattle).captureAnimal();
            }

            currBattle.opponentAttack(); // Can we make this method print the text for what move was played and how much damage it did?
        } while (currBattle.getWinner() == null);
        scan.close();
        return currBattle.getWinner();
    }
    
    /*
    * @param animal is the animal that the move belongs to
    * @param title is the name of the move
    * @returns the move object
    */
    static Move getMoveFromTitle(Animal animal, String title) {
        Move[] moves = animal.getMoves();
        for (int i = 0; i < 4; i++) {
            if (moves[i] != null && moves[i].getTitle().toLowerCase().equals(title)) {
                return moves[i];
            }
        }
        throw new IllegalArgumentException();
    }
}
