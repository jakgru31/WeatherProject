package org.org.weatherapp.GUI;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.org.weatherapp.BASE.WeatherDescription;
import org.org.weatherapp.BASE.WeatherData;
import org.org.weatherapp.API.APIWeatherService;

public class WeatherViewController{

    @FXML
    private Label CityLabel;

    @FXML
    private Label DescLabel;

    @FXML
    private Label HumLabel;

    @FXML
    private ImageView IconImageView;

    @FXML
    private Label MainLabel;

    @FXML
    private Label PressureLabel;

    @FXML
    private Label TempLabel;

    @FXML
    private TextField searchCityField;

    @FXML
    private Button searchButton;
    private String cityName;
    private APIWeatherService apiWeatherService;
    private Timeline update;
    public WeatherViewController() {
        this.apiWeatherService= new APIWeatherService();
    }

    @FXML
    void initialize() {
        update = new Timeline(
                new KeyFrame(Duration.ZERO, event -> updateWeatherInfo()),new KeyFrame(Duration.minutes(1)
        ));
        update.setCycleCount(Animation.INDEFINITE);
        update.play();

    }
    @FXML
    void onSearchButtonClicked(ActionEvent event) {
        cityName = searchCityField.getText();
        updateWeatherInfo();
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
        searchCityField.setText(cityName);
        updateWeatherInfo();
    }

    private void updateWeatherInfo() {
        WeatherData weatherData = apiWeatherService.getWeatherData(cityName);
        WeatherDescription weatherDescription = apiWeatherService.getWeatherDescription(cityName);

        CityLabel.setText(weatherData.getCity());
        TempLabel.setText(String.format("%.1f°C", weatherData.getTemperature()));
        HumLabel.setText("Humidity: " + weatherData.getHumidity() + " %");
        PressureLabel.setText("Pressure: " + weatherData.getPressure() + " hPa");
        MainLabel.setText(weatherDescription.getMain());
        DescLabel.setText(capitalizeFirstLetter(weatherDescription.getDescription()));

        Image icon = new Image("https://openweathermap.org/img/wn/"+weatherDescription.getIcon()+"@2x.png");
        IconImageView.setImage(icon);
    }
    private String capitalizeFirstLetter(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }

}
