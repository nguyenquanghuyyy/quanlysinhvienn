/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qlsv.register.controller;

import qlsv.register.dao.StudentDao;
import qlsv.register.dao.SubjectDao;
import qlsv.register.entity.Subject;
import qlsv.register.view.OptionView;
import qlsv.register.view.RegisterView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author PC
 */
public class RegisterController {
    private final RegisterView registerView;
    private OptionView optionView;
    private final StudentDao studentDao;
    private final SubjectDao subjectDao;

    public RegisterController(RegisterView registerView) {
        this.registerView = registerView;
        studentDao = new StudentDao();
        subjectDao = new SubjectDao();
        
        registerView.addReturnListener(new ReturnListener());
        registerView.addListStudentSelectionListener(new ListStudentSelectionListener());
        registerView.addListSubjectSelectionListener(new ListSubjectSelectionListener());
        registerView.addAddListener(new AddSubjectListener());
        registerView.addDeleteListener(new DeleteSubjectListener());
        registerView.addClearSubjectListener(new ClearSubjectListener());
        registerView.addDistributeListener(new DistributeStudentListener());
    }
    
    public void showRegisterView() {
       registerView.setVisible(true);
    }
    
    class ReturnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            optionView = new OptionView();
            OptionController optionController = new OptionController(optionView);
            optionController.showOptionView();
            registerView.setVisible(false);
        }
        
    }
    
    class ListStudentSelectionListener implements ListSelectionListener {
        
        @Override
        public void valueChanged(ListSelectionEvent e) {
            registerView.fillStudentFromSelectedRow();
        }
        
    }
    
    class ListSubjectSelectionListener implements ListSelectionListener {
        
        @Override
        public void valueChanged(ListSelectionEvent e) {
            registerView.fillSubjectFromSelectedRow();
        }
        
    }
    
    class ClearSubjectListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            registerView.clearSubjectInfo();
        }
    }
    
    class AddSubjectListener implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            Subject s = registerView.getSubjectInfo();
            boolean check = true;
            int index = 0;
            
            for (int i = 0; i < studentDao.getListStudents().size(); i++) {
                if (registerView.selectedStudent().getID().equals(studentDao.getListStudents().get(i).getID())) {
                    if (studentDao.getListStudents().get(i).getRegistedCredit() + s.getCredit() > studentDao.getListStudents().get(i).getLimitedCredit()) {
                        registerView.showMessage("Số tín chỉ đăng ký không được vượt quá số tín chỉ tối đa.");
                        break;
                    }
                    
                    int sumQuantity = 0;
                    int sumRegistedStudent = 0;
                    
                    for (Subject subject : subjectDao.getListSubjects()) {
                        if (subject.getName().equals(s.getName())) {
                            sumQuantity += subject.getQuantity();
                            sumRegistedStudent += subject.getRegistedStudent();
                        }
                    }
                    
                    if (sumQuantity == sumRegistedStudent) {
                        check = false;
                        registerView.showMessage("Tất cả các lớp của môn " + s.getName() + " đã đầy.");
                        break;
                    }
                    
                    for (int j = 0; j < studentDao.getListStudents().get(i).getSubject().size(); j++) {
                        if (studentDao.getListStudents().get(i).getSubject().get(j).getName().equals(s.getName())) {
                            check = false;
                            registerView.showMessage(registerView.selectedStudent().getName() + " đã đăng kí môn " + s.getName().trim() + ".");
                            break;
                        }
                    }
                    
                    if (s != null && check) {
                        studentDao.getListStudents().get(i).setRegistedCredit(studentDao.getListStudents().get(i).getRegistedCredit() + s.getCredit());
                        studentDao.addSubjectInRegisterView(studentDao.getListStudents().get(i), s);
                        index = i;
                        break;
                    }
                }
            }
            
            if (s != null && check) {
                registerView.showListSubjects(studentDao.getListStudents().get(index).getSubject());
                registerView.setLabelRegistedCredit(studentDao.getListStudents().get(index).getRegistedCredit() + "");
                registerView.clearSubjectInfo();
                registerView.showMessage("Thêm thành công.");
            }
            
        }
    }
    
    class DeleteSubjectListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Subject s = registerView.getSubjectInfo();
            int index = 0;
            
            for (int i = 0; i < studentDao.getListStudents().size(); i++) {
                if (registerView.selectedStudent().getID().equals(studentDao.getListStudents().get(i).getID())) {
                    studentDao.getListStudents().get(i).setRegistedCredit(studentDao.getListStudents().get(i).getRegistedCredit() - s.getCredit());
                    
                    for (int j = 0; j < studentDao.getListStudents().get(i).getSubject().size(); j++) {
                        if (studentDao.getListStudents().get(i).getSubject().get(j).getIDclass() != null) {
                            for (int k = 0; k < subjectDao.getListSubjects().size(); k++) {
                                if (subjectDao.getListSubjects().get(k).getIDclass().equals(studentDao.getListStudents().get(i).getSubject().get(j).getIDclass())) {
                                    subjectDao.getListSubjects().get(k).setRegistedStudent(subjectDao.getListSubjects().get(k).getRegistedStudent() - 1);
//                                    subjectDao.getListSubjects().get(k).setAddedStudent(subjectDao.getListSubjects().get(k).getAddedStudent() - 1);
                                    subjectDao.writeListSujects(subjectDao.getListSubjects());
                                    break;
                                }
                            }
                        }
                        
                        if (studentDao.getListStudents().get(i).getSubject().get(j).getName().equals(s.getName())) {
                            studentDao.getListStudents().get(i).getSubject().remove(j);
                            studentDao.writeListStudents(studentDao.getListStudents());
                            break;
                        }
                    }
                    index = i;
                    break;
                }
            }
            
            if (s != null) {
                registerView.showListSubjects(studentDao.getListStudents().get(index).getSubject());
                registerView.setLabelRegistedCredit(studentDao.getListStudents().get(index).getRegistedCredit() + "");
                registerView.clearSubjectInfo();
                registerView.showMessage("Xóa thành công.");
            }
        }
    }
    
    class DistributeStudentListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String studentID = registerView.getTextFieldStudentID();
            String subjectName = registerView.getComboBoxSubjectName();
            String typeClass = registerView.getComboBoxTypeClass();
            studentDao.distributeStudentRegisterView(subjectDao.getListSubjects(), studentID, subjectName, typeClass);
            for (int i = 0; i < studentDao.getListStudents().size(); i++) {
                if (studentDao.getListStudents().get(i).getID().equals(studentID)) {
                    registerView.showListSubjects(studentDao.getListStudents().get(i).getSubject());
                }
            }
            registerView.clearSubjectInfo();
            registerView.showMessage("Phân lớp thành công.");
        }
        
    }
}
