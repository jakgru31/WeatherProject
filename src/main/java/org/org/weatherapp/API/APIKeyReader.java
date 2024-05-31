package org.org.weatherapp.API;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class APIKeyReader {
    public static String reader() {
        String APIKEY = "";
        try{
            File apiKey = new File("/Users/jakubgruszka/Desktop/Pliki/JavaProjects/weatherApp/src/main/java/org/org/weatherapp/API/apiKey.txt"); //put your API key path
            Scanner reader = new Scanner(apiKey);
            while (reader.hasNext()){
                APIKEY = reader.nextLine();
            }
        }
        catch (FileNotFoundException e){
            System.out.println("Error");
            e.printStackTrace();
        }
    return APIKEY;
    }
}
