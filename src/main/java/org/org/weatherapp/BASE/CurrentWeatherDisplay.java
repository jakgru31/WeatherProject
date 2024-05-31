package org.org.weatherapp.BASE;

public class CurrentWeatherDisplay implements Display, Observer{
    private double temperature;
    private double humidity;
    private double pressure;

    @Override
    public void display() {
        System.out.println("CURRENT WEATHER DATA");
        System.out.println("Current temp: " + temperature);
        System.out.println("Currnt hum: " + humidity);
        System.out.println("Currnt press: " + pressure);
    }

    @Override
    public void update(double temperature, double humidity, double pressure) {
        this.temperature=temperature;
        this.humidity=humidity;
        this.pressure=pressure;
    }
}
