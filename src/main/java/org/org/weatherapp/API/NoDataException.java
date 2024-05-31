package org.org.weatherapp.API;

public class NoDataException extends Exception{
    public NoDataException() {
    }

    public NoDataException(String message) {
        super(message);
    }
}
