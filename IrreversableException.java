public class IrreversableException extends Exception{
    public IrreversableException(){
        super("This Card is Irreversable.");
    }

    public IrreversableException(String message){
        super(message);
    }
}
