package com.medicine.medicineinventory.model;

import com.medicine.medicineinventory.entity.Medicine;

import java.util.List;

public class CSVUploadResult {
    private List<Medicine> validMedicines;
    private List<String> errorLogs;

    // Constructors, getters, setters


    public CSVUploadResult(List<Medicine> validMedicines, List<String> errorLogs) {
        this.validMedicines = validMedicines;
        this.errorLogs = errorLogs;
    }

    public List<Medicine> getValidMedicines() {
        return validMedicines;
    }

    public void setValidMedicines(List<Medicine> validMedicines) {
        this.validMedicines = validMedicines;
    }

    public List<String> getErrorLogs() {
        return errorLogs;
    }

    public void setErrorLogs(List<String> errorLogs) {
        this.errorLogs = errorLogs;
    }
}