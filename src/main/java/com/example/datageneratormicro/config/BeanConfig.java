package com.example.datageneratormicro.config;

import com.jcabi.xml.XML;
import com.jcabi.xml.XMLDocument;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
public class BeanConfig {
    @SneakyThrows
    @Bean
    public XML producerXML() {
        return new XMLDocument(
                getClass().getResourceAsStream("/kafka/producer.xml").readAllBytes() // в этом файле будет находится
                // конфигурация producer'a(конфигурация кафки которая будет отправлена)
        );
    }

}
