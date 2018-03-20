package com.example.aaa.campussystem.ModelClass;

/**
 * Created by AAA on 3/15/2018.
 */

public class SignUpClass {
    private String name;
    private String email;
    private String password;
    private String purpose;
    private String id;
    private String checkAccount;

    public SignUpClass(){}

    public SignUpClass(String name, String email, String password, String purpose, String id, String checkAccount) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.purpose = purpose;
        this.id = id;
        this.checkAccount = checkAccount;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getCheckAccount() {
        return checkAccount;
    }

    public void setCheckAccount(String checkAccount) {
        this.checkAccount = checkAccount;
    }
}
