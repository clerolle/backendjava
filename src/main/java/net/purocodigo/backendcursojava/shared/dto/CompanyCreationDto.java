package net.purocodigo.backendcursojava.shared.dto;

import java.io.Serializable;

public class CompanyCreationDto implements Serializable {

    private static final long  serialVersionUID= 1L;

    private String nit;

    private String name;
    
    private String address;
    
    private int phone;

    private String userEmail;

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    
}
