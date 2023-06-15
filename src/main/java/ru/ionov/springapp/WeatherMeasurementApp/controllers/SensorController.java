package ru.ionov.springapp.WeatherMeasurementApp.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.ionov.springapp.WeatherMeasurementApp.dto.SensorDTO;
import ru.ionov.springapp.WeatherMeasurementApp.models.Sensor;
import ru.ionov.springapp.WeatherMeasurementApp.services.SensorService;
import ru.ionov.springapp.WeatherMeasurementApp.util.ErrorResponse;

import static ru.ionov.springapp.WeatherMeasurementApp.util.ErrorsUtil.returnsErrorsToClient;

@RestController
@RequestMapping("/sensors")
public class SensorController {
    private final SensorService sensorService;
    private final ModelMapper modelMapper;

    @Autowired
    public SensorController(SensorService sensorService, ModelMapper modelMapper) {
        this.sensorService = sensorService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> create (@RequestBody @Valid SensorDTO sensorDTO,
                                              BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            returnsErrorsToClient(bindingResult,sensorDTO);
        }
        sensorService.save(convertToSensor(sensorDTO));

        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Sensor convertToSensor(SensorDTO sensorDTO) {
        return modelMapper.map(sensorDTO,Sensor.class);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(RuntimeException e){
        ErrorResponse response = new ErrorResponse(
                e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }
}
