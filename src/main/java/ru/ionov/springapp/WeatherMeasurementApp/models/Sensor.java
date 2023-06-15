package ru.ionov.springapp.WeatherMeasurementApp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name="Sensor")
public class Sensor {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    @NotEmpty(message = "name should me not empty")
    @Size(min = 3,max = 30,message = "Name should be from 3 to 30 char")
    private String name;

    //todo maybe it isn't necessary
//    @OneToMany(mappedBy = "sensor")
//    private List<Measurement> measurements;

    public Sensor(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public List<Measurement> getMeasurements() {
//        return measurements;
//    }
//
//    public void setMeasurements(List<Measurement> measurements) {
//        this.measurements = measurements;
//    }
}
