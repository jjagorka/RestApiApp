package ru.ionov.springapp.WeatherMeasurementApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ionov.springapp.WeatherMeasurementApp.models.Measurement;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement,Integer> {

}
