package ru.ionov.springapp.WeatherMeasurementApp.util;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import ru.ionov.springapp.WeatherMeasurementApp.dto.MeasurementDTO;

import java.util.List;

public class ErrorsUtil {
    public static void returnsErrorsToClient(BindingResult bindingResult,Object o){

        StringBuilder errorMsg = new StringBuilder();

        List<FieldError> errors = bindingResult.getFieldErrors();
        for (FieldError error: errors){
            errorMsg.append(error.getField())
                    .append(" - ").append(error.getDefaultMessage())
                    .append(";");
        }
        //todo bad
        if (o instanceof MeasurementDTO){
            throw new MeasurementNotCreatedException(errorMsg.toString());
        }
        else{
            throw new SensorNotCreatedException(errorMsg.toString());
        }

    }
}
