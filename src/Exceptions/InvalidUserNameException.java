package Exceptions;

public class InvalidUserNameException extends RuntimeException{
    public InvalidUserNameException(String s ){
        super("UserName must be 10+ long " + s +"'s length is "+ s.length());
    }
}
