package Exceptions;

public class InvalideMailException extends RuntimeException{
    public InvalideMailException(String email){
        super("Invalid email:" + email);
    }
}
