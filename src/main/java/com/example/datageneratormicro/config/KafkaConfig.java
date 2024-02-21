package com.example.datageneratormicro.config;

import com.jcabi.xml.XML;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderOptions;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class KafkaConfig {

    @Value("${spring.kafka.bootstrap-servers}") //серверы кафки на которые отправляется инфа
    private String servers;

    private final XML settings;

    //тут описываются топики, те части, условно, папки в которые отправляются записи в кафку
    //эти топики содержат партишены(разделения)
    @Bean
    public NewTopic temperatureTopic() {
        return TopicBuilder.name("data-temperature")
                .partitions(5) // указывается нечетное кол-во разделений, чтоб кафка корректно работала
                .replicas(1)
                .config(
                        TopicConfig.RETENTION_MS_CONFIG,
                        String.valueOf(Duration.ofDays(7).toMillis()) // через 7 дней сообщение удаляется из топика
                )
                .build();
    }

    @Bean
    public NewTopic voltageTopic() {
        return TopicBuilder.name("data-voltage")
                .partitions(5)
                .replicas(1)
                .config(
                        TopicConfig.RETENTION_MS_CONFIG,
                        String.valueOf(Duration.ofDays(7).toMillis())
                )
                .build();
    }

    @Bean
    public NewTopic powerTopic() {
        return TopicBuilder.name("data-power")
                .partitions(5)
                .replicas(1)
                .config(
                        TopicConfig.RETENTION_MS_CONFIG,
                        String.valueOf(Duration.ofDays(7).toMillis())
                )
                .build();
    }

    //Конфигурация кафки, сендера, того кто отправляет сообщения в кафку
    @Bean
    public SenderOptions<String, Object> senderOptions() {
        Map<String, Object> props = new HashMap<>(3);
        props.put(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, //строка которая называет конфиг
                servers
        );
        props.put(
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                new TextXPath(this.settings, "//keySerializer") // из producer.xml из keySerializer получаются значения и передавать в ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                        .toString()
        );
        props.put(
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, //строка которая называет конфиг
                new TextXPath(this.settings, "//valueSerializer")
                        .toString()
        );
        return SenderOptions.create(props);
    }

    @Bean // сендер который отправляет сообщения
    public KafkaSender<String, Object> sender() {
        return KafkaSender.create(senderOptions());
    }

}
