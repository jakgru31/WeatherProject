package org.org.weatherapp.API;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class APILocationReader {
    private static final String API_KEY = APIKeyReader.reader();

    public static JsonObject getLocationData(String city) {
        city = city.replaceAll(" ", "+");
        String API_URL = "http://api.openweathermap.org/geo/1.0/direct?q=" + city + "&limit=1&appid=" + API_KEY;
        try {
            HttpURLConnection apiConnection = fetchAPIConnection(API_URL);
            if (apiConnection.getResponseCode() != 200) {
                System.out.println("Error: Couldn't connect to API");
                return null;
            }
            String JSONResponse = readAPIResponse(apiConnection);
            JsonParser parser = new JsonParser();
            JsonElement jsonElement = parser.parse(JSONResponse);

            if (jsonElement.isJsonArray()) {
                JsonArray jsonArray = jsonElement.getAsJsonArray();
                if (jsonArray.size() > 0) {
                    JsonObject locationData = jsonArray.get(0).getAsJsonObject();
                    double lat = locationData.get("lat").getAsDouble();
                    double lon = locationData.get("lon").getAsDouble();
                    return locationData;
                }
                else {
                    throw new NoDataException("No data found for the specified city");
                }
            }
            else {
                throw new WrongResponseFormatException("Invalid response format: Expected a JSON array");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return null;
    }


    private static HttpURLConnection fetchAPIConnection(String APIURL) {
        try {
            URL url = new URL(APIURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            return connection;
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
        return null;
    }

    private static String readAPIResponse(HttpURLConnection connection) {
        StringBuilder resultJSON = new StringBuilder();
        try {
            Scanner sc = new Scanner(connection.getInputStream());
            while (sc.hasNext()) {
                resultJSON.append(sc.nextLine());
            }
            sc.close();
            return resultJSON.toString();
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
        return null;
    }
}