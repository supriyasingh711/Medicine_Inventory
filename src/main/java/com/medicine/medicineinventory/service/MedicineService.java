package com.medicine.medicineinventory.service;


import com.medicine.medicineinventory.dto.UpdateMedicineRequest;
import com.medicine.medicineinventory.entity.Medicine;
import com.medicine.medicineinventory.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.kafka.core.KafkaTemplate;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MedicineService {
    @Autowired
    private MedicineRepository medicineRepository;

    @Autowired
    private  KafkaTemplate<String,String> kafkaTemplate;

    public List<Medicine> getAllMedicine(){
        return medicineRepository.findAll();
    }

    public Optional<Medicine> getMedicineById(Long id){
        return medicineRepository.findById(id);
    }

    public Medicine addMedicine(Medicine medicine){
        return medicineRepository.save(medicine);
    }

    public void deleteMedicine(Long id){
        medicineRepository.deleteById(id);
    }
    public Medicine updateMedicine(Long id, UpdateMedicineRequest request){
        Medicine medicine=medicineRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Medicine not found"));
        medicine.setName(request.getName());
        medicine.setQuantity(request.getQuantity());
        medicine.setPrice(request.getPrice());
        medicine.setExpiryDate(request.getExpiryDate());
        medicine.setBatchNumber(request.getBatchNumber());
        medicine.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
     updateStock(medicine);
        return medicineRepository.save(medicine);
    }

    private void updateStock(Medicine medicine) {
        if(medicine.getQuantity()<medicine.getMinThreshold()){
            String alertMesssage="Restock Alert :"+medicine.getName()+"{Qty :"+medicine.getQuantity()+"}";
            kafkaTemplate.send("restock-alerts",alertMesssage);
        }
    }

}
