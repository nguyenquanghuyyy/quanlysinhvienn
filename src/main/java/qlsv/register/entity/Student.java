/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qlsv.register.entity;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author PC
 */
public class Student {
    private String ID;
    private String name;
    private String gender;
    private String dob;
    private String address;
    private String major;
    private String term;
    private int limitedCredit;
    private int registedCredit;
    private List<Subject> subject;

    public Student() {
        subject = new ArrayList<>();
        registedCredit = 0;
    }

    public Student(String ID, String name, String gender, String dob, String address, String major, String term, int limitedCredit, int registedCredit, List<Subject> subject) {
        this.ID = ID;
        this.name = name;
        this.gender = gender;
        this.dob = dob;
        this.address = address;
        this.major = major;
        this.term = term;
        this.limitedCredit = limitedCredit;
        this.registedCredit = registedCredit;
        this.subject = subject;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public int getLimitedCredit() {
        return limitedCredit;
    }

    public void setLimitedCredit(int limitedCredit) {
        this.limitedCredit = limitedCredit;
    }

    public int getRegistedCredit() {
        return registedCredit;
    }

    public void setRegistedCredit(int registedCredit) {
        this.registedCredit = registedCredit;
    }

    public List<Subject> getSubject() {
        return subject;
    }

    public void setSubject(List<Subject> subject) {
        this.subject = subject;
    }
}
