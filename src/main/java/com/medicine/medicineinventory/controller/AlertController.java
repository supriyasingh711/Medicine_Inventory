package com.medicine.medicineinventory.controller;


import com.medicine.medicineinventory.service.AlertProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api/alert")
public class AlertController {
    @Autowired
    private AlertProducerService alertProducerService;
    
	    @GetMapping
	    public String sendDummyAlert(){
	        alertProducerService.sendRestockAlert("dummy");
	        return "Alert send to kafka : dummy";
	    }

        @PostMapping
        public String sendAlert(@RequestParam String message){
            alertProducerService.sendRestockAlert(message);
            return "Alert send to kafka : "+message;
        }
}
