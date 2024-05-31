package org.org.weatherapp.API;

public class NoAPIConnectionException extends Exception{
    public NoAPIConnectionException() {
    }
    public NoAPIConnectionException(String message) {
        super(message);
    }
}
