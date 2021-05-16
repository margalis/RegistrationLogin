import Exceptions.InvalidFullNameException;
import Exceptions.InvalidPasswordException;
import Exceptions.InvalidUserNameException;
import Exceptions.InvalideMailException;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Account {
    private String FullName;
    private String UserName;
    private String eMail;
    private String password;

    public String getFullName() {
        return FullName;
    }
    public void setFullName(String fullName) {
        if(fullName.equals(null)|| fullName.isEmpty())
        {throw new InvalidFullNameException(); }

        FullName = fullName;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {


        if (userName.length() >= 10) {
            UserName = userName;
        } else {
            throw new InvalidUserNameException(userName);
        }

    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(eMail);

        if(matcher.matches() ==true ){
            this.eMail = eMail;
        }else throw new InvalideMailException(eMail);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        int NumberCounter= 0 ;
        int UpperCaseCounter = 0;
        for(int i = 0 ; i<password.length() ; ++i){
            if(password.charAt(i)>=65 && password.charAt(i)<=90){
                UpperCaseCounter++;
            }
            if(password.charAt(i)>=48 && password.charAt(i)<=57){
                NumberCounter++;
            }
        }
        if(password.length()<10 || UpperCaseCounter< 2 || NumberCounter < 3 ){
            throw new InvalidPasswordException(password);
        }
        this.password = password;
    }

    @Override
    public String toString() {
        return "Account{" +
                "FullName='" + FullName + '\'' +
                ", UserName='" + UserName + '\'' +
                ", eMail='" + eMail + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
