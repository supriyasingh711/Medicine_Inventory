package com.medicine.medicineinventory.controller;


import com.medicine.medicineinventory.dto.UpdateMedicineRequest;
import com.medicine.medicineinventory.entity.Medicine;
import com.medicine.medicineinventory.service.MedicineService;
import jakarta.validation.Valid;
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
    public Object getAll(){
//        List<Medicine> medicines = medicineService.getAllMedicine();
//        System.out.println("Fetched Medicines: " + medicines.size());
        return medicineService.getAllMedicine();
    }

    @GetMapping("/{id}")
    public Object getMedicineById(@PathVariable Long id){
//        Optional<Medicine> m=medicineService.getMedicineById(id);
//        return m.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    	return medicineService.getMedicineById(id);
    }

    @PostMapping
    public Object add(@RequestBody Medicine medicine){
//        return medicineService.addMedicine(medicine);
    	return medicineService.addMedicine(medicine);
    }

    @DeleteMapping("/{id}")
    public Object delete(@PathVariable Long id){
//        medicineService.deleteMedicine(id);
    	return medicineService.deleteMedicine(id);
    }
    @PutMapping("/{id}")
    public Object updateMedicine(@PathVariable Long id, @Valid @RequestBody UpdateMedicineRequest request){
//        Medicine updatedMedicine= medicineService.updateMedicine(id,request);
    	return medicineService.updateMedicine(id, request);
    }
}
