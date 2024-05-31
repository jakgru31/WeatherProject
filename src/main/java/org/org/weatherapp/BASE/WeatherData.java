package org.org.weatherapp.BASE;

import java.util.ArrayList;
import java.util.List;

public class WeatherData implements Subject {


    private String city;
    private double temperature;
    private double humidity;
    private double pressure;
    private List<Observer> observers = new ArrayList<>();

    public WeatherData(String city, double temperature, double humidity, double pressure) {
        this.city = city;
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
    }

    @Override
    public void registerObserver(Observer o) {
        if(!observers.contains(o)){
            observers.add(o);
        }
    }

    @Override
    public void removeObserver(Observer o) {
        if(!observers.contains(o)){
            observers.remove(o);
        }

    }

    @Override
    public void notifyObserver() {
        for(Observer o: observers){
            o.update(temperature,humidity,pressure);
        }
    }

    @Override
    public String toString() {
        return "WeatherData{" +
                "city='" + city + '\'' +
                ", temperature=" + temperature +
                ", humidity=" + humidity +
                ", pressure=" + pressure +
                '}';
    }

    public double getTemperature() {
        return temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getPressure() {
        return pressure;
    }
}
