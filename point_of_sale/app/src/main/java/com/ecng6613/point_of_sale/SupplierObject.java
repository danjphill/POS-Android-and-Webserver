package com.ecng6613.point_of_sale;

/**
 * Created by Daniel Phillips on 5/22/2017.
 */

public class SupplierObject {
    private String SupplierID;
    private String SupplierName;
    private String SupplierPhone;
    private String SupplierAddress;

    SupplierObject(String SupplierID,String SupplierName, String SupplierPhone,String SupplierAddress){
        this.SupplierID = SupplierID;
        this.SupplierName = SupplierName;
        this.SupplierPhone = SupplierPhone;
        this.SupplierAddress = SupplierAddress;
    }

    public String getSupplierAddress() {
        return SupplierAddress;
    }

    public String getSupplierID() {
        return SupplierID;
    }

    public String getSupplierName() {
        return SupplierName;
    }

    public String getSupplierPhone() {
        return SupplierPhone;
    }
}
