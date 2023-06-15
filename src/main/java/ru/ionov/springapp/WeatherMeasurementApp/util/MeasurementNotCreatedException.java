package ru.ionov.springapp.WeatherMeasurementApp.util;

public class MeasurementNotCreatedException extends RuntimeException{
    public MeasurementNotCreatedException(String msg){
        super(msg);
    }
}
