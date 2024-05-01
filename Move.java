//Move class. Contains a location from a row and column, and can be formed from a string or set of numbers.
public class Move {

    //Declare variables
    private int row, col;

    /**
     * Constructor, Creates a move location from a row and column value
     * @param r : Row
     * @param c : Column
     */
    public Move(int r, int c){
        row = r;
        col = c;
    }

    /**
     * Constructor, creates a move location from a string containing a row and column valuye
     * @param m : String to be parsed
     */
    public Move(String m){
        //Take first character and convert it to a corresponding integer
        row = Character.toLowerCase(m.charAt(0)) - 'a' + 0;

        //Take second character and parse to integer.
        col = Integer.parseInt(m.substring(1)) - 1;
    }

    /**
     * Getter for row
     * @return row
     */
    public int row(){
        return row;
    }

    /**
     * Getter for column
     * @return col
     */
    public int col(){
        return col;
    }

    /**
     * Displays move as a string.
     * @return String representation of row and column in letter-number format.
     */
    public String toString(){
        return String.format("%s%s",String.valueOf((char)(row + 65)),col+1);
    }
}
