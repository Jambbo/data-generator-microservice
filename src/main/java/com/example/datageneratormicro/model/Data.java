package com.example.datageneratormicro.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class Data {

    private Long sensorId;
    private LocalDateTime timestamp;
    private double measurement; // тут будем хранить само значение, то которое прислал датчик
    private MeasurementType measurementType; // тип самого значения

    public enum MeasurementType {
        TEMPERATURE,
        VOLTAGE,
        POWER
    }

}
