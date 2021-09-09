package Utilities;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileHandling {

    //Used to Create a New File
    public static boolean createAFile(String filePath) {
        try{
            File file = new File(filePath);
            return file.createNewFile();
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //Append Content Into an File
    public static boolean appendDataIntoAFile(String filePath, String dataToAppend) {
        try{
            Files.write(Paths.get(filePath),dataToAppend.getBytes(), StandardOpenOption.APPEND);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //Read Contents from File
    public static String getContentsFromFile(String filePath){
        String fileContent = "";
        try{
            fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
        }catch (Exception e){
            e.printStackTrace();
        }
        return fileContent;
    }

}
