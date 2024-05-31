module org.org.weatherapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires com.google.gson;
    requires java.net.http;

    opens org.org.weatherapp to javafx.fxml;
    exports org.org.weatherapp.GUI;
    opens org.org.weatherapp.GUI to javafx.fxml;
    exports org.org.weatherapp.BASE;
    opens org.org.weatherapp.BASE to javafx.fxml;
}