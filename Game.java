public class Game {
    private ComputerBoard computer;
    private UserBoard player;

    public Game(){
        computer = new ComputerBoard("compFleet.txt");
        player = new UserBoard("userFleet.txt");
    }

    public String[] makeComputerMove(){
        return player.makeComputerMove();
    }

    public String makePlayerMove(String m){
        return computer.makePlayerMove(new Move(m));
    }

    public boolean playerDefeated(){
        return player.gameOver();
    }

    public boolean computerDefeated(){
        return computer.gameOver();
    }

    public String toString(){
        String op = String.format("USER\n");
        op += player;
        op += String.format("\nCOMPUTER\n");
        op += computer;
        return op;
    }
}
