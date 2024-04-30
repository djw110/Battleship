public class BoardException extends Exception {
    public BoardException(){
        super("Improper Board Function");
    }
    public BoardException(String s){
        super(s);
    }
}