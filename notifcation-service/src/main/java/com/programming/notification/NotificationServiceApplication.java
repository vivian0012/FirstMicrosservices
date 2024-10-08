package com.programming.notification;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
@Slf4j
public class NotificationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationServiceApplication.class, args);
    }

    @KafkaListener(topics = "notificationTopic") // É quem vai ouvir o tópico
    public void handlerNotification(LabsPlacedEvent labsPlacedEvent) {

        log.info("notification for other Service", labsPlacedEvent.getLabsEvent());
    }

}