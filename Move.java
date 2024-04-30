public class Move {
    private int row, col;

    public Move(int r, int c){
        row = r;
        col = c;
    }
    public Move(String m){
        row = Character.toLowerCase(m.charAt(0)) - 'a' + 0;
        col = Integer.parseInt(m.substring(1)) - 1;
    }

    public int row(){
        return row;
    }

    public int col(){
        return col;
    }

    public String toString(){
        return String.format("%s%s",String.valueOf((char)(row + 65)),col+1);
    }
}
