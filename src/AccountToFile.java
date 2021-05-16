import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class AccountToFile {
    public static String getMd5(String input) {
        try {

            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() method is called to calculate message digest
            //  of an input digest() return array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeeFromAccount(Account a) throws IOException {
        StringBuilder s = new StringBuilder("");
        s.append(a.getFullName()).append(",").append(a.getUserName())
                .append(",").append(a.geteMail()).append(",").append(getMd5(a.getPassword()));
        String sd = s.toString();
        FileWriter fw = new FileWriter("Files/database.txt", true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(sd);
        bw.newLine();

        bw.close();
    }

    //petq chekav
    public static Account ReadFromAccount(String data) {
        String[] current = data.split("\\s*,\\s*");
        Account as = new Account();
        as.setFullName(current[0]);
        as.setUserName(current[1]);
        as.seteMail(current[2]);
        as.setPassword(current[3]);
        return as;
    }

    public static HashMap<String, String> getUsersNameAndPassword(List<String> data) {

        HashMap<String, String> pairs = new LinkedHashMap<>();
        Iterator<String> it = data.listIterator();
        while (it.hasNext()) {
            String[] current = it.next().split("\\s*,\\s*");
            pairs.put(current[1], current[3]);
        }
        return pairs;
    }


}
