import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

/** A board represents a layout of cellstatuses in a grid, and a fleet positioned on them.
 Different types of boards should inherit from this class.
 */
public abstract class Board {
    /**
    Declare variables for the cellstatus grid, size of the board, and fleet.
     */
    private ArrayList<ArrayList<CellStatus>> layout = new ArrayList<ArrayList<CellStatus>>();
    private Fleet fleet;
    public static final int SIZE = 5;
    
    /**
     * Constructor for a Board object. 
     * Initializes fleets and reads input file to assign appropriate cellstatuses in the grid to match input data.
     * @exception FileNotFound : Gracefully terminates program
     * @param fileName : The name of the file to be read when assigning fleet positions
     */
    public Board(String fileName){

        //Initialize new fleet and create new file
        fleet = new Fleet();
        File inputFile = new File(fileName);

        int shipscounted = 0;

        //Attempt to assign a new scanner to the input file
        try (Scanner fileReader = new Scanner(inputFile);) {

            //Add 9 new rows of 9 new cellstatuses to layout. Initialize them to NOTHING.
            for(int row = 0; row <= 9; row++){
                ArrayList<CellStatus> r = new ArrayList<CellStatus>();
                for(int col = 0; col <= 9; col++){
                    r.add(CellStatus.NOTHING);
                }
                layout.add(r);
            }

            //Add new ships to the layout.
            while(fileReader.hasNext()){

                //Split each line into an array of Strings.
                String [] curr = fileReader.nextLine().split("\\s+");

                //Convert String data further into integers for ease of use.
                int fRow = Character.toLowerCase(curr[1].charAt(0)) - 'a' + 0;
                int fCol = Integer.parseInt(curr[1].substring(1)) - 1;
                int tRow = Character.toLowerCase(curr[2].charAt(0)) - 'a' + 0;
                int tCol = Integer.parseInt(curr[2].substring(1)) - 1;

                //Check if either String contains a value greater than 9. Due to outside validation, it must therefore be 10.
                if(curr[1].length() > 2){
                    fCol = 9;
                }

                if(curr[2].length() > 2){
                    tCol = 9;
                }

                //Determine the shiptype for this data entry, and insert a corresponding cell status.
                switch (curr[0]) {
                    /*For each case, determine if the ship is vertically or horizontally oriented,
                    then assign the appropriate status to each cell between the endpoints. */
                    case "D":
                        if (fRow == tRow){
                            for(int c = fCol; c <= tCol; c++){
                                layout.get(fRow).set(c,CellStatus.DESTROYER); 
                            }
                        }
                        else if(fCol == tCol) {
                            for(int r = fRow; r <= tRow; r++){
                                layout.get(r).set(fCol,CellStatus.DESTROYER);
                            }
                        }
                        break;
                    case "B":
                        if (fRow == tRow){
                            for(int c = fCol; c <= tCol; c++){
                                layout.get(fRow).set(c,CellStatus.BATTLESHIP); 
                            }
                        }
                        else if(fCol == tCol) {
                            for(int r = fRow; r <= tRow; r++){
                                layout.get(r).set(fCol,CellStatus.BATTLESHIP);
                            }
                        }
                        break;
                    case "C":
                        if (fRow == tRow){
                            for(int c = fCol; c <= tCol; c++){
                                layout.get(fRow).set(c,CellStatus.CRUISER); 
                            }
                        }
                        else if(fCol == tCol) {
                            for(int r = fRow; r <= tRow; r++){
                                layout.get(r).set(fCol,CellStatus.CRUISER);
                            }
                        }
                        break;
                    case "S":
                        if (fRow == tRow){
                            for(int c = fCol; c <= tCol; c++){
                                layout.get(fRow).set(c,CellStatus.SUB); 
                            }
                        }
                        else if(fCol == tCol) {
                            for(int r = fRow; r <= tRow; r++){
                                layout.get(r).set(fCol,CellStatus.SUB);
                            }
                        }
                        break;
                    case "A":
                        if (fRow == tRow){
                            for(int c = fCol; c <= tCol; c++){
                                layout.get(fRow).set(c,CellStatus.AIRCRAFT_CARRIER); 
                            }
                        }
                        else if(fCol == tCol) {
                            for(int r = fRow; r <= tRow; r++){
                                layout.get(r).set(fCol,CellStatus.AIRCRAFT_CARRIER);
                            }
                        }
                        break;
                
                    default:
                        System.out.println("Nothing On This Line");;
                }
                shipscounted++;
            }
        }
        catch(FileNotFoundException e){
            System.out.println(String.format("%s is not a valid input file", fileName));
            System.exit(0);
        }

        //Check if there are 5 ships in the data file. If not, the file is invalid: Exit the Program.
        if(shipscounted != 5){
            System.out.println("File does not contain 5 ships.");
            System.exit(0);
        }

    }

    /**
     * Changes the status of specified cell to hit.
     * @param move : The move containing location data which will be applied to the layout.
     * @return The original cellstatus of the location described by move.
     */
    public CellStatus applyMoveToLayout(Move move){
        CellStatus ogStatus = layout.get(move.row()).get(move.col());

        //Depending on the original status of the cell, change it to that status + _HIT.
        switch (ogStatus){
            case NOTHING: layout.get(move.row()).set(move.col(), CellStatus.NOTHING_HIT); break;
            case DESTROYER: layout.get(move.row()).set(move.col(), CellStatus.DESTROYER_HIT); break;
            case BATTLESHIP: layout.get(move.row()).set(move.col(), CellStatus.BATTLESHIP_HIT); break;
            case AIRCRAFT_CARRIER: layout.get(move.row()).set(move.col(), CellStatus.AIRCRAFT_CARRIER_HIT); break;
            case CRUISER: layout.get(move.row()).set(move.col(), CellStatus.CRUISER_HIT); break;
            case SUB: layout.get(move.row()).set(move.col(), CellStatus.SUB_HIT); break;
            default: break;
        }

        return ogStatus;
    }

    /**
     * Checks a specific cell to see if it is not hit or sunk.
     * @param move : The move containing location data for the cell being checked
     * @return Whether the cell is open or not.
     */
    public boolean moveAvailable(Move move){

        //If the move is not on the board, return false
        if(move.row() > 9 || move.row() < 0 || move.col() > 9 || move.col() < 0){
            return false;
        }
        //For all cases where the cell is not hit or sunk, return true. Otherwise, false.
        switch(layout.get(move.row()).get(move.col())){
            case NOTHING:
            case DESTROYER:
            case BATTLESHIP:
            case AIRCRAFT_CARRIER:
            case CRUISER:
            case SUB:
            return true;
            default:
            return false;
        }
    }

    /**
     * Getter for the layout ArrayList
     * @return Reference to layout.
     */
    public ArrayList<ArrayList<CellStatus>> getLayout(){
        return layout;
    }

    /**
     * Getter for the fleet.
     * @return Reference to felet.
     */
    public Fleet getFleet(){
        return fleet;
    }

    /**
     * Determines if all ships on this board have been sunk.
     * @return Truth value of fleet's gameOver method.
     */
    public boolean gameOver(){
        return fleet.gameOver();
    }

}