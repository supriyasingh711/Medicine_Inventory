package com.medicine.medicineinventory.controller;


import com.medicine.medicineinventory.entity.Medicine;
import com.medicine.medicineinventory.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/medicine")
public class MedicineController {
    @Autowired
    public MedicineService medicineService;

    @GetMapping
    public List<Medicine> getAll(){
        List<Medicine> medicines = medicineService.getAllMedicine();
        System.out.println("Fetched Medicines: " + medicines.size());
        return medicineService.getAllMedicine();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medicine> getMedicineById(@PathVariable Long id){
        Optional<Medicine> m=medicineService.getMedicineById(id);
        return m.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }

    @PostMapping
    public Medicine add(@RequestBody Medicine medicine){
        return medicineService.addMedicine(medicine);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        medicineService.deleteMedicine(id);
    }
}
