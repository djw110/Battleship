public class Fleet {
   private Ship battleShip, aircraftCarrier, cruiser, sub, destroyer; 

    public Fleet(){
        battleShip = new Battleship();
        aircraftCarrier = new AircraftCarrier();
        cruiser = new Cruiser();
        sub = new Sub();
        destroyer = new Destroyer();
    }

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

    public boolean gameOver(){
        if(battleShip.getSunk() == true && aircraftCarrier.getSunk() == true && cruiser.getSunk() == true && sub.getSunk() == true && destroyer.getSunk() == true){
            return true;
        }
        return false;
    }
}
