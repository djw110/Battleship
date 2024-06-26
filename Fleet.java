//Fleet class. Contains one ship of each type, and can update them based off of moves from the board.
public class Fleet {

    //Declare fleet
   private Ship battleShip, aircraftCarrier, cruiser, sub, destroyer; 

   /**
    * Standard Constructor, initialize new ship for each type.
    */
    public Fleet(){
        battleShip = new Battleship();
        aircraftCarrier = new AircraftCarrier();
        cruiser = new Cruiser();
        sub = new Sub();
        destroyer = new Destroyer();
    }

    /**
     * Applies a hit to a ship in the fleet depending on it's type. Returns whether it sunk.
     * @param s : Shiptype to be updated
     * @return Result of whether or not the ship has sunk.
     */
    public boolean updateFleet(ShipType s){
        switch(s){
            case ST_AIRCRAFT_CARRIER: return aircraftCarrier.hit();
            case ST_BATTLESHIP: return battleShip.hit();
            case ST_CRUISER: return cruiser.hit();
            case ST_DESTROYER: return destroyer.hit();
            case ST_SUB: return sub.hit();
            default: return false;
        }
    }

    /**
     * Determines whether all ships in fleet have sunk.
     * @return Truth value of if all ships have sunk
     */
    public boolean gameOver(){
        if(battleShip.getSunk() == true && aircraftCarrier.getSunk() == true && cruiser.getSunk() == true && sub.getSunk() == true && destroyer.getSunk() == true){
            return true;
        }
        return false;
    }
}
