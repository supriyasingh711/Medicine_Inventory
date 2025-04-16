package com.medicine.medicineinventory.dto;


import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class UpdateMedicineRequest {

    @NotBlank(message = "Name cannot be empty")
    private String name;
    
    @NotBlank(message = "Batch Number cannot be empty")
    private String batchNumber;

    @Min(value=1,message="Quantity must be atleast 1")
    private int quantity;

    @DecimalMin(value = "0.0",inclusive = false,message = "Price must be greater than zero")
    private double price;

    private LocalDate expiryDate;

    public  String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public  String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }
}
