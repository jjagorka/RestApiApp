package ru.ionov.springapp.WeatherMeasurementApp.util;

public class SensorNotCreatedException extends RuntimeException{
    public SensorNotCreatedException(String msg){
        super(msg);
    }
}