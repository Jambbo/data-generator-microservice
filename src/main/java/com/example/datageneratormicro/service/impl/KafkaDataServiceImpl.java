package com.example.datageneratormicro.service.impl;

import com.example.datageneratormicro.model.Data;
import com.example.datageneratormicro.service.KafkaDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderRecord;

@Service
@RequiredArgsConstructor
public class KafkaDataServiceImpl implements KafkaDataService {
    //тут String - ключ который будет использоваться для сообщений, Object - тип данных которые будут отправляться
    private final KafkaSender<String, Object> kafkaSender;

    @Override
    public void send(Data data) {
        String topic =
                switch (data.getMeasurementType()) {

                    case TEMPERATURE -> "data-temperature";
                    case VOLTAGE -> "data-voltage";
                    case POWER -> "data-power";
                };

        kafkaSender.send(
//Mono - объект который инкапсулирует в себе данные и используются, для того чтобы отправить их
// в реактивном подходе, тоби ж мы не дожидаемся ответа от кафки, а выполняем send() и продолжаем исполнение программы
                        Mono.just(
                                SenderRecord.create(
                                        topic,
                                        0,
                                        System.currentTimeMillis(),
                                        /* в качестве ключа -> */       String.valueOf(data.hashCode()),
                                        data,
                                        null
                                )
                        )
                )
                .subscribe();
    }
}

