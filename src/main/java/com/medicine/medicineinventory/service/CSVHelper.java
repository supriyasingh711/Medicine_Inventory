package com.medicine.medicineinventory.service;

import com.medicine.medicineinventory.entity.Medicine;
import com.medicine.medicineinventory.model.CSVUploadResult;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CSVHelper {
    public static CSVUploadResult csvToMedicine(InputStream is){
        List<Medicine> medicines=new ArrayList<>();
        List<String> errorLogs=new ArrayList<>();
        try(
            BufferedReader fileReader=new BufferedReader(new InputStreamReader(is,"UTF-8"));
            CSVParser csvParser=new CSVParser(fileReader,
                    CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())
            ){
                int rowNumber=1;
                for(CSVRecord csvRecord:csvParser){
                   rowNumber++;
                   try{
                       String name=csvRecord.get("Name");
                       String batch=csvRecord.get("Batch No.");
                       LocalDate expiry= LocalDate.parse(csvRecord.get("ExpiryDate"));
                       int quantity=Integer.parseInt(csvRecord.get("Quantity"));
                       double price=Double.parseDouble(csvRecord.get("Price"));
                       if(name==null || name.isEmpty()){
                           errorLogs.add("Row "+rowNumber+": Missing 'Name'");
                           continue;
                       }
                       Medicine med=new Medicine(name,batch,quantity,price,expiry);
                       medicines.add(med);

                   }        catch (Exception e){
                errorLogs.add("Row" +rowNumber+":"+e.getMessage());
            }
                }
            }
        catch (Exception e){
            throw new RuntimeException("CSV parsing failed: "+e.getMessage());
        }
        return new CSVUploadResult(medicines,errorLogs);

    }
}
