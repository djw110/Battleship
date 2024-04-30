import java.util.Random;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        Game g = new Game();
        Scanner keyboard = new Scanner(System.in);

        Random coin = new Random();
        int flip = coin.nextInt(0,2);

        System.out.println(g);

        flip = 0;
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

                                String result = g.makePlayerMove(nextMove);
                                if (result != null){
                                    System.out.println(result);
                                }

                                System.out.println("");
                                validator = true;
                            }
                        }
                    }
                    System.out.println(g);
                }
        }
        keyboard.close();
    }
}
