package src.exceptions;

public class EmptyCartException extends Exception{
    public EmptyCartException(){
        super("Cart is Empty.");
    }
}
