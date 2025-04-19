package com.medicine.medicineinventory.service;

import com.medicine.medicineinventory.entity.Medicine;
import com.medicine.medicineinventory.repository.MedicineRepository;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class AlertProducerService {

    private static final String TOPIC="restock-alerts";

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    @Autowired
    private MedicineRepository medicineRepository;

    public void updateStock(Long medicineId,int newQuantity){
        Optional<Medicine> medicineOptional=medicineRepository.findById(medicineId);
        if(medicineOptional.isPresent()){
            Medicine medicine=medicineOptional.get();
            String medicineName=medicine.getName();
            if(newQuantity<=5){

                String alertMessage="Low stock for product: "+medicineName;
                sendRestockAlert(alertMessage);
            }

        }else{
            System.out.println("Medicine with id"+medicineId+"not found");
        }
    }

    public void sendRestockAlert(String message){
        System.out.println("Sending kafka message :  "+message);
        kafkaTemplate.send(TOPIC,message);
    }
}
