package edu.ithaca.dragon.wildlife;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


public class WildlifeSimulator {
    private HashMap<Integer, Area> areas = new HashMap<>();
    private HashMap<String, Animal> animals = new HashMap<>();
    private HashMap<String, Move> moveList = new HashMap<>();
    private Area currArea;
    private Battle currBattle;
    private Player player;
    
    public WildlifeSimulator(HashMap<Integer, Area> areas, Player player) {
        this.areas = areas;
        this.player = player;
        this.currArea = this.areas.get(1);
    }

    public WildlifeSimulator() {}

    /**
     * Initial area HashMap load from set file of areas
     * Loads in areas with their trainers and animals etc.
     * Loaded from target/initial/initial-areas.json
     */
    public void initalLoad() {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<HashMap<Integer, Area>> typeRef = new TypeReference<HashMap<Integer, Area>>(){};
        try {
            this.areas = mapper.readValue(new File("target/initial/initial-areas.json"), typeRef);
            this.currArea = this.areas.get(1);
        }
        catch (Exception e) {
            System.out.println("Failed to create new save file:\n" + e);
        }

    }

    public void loadFromSave() {
        
    }

    public Trainer startBattle(){
        currBattle = new Battle(player, currArea.getTrainers()[0], currArea.getClimate());
        // while no winner exists, run turns
        // in future we need to add a speed stat to animals to check who goes first
        Scanner scan = new Scanner(System.in);
        do {
            System.out.println("Player's " + currBattle.getPlayerAnimal().getName() + " health: " + currBattle.getPlayerAnimal().getCurrentHP());
            System.out.println("Opponent's " + currBattle.getOpponentAnimal().getName() + " health: " + currBattle.getOpponentAnimal().getCurrentHP());

            String selectedActionString;
            do{
                System.out.println("\nWill you: \nAttack\nSwap\n");
                selectedActionString = scan.nextLine().toLowerCase();
            } while (!selectedActionString.equals("attack") && !selectedActionString.equals("swap"));

            if (selectedActionString.equals("attack")){
                String selectedMoveString;
                Move selectedMove;
                Move[] pMoves = currBattle.getPlayerAnimal().getMoves();
                ArrayList<String> moveList = new ArrayList<>();
                do{
                    System.out.println("\nChoose a move: " + "\n");
                    
                    for (Move move : pMoves) {
                        if (move != null){
                            moveList.add(move.getTitle().toLowerCase());
                            System.out.println(move.getTitle());
                        }
                    }
                    
                    selectedMoveString = scan.nextLine().toLowerCase();
                } while (!moveList.contains(selectedMoveString));
                selectedMove = WildlifeSimulator.getMoveFromTitle(currBattle.getPlayerAnimal(), selectedMoveString);
                System.out.println("\nYour " + currBattle.getPlayerAnimal().getName() + " used " + selectedMove.getTitle() + " which dealt " + selectedMove.getDamage() + " damage to opponent's " + currBattle.getOpponentAnimal().getName() + "!\n");
                currBattle.playerAttack(selectedMove);
            }
            else if (selectedActionString.equals("swap")) {
                System.out.println("\nWhich animal will you swap to?\n");
                for (Animal animal : currBattle.getPlayerAnimalsArray()) {
                    if (animal != null && animal != currBattle.getPlayerAnimal()) {
                        System.out.println(animal.getName());
                    }
                }
                String swapString;
                ArrayList<String> namesList = new ArrayList<>();
                for (Animal animal : currBattle.getPlayerAnimalsArray()) {
                    if (animal != null) {
                        namesList.add(animal.getName().toLowerCase());
                    }
                }
                do {
                    swapString = scan.nextLine().toLowerCase();
                } while (!namesList.contains(swapString)); // not sure contains will work here

                for (Animal animal : currBattle.getPlayerAnimalsArray()) {
                    if (animal != null && animal.getName().toLowerCase().equals(swapString)) {
                        currBattle.playerSwapAnimal(animal);
                    }
                }
            }
            if(currBattle.getWinner() == null){
                currBattle.opponentAttack(); // Can we make this method print the text for what move was played and how much damage it did?
            }
        } while (currBattle.getWinner() == null);
        scan.close();
        if (currBattle.getWinner() == player) {
            System.out.println("You Win!");
        }
        else System.out.println("You Lose");
        return currBattle.getWinner();
    }



    public Trainer startWildBattle(){
        currBattle = new WildBattle(player, currArea.getTrainers()[0], currArea.getClimate());
        // while no winner exists, run turns
        // in future we need to add a speed stat to animals to check who goes first
        Scanner scan = new Scanner(System.in);
        boolean run = false;
        do {
            System.out.println("Player's "+ currBattle.getPlayerAnimal().getName() + " health: " + currBattle.getPlayerAnimal().getCurrentHP());
            System.out.println("Opponent's " + currBattle.oppAnimal.getName() + " health: " + currBattle.getOpponentAnimal().getCurrentHP()+"\n");

            String selectedActionString;
            do {
                System.out.println("Will you: \nAttack\nSwap\nRun\nCatch\n");
                selectedActionString = scan.nextLine().toLowerCase();
            } while (!selectedActionString.equals("attack") && !selectedActionString.equals("swap") && !selectedActionString.equals("run") && !selectedActionString.equals("catch"));

            if (selectedActionString.equals("attack")){
                String selectedMoveString;
                Move selectedMove;
                Move[] pMoves = currBattle.getPlayerAnimal().getMoves();
                ArrayList<String> moveList = new ArrayList<>();
                do{
                    System.out.println("\nChoose a move: " + "\n");
                    
                    for (Move move : pMoves) {
                        if (move != null){
                            moveList.add(move.getTitle().toLowerCase());
                            System.out.println(move.getTitle());
                        }
                    }
                    
                    selectedMoveString = scan.nextLine().toLowerCase();
                } while (!moveList.contains(selectedMoveString));
                selectedMove = WildlifeSimulator.getMoveFromTitle(currBattle.getPlayerAnimal(), selectedMoveString);
                int DMG = selectedMove.getDamage();
                String strDMG = Integer.toString(DMG);
                System.out.println("\nYour " + currBattle.getPlayerAnimal().getName() + " used " + selectedMove.getTitle() + " which dealt " + strDMG + " damage to opponent's " + currBattle.getOpponentAnimal().getName() + "!\n");
                currBattle.playerAttack(selectedMove);
            }
            
            else if (selectedActionString.equals("swap")) {
                System.out.println("\nWhich animal will you swap to?\n");
                for (Animal animal : currBattle.getPlayerAnimalsArray()) {
                    if (animal != null && animal != currBattle.getPlayerAnimal()){
                        System.out.println(animal.getName());
                    }
                }
                String swapString;
                ArrayList<String> namesList = new ArrayList<>();
                for (Animal animal : currBattle.getPlayerAnimalsArray()) {
                    if (animal != null){
                        namesList.add(animal.getName().toLowerCase());
                    }
                }
                do {
                    swapString = scan.nextLine().toLowerCase();
                } while (!namesList.contains(swapString)); // not sure contains will work here

                for (Animal animal : currBattle.getPlayerAnimalsArray()) {
                    if (animal != null && animal.getName().toLowerCase().equals(swapString)) {
                        currBattle.playerSwapAnimal(animal); // TODO fix, will throw exception if invalid animal is picked
                    }
                }
            }

            else if (selectedActionString.equals("run")) {
                ((WildBattle)currBattle).run();
                run = true;
            }

            else if (selectedActionString.equals("catch")) {
                ((WildBattle)currBattle).captureAnimal();
            }

            if(currBattle.getWinner() == null){
                currBattle.opponentAttack(); // Can we make this method print the text for what move was played and how much damage it did?
            } // Can we make this method print the text for what move was played and how much damage it did?
        } while (currBattle.getWinner() == null);
        scan.close();
        if (currBattle.getWinner() == player) {
            System.out.println("You Win!");
        }
        else if(run == false){
            System.out.println("You Lose");
        }
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

    public static HashMap<String, Animal> getAnimals(){
        HashMap<String, Animal> animals = new HashMap<>();
        Path fPath = Paths.get("src/main/java/edu/ithaca/dragon/wildlife/Animals.csv"); //Scanning CSV file function adapted from: https://www.java67.com/2015/08/how-to-load-data-from-csv-file-in-java.html
        try(BufferedReader br = Files.newBufferedReader(fPath, StandardCharsets.US_ASCII)) {
            String line = br.readLine();
            while (line != null) {
                String[] attributes = line.split(",");
                int i = 0;

                String aName = attributes[i];
                i++;
                String strHP = attributes[i];
                i++;
                int bHP = Integer.valueOf(strHP);
                String strAD = attributes[i];
                i++;
                int bAD = Integer.valueOf(strAD);
                HashMap<Integer, ArrayList<String>> possibleMove = new HashMap<>();
                while(i < attributes.length){
                    i++;
                    String[] move = attributes[i].split(" ");
                    String strLvl = move[0];
                    int lvl = Integer.valueOf(strLvl);
                    if(possibleMove.containsKey(lvl)){
                        possibleMove.get(1).add(move[1]);
                    }
                    else{
                        ArrayList<String> moveNames = new ArrayList<>();
                        moveNames.add(move[1]);
                        possibleMove.put(lvl, moveNames); 
                    }

                }
                Animal newAnimal = new Animal(bHP, bHP, bAD, aName, possibleMove);
                animals.put(aName, newAnimal);
                line = br.readLine();
            }

        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return animals;
    }

    public static HashMap<Integer, ArrayList<String>> animalLearnSet(String animalName) {
        HashMap<Integer, ArrayList<String>> learnSet = new HashMap<Integer, ArrayList<String>>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/java/edu/ithaca/dragon/wildlife/Animals.csv"));
            String line = br.readLine();
            while (line != null) {
                String[] attributes = line.split(",");
                if (attributes[0].toLowerCase().equals(animalName.toLowerCase())) {
                    for (int i=3; i<attributes.length; i++){
                        String[] moveData = attributes[i].split(" ");
                        Integer levelLearned = Integer.parseInt(moveData[0]);
                        if (learnSet.containsKey(levelLearned)) {
                            ArrayList<String> moves = learnSet.get(levelLearned);
                            if (moveData.length == 3){
                                moves.add(moveData[1] + " " + moveData[2]);
                            }
                            else {
                                moves.add(moveData[1]);
                            }
                            learnSet.put(levelLearned, moves);
                        }
                        else{
                            ArrayList<String> newMove = new ArrayList<String>();
                            if (moveData.length == 3){
                                newMove.add(moveData[1] + " " + moveData[2]);
                            } else {
                                newMove.add(moveData[1]);
                            }
                            learnSet.put(Integer.parseInt(moveData[0]), newMove);
                        }
                    }
                }
                line = br.readLine();
            }
            br.close();
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return learnSet;
    }

    public static HashMap<String, Move> getMoveList(){
        HashMap<String, Move> moveList = new HashMap<>();
        Path fPath2 = Paths.get("Moves.csv"); //Scanning CSV file function adapted from: https://www.java67.com/2015/08/how-to-load-data-from-csv-file-in-java.html
        //try(BufferedReader br = Files.newBufferedReader(fPath2, StandardCharsets.US_ASCII)) {
        try{
            BufferedReader br = new BufferedReader(new FileReader("src/main/java/edu/ithaca/dragon/wildlife/Moves.csv"));
            String line = br.readLine();
            while (line != null) {
                String[] attributes = line.split(",");
                int i = 0;
                
                String mName = attributes[i];
                i++;
                String strDmg = attributes[i];
                i++;
                int dmg = Integer.valueOf(strDmg);
                String strAmt = attributes[i];
                i++;
                int amt = Integer.valueOf(strAmt);

                if(i < attributes.length){
                    String strEffect = attributes[i];
                    Move newMove = new Move(mName, dmg, amt, strEffect);
                    moveList.put(mName, newMove);

                }
                else{
                    Move newMove = new Move(mName, dmg, amt);
                    moveList.put(mName, newMove);

                }
                line = br.readLine();
            }

        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
    
        return moveList;
    }
    public Area getCurrArea() {
        return currArea;
    }
    
    public HashMap<Integer, Area> getAreas() {
        return areas;
    }

    public void setCurrArea(Integer areaID) {
        currArea = areas.get(areaID);
    }
}

