/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qlsv.register.dao;

import qlsv.register.entity.Student;
import qlsv.register.entity.StudentXML;
import qlsv.register.entity.Subject;
import qlsv.register.utils.FileUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author PC
 */
public class StudentDao {
    private static final String STUDENT_FILE_NAME = "student.xml";
    private List<Student> listStudents;
    
    public StudentDao() {
        this.listStudents = readListStudents();
        if (listStudents == null) {
            listStudents = new ArrayList<>();
        }
    }
        
    public void writeListStudents(List<Student> students) {
        StudentXML studentXML = new StudentXML();
        studentXML.setStudent(students);
        FileUtils.writeXMLtoFile(STUDENT_FILE_NAME, studentXML);
    }
    
    
    private List<Student> readListStudents() {
        List<Student> list = new ArrayList<>();
        StudentXML studentXML = (StudentXML) FileUtils.readXMLFile(
                STUDENT_FILE_NAME, StudentXML.class);
        if (studentXML != null) {
            list = studentXML.getStudent();
        }
        return list;
    }

    public List <Student> getListStudents() {
        return listStudents;
    }

    public void setListStudents(List<Student> listStudents) {
        this.listStudents = listStudents;
    }
    
    public void add (Student student) {
        listStudents.add(student);
        writeListStudents (listStudents);
    }
    
    public void edit(Student student) {
        int size = listStudents.size();
        for (int i = 0; i < size; i++) {
            if (listStudents.get(i).getID().equals(student.getID())) {
                listStudents.get(i).setName(student.getName());
                listStudents.get(i).setGender(student.getGender());
                listStudents.get(i).setDob(student.getDob());
                listStudents.get(i).setAddress(student.getAddress());
                listStudents.get(i).setMajor(student.getMajor());
                listStudents.get(i).setTerm(student.getTerm());
                listStudents.get(i).setLimitedCredit(student.getLimitedCredit());
                writeListStudents(listStudents);
                break;
            }
        }
    }
    
    public boolean delete(Student student) {
        boolean isFound = false;
        int size = listStudents.size();
        for (int i = 0; i < size; i++) {
            if (listStudents.get(i).getID().equals(student.getID())) {
                student = listStudents.get(i);
                isFound = true;
                break;
            }
        }
        if (isFound) {
            SubjectDao sd = new SubjectDao();
            for (Subject sStudent : student.getSubject()) {
                for (int i = 0; i < sd.getListSubjects().size(); i++) {
                    if (sStudent.getIDclass() == null) break;
                    
                    if (sStudent.getIDclass().equals(sd.getListSubjects().get(i).getIDclass())) {
                        sd.getListSubjects().get(i).setRegistedStudent(sd.getListSubjects().get(i).getRegistedStudent() - 1);
                    }
                }
            }
            listStudents.remove(student);
            writeListStudents(listStudents);
            sd.writeListSujects(sd.getListSubjects());
            return true;
        }
        return false;
    }
    public void sortStudentByName() {
        Collections.sort(listStudents, new Comparator<Student>() {
            @Override
            public int compare(Student student1, Student student2) {
                String name1 = student1.getName().substring(student1.getName().lastIndexOf(" ") + 1);
                String name2 = student2.getName().substring(student2.getName().lastIndexOf(" ") + 1);
                int compareName = name1.compareTo(name2);
                if (compareName == 0) {
                    String last1 = student1.getName().substring(0, student1.getName().lastIndexOf(" ") - 1);
                    String last2 = student2.getName().substring(0, student2.getName().lastIndexOf(" ") - 1);
                    return last1.compareTo(last2);
                } else {
                    return compareName;
                }
            }
        });
    }
    
    public List<Student> searchByName(String s) {
        List<Student> temp = new ArrayList<>();
        for (Student student : listStudents) {
            if (student.getName().contains(s)) {
                temp.add(student);
            }
        }
        return temp;
    }
    
    public List<Student> searchByID(String s) {
        List<Student> temp = new ArrayList<>();
        for (Student student : listStudents) {
            if (student.getID().contains(s)) {
                temp.add(student);
            }
        }
        return temp;
    }
    
    public List<Student> searchByMajor(String s) {
        List<Student> temp = new ArrayList<>();
        for (Student student : listStudents) {
            if (student.getMajor().contains(s)) {
                temp.add(student);
            }
        }
        return temp;
    }
    
    
    public void addSubjectInRegisterView(Student student, Subject subject){
        int size = listStudents.size();
        for (int i = 0; i < size; i++) {
            if (listStudents.get(i).getID().equals(student.getID())){
                listStudents.get(i).getSubject().add(subject);
                writeListStudents(listStudents);
                break;
            }
        }
    }
    
    public void distributeStudentRegisterView (List<Subject> list, String ID, String name, String type) {
        for (int i = 0; i < listStudents.size(); i++) {
            if (ID.equals(listStudents.get(i).getID())) {
                for (int k = 0; k < list.size(); k++) {
                    if (list.get(k).getName().equals(name) && list.get(k).getTypeClass().equals(type) && list.get(k).getRegistedStudent() < list.get(k).getQuantity()) {
                        for (int j = 0; j < listStudents.get(i).getSubject().size(); j++) {
                            if (listStudents.get(i).getSubject().get(j).getName().equals(name) && listStudents.get(i).getSubject().get(j).getTypeClass().equals(type)) {
                                listStudents.get(i).getSubject().get(j).setIDclass(list.get(k).getIDclass());
                                listStudents.get(i).getSubject().get(j).setTeacher(list.get(k).getTeacher());
                                listStudents.get(i).getSubject().get(j).setExam(list.get(k).getExam());
                                listStudents.get(i).getSubject().get(j).setQuantity(list.get(k).getQuantity());
                                list.get(k).setRegistedStudent(list.get(k).getRegistedStudent() + 1);
                                listStudents.get(i).getSubject().get(j).setRegistedStudent(list.get(k).getRegistedStudent());
                                break;
                            }
                        }
                        break;
                    }
                }
                break;
            }
        }
        writeListStudents(listStudents);
        (new SubjectDao()).writeListSujects(list);
    }
}


