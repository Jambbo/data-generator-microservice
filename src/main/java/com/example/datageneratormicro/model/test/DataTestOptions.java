package com.example.datageneratormicro.model.test;

import com.example.datageneratormicro.model.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Конфигурация для автоматической отправки сообщений
@NoArgsConstructor
@Getter
@Setter
public class DataTestOptions {

    private int delayInSeconds;
    private Data.MeasurementType[] measurementTypes;

}
