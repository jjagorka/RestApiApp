package ru.ionov.springapp.WeatherMeasurementApp.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.ionov.springapp.WeatherMeasurementApp.dto.MeasurementDTO;
import ru.ionov.springapp.WeatherMeasurementApp.dto.MeasurementsResponse;
import ru.ionov.springapp.WeatherMeasurementApp.models.Measurement;
import ru.ionov.springapp.WeatherMeasurementApp.services.MeasurementService;
import ru.ionov.springapp.WeatherMeasurementApp.util.ErrorResponse;


import java.util.stream.Collectors;

import static ru.ionov.springapp.WeatherMeasurementApp.util.ErrorsUtil.returnsErrorsToClient;

@RestController
@RequestMapping("/measurement")
public class MeasurementController {
    private final MeasurementService measurementService;
    private final ModelMapper modelMapper;

    @Autowired
    public MeasurementController(MeasurementService measurementService,
                                 ModelMapper modelMapper) {
        this.measurementService = measurementService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public MeasurementsResponse getAllMeasurements(){
        return new MeasurementsResponse(measurementService.findAll().stream().map(this::convertToMeasurementDTO)
                .collect(Collectors.toList()));
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add (@RequestBody @Valid MeasurementDTO measurementDTO,
                                              BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            returnsErrorsToClient(bindingResult,measurementDTO);

        }
        measurementService.addMeasurement(convertToMeasurement(measurementDTO));

        return ResponseEntity.ok(HttpStatus.OK);
    }

    private MeasurementDTO convertToMeasurementDTO(Measurement measurement) {

        return modelMapper.map(measurement,MeasurementDTO.class);
    }

    private Measurement convertToMeasurement(MeasurementDTO measurementDTO) {

        return modelMapper.map(measurementDTO,Measurement.class);
    }


    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(RuntimeException e){
        ErrorResponse response = new ErrorResponse(
                e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }
}
