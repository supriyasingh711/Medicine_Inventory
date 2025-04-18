package com.medicine.medicineinventory.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class AlertConsumer {

    @KafkaListener(topics = "restock-alerts",groupId = "alert-consumer-group")
    public void listen(String message){
        System.out.println("Received kafka alert Consumer"+message);
    }
}
