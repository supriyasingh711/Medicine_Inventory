package com.medicine.medicineinventory.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

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
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    private Long id;

    @JsonProperty
    private String name;

    @Column(name="batch_number")
    @JsonProperty
    private String batchNumber;

    @JsonProperty
    private Integer quantity;

    @Column(name = "expiry_date")
    @JsonProperty
    private LocalDate expiryDate;

    @JsonProperty
    private Timestamp createdAt;
    @JsonProperty
    private Timestamp updatedAt;
}
