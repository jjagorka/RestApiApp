package ru.ionov.springapp.WeatherMeasurementApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ionov.springapp.WeatherMeasurementApp.models.Measurement;
import ru.ionov.springapp.WeatherMeasurementApp.models.Sensor;
import ru.ionov.springapp.WeatherMeasurementApp.repositories.MeasurementRepository;
import ru.ionov.springapp.WeatherMeasurementApp.repositories.SensorRepository;
import ru.ionov.springapp.WeatherMeasurementApp.util.SensorNotFoundException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasurementService {
    private final MeasurementRepository measurementRepository;
    private final SensorRepository sensorRepository;
    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository, SensorRepository sensorRepository) {
        this.measurementRepository = measurementRepository;
        this.sensorRepository = sensorRepository;
    }

    public List<Measurement> findAll(){
       return measurementRepository.findAll();
    }

    @Transactional
    public void addMeasurement(Measurement measurement){
        enrichMeasurement(measurement);
        Sensor sensor = sensorRepository.findByName(measurement
                        .getSensor()
                        .getName())
                .orElseThrow(()-> new SensorNotFoundException("The name of sensor doesn't  exists"));
        measurement.setSensor(sensor);
        measurementRepository.save(measurement);
    }

    private void enrichMeasurement(Measurement measurement) {

        measurement.setMeasurement_date_time(LocalDateTime.now());
    }
}
