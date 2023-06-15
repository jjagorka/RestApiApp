package ru.ionov.springapp.WeatherMeasurementApp.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;


public class MeasurementDTO {

    @NotNull
    private SensorDTO sensor;

    @NotNull
    private Boolean raining;

    @Min(value = -100, message = "The temperature can't be less then -100 Degrees Celcius")
    @Max(value = 100, message = "The temperature can't be more then 100 Degrees Celcius")
    @NotNull
    private Double temperature;

    public SensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorDTO sensor) {
        this.sensor = sensor;
    }

    public Boolean getRaining() {
        return raining;
    }

    public void setRaining(Boolean raining) {
        this.raining = raining;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }
}
