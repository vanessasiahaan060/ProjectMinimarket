/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package minimarket_proyek;
import java.sql.Date;

/**
 *
 * @author Vanessa Siahaan
 */
public class cashierData {
    
    private String cashierId;
    private String password;
    private String firstname;
    private String lastname;
    private String gender;
    private Date date;
    
    public cashierData(String cashierId, String password, String firstname, String lastname, String gender, Date date){
        this.cashierId = cashierId;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.date = date;
    }
    
    public String getCashierId(){
        return cashierId;
    }
    public String getPassword(){
        return password;
    }
    public String getFirstName(){
        return firstname;
    }
    public String getLastName(){
        return lastname;
    }
    public String getGender(){
        return gender;
    }
    public Date getDate(){
        return date;
    }
}
