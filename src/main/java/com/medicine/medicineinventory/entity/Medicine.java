package com.medicine.medicineinventory.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;
import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "medicine")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    private Long id;

    @JsonProperty
    private String name;

    public int getMinThreshold() {
        return minThreshold;
    }

    private int minThreshold=5;

    public Medicine() {
    }

    @Column(name="batch_number")
    @JsonProperty
    private String batchNumber;

    @JsonProperty
    private Integer quantity;

    @JsonProperty
    private Double price;

    public Double getPrice() {
        return price;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Column(name = "expiry_date")
    @JsonProperty
    private LocalDate expiryDate;

    @JsonProperty
    @CreatedDate
    private Timestamp createdAt;
    @JsonProperty
    @LastModifiedDate
    private Timestamp updatedAt;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Medicine(String name, String batchNumber, Integer quantity, Double price, LocalDate expiryDate) {
        this.name = name;
        this.batchNumber = batchNumber;
        this.quantity = quantity;
        this.price = price;
        this.expiryDate = expiryDate;
    }
}
