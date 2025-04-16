package com.medicine.medicineinventory.service;

import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class AlertProducerService {

    private static final String TOPIC="restock-alerts";

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    public void sendRestockAlert(String message){
        this.kafkaTemplate.send(TOPIC,message);
    }
}
