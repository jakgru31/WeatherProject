package org.org.weatherapp.BASE;

public class WeatherDescription {
    private String main;
    private String description;
    private String icon;

    public WeatherDescription(String main, String description, String icon) {
        this.main = main;
        this.description = description;
        this.icon = icon;
    }

    public String getMain() {
        return main;
    }

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }

    @Override
    public String toString() {
        return "WeatherDescription{" +
                "main='" + main + '\'' +
                ", description='" + description + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }
}
