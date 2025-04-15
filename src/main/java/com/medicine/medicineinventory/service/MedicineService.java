package com.medicine.medicineinventory.service;


import com.medicine.medicineinventory.dto.UpdateMedicineRequest;
import com.medicine.medicineinventory.entity.Medicine;
import com.medicine.medicineinventory.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class MedicineService {
    @Autowired
    private MedicineRepository medicineRepository;

    public Object getAllMedicine(){
    	
    	Map<String, String> response = new HashMap<>();
    	try 
    	{
    		List<Medicine> medList = medicineRepository.findAll();  
    	
	    	if(medList.isEmpty()) 
	    	{
	    		response.put("error", "Empty List");
	    		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
	    	}
	        return ResponseEntity.status(HttpStatus.OK).body(medList);
    	}
    	catch(Exception e)
    	{
    		response.put("error", "Internal Server error");
    		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);    		
    	}
    	//return medicineRepository.findAll();  
    }

    public Object getMedicineById(Long id){
    	
    	
    	Map<String, String> response = new HashMap<>();
    	try 
    	{
    		Optional <Medicine> med = medicineRepository.findById(id);
    	
	    	if(med.isEmpty()) 
	    	{
	    		response.put("error", "No medicines found");
	    		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
	    	}
	    	return ResponseEntity.status(HttpStatus.OK).body(med);
    	}
    	catch(Exception e)
    	{
    		response.put("error", "Internal Server error");
    		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);    		
    	}
        //return medicineRepository.findById(id);
    }

    public Object addMedicine(Medicine medicine){
    	
    	Map<String, String> response = new HashMap<>();
    	try 
    	{
    		if(medicine.getName().trim().isBlank() || 
				medicine.getBatchNumber().trim().isBlank() || 
				medicine.getQuantity()==0 || 
				medicine.getPrice()==0
    		){
	    		response.put("error", "Invalid data");
	    		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
	    	}
    		Medicine savedMed = medicineRepository.save(medicine);
    		return ResponseEntity.status(HttpStatus.OK).body(savedMed);
    	}
    	catch(Exception e)
    	{
    		response.put("error", "Internal Server error");
    		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);    		
    	}
        //return medicineRepository.save(medicine);
    }

    public Object deleteMedicine(Long id){
    	Map<String, String> response = new HashMap<>();
    	try 
    	{
    		Optional <Medicine> med = medicineRepository.findById(id);
        	
	    	if(med.isEmpty()) 
	    	{
	    		response.put("error", "No medicines found");
	    		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
	    	}
	    	medicineRepository.deleteById(id);
	    	return ResponseEntity.status(HttpStatus.OK).body(med);
    	}
    	catch(Exception e)
    	{
    		response.put("error", "Internal Server error");
    		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);    		
    	}
        //medicineRepository.deleteById(id);
    }
    public Object updateMedicine(Long id, UpdateMedicineRequest request){
    	
    	Map<String, String> response = new HashMap<>();
    	try 
    	{
    		Optional <Medicine> med = medicineRepository.findById(id);
        	
	    	if(med.isEmpty()) 
	    	{
	    		response.put("error", "No medicines found");
	    		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
	    	}
	    	
	    	Medicine medicine = med.get();
	    	
	    	medicine.setName(request.getName());
	        medicine.setQuantity(request.getQuantity());
	        medicine.setPrice(request.getPrice());
	        medicine.setExpiryDate(request.getExpiryDate());
	        medicine.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
	        
	        medicineRepository.save(medicine);
	        return ResponseEntity.status(HttpStatus.OK).body(medicine);
    	}
    	catch(Exception e)
    	{
    		response.put("error", "Internal Server error");
    		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);    		
    	}
//        Medicine medicine=medicineRepository.findById(id)
//                .orElseThrow(()->new RuntimeException("Medicine not found"));
//        medicine.setName(request.getName());
//        medicine.setQuantity(request.getQuantity());
//        medicine.setPrice(request.getPrice());
//        medicine.setExpiryDate(request.getExpiryDate());
//        medicine.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));

        //return medicineRepository.save(medicine);
    }

}
