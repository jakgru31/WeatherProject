package org.org.weatherapp.API;

public class WrongResponseFormatException extends Exception{
    public WrongResponseFormatException() {
    }
    public WrongResponseFormatException(String message) {
        super(message);
    }
}
