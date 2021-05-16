package Exceptions;

public class InvalidPasswordException extends RuntimeException{
    public InvalidPasswordException(String password){
        super("INVALID PASSWORD:"+password + "password should consist" +
                "of 2 uppercase and 3 numeric symbols, length>=10");
    }
}
