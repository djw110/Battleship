import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public abstract class Board {
    private ArrayList<ArrayList<CellStatus>> layout = new ArrayList<ArrayList<CellStatus>>();
    private Fleet fleet;
    public static final int SIZE = 5;
    
    public Board(String fileName){

        fleet = new Fleet();
        File inputFile = new File(fileName);

        try (Scanner fileReader = new Scanner(inputFile);) {
            for(int row = 0; row <= 9; row++){
                ArrayList<CellStatus> r = new ArrayList<CellStatus>();
                for(int col = 0; col <= 9; col++){
                    r.add(CellStatus.NOTHING);
                }
                layout.add(r);
            }

            while(fileReader.hasNext()){
                String [] curr = fileReader.nextLine().split("\\s+");

                int fRow = Character.toLowerCase(curr[1].charAt(0)) - 'a' + 0;
                int fCol = Integer.parseInt(curr[1].substring(1)) - 1;
                int tRow = Character.toLowerCase(curr[2].charAt(0)) - 'a' + 0;
                int tCol = Integer.parseInt(curr[2].substring(1)) - 1;

                if(curr[1].length() > 2){
                    fCol = 9;
                }

                if(curr[2].length() > 2){
                    tCol = 9;
                }

                switch (curr[0]) {
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
            }
        }
        catch(FileNotFoundException e){
            System.out.println(String.format("%s is not a valid input file", fileName));
            System.exit(0);
        }

    }

    public CellStatus applyMoveToLayout(Move move){
        CellStatus ogStatus = layout.get(move.row()).get(move.col());
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

    public boolean moveAvailable(Move move){
        if(move.row() > 9 || move.row() < 0 || move.col() > 9 || move.col() < 0){
            return false;
        }
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

    public ArrayList<ArrayList<CellStatus>> getLayout(){
        return layout;
    }

    public Fleet getFleet(){
        return fleet;
    }

    public boolean gameOver(){
        return fleet.gameOver();
    }

}