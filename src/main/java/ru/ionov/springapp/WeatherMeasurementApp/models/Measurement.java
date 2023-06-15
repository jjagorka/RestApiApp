package ru.ionov.springapp.WeatherMeasurementApp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "Measurement")
public class Measurement {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "sensor",referencedColumnName = "name")
    private Sensor sensor;

    @Column(name = "raining")
    @NotNull
    private Boolean raining;

    @Min(value = -100, message = "The temperature can't be less then -100 Degrees Celcius")
    @Max(value = 100, message = "The temperature can't be more then 100 Degrees Celcius")
    @Column(name = "temperature")
    private Double temperature;

    @Column(name = "measurement_date_time")
    @NotNull
    private LocalDateTime measurement_date_time;

    public Measurement(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
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

    public LocalDateTime getMeasurement_date_time() {
        return measurement_date_time;
    }

    public void setMeasurement_date_time(LocalDateTime measurement_date_time) {
        this.measurement_date_time = measurement_date_time;
    }
}
