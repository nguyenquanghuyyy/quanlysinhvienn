/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qlsv.register.controller;

import qlsv.register.dao.SubjectDao;
import qlsv.register.entity.Subject;
import qlsv.register.view.OptionView;
import qlsv.register.view.SubjectView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author PC
 */
public class SubjectController {
    private final SubjectView subjectView;
    private OptionView optionView;
    private final SubjectDao subjectDao;

    public SubjectController(SubjectView subjectView) {
        this.subjectView = subjectView;
        subjectDao = new SubjectDao();
        
        subjectView.addReturnListener(new ReturnListener());
        subjectView.addClearListener(new ClearSubjectListener());
        subjectView.addAddListener(new AddSubjectListener());
        subjectView.addEditListener(new EditSubjectListener());
        subjectView.addDeleteListener(new DeleteSubjectListener());
        subjectView.addSortNameListener(new SortSubjectNameListener());
        subjectView.addSortRegistedStudentListener(new SortRegistedStudentListener());
        subjectView.addSearchListener(new SearchSubjectListener());
        subjectView.addRefreshListener(new RefreshSearchListener());
        subjectView.addListSubjectSelectionListener(new ListSubjectSelectionListener());
    }
    
    public void showSubjectView() {
        List<Subject> subjectList = subjectDao.getListSubjects();
        subjectView.setVisible(true);
        subjectView.showListSubjects(subjectList);
    }
    
    class ReturnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            optionView = new OptionView();
            OptionController optionController = new OptionController(optionView);
            optionController.showOptionView();
            subjectView.setVisible(false);
        }
    }
    
    class ClearSubjectListener implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            subjectView.clearSubjectInfo();
        }
    }
    
    class AddSubjectListener implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            Subject s = subjectView.getSubjectInfo();
            
            for (Subject subject : subjectDao.getListSubjects()) {
                if (s.getIDclass().equals(subject.getIDclass())) {
                    subjectView.showMessage("Mã lớp đã tồn tại");
                    s = null;
                    break;
                }
            }
            
            if (s != null) {
                subjectDao.add(s);
                System.out.println(s.getRegistedStudent());
                subjectView.showListSubjects(subjectDao.getListSubjects());
                subjectView.clearSubjectInfo();
                subjectView.showMessage("Thêm thành công");
            }
        }
    }
    
    class EditSubjectListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Subject s = subjectView.getSubjectInfo();
            if (s != null) {
                subjectDao.edit(s);
                subjectDao.sortSubjectByID();
                subjectView.showListSubjects(subjectDao.getListSubjects());
                subjectView.clearSubjectInfo();
                subjectView.showMessage("Cập nhật thành công");
            }
        }
    }
    
    class DeleteSubjectListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Subject s = subjectView.getSubjectInfo();
            if (s != null) {
                subjectDao.delete(s);
                subjectView.showListSubjects(subjectDao.getListSubjects());
                subjectView.clearSubjectInfo();
                subjectView.showMessage("Xóa thành công");
            }
        }
    }
    
    class SortSubjectNameListener implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            subjectDao.sortSubjectByName();
            subjectView.showListSubjects(subjectDao.getListSubjects());
        }
    }
    
    class SortRegistedStudentListener implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            subjectDao.sortSubjectByRegistedStudent();
            subjectView.showListSubjects(subjectDao.getListSubjects());
        }
    }
    
    class SearchSubjectListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (subjectView.getComboBoxSearch().equals("<none>"))  {
                subjectView.showMessage("Chưa lựa chọn kiểu tìm kiếm");
            }
            
            else {
                switch (subjectView.getComboBoxSearch()) {
                    case "Tên môn học":
                        if (subjectDao.searchByName(subjectView.getTextFieldSearch()).isEmpty()) {
                            subjectView.showListSubjects(subjectDao.getListSubjects());
                            subjectView.showMessage("Không có kết quả phù hợp");
                        } else {
                            subjectView.showListSubjects(subjectDao.searchByName(subjectView.getTextFieldSearch()));
                            subjectView.clearSubjectInfo();
                        }
                        break;
                    case "Mã lớp":
                        if (subjectDao.searchByIDclass(subjectView.getTextFieldSearch()).isEmpty()) {
                            subjectView.showListSubjects(subjectDao.getListSubjects());
                            subjectView.showMessage("Không có kết quả phù hợp");
                        } else {
                            subjectView.showListSubjects(subjectDao.searchByIDclass(subjectView.getTextFieldSearch()));
                            subjectView.clearSubjectInfo();
                        }
                        break;
                }
                
                if (subjectView.getTextFieldSearch().equals("")) {
                    subjectView.showListSubjects(subjectDao.getListSubjects());
                    subjectView.showMessage("Không có kết quả phù hợp");
                }
            }
        }
        
    }
    
    class RefreshSearchListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            for (Subject s : subjectDao.getListSubjects()) {
                subjectView.setTextFieldSearch("");
                subjectView.showListSubjects(subjectDao.getListSubjects());
                subjectView.clearSubjectInfo();
            }
        }
        
    }
    
    class ListSubjectSelectionListener implements ListSelectionListener {
        
        @Override
        public void valueChanged(ListSelectionEvent e) {
            subjectView.fillSubjectFromSelectedRow();
        }
        
    }
}
