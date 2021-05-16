package Exceptions;

public class InvalidFullNameException extends RuntimeException{
    public InvalidFullNameException(){
        super("Invalid full name : either  empty or null");
    }
}
