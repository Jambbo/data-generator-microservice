package com.example.datageneratormicro.service;

import com.example.datageneratormicro.model.Data;

public interface KafkaDataService {

    void send(Data data);

}
