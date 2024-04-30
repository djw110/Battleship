import java.util.Random;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        Game g = new Game();
        Scanner keyboard = new Scanner(System.in);

        Random coin = new Random();
        int flip = coin.nextInt(0,2);

        System.out.println(g);

        if(flip == 0){
            System.out.println("You won the coin toss and play first.");

            while(!g.playerDefeated() && !g.computerDefeated()){
                
                    boolean validator = false;
                    
                    while(validator == false){

                        System.out.print("Your Move: ");
                        String nextMove = keyboard.nextLine();

                        if (nextMove.length() > 1 && Character.isDigit(nextMove.charAt(1)) && 
                        (nextMove.length() < 3 || nextMove.length() < 4 && Character.isDigit(nextMove.charAt(2)))){

                            int row = Character.toLowerCase(nextMove.charAt(0)) - 'a' + 0;
                            int col = Integer.parseInt(nextMove.substring(1)) - 1;

                            if (row <= 9 && row >= 0 && col <= 9 && col >= 0){

                                String pResult = g.makePlayerMove(nextMove);
                                if (pResult != null){
                                    System.out.println(pResult);
                                }

                                System.out.println("");
                                validator = true;
                            }
                        }
                    }
                    System.out.println(g);

                    System.out.print("Computers Turn: Press Enter to Continue.");
                    keyboard.nextLine();
                    String[] cResult = g.makeComputerMove();
                    System.out.println(String.format("The Computer Chose %s", cResult[0]));
                    if(cResult[1] != null){
                        System.out.println(cResult[1]);
                    }
                    System.out.println("");
                    System.out.println(g);
                }
        }
        else {
            System.out.println("The Computer won the coin toss and plays first.");

            while(!g.playerDefeated() && !g.computerDefeated()){
                
                    System.out.print("Computer's Move: Press Enter to Continue.");
                    keyboard.nextLine();
                    String[] cResult = g.makeComputerMove();
                    System.out.println(String.format("The Computer Chose %s", cResult[0]));
                    if(cResult[1] != null){
                        System.out.println(cResult[1]);
                    }
                    System.out.println("");
                    System.out.println(g);

                    boolean validator = false;
                    
                    while(validator == false){

                        System.out.print("Your Move: ");
                        String nextMove = keyboard.nextLine();

                        if (nextMove.length() > 1 && Character.isDigit(nextMove.charAt(1)) && 
                        (nextMove.length() < 3 || nextMove.length() < 4 && Character.isDigit(nextMove.charAt(2)))){

                            int row = Character.toLowerCase(nextMove.charAt(0)) - 'a' + 0;
                            int col = Integer.parseInt(nextMove.substring(1)) - 1;

                            if (row <= 9 && row >= 0 && col <= 9 && col >= 0){

                                String pResult = g.makePlayerMove(nextMove);
                                if (pResult != null){
                                    System.out.println(pResult);
                                }

                                System.out.println("");
                                validator = true;
                            }
                        }
                    }
                    System.out.println(g);

                }
        }
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
