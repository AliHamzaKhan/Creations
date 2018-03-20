package com.example.aaa.campussystem.ModelClass;

/**
 * Created by AAA on 3/15/2018.
 */

public class CompanyProfile {
   private String name;
   private String address;
   private String faculty;
   private String phone;
   String image;
   private String id;
   private CompanyPostAdd Jobs;

    public CompanyProfile(){}
    public CompanyProfile(String name, String address, String faculty, String phone, String image, String id) {
        this.name = name;
        this.address = address;
        this.faculty = faculty;
        this.phone = phone;
        this.image = image;
        this.id = id;

    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public CompanyPostAdd getJobs() {
        return Jobs;
    }

    public void setJobs(CompanyPostAdd jobs) {
        Jobs = jobs;
    }
}
