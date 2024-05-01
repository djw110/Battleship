import java.util.Random;
import java.util.ArrayList;

/**
 * UserBoard is-a board which contains a grid of cells owned by the User-player. Can be interacted with by a game via moves.
 */
public class UserBoard extends Board {

    //Initialize a random object and arrayList of possible moves.
    private Random rand;
    private ArrayList<Move> moves;

    /**
     * Standard constructor. Initializes board with appropriate fleet with super constructor and arrayList of all possible moves.
     * @param s
     */
    public UserBoard(String s){

        super(s);
        rand = new Random();
        moves = new ArrayList<Move>();

        //For every cell in the grid, create a new possible move in the list given it's location.
        for (int r = 0; r <= 9; r++){
            for (int c = 0; c <= 9; c++){
                moves.add(new Move(r, c));
            }
        }

    }

    /**
     * Take a random move from the moves list, remove it from the list and return a reference to it.
     * @return The picked move.
     */
    public Move pickRandomMove(){
        int mIndex = rand.nextInt(moves.size());
        return moves.remove(mIndex);
    }

    /**
     * Generates a random possible move and applies it to the layout. Checks to see if ships are sunk and updates the appropriate cells.
     * @return : Array of two Strings. 1: Computer's move location. 2: Messages that may arise from game events.
     */
    public String[] makeComputerMove(){

        //Create randomly generated move, and apply it to the grid. Initialize output array.
        Move move = pickRandomMove();
        String[] op = new String[2];
        CellStatus status = super.applyMoveToLayout(move);

        //Set the move location to the first String in the output array.
        op[0] = move.toString();

        //Check the status of the hit cell. If it is a ship, update the fleet with the corresponding ship type. Otherwise, return null.
        switch(status){
            case AIRCRAFT_CARRIER:
            
                //If updateFleet returns true, the ship is sunk. Therefore, update all of the ship's assigned cells to SUNK.
                if(super.getFleet().updateFleet(ShipType.ST_AIRCRAFT_CARRIER)){

                    //Check for matching cellstatus for every cell in the row, then update.
                    for (int i = 0; i <= 9; i++){
                        if (super.getLayout().get(move.row()).get(i) == CellStatus.AIRCRAFT_CARRIER_HIT){
                            super.getLayout().get(move.row()).set(i, CellStatus.AIRCRAFT_CARRIER_SUNK);
                        }
                    }

                    //Check for matching cellstatus for every cell in the column, then update.
                    for (ArrayList<CellStatus> nRow : super.getLayout()) {
                        if (nRow.get(move.col()) == CellStatus.AIRCRAFT_CARRIER_HIT){
                            nRow.set(move.col(), CellStatus.AIRCRAFT_CARRIER_SUNK);
                        }
                    }

                    //Add return message to the second String in the output array.
                    op[1] = "The Computer sunk your Aircraft Carrier!";
                }
                break;
            case DESTROYER: 

                //If updateFleet returns true, the ship is sunk. Therefore, update all of the ship's assigned cells to SUNK.
                if(super.getFleet().updateFleet(ShipType.ST_DESTROYER)){

                    //Check for matching cellstatus for every cell in the row, then update.
                    for (int i = 0; i <= 9; i++){
                        if (super.getLayout().get(move.row()).get(i) == CellStatus.DESTROYER_HIT){
                            super.getLayout().get(move.row()).set(i, CellStatus.DESTROYER_SUNK);
                        }
                    }

                    //Check for matching cellstatus for every cell in the column, then update.
                    for (ArrayList<CellStatus> nRow : super.getLayout()) {
                        if (nRow.get(move.col()) == CellStatus.DESTROYER_HIT){
                            nRow.set(move.col(), CellStatus.DESTROYER_SUNK);
                        }
                    }

                    //Add return message to the second String in the output array.
                    op[1] = "The Computer sunk your Destroyer!";
                }
                break;
            case SUB: 

                //If updateFleet returns true, the ship is sunk. Therefore, update all of the ship's assigned cells to SUNK.
                if(super.getFleet().updateFleet(ShipType.ST_SUB)){

                    //Check for matching cellstatus for every cell in the row, then update.
                    for (int i = 0; i <= 9; i++){
                        if (super.getLayout().get(move.row()).get(i) == CellStatus.SUB_HIT){
                            super.getLayout().get(move.row()).set(i, CellStatus.SUB_SUNK);
                        }
                    }

                    //Check for matching cellstatus for every cell in the column, then update.
                    for (ArrayList<CellStatus> nRow : super.getLayout()) {
                        if (nRow.get(move.col()) == CellStatus.SUB_HIT){
                            nRow.set(move.col(), CellStatus.SUB_SUNK);
                        }
                    }

                    //Add return message to the second String in the output array.
                    op[1] = "The Computer sunk your Sub!";
                }
                break;
            case CRUISER:
            
                //If updateFleet returns true, the ship is sunk. Therefore, update all of the ship's assigned cells to SUNK.
                if(super.getFleet().updateFleet(ShipType.ST_CRUISER)){

                    //Check for matching cellstatus for every cell in the row, then update.
                    for (int i = 0; i <= 9; i++){
                        if (super.getLayout().get(move.row()).get(i) == CellStatus.CRUISER){
                            super.getLayout().get(move.row()).set(i, CellStatus.CRUISER);
                        }
                    }

                    //Check for matching cellstatus for every cell in the column, then update.
                    for (ArrayList<CellStatus> nRow : super.getLayout()) {
                        if (nRow.get(move.col()) == CellStatus.CRUISER_HIT){
                            nRow.set(move.col(), CellStatus.CRUISER_SUNK);
                        }
                    }

                    //Add return message to the second String in the output array.
                    op[1] = "The Computer sunk your Cruiser!";
                }
                break;
            case BATTLESHIP: 

                //If updateFleet returns true, the ship is sunk. Therefore, update all of the ship's assigned cells to SUNK.
                if(super.getFleet().updateFleet(ShipType.ST_BATTLESHIP)){

                    //Check for matching cellstatus for every cell in the row, then update.
                    for (int i = 0; i <= 9; i++){
                        if (super.getLayout().get(move.row()).get(i) == CellStatus.BATTLESHIP_HIT){
                            super.getLayout().get(move.row()).set(i, CellStatus.BATTLESHIP_SUNK);
                        }
                    }

                    //Check for matching cellstatus for every cell in the column, then update.
                    for (ArrayList<CellStatus> nRow : super.getLayout()) {
                        if (nRow.get(move.col()) == CellStatus.BATTLESHIP_HIT){
                            nRow.set(move.col(), CellStatus.BATTLESHIP_SUNK);
                        }
                    }

                    //Add return message to the second String in the output array.
                    op[1] = "The Computer sunk your Battleship!";
                }
                break;

                //Set the event message string to blank if no ships sunk.
            default:
                op[1] = null;
        }

        return op;
    }

    /**
     * Display a grid of UserBoards's data with content of each cell on the user-visible side showing, aka. the second value in the
     * Cellstatus toString.
     @return A formatted and labelled grid displaying UserBoard's data.
     */
    public String toString(){
        //Display column numbers
        String op = String.format("  1 2 3 4 5 6 7 8 9 10\n");
        for (int i = 0; i<=9; i++){
            //Display the letter of each row, then the user-side of each cellStatus in the row. Add new line
            op += String.valueOf((char)(i + 65)) + " ";
            
            for (CellStatus col : super.getLayout().get(i)) {
                op += col.toString().substring(1) + " ";
            }
            op += String.format("\n");
        }

        //Return result
        return op;
    }
}
