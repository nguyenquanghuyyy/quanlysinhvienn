/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qlsv.register.controller;

import qlsv.register.dao.StudentDao;
import qlsv.register.entity.Student;
import qlsv.register.view.OptionView;
import qlsv.register.view.StudentView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author PC
 */
public class StudentController {
    private final StudentView studentView;
    private OptionView optionView;
    private final StudentDao studentDao;

    public StudentController(StudentView studentView) {
        this.studentView = studentView;
        studentDao = new StudentDao();
        
        studentView.addReturnListener(new ReturnListener());
        studentView.addAddStudentListener(new AddStudentListener());
        studentView.addListStudentSelectionListener(new ListStudentSelectionListener());
        studentView.addClearListener(new ClearStudentListener());
        studentView.addEditListener(new EditStudentListener());
        studentView.addDeleteListener(new DeleteStudentListener());
        studentView.addSortNameListener(new SortStudentNameListener());
        studentView.addSearchListener(new SearchStudentListener());
        studentView.addRefreshListener(new RefreshSearchListener());
    }

    public void showStudentView() {
        List<Student> studentList = studentDao.getListStudents();
        studentView.setVisible(true);
        studentView.showListStudents(studentList);
    }

    
    class ReturnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            optionView = new OptionView();
            OptionController optionController = new OptionController(optionView);
            optionController.showOptionView();
            studentView.setVisible(false);
        }
    }
    
    class AddStudentListener implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            Student s = studentView.getStudentInfo();
            
            for (Student student : studentDao.getListStudents()) {
                if (s.getID().equals(student.getID())) {
                    studentView.showMessage("Mã sinh viên đã tồn tại");
                }
            }
            
            if (s != null) {
                studentDao.add(s);
                studentView.showListStudents(studentDao.getListStudents());
                studentView.clearStudentInfo();
                studentView.showMessage("Thêm thành công");
            }
        }
    }
    
    class ListStudentSelectionListener implements ListSelectionListener {       
        @Override
        public void valueChanged(ListSelectionEvent e) {
            studentView.fillStudentFromSelectedRow();
        }
    }
    
    class ClearStudentListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            studentView.clearStudentInfo();
        }
    }
    
    class EditStudentListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Student s = studentView.getStudentInfo();
            if (s != null) {
                studentDao. edit(s);
                studentView.showListStudents(studentDao.getListStudents());
                studentView.clearStudentInfo();
                studentView.showMessage("Cập nhật thành công");
            }
        }
    }
    
    class DeleteStudentListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Student s = studentView.getStudentInfo();
            if (s != null) {
                studentDao.delete(s);
                studentView.showListStudents(studentDao.getListStudents());
                studentView.clearStudentInfo();
                studentView.showMessage("Xóa thành công");
            }
        }
    }
        
    class SortStudentNameListener implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            studentDao.sortStudentByName();
            studentView.showListStudents(studentDao.getListStudents());
        }
    }
        
    class SearchStudentListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (studentView.getComboBoxSearch().equals("<none>")) {
                studentView.showMessage("Chưa lựa chọn kiểu tìm kiếm");
            }
            
            else {
                switch (studentView.getComboBoxSearch()) {
                    case "Tên sinh viên":
                        if (studentDao.searchByName(studentView.getTextFieldSearch()).isEmpty()) {
                            studentView.showListStudents(studentDao.getListStudents());
                            studentView.showMessage("Không có kết quả phù hợp");
                        } else {
                            studentView.showListStudents(studentDao.searchByName(studentView.getTextFieldSearch()));
                            studentView.clearStudentInfo();
                        }
                        break;
                    case "Mã sinh viên":
                        if (studentDao.searchByID(studentView.getTextFieldSearch()).isEmpty()) {
                            studentView.showListStudents(studentDao.getListStudents());
                            studentView.showMessage("Không có kết quả phù hợp");
                        } else {
                            studentView.showListStudents(studentDao.searchByID(studentView.getTextFieldSearch()));
                            studentView.clearStudentInfo();
                        }
                        break;
                    case "Chuyên ngành":
                        if (studentDao.searchByMajor(studentView.getTextFieldSearch()).isEmpty()) {
                            studentView.showListStudents(studentDao.getListStudents());
                            studentView.showMessage("Không có kết quả phù hợp");
                        } else {
                            studentView.showListStudents(studentDao.searchByMajor(studentView.getTextFieldSearch()));
                            studentView.clearStudentInfo();
                        }
                        break;
                }
                
                if (studentView.getTextFieldSearch().equals("")) {
                    studentView.showListStudents(studentDao.getListStudents());
                    studentView.showMessage("Không có kết quả phù hợp");
                }
                
                
            }
        }
    }
    
    class RefreshSearchListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            for (Student s : studentDao.getListStudents()) {
                studentView.setTextFieldSearch("");
                studentView.showListStudents(studentDao.getListStudents());
                studentView.clearStudentInfo();
            }
        }
        
    }
}
