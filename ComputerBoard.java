import java.util.ArrayList;

public class ComputerBoard extends Board {

    public ComputerBoard(String f){
        super(f);
    }

    public String makePlayerMove(Move move){
        if(moveAvailable(move)){
            CellStatus status = super.applyMoveToLayout(move);
            switch(status){
                case AIRCRAFT_CARRIER: 
                    if(super.getFleet().updateFleet(ShipType.ST_AIRCRAFT_CARRIER)){
                        for (int i = 0; i <= 9; i++){
                            if (super.getLayout().get(move.row()).get(i) == CellStatus.AIRCRAFT_CARRIER_HIT){
                                super.getLayout().get(move.row()).set(i, CellStatus.AIRCRAFT_CARRIER_SUNK);
                            }
                        }
                        for (ArrayList<CellStatus> nRow : super.getLayout()) {
                            if (nRow.get(move.col()) == CellStatus.AIRCRAFT_CARRIER_HIT){
                                nRow.set(move.col(), CellStatus.AIRCRAFT_CARRIER_SUNK);
                            }
                        }
                        return "You sunk my Aircraft Carrier!";
                    }
                    break;
                case DESTROYER: 
                    if(super.getFleet().updateFleet(ShipType.ST_DESTROYER)){
                        for (int i = 0; i <= 9; i++){
                            if (super.getLayout().get(move.row()).get(i) == CellStatus.DESTROYER_HIT){
                                super.getLayout().get(move.row()).set(i, CellStatus.DESTROYER_SUNK);
                            }
                        }
                        for (ArrayList<CellStatus> nRow : super.getLayout()) {
                            if (nRow.get(move.col()) == CellStatus.DESTROYER_HIT){
                                nRow.set(move.col(), CellStatus.DESTROYER_SUNK);
                            }
                        }
                        return "You sunk my Destroyer!";
                    }
                    break;
                case SUB: 
                    if(super.getFleet().updateFleet(ShipType.ST_SUB)){
                        for (int i = 0; i <= 9; i++){
                            if (super.getLayout().get(move.row()).get(i) == CellStatus.SUB_HIT){
                                super.getLayout().get(move.row()).set(i, CellStatus.SUB_SUNK);
                            }
                        }
                        for (ArrayList<CellStatus> nRow : super.getLayout()) {
                            if (nRow.get(move.col()) == CellStatus.SUB_HIT){
                                nRow.set(move.col(), CellStatus.SUB_SUNK);
                            }
                        }
                        return "You sunk my Sub!";
                    }
                    break;
                case CRUISER: 
                    if(super.getFleet().updateFleet(ShipType.ST_CRUISER)){
                        for (int i = 0; i <= 9; i++){
                            if (super.getLayout().get(move.row()).get(i) == CellStatus.CRUISER_HIT){
                                super.getLayout().get(move.row()).set(i, CellStatus.CRUISER_SUNK);
                            }
                        }
                        for (ArrayList<CellStatus> nRow : super.getLayout()) {
                            if (nRow.get(move.col()) == CellStatus.CRUISER_HIT){
                                nRow.set(move.col(), CellStatus.CRUISER_SUNK);
                            }
                        }
                        return "You sunk my Cruiser!";
                    }
                    break;
                case BATTLESHIP: 
                    if(super.getFleet().updateFleet(ShipType.ST_BATTLESHIP)){
                        for (int i = 0; i <= 9; i++){
                            if (super.getLayout().get(move.row()).get(i) == CellStatus.BATTLESHIP_HIT){
                                super.getLayout().get(move.row()).set(i, CellStatus.BATTLESHIP_SUNK);
                            }
                        }
                        for (ArrayList<CellStatus> nRow : super.getLayout()) {
                            if (nRow.get(move.col()) == CellStatus.BATTLESHIP_HIT){
                                nRow.set(move.col(), CellStatus.BATTLESHIP_SUNK);
                            }
                        }
                        return "You sunk my Battleship!";
                    }
                    break;
                default:
                    return null;
            }
        }
        else{
            return "Duplicate Move, turn forfeit.";
        }
        return null;
    }

    public String toString(){
        String op = String.format("  1 2 3 4 5 6 7 8 9 10\n");
        for (int i = 0; i<=9; i++){
            op += String.valueOf((char)(i + 65)) + " ";
            for (CellStatus col : super.getLayout().get(i)) {
                op += col.toString().substring(0,1) + " ";
            }
            op += String.format("\n");
        }
        return op;
    }
}
