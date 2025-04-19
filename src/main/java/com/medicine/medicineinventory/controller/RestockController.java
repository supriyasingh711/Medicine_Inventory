package com.medicine.medicineinventory.controller;


import com.medicine.medicineinventory.dto.StockRequest;
import com.medicine.medicineinventory.entity.Medicine;
import com.medicine.medicineinventory.repository.MedicineRepository;
import com.medicine.medicineinventory.service.RestockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/restock")
public class RestockController {
    @Autowired
    private MedicineRepository medicineRepository;

    @Autowired
    private RestockService restockService;

    @PostMapping("/suggestions")
    public List<Medicine> getRestockSuggestions(@RequestBody StockRequest request){
        int maxStockSize= request.getStockSize();
        List<Medicine> medicines=medicineRepository.findAll();
        List<Medicine> selectedMedicine=restockService.getOptimalRestock(medicines,maxStockSize);
//        model.addAttribute("selectedMedicines",selectedMedicine);
//        return "suggestions";
        return selectedMedicine;
    }
}
