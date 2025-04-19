package com.medicine.medicineinventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.kafka.annotation.EnableKafka;

@EnableJpaAuditing
@SpringBootApplication
@EnableKafka

public class MedicineInventoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedicineInventoryApplication.class, args);
	}

}
