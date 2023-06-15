package ru.ionov.springapp.WeatherMeasurementApp.util;

public class SensorNotFoundException extends RuntimeException{
    public SensorNotFoundException(String msg){
        super(msg);
    }
}
