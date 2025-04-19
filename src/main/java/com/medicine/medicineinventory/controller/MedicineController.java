package com.medicine.medicineinventory.controller;


import com.medicine.medicineinventory.dto.UpdateMedicineRequest;
import com.medicine.medicineinventory.entity.Medicine;
import com.medicine.medicineinventory.service.AlertProducerService;
import com.medicine.medicineinventory.service.MedicineService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/medicine")
public class MedicineController {
    @Autowired
    public MedicineService medicineService;

    @Autowired
    public AlertProducerService alertProducerService;

    @GetMapping("/")
    public String showHome(){
        return "index";
    }
    @GetMapping
    public ResponseEntity<List<Medicine>> getAll(){
        List<Medicine> medicines = medicineService.getAllMedicine();
//        System.out.println("Fetched Medicines: " + medicines.size());
        List<Medicine> medicines1=medicineService.getAllMedicine();
        return new ResponseEntity<>(medicines1, HttpStatus.OK);
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
    @PutMapping("/{id}")
    public ResponseEntity<?> updateMedicine(@PathVariable Long id, @Valid @RequestBody UpdateMedicineRequest request){
        Medicine updatedMedicine= medicineService.updateMedicine(id,request);
        alertProducerService.updateStock(id,updatedMedicine.getQuantity());

        return  ResponseEntity.ok(updatedMedicine);
    }
}
