/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qlsv.register.entity;

/**
 *
 * @author PC
 */
public class Subject {
    private String name;
    private String IDclass;
    private String typeClass;
    private String teacher;
    private int credit;
    private String exam;
    private int quantity;
    private int registedStudent;

    public Subject() {
        registedStudent = 0;
    }

    public Subject(String name, String IDclass, String typeClass, String teacher, int credit, String exam, int quantity, int registedStudent) {
        this.name = name;
        this.IDclass = IDclass;
        this.typeClass = typeClass;
        this.teacher = teacher;
        this.credit = credit;
        this.exam = exam;
        this.quantity = quantity;
        this.registedStudent = registedStudent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIDclass() {
        return IDclass;
    }

    public void setIDclass(String IDclass) {
        this.IDclass = IDclass;
    }

    public String getTypeClass() {
        return typeClass;
    }

    public void setTypeClass(String typeClass) {
        this.typeClass = typeClass;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public String getExam() {
        return exam;
    }

    public void setExam(String exam) {
        this.exam = exam;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getRegistedStudent() {
        return registedStudent;
    }

    public void setRegistedStudent(int registedStudent) {
        this.registedStudent = registedStudent;
    }
}
