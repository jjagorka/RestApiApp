package ru.ionov.springapp.WeatherMeasurementApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ionov.springapp.WeatherMeasurementApp.models.Sensor;
import ru.ionov.springapp.WeatherMeasurementApp.repositories.SensorRepository;
import ru.ionov.springapp.WeatherMeasurementApp.util.SensorNotFoundException;

@Service
@Transactional(readOnly = true)
public class SensorService {
    private final SensorRepository sensorRepository;

    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Transactional
    public void save(Sensor sensor){

        if (sensorRepository.findByName(sensor.getName()).isPresent()){
            throw new SensorNotFoundException("This Name already exists");
        }
        else {
            sensorRepository.save(sensor);
        }
    }
}
