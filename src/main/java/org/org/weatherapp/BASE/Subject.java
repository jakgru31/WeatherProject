package org.org.weatherapp.BASE;

public interface Subject {
    void registerObserver(Observer o);
    void  removeObserver(Observer o);
    void notifyObserver();
}
