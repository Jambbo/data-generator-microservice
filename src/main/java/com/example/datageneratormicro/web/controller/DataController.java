package com.example.datageneratormicro.web.controller;

import com.example.datageneratormicro.model.Data;
import com.example.datageneratormicro.model.test.DataTestOptions;
import com.example.datageneratormicro.service.KafkaDataService;
import com.example.datageneratormicro.service.TestDataService;
import com.example.datageneratormicro.web.dto.DataDto;
import com.example.datageneratormicro.web.dto.DataTestOptionsDto;
import com.example.datageneratormicro.web.mapper.DataMapper;
import com.example.datageneratormicro.web.mapper.DataTestOptionsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/data")
@RequiredArgsConstructor
public class DataController {

    private final KafkaDataService kafkaDataService; // в этот сервис будут отправляться данные
    private final TestDataService testDataService;

    private final DataMapper dataMapper;
    private final DataTestOptionsMapper dataTestOptionsMapper;

    @PostMapping("/send")
    public void send(@RequestBody DataDto dto) {
        Data data = dataMapper.toEntity(dto);
        kafkaDataService.send(data);
    }

    @PostMapping("/test/send")
    public void testSend(@RequestBody DataTestOptionsDto testOptionsDto) {
        DataTestOptions dataTestOptions = dataTestOptionsMapper.toEntity(testOptionsDto);
        testDataService.sendMessages(dataTestOptions);
    }

}
