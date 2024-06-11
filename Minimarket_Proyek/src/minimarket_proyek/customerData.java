/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package minimarket_proyek;

import java.sql.Date;
/**
 *
 * @author vanessa siahaan
 */
public class customerData {
    
    private Integer customerId;
    private String brand;
    private String productName;
    private Integer quantity;
    private Double price;
    private Date date;
    
    public customerData(Integer customerId, String brand, String productName, Integer quantity, Double price, Date date  ){
        this.customerId = customerId;
        this.brand = brand;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.date = date;
    }
    public Integer getCustomerId(){
        return customerId;
    }
    public String getBrand(){
        return brand;
    }
    public String getProductName(){
        return productName;
    }
    public Integer getQuantity(){
        return quantity;
    }
    public Double getPrice(){
        return price;
    }
    public Date getDate(){
        return date;
    }
    
    
}
