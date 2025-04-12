package com.medicine.medicineinventory.service;


import com.medicine.medicineinventory.dto.UpdateMedicineRequest;
import com.medicine.medicineinventory.entity.Medicine;
import com.medicine.medicineinventory.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MedicineService {
    @Autowired
    private MedicineRepository medicineRepository;

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
        medicine.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));

        return medicineRepository.save(medicine);
    }

}
