/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qlsv.register.dao;


import qlsv.register.entity.Student;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import qlsv.register.entity.Subject;
import qlsv.register.entity.SubjectXML;
import qlsv.register.utils.FileUtils;

import java.util.HashSet;


public class SubjectDao {
    private static final String SUBJECT_FILE_NAME = "subject.xml";
    private List<Subject> listSubjects;
    
    public SubjectDao() {
        this.listSubjects = readListSubjects();
        if (listSubjects == null) {
            listSubjects = new ArrayList<>();
        }
    }
    
    
    /**
     * Lưu các đối tượng  vào file subject.xml
     * 
     * @param subjects
     */
    public void writeListSujects(List<Subject> subjects) {
        SubjectXML subjectXML = new SubjectXML();
        subjectXML.setSubject(subjects);
        FileUtils.writeXMLtoFile(SUBJECT_FILE_NAME, subjectXML);
    }
    
    
    /**
     * Đọc các đối tượng subject từ file subject.xml
     * 
     * @return list student
     */
    private List<Subject> readListSubjects() {
        List<Subject> list = new ArrayList<>();
        SubjectXML subjectXML = (SubjectXML) FileUtils.readXMLFile(
                SUBJECT_FILE_NAME, SubjectXML.class);
        if (subjectXML != null) {
            list = subjectXML.getSubject();
        }
        return list;
    }
    
    public void add (Subject subject) {
        subject.setRegistedStudent(0);
        listSubjects.add(subject);
        writeListSujects (listSubjects);
    }
    
    /**
     * cập nhật môn học vào listSubjects và lưu listSubjects vào file
     * 
     * @param subject
     */
    public void edit(Subject subject) {
        int size = listSubjects.size();
        
        for (int i = 0; i < size; i++) {
            if (listSubjects.get(i).getIDclass().equals(subject.getIDclass())) {
                listSubjects.get(i).setTypeClass(subject.getTypeClass());
                listSubjects.get(i).setTeacher(subject.getTeacher());
                listSubjects.get(i).setCredit(subject.getCredit());
                listSubjects.get(i).setExam(subject.getExam());
                listSubjects.get(i).setQuantity(subject.getQuantity());
                writeListSujects(listSubjects);
                break;
            }
        }
        
        StudentDao studentDao = new StudentDao();
        for (int i = 0; i < studentDao.getListStudents().size(); i++) {
            for (int j = 0; j < studentDao.getListStudents().get(i).getSubject().size(); j++) {
                if (subject.getIDclass().equals(studentDao.getListStudents().get(i).getSubject().get(j).getIDclass())) {
                    studentDao.getListStudents().get(i).getSubject().get(j).setTypeClass(subject.getTypeClass());
                    studentDao.getListStudents().get(i).getSubject().get(j).setTeacher(subject.getTeacher());
                    int temp = studentDao.getListStudents().get(i).getRegistedCredit() - studentDao.getListStudents().get(i).getSubject().get(j).getCredit();
                    studentDao.getListStudents().get(i).getSubject().get(j).setCredit(subject.getCredit());
                    studentDao.getListStudents().get(i).setRegistedCredit(temp + studentDao.getListStudents().get(i).getSubject().get(j).getCredit());
                    studentDao.getListStudents().get(i).getSubject().get(j).setExam(subject.getExam());
                    studentDao.getListStudents().get(i).getSubject().get(j).setQuantity(subject.getQuantity());
                    studentDao.writeListStudents(studentDao.getListStudents());
                }
            }
        }
    }

    /**
     * xóa môn học từ listSubjects và lưu listSubjectts vào file
     * 
     * @param subject
     * @return 
     */
     
        public boolean delete(Subject subject) {
        boolean isFound = false;
        int size = listSubjects.size();
        for (int i = 0; i < size; i++) {
            if (listSubjects.get(i).getIDclass().equals(subject.getIDclass())) {
                subject = listSubjects.get(i);
                isFound = true;
                break;
            }
        }
        if (isFound) {
            StudentDao sd = new StudentDao();
            for (Student student : sd.getListStudents()) {
                int count = 0;
                for (Subject s : listSubjects) {
                    if (s.getName().equals(subject.getName())) count++;
                }
                
                for (int i = 0; i < student.getSubject().size(); i++) {
                    if (student.getSubject().get(i).getName().equals(subject.getName()) && student.getSubject().get(i).getIDclass() == null && count == 1) {
                        student.getSubject().remove(i);
                        student.setRegistedCredit(student.getRegistedCredit() - subject.getCredit());
                        break;
                    }
                    
                    if (subject.getIDclass().equals(student.getSubject().get(i).getIDclass())) {
                        student.getSubject().remove(i);
                        student.setRegistedCredit(student.getRegistedCredit() - subject.getCredit());
                        break;
                    }
                }
            }
            listSubjects.remove(subject);
            writeListSujects(listSubjects);
            sd.writeListStudents(sd.getListStudents());
            return true;
        }
        return false;
    }
   
     

    /**
     * sắp xếp danh sách môn học theo tên môn học
     */
        
    public void sortSubjectByName() {
        Collections.sort(listSubjects, new Comparator<Subject>() {
            @Override
            public int compare(Subject student1, Subject student2) {
                if (!student1.getName().equals(student2.getName())) {
                    return student1.getName().compareTo(student2.getName());
                } else {
                    return student1.getIDclass().compareTo(student2.getIDclass());
                }
            }
        });
    }
    
    /**
     * sắp xếp danh sách môn học theo mã môn học
     */
    public void sortSubjectByID() {
        Collections.sort(listSubjects, new Comparator<Subject>() {
            @Override
            public int compare(Subject student1, Subject student2) {
                return student1.getIDclass().compareTo(student2.getIDclass());
            }
        });
    }
    
    /**
     * sắp xếp danh sách môn học theo số lượng sinh viên đã đăng ký theo thứ tự tăng dần
     */
        
    public void sortSubjectByRegistedStudent() {
        Collections.sort (listSubjects, new Comparator<Subject>() {
            @Override
            public int compare(Subject student1, Subject student2) {
                if (student1.getRegistedStudent() > student2.getRegistedStudent()) {
                    return -1;
                }
                if (student1.getRegistedStudent() == student2.getRegistedStudent()) {
                    if (student1.getQuantity() > student2.getQuantity()) {
                        return -1;
                    }
                    if (student1.getQuantity() == student2.getQuantity()) {
                        if (!student1.getName().equals(student2.getName())) {
                            return student1.getName().compareTo(student2.getName());
                        } else {
                            return student1.getIDclass().compareTo(student2.getIDclass());
                        }
                    }
                }
                return 1;
            }
        });
    }
    
    /**
     * tìm kiếm môn học theo tên môn học
     * 
     * @param s
     * @return 
     */
    
    public List<Subject> searchByName(String s) {
        List<Subject> temp = new ArrayList<>();
        for (Subject subject : listSubjects) {
            if (subject.getName().contains(s)) {
                temp.add(subject);
            }
        }
        return temp;
    }
    
    /**
     * tìm kiếm môn học theo mã lớp
     * 
     * @param s
     * @return 
     */
    
    public List<Subject> searchByIDclass(String s) {
        List<Subject> temp = new ArrayList<>();
        for (Subject subject : listSubjects) {
            if (subject.getIDclass().contains(s)) {
                temp.add(subject);
            }
        }
        return temp;
    }
    
    //Thống kê
    public int statisticsSumClass (){
        return listSubjects.size();
    }
    
    public int statisticsSumSubject(){
        HashSet<String> set = new HashSet<>();
        
        for (Subject s : listSubjects) {
            set.add(s.getName());
        }
        return set.size();
    }
    
    public int sumCredit (){
        int count = 0;
        for (Subject s : listSubjects){
            count += s.getCredit();
        }
        return count;
    }
    
    public List <String> maxNumberofStudents (){ //Thử xem có sử dụng stack được không
        int maxStudent = 0;
        List<String> temp = new ArrayList<>();
                
        for (Subject subject : listSubjects){
            if (subject.getQuantity() >maxStudent){
                maxStudent = subject.getQuantity();
            }
        }
            
        for (Subject s : listSubjects) {
            if (s.getQuantity() == maxStudent) {
                temp.add(s.getName());
            }
        }
        return temp;
    } 
     public List <String> minNumberofStudents (){ //Thử xem có sử dụng stack được không
        int minStudent = listSubjects.get(0).getQuantity();
        List<String> temp = new ArrayList<>();
                
        for (Subject subject : listSubjects){
            if (subject.getQuantity() < minStudent){
                minStudent = subject.getQuantity();
            }
        }
            
        for (Subject s : listSubjects) {
            if (s.getQuantity() == minStudent) {
                temp.add(s.getName());
            }
        }
        return temp;
    } 
    
    public List<Subject> getListSubjects() {
        return listSubjects;
    }

    public void setListStudents(List<Subject> listSubjects) {
        this.listSubjects = listSubjects;
    }
}
