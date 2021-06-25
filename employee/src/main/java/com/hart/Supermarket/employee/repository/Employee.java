package com.hart.Supermarket.employee.repository;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;
import java.util.UUID;

@Document(collection="Employees")
public class Employee {

    @Id
    private String uuid;

    private String email;
    private String password;
    private String twoFactorString;
    private String secQuestion;
    private String secAnswer;
    private String firstName;
    private String surname;
    private String title;
    private String phoneNumber;
    private int dateHired;
    private boolean management;
    private boolean executive;
    private String[] roles;


    public Employee(String email, String password, String twoFactorString, String secQuestion,
                    String secAnswer, String firstName, String surname, String title, String phoneNumber,
                    int dateHired, boolean management, boolean executive, String[] roles) {
        this.uuid = UUID.randomUUID().toString();
        this.email = email;
        this.password = password;
        this.twoFactorString = twoFactorString;
        this.secQuestion = secQuestion;
        this.secAnswer = secAnswer;
        this.firstName = firstName;
        this.surname = surname;
        this.title = title;
        this.phoneNumber = phoneNumber;
        this.dateHired = dateHired;
        this.management = management;
        this.executive = executive;
        this.roles = roles;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid() {
        this.uuid = UUID.randomUUID().toString();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTwoFactorString() {
        return twoFactorString;
    }

    public void setTwoFactorString(String twoFactorString) {
        this.twoFactorString = twoFactorString;
    }

    public String getSecQuestion() {
        return secQuestion;
    }

    public void setSecQuestion(String secQuestion) {
        this.secQuestion = secQuestion;
    }

    public String getSecAnswer() {
        return secAnswer;
    }

    public void setSecAnswer(String secAnswer) {
        this.secAnswer = secAnswer;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getDateHired() {
        return dateHired;
    }

    public void setDateHired(int dateHired) {
        this.dateHired = dateHired;
    }

    public boolean isManagement() {
        return management;
    }

    public void setManagement(boolean management) {
        this.management = management;
    }

    public boolean isExecutive() {
        return executive;
    }

    public void setExecutive(boolean executive) {
        this.executive = executive;
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }


    @Override
    public String toString() {
        return "Employee{" +
                "uuid='" + uuid + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", twoFactorString='" + twoFactorString + '\'' +
                ", secQuestion='" + secQuestion + '\'' +
                ", secAnswer='" + secAnswer + '\'' +
                ", firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", title='" + title + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", dateHired=" + dateHired +
                ", management=" + management +
                ", executive=" + executive +
                ", roles=" + Arrays.toString(roles) +
                '}';
    }
}
