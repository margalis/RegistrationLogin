import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class FileService {

    public static void createFolder(String path){
        File f= new File(path);
        f.mkdirs(); // mkdir mi hat dir i hamar

    }
    public static void createFile(String path, String name){
        File  file = new File(path+File.separator +name);
        try{
            file.createNewFile();
        }catch (IOException e ){
            e.printStackTrace();
        }
    }
    public  static void write(String path, String text){
        try{
            Files.write(Paths.get(path),text.getBytes(), StandardOpenOption.APPEND);
            // main ic pti \n ov tanq bayc
        }
        catch(IOException e ){
            e.printStackTrace();
        }
    }
    public static void  writee(String ssss) throws IOException{
        FileWriter fw = new FileWriter("Files/database.txt", true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(ssss);
        bw.newLine();
        bw.close();

    }
    public static String read(String path){
        String result ="";
        try{
            byte[] bytes = Files.readAllBytes(Paths.get(path));
            result = new String(bytes, StandardCharsets.UTF_8);
        }
        catch (IOException e ){
            e.printStackTrace();
        }
        return result;
    }

    public static List<String> readLines(String path) throws IOException {
            return Files.readAllLines(Paths.get(path));
    }
}
