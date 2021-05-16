import Exceptions.InvalidFullNameException;
import Exceptions.InvalidPasswordException;
import Exceptions.InvalidUserNameException;
import Exceptions.InvalideMailException;

import java.io.IOException;
import java.util.*;

public class Test {
    public static void main(String[] args) throws IOException {
        System.out.println("menu");
        boolean isActive = true;
        while (isActive) {
            System.out.println("enter 1 or 2: to quit enter 3 \n" +
                    "1 for Registration\n" +
                    "2 for login");
            Scanner s = new Scanner(System.in);
            int c = s.nextInt();
            switch (c) {
                case 1:
                    Registration();
                    break;
                case 2:
                    if (Login()) {
                        System.out.println("Success");
                    } else {
                        System.out.println("Invalid login");
                    }
                    break;
                case 3:
                    isActive = false;
                    break;
                default:
                    System.out.println("invalid number");
            }
        }
    }

    private static boolean Login() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter username");
        String username = sc.next();
        System.out.println("Enter password");
        String password = sc.next();
        ArrayList<String> allData = (ArrayList<String>) FileService.readLines("Files/database.txt");
        HashMap<String, String> dataUPs = AccountToFile.getUsersNameAndPassword(allData);
        boolean is = false;
        for (Map.Entry<String, String> entry : dataUPs.entrySet()) {
            if (username.equals(entry.getKey()) &&
                    AccountToFile.getMd5(password).equals(entry.getValue())) {
                is = true;
                break;
            }
        }
        return is;
    }

    private static void Registration() throws IOException  {
        ArrayList<String> allData = (ArrayList<String>) FileService.readLines("Files/database.txt");
        HashMap<String, String> dataUPs = AccountToFile.getUsersNameAndPassword(allData);

        System.out.println("Registration");
        Scanner sc = new Scanner(System.in);
        Account a = new Account();

        System.out.println("Enter your full name");
        while (true) {
            String fullname = sc.nextLine();
            try {
                a.setFullName(fullname);
                break;
            } catch (InvalidFullNameException e) {
                System.out.println("Invalid Full Name, please write it again");
            }
        }

        System.out.println("Enter your username: It should consist of minimum 10 symbols");
        while (true) {
            String username = sc.nextLine();
            boolean UsernameFound = false;
            if(dataUPs.containsKey(username)){
                System.out.println("username already exists, try another one");
            }
            else{
                try {
                    a.setUserName(username);
                    break;
                } catch (InvalidUserNameException e) {
                    System.out.println("Invalid Username, please write it again+\n" +
                            ">=10");
                }
            }
        }

        System.out.println("Enter your email");
        outer3:
        while(true){
            String email = sc.nextLine();
            try{
                a.seteMail(email);
                break;
            }catch(InvalideMailException e){
                System.out.println("Invalid email, try again!");
                continue outer3;
            }
        }

        System.out.println("Enter your password");
        while(true){
            String password = sc.nextLine();
            if(dataUPs.containsValue(AccountToFile.getMd5(password))){
                System.out.println("password unavailable, try another one");
            }
            else{
                try{   a.setPassword(password);
                       break;
                }catch(InvalidPasswordException e){
                    System.out.println("invalid password format: password should consist of \n" +
                            "2 upperCases and 3 numbers, length>=10");
                }
            }
        }
        //System.out.println(a);
        AccountToFile.writeeFromAccount(a);
        System.out.println("You've successfully  registered");
    }

}
