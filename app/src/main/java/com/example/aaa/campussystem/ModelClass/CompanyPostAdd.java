package com.example.aaa.campussystem.ModelClass;

/**
 * Created by AAA on 3/15/2018.
 */

public class CompanyPostAdd {
    private String designation;
    private String sellery;
    private String experience;
    private String information;
    private String email;
    private String phoneNo;
    private String id;
    private String companyId;
    private String checkAccount;
   private  StudentProfileClass studentProfileClass;


    public CompanyPostAdd(){}
    public CompanyPostAdd(String companyId,String designation, String sellery, String experience, String information, String email, String phoneNo, String id, String checkAccount,StudentProfileClass studentProfileClass) {
        this.designation = designation;
        this.sellery = sellery;
        this.experience = experience;
        this.information = information;
        this.email = email;
        this.phoneNo = phoneNo;
        this.checkAccount = checkAccount;
        this.id = id;
        this.companyId = companyId;
        this.studentProfileClass = studentProfileClass;

    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getSellery() {
        return sellery;
    }

    public void setSellery(String sellery) {
        this.sellery = sellery;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getCheckAccount() {
        return checkAccount;
    }

    public void setCheckAccount(String checkAccount) {
        this.checkAccount = checkAccount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public StudentProfileClass getStudentProfileClass() {
        return studentProfileClass;
    }

    public void setStudentProfileClass(StudentProfileClass studentProfileClass) {
        this.studentProfileClass = studentProfileClass;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }
}
