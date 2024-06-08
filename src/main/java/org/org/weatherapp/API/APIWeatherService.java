package org.org.weatherapp.API;

import com.google.gson.*;
import org.org.weatherapp.BASE.WeatherData;
import org.org.weatherapp.BASE.WeatherDescription;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class APIWeatherService {
    private static final String API_KEY = APIKeyReader.reader();

    public static WeatherData getWeatherData(String city) {
        JsonObject jsonCity = APILocationReader.getLocationData(city);
        String latitude = jsonCity.get("lat").getAsString();
        String longitude = jsonCity.get("lon").getAsString();
        final String API_URL = "https://api.openweathermap.org/data/2.5/weather?lat="+latitude+"&lon="+longitude+"&appid="+API_KEY+"&units=metric";
        try {
            HttpsURLConnection apiConnection = (HttpsURLConnection) fetchAPIConnection(API_URL);
            if (apiConnection.getResponseCode() != 200) {
                throw new NoAPIConnectionException("Error: Couldn't connect to API");
            }
            String JSONResponse = readAPIResponse(apiConnection);
            JsonParser parser = new JsonParser();
            JsonObject jsonObject = parser.parse(JSONResponse).getAsJsonObject();
            JsonObject main = jsonObject.getAsJsonObject("main");
            if (main != null) {
                double temp = main.get("temp").getAsDouble();
                double pressure = main.get("pressure").getAsDouble();
                double humidity = main.get("humidity").getAsDouble();
                WeatherData data = new WeatherData(city, temp, pressure, humidity);
                return data;
            } else {
                throw new NoDataException("No 'main' data found in the response");
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
    public static WeatherDescription getWeatherDescription(String city) {
        JsonObject jsonCity = APILocationReader.getLocationData(city);
        String latitude = jsonCity.get("lat").getAsString();
        String longitude = jsonCity.get("lon").getAsString();
        final String API_URL = "https://api.openweathermap.org/data/2.5/weather?lat="+latitude+"&lon="+longitude+"&appid="+API_KEY+"&units=metric";
        try {
            HttpsURLConnection apiConnection = (HttpsURLConnection) fetchAPIConnection(API_URL);
            if (apiConnection.getResponseCode() != 200) {
                throw new NoAPIConnectionException("Error: Couldn't connect to API");
            }
            String JSONResponse = readAPIResponse(apiConnection);
            JsonParser parser = new JsonParser();
            JsonObject jsonObject = parser.parse(JSONResponse).getAsJsonObject();
            JsonArray weather = jsonObject.getAsJsonArray("weather");
            if (weather != null) {
                JsonObject weatherObject = weather.get(0).getAsJsonObject();
                String main = weatherObject.get("main").getAsString();
                String description = weatherObject.get("description").getAsString();
                String icon = weatherObject.get("icon").getAsString();
                WeatherDescription weatherDescription = new WeatherDescription(main, description, icon);
                return weatherDescription;
            } else {
                throw new NoDataException("No 'weather' data found in the response");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return null;

    }

}
