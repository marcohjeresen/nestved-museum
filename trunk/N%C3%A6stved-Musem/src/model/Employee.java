/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.ArrayList;

/**
 *
 * @author markh_000
 */
public class Employee {
    private int cpr;
    private String name;
    private String address;
    private int postZip;
    private String city;
    private int password;
    private int phone;
    private ArrayList phoneList;

    public Employee(int cpr, String name, String address, int postZip, String city, int password) {
        this.cpr = cpr;
        this.name = name;
        this.address = address;
        this.postZip = postZip;
        this.city = city;

        this.password = password;
        phoneList = new ArrayList();
    }
    public void setPhoneList(int number){
        phoneList.add(number);
    }

    public ArrayList getPhoneList() {
        return phoneList;
    }

    public int getCpr() {
        return cpr;
    }

    public void setCpr(int cpr) {
        this.cpr = cpr;
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

    public int getPostZip() {
        return postZip;
    }

    public void setPostZip(int postZip) {
        this.postZip = postZip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

  
    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    @Override
    public String toString() {
        String employee = "cpr: " + cpr + " name: " + name + " address: " + address + " postZip: " + postZip + " city: " 
                + city + " phoneList: ";
        for (Object number : phoneList) {
            employee = employee + number+" - ";
        }
        return employee;
    }
    
}
