package com.medicine.medicineinventory.service;


import com.medicine.medicineinventory.entity.Medicine;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestockService {
    public List<Medicine> getOptimalRestock(List<Medicine> medicines,int maxStockSize){
        int n=medicines.size();
        double[][] dp= new double[n + 1][maxStockSize + 1];
        for(int i=1;i<=n;i++){
            Medicine medicine=medicines.get(i-1);
            for(int j=0;j<=maxStockSize;j++){
                if(medicine.getQuantity()<=j){
                    dp[i][j]=Math.max(dp[i-1][j],medicine.getPrice()+dp[i-1][j-medicine.getQuantity()]);
                }else{
                    dp[i][j]=dp[i-1][j];
                }
            }

        }
        List<Medicine> selected=new ArrayList<>();
        int i=n,j=maxStockSize;
        while(i>0 && j>0){
            if(dp[i][j]!=dp[i-1][j]){
                Medicine med=medicines.get(i-1);
                selected.add(med);
                j-=med.getQuantity();
            }
            i--;
        }
        return selected;
    }
}
