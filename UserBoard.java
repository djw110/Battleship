import java.util.Random;
import java.util.ArrayList;

public class UserBoard extends Board {

    private Random rand;
    private ArrayList<Move> moves;

    public UserBoard(String s){

        super(s);
        rand = new Random();
        moves = new ArrayList<Move>();

        for (int r = 0; r <= 9; r++){
            for (int c = 0; c <= 9; c++){
                moves.add(new Move(r, c));
            }
        }

    }

    public Move pickRandomMove(){
        int mIndex = rand.nextInt(moves.size());
        return moves.remove(mIndex);
    }

    public String[] makeComputerMove(){
        Move move = pickRandomMove();
        String[] op = new String[2];
        CellStatus status = super.applyMoveToLayout(move);

        op[0] = move.toString();

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
                    op[1] = "The Computer sunk your Aircraft Carrier!";
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
                    op[1] = "The Computer sunk your Destroyer!";
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
                    op[1] = "The Computer sunk your Sub!";
                }
                break;
            case CRUISER: 
                if(super.getFleet().updateFleet(ShipType.ST_CRUISER)){
                    for (int i = 0; i <= 9; i++){
                        if (super.getLayout().get(move.row()).get(i) == CellStatus.CRUISER){
                            super.getLayout().get(move.row()).set(i, CellStatus.CRUISER);
                        }
                    }
                    for (ArrayList<CellStatus> nRow : super.getLayout()) {
                        if (nRow.get(move.col()) == CellStatus.CRUISER_HIT){
                            nRow.set(move.col(), CellStatus.CRUISER_SUNK);
                        }
                    }
                    op[1] = "The Computer sunk your Cruiser!";
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
                    op[1] = "The Computer sunk your Battleship!";
                }
                break;
            default:
                op[1] = null;
        }

        return op;
    }

    public String toString(){
        String op = String.format("  1 2 3 4 5 6 7 8 9 10\n");
        for (int i = 0; i<=9; i++){
            op += String.valueOf((char)(i + 65)) + " ";
            for (CellStatus col : super.getLayout().get(i)) {
                op += col.toString().substring(1) + " ";
            }
            op += String.format("\n");
        }
        return op;
    }
}
