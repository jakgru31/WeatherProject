package org.org.weatherapp.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class WeatherIntroController {

    @FXML
    private Label introLabel;

    @FXML
    private TextField locationTextField;

    @FXML
    private Button searchButton;

    @FXML
    private Label welcomeLabel;


    @FXML
    void searchButtonClicked(ActionEvent event) throws NoLocationException {
        String location = locationTextField.getText();

        if (location.isEmpty()) {
            throw new NoLocationException("Location cannot be empty");
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/org/weatherapp/weather-view.fxml"));
            Parent root = loader.load();

            WeatherViewController controller = loader.getController();
            controller.setCityName(location);

            Stage stage = new Stage();
            stage.setTitle("Weather");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();

            Stage currentStage = (Stage) locationTextField.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}