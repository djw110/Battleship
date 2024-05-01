import java.util.ArrayList;

/**
 * ComputerBoard is-a board which contains a grid of cells owned by the Computer-opponent. Can be interacted with by a game via moves.
 */
public class ComputerBoard extends Board {

    /**
     * Standard constructor, passes f to super constructor.
     * @param f : Filename to be assigned to the super constructor.
     */
    public ComputerBoard(String f){
        super(f);
    }

    /**
     * Takes a proposed move and applies it to the layout. Checks to see if ships are sunk and updates the appropriate cells.
     * @param move : Move containing the location data for the cell to be affected.
     * @return : Messages that may arise from game events.
     */
    public String makePlayerMove(Move move){

        //Check if move is in bounds and non-duplicate
        if(moveAvailable(move)){

            //Apply move to layout and store result
            CellStatus status = super.applyMoveToLayout(move);

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

                        //Return message
                        return "You sunk my Aircraft Carrier!";
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

                        //Return message
                        return "You sunk my Destroyer!";
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

                        //Return message
                        return "You sunk my Sub!";
                    }
                    break;
                case CRUISER:
                    //If updateFleet returns true, the ship is sunk. Therefore, update all of the ship's assigned cells to SUNK.
                    if(super.getFleet().updateFleet(ShipType.ST_CRUISER)){

                        //Check for matching cellstatus for every cell in the row, then update.
                        for (int i = 0; i <= 9; i++){
                            if (super.getLayout().get(move.row()).get(i) == CellStatus.CRUISER_HIT){
                                super.getLayout().get(move.row()).set(i, CellStatus.CRUISER_SUNK);
                            }
                        }

                        //Check for matching cellstatus for every cell in the column, then update.
                        for (ArrayList<CellStatus> nRow : super.getLayout()) {
                            if (nRow.get(move.col()) == CellStatus.CRUISER_HIT){
                                nRow.set(move.col(), CellStatus.CRUISER_SUNK);
                            }
                        }

                        //Return message
                        return "You sunk my Cruiser!";
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

                        //Return message
                        return "You sunk my Battleship!";
                    }
                    break;
                default:
                    return null;
            }
        }
        //Report if move not available
        else{
            return "Duplicate Move, turn forfeit.";
        }
        return null;
    }
    


    /**
     * Displays a grid of ComputerBoard's data with content of each cell on the user-visible side showing, aka. the first value in the
     * Cellstatus toString.
     @return A formatted and labelled grid displaying ComputerBoard's data.
     */
    public String toString(){
        //Display column numbers
        String op = String.format("  1 2 3 4 5 6 7 8 9 10\n");
        
        for (int i = 0; i<=9; i++){

            //Display the letter of each row, then the user-side of each cellStatus in the row. Add new line
            op += String.valueOf((char)(i + 65)) + " ";
            for (CellStatus col : super.getLayout().get(i)) {
                op += col.toString().substring(0,1) + " ";
            }
            op += String.format("\n");
        }

        //Return result
        return op;
    }
}
