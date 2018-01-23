package com.ecng6613.point_of_sale;

/**
 * Created by Daniel Phillips on 4/24/2017.
 */

public class MainAdapterObject {



    private String id;
    private String product_name;
    private String price;
    private String price_units;
    private String upc_barcode;
    private String quantity;
    private String number_sold;
    private String margin_percentage;
    private String total_sales;
    private String location_id;
    private String expiration_data;
    private String supplier_id;

    MainAdapterObject(String id,
                      String product_name,
                      String price,
                      String price_units,
                      String upc_barcode,
                      String quantity,
                      String number_sold,
                      String margin_percentage,
                      String total_sales,
                      String location_id,
                      String expiration_data,
                      String supplier_id){

        this.id = id;
        this.product_name = product_name;
        this.price = price;
        this.price_units = price_units;
        this.upc_barcode = upc_barcode;
        this.quantity = quantity;
        this.number_sold = number_sold;
        this.margin_percentage = margin_percentage;
        this.total_sales = total_sales;
        this.location_id = location_id;
        this.expiration_data = expiration_data;
        this.supplier_id = supplier_id;
    }

    public String getId() {
        return id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getPrice() {
        return price;
    }

    public String getPrice_units() {
        return price_units;
    }

    public String getUpc_barcode() {
        return upc_barcode;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getNumber_sold() {
        return number_sold;
    }

    public String getMargin_percentage() {
        return margin_percentage;
    }

    public String getTotal_sales() {
        return total_sales;
    }

    public String getLocation_id() {
        return location_id;
    }

    public String getExpiration_data() {
        return expiration_data;
    }

    public String getSupplier_id() {
        return supplier_id;
    }
}


