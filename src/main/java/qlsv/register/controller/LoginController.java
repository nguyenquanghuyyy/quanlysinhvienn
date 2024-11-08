package qlsv.register.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import qlsv.register.dao.UserDao;
import qlsv.register.view.LoginView;
import qlsv.register.view.OptionView;

import qlsv.register.entity.User;

public class LoginController {
    private final UserDao userDao;
    private final LoginView loginView;
    private OptionView optionView;
    
    public LoginController(LoginView loginView) {
        this.loginView = loginView;
        this.userDao = new UserDao();
        loginView.addLoginListener(new LoginListener());
//        loginView.addEmailKeyPressed(new TextFieldEmailKeyPressed());
//        loginView.addPasswordkeyPressed(new PasswordFieldKeyPressed());
    }
    
    public void showLoginView() {
        loginView.setVisible(true);
    }
    
    /**
     * Lớp LoginListener 
     * chứa cài đặt cho sự kiện click button "Login"
     */
    class LoginListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            User user = loginView.getUser();
            if (userDao.checkEmail(user) && userDao.checkPassword(user)) {
                // nếu đăng nhập thành công, mở màn hình option
                optionView = new OptionView();
                OptionController optionController = new OptionController(optionView);
                optionController.showOptionView();
                loginView.setVisible(false);
            } else if (!userDao.checkEmailIsEmpty(user)) {
                loginView.EmailIsEmpty();
            } else if (userDao.checkEmail(user) && !userDao.checkPasswordIsEmpty(user)) {
                loginView.PasswordIsEmpty();
            } else if (!userDao.checkEmail(user)) {
                loginView.EmailErro();
            } else if (userDao.checkEmail(user) && !userDao.checkPassword(user)) {
                loginView.PasswordErro();
            }
        }
    }
    
//    class PasswordFieldKeyPressed implements KeyListener {
//
//        @Override
//        public void keyTyped(KeyEvent e) {
//        }
//
//        @Override
//        public void keyPressed(KeyEvent e) {
//            User user = loginView.getUser();
//            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
//                if (userDao.checkEmail(user) && userDao.checkPassword(user)) {
//                    // nếu đăng nhập thành công, mở màn hình option
//                    optionView = new OptionView();
//                    OptionController optionController = new OptionController(optionView);
//                    optionController.showOptionView();
//                    loginView.setVisible(false);
//                } else if (!userDao.checkEmailIsEmpty(user)) {
//                    loginView.EmailIsEmpty();
//                } else if (userDao.checkEmail(user) && !userDao.checkPasswordIsEmpty(user)) {
//                    loginView.PasswordIsEmpty();
//                } else if (!userDao.checkEmail(user)) {
//                    loginView.EmailErro();
//                } else if (userDao.checkEmail(user) && !userDao.checkPassword(user)) {
//                    loginView.PasswordErro();
//                }
//                
//            }
//        }
//
//        @Override
//        public void keyReleased(KeyEvent e) {
//        }
//    }
//    
//    class TextFieldEmailKeyPressed implements KeyListener {
//
//        @Override
//        public void keyTyped(KeyEvent e) {
//        }
//
//        @Override
//        public void keyPressed(KeyEvent e) {
//            User user = loginView.getUser();
//            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
//                if (userDao.checkEmail(user) && userDao.checkPassword(user)) {
//                    // nếu đăng nhập thành công, mở màn hình option
//                    optionView = new OptionView();
//                    OptionController optionController = new OptionController(optionView);
//                    optionController.showOptionView();
//                    loginView.setVisible(false);
//                } else if (!userDao.checkEmailIsEmpty(user)) {
//                    loginView.EmailIsEmpty();
//                } else if (userDao.checkEmail(user) && !userDao.checkPasswordIsEmpty(user)) {
//                    loginView.PasswordIsEmpty();
//                } else if (!userDao.checkEmail(user)) {
//                    loginView.EmailErro();
//                } else if (userDao.checkEmail(user) && !userDao.checkPassword(user)) {
//                    loginView.PasswordErro();
//                }
//                
//                loginView.getTextFieldEmail().requestFocusInWindow();
//            }
//        }
//
//        @Override
//        public void keyReleased(KeyEvent e) {
//        }
//    }
}
