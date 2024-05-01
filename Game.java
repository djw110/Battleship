//Game class. Takes 2 boards and allows moves to be made in a streamlined manner. Displays both boards.
public class Game {

    //Declare boards
    private ComputerBoard computer;
    private UserBoard player;

    /**
     * Initialize new boards with appropriate fleet filenames.
     */
    public Game(){
        computer = new ComputerBoard("compFleet.txt");
        player = new UserBoard("userFleet.txt");
    }

    /**
     * Simulates a move against the player's board and returns result.
     * @return An array of strings. The first value will be the Computer's move location, the second will be any event messages that arise.
     */
    public String[] makeComputerMove(){
        return player.makeComputerMove();
    }

    /**
     * Simulates a move against the Computer's board by the player.
     * @param m : The String to be converted into a move location which is passed to makePlayerMove.
     * @return Any event messages that arise.
     */
    public String makePlayerMove(String m){
        return computer.makePlayerMove(new Move(m));
    }

    /**
     * Checks if the playerBoard is completely sunk.
     * @return If player has lost.
     */
    public boolean playerDefeated(){
        return player.gameOver();
    }

    /**
     * Checks if the computerBoard is completely sunk.
     * @return If the computer has lost.
     */
    public boolean computerDefeated(){
        return computer.gameOver();
    }

    /**
     * Displays 2 formatted grids containing the user and computer boards respectively.
     * Labels each.
     * @return String containing the two boards.
     */
    public String toString(){
        String op = String.format("USER\n");
        op += player;
        op += String.format("\nCOMPUTER\n");
        op += computer;
        return op;
    }
}
