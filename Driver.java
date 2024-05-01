import java.util.Random;
import java.util.Scanner;

/**
 * Simulates a single game of battleship using the Game class.
 */
public class Driver {
    public static void main(String[] args) {

        //Initialize new Game, random object, and Scanner for system input.
        Game g = new Game();
        Scanner keyboard = new Scanner(System.in);
        Random coin = new Random();

        //Generate a random 50% chance value.
        int flip = coin.nextInt(0,2);

        //Display board
        System.out.println(g);

        //If the player wins the coin toss::
        if(flip == 0){
            System.out.println("You won the coin toss and play first.");

            while(!g.playerDefeated() && !g.computerDefeated()){
                
                    //Ensure move is valid. Continue to prompt user until valid move is input.
                    boolean validator = false;
                    
                    while(validator == false){

                        //Take user input for move.
                        System.out.print("Your Move: ");
                        String nextMove = keyboard.nextLine();

                        //Check if the move fits proper formatting
                        if (nextMove.length() > 1 && Character.isDigit(nextMove.charAt(1)) && 
                        (nextMove.length() < 3 || nextMove.length() < 4 && Character.isDigit(nextMove.charAt(2)))){

                            //Create location values from the validated input string.
                            int row = Character.toLowerCase(nextMove.charAt(0)) - 'a' + 0;
                            int col = Integer.parseInt(nextMove.substring(1)) - 1;

                            //Check if the location is inbounds.
                            if (row <= 9 && row >= 0 && col <= 9 && col >= 0){

                                //Pass the move to the game and print any resulting messages.
                                String pResult = g.makePlayerMove(nextMove);
                                if (pResult != null){
                                    System.out.println(pResult);
                                }

                                System.out.println("");
                                validator = true;
                            }
                        }
                    }

                    //Display board
                    System.out.println(g);

                    //Check for user input and declare computer's turn.
                    System.out.print("Computers Turn: Press Enter to Continue.");
                    keyboard.nextLine();

                    //Pass computer's move to game and print the chosen move and any resulting messages
                    String[] cResult = g.makeComputerMove();
                    System.out.println(String.format("The Computer Chose %s", cResult[0]));
                    if(cResult[1] != null){
                        System.out.println(cResult[1]);
                    }

                    //Display board
                    System.out.println("");
                    System.out.println(g);
                }
        }
        else {
            System.out.println("The Computer won the coin toss and plays first.");

            while(!g.playerDefeated() && !g.computerDefeated()){
                
                    //Check for user input and declare computer's turn.
                    System.out.print("Computers Turn: Press Enter to Continue.");
                    keyboard.nextLine();

                    //Pass computer's move to game and print the chosen move and any resulting messages
                    String[] cResult = g.makeComputerMove();
                    System.out.println(String.format("The Computer Chose %s", cResult[0]));
                    if(cResult[1] != null){
                        System.out.println(cResult[1]);
                    }

                    //Display board
                    System.out.println("");
                    System.out.println(g);

                    //Ensure move is valid. Continue to prompt user until valid move is input.
                    boolean validator = false;
                    
                    while(validator == false){

                        //Take user input for move.
                        System.out.print("Your Move: ");
                        String nextMove = keyboard.nextLine();

                        //Check if the move fits proper formatting
                        if (nextMove.length() > 1 && Character.isDigit(nextMove.charAt(1)) && 
                        (nextMove.length() < 3 || nextMove.length() < 4 && Character.isDigit(nextMove.charAt(2)))){

                            //Create location values from the validated input string.
                            int row = Character.toLowerCase(nextMove.charAt(0)) - 'a' + 0;
                            int col = Integer.parseInt(nextMove.substring(1)) - 1;

                            //Check if the location is inbounds.
                            if (row <= 9 && row >= 0 && col <= 9 && col >= 0){

                                //Pass the move to the game and print any resulting messages.
                                String pResult = g.makePlayerMove(nextMove);
                                if (pResult != null){
                                    System.out.println(pResult);
                                }

                                System.out.println("");
                                validator = true;
                            }
                        }
                    }

                    //Display Board
                    System.out.println(g);

                }
        }

        //Check if the game's playerboard or userboard is defeated, and print appropriate message. Close inputs and terminate.
        if (g.playerDefeated()){
            System.out.println("Game Over");
            System.out.println("The Computer Sank All Of Your Ships!");
        }
        else if(g.computerDefeated()){
            System.out.println("You won!");
            System.out.println("You Sunk All of the Enemy Ships!");
        }
        keyboard.close();
    }
}
