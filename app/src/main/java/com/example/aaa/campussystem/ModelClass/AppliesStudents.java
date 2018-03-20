package com.example.aaa.campussystem.ModelClass;

/**
 * Created by AAA on 3/17/2018.
 */

public class AppliesStudents {
    private String name;
    private String email;
    private String phone;
    private String qualification;
    private String experience;
    private String id;

    public AppliesStudents(){}

    public AppliesStudents(String name, String email, String phone, String qualification, String experience, String id) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.qualification = qualification;
        this.experience = experience;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
