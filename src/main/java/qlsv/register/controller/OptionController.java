/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qlsv.register.controller;

import qlsv.register.view.OptionView;
import qlsv.register.view.RegisterView;
import qlsv.register.view.StatisticsView;
import qlsv.register.view.StudentView;
import qlsv.register.view.SubjectView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author PC
 */
public class OptionController {
    private final OptionView optionView;
    private SubjectView subjectView;
    private StudentView studentView;
    private RegisterView registerView;
    private StatisticsView statisticsView;

    public OptionController(OptionView optionView) {
        this.optionView = optionView;
        
        optionView.addSubjectListener(new SubjectListener());
        optionView.addStudentListener(new StudentListener());
        optionView.addRegisterListener(new RegisterListener());
        optionView.addStatisticsListener(new StatisticsListener());
    }
    
    public void showOptionView() {
        optionView.setVisible(true);
    }

    class SubjectListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            subjectView = new SubjectView();
            SubjectController subjectController = new SubjectController(subjectView);
            subjectController.showSubjectView();
            optionView.setVisible(false);
        }
        
    }
    
    class StudentListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            studentView = new StudentView();
            StudentController studentController = new StudentController(studentView);
            studentController.showStudentView();
            optionView.setVisible(false);
        }
        
    }
    
    class RegisterListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            registerView = new RegisterView();
            RegisterController registerController = new RegisterController(registerView);
            registerController.showRegisterView();
            optionView.setVisible(false);
        }
        
    }
    
    class StatisticsListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            statisticsView = new StatisticsView();
            StatisticsController statisticsController = new StatisticsController(statisticsView);
            statisticsController.showStatisticsView();
            optionView.setVisible(false);
        }
        
    }
}
