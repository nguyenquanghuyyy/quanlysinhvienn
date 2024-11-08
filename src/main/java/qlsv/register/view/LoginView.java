/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package qlsv.register.view;

import button.MyButton;
import qlsv.register.controller.OptionController;
import qlsv.register.dao.UserDao;
import qlsv.register.entity.User;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author PC
 */
public class LoginView extends javax.swing.JFrame {
    private final UserDao userDao = new UserDao();
    private OptionView optionView = new OptionView();

    /**
     * Creates new form Login
     */
    public LoginView() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Đăng nhập");
        
        ImageIcon icon = new ImageIcon("logo\\logo.png");
//        ImageIcon icon = new ImageIcon("src\\main\\resources\\img\\logo.png");
        setIconImage(icon.getImage());
        
        addPlaceholderStype(TextFieldEmail);
        addPlaceholderStype(PasswordField);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LabelUserID = new javax.swing.JLabel();
        LabelPassword = new javax.swing.JLabel();
        SeparatorEmail = new javax.swing.JSeparator();
        TextFieldEmail = new javax.swing.JTextField();
        SeparatorPassword = new javax.swing.JSeparator();
        PasswordField = new javax.swing.JPasswordField();
        ButtonLogin = new button.MyButton();
        BackGround = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(512, 400));
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LabelUserID.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        LabelUserID.setForeground(new java.awt.Color(0, 51, 51));
        LabelUserID.setText("Email:");
        LabelUserID.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        getContentPane().add(LabelUserID, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, -1, 30));

        LabelPassword.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        LabelPassword.setForeground(new java.awt.Color(0, 51, 51));
        LabelPassword.setText("Mật khẩu:");
        getContentPane().add(LabelPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 180, -1, 30));

        SeparatorEmail.setForeground(new java.awt.Color(153, 153, 153));
        getContentPane().add(SeparatorEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 170, 250, 10));

        TextFieldEmail.setBackground(new java.awt.Color(242, 242, 242));
        TextFieldEmail.setForeground(java.awt.Color.gray);
        TextFieldEmail.setText("Nhập email");
        TextFieldEmail.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TextFieldEmail.setOpaque(true);
        TextFieldEmail.setPreferredSize(new java.awt.Dimension(64, 22));
        TextFieldEmail.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                TextFieldEmailFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                TextFieldEmailFocusLost(evt);
            }
        });
        TextFieldEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TextFieldEmailKeyPressed(evt);
            }
        });
        getContentPane().add(TextFieldEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 140, 250, 30));

        SeparatorPassword.setForeground(new java.awt.Color(153, 153, 153));
        getContentPane().add(SeparatorPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 230, 250, 10));

        PasswordField.setBackground(new java.awt.Color(242, 242, 242));
        PasswordField.setForeground(java.awt.Color.gray);
        PasswordField.setText("Nhập mật khẩu");
        PasswordField.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        PasswordField.setEchoChar('\u0000');
        PasswordField.setMinimumSize(new java.awt.Dimension(64, 22));
        PasswordField.setPreferredSize(new java.awt.Dimension(64, 22));
        PasswordField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                PasswordFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                PasswordFieldFocusLost(evt);
            }
        });
        PasswordField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PasswordFieldKeyPressed(evt);
            }
        });
        getContentPane().add(PasswordField, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 200, 250, 30));

        ButtonLogin.setBorder(null);
        ButtonLogin.setForeground(new java.awt.Color(0, 153, 51));
        ButtonLogin.setText("Đăng nhập");
        ButtonLogin.setMaximumSize(new java.awt.Dimension(30, 10));
        ButtonLogin.setMinimumSize(new java.awt.Dimension(30, 10));
        ButtonLogin.setPreferredSize(new java.awt.Dimension(30, 10));
        ButtonLogin.setRadius(20);
        getContentPane().add(ButtonLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 260, 90, 30));
        ButtonLogin.setFont(new java.awt.Font("Cambria", 1, 14));

        BackGround.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/LoginBackGround.jpg"))); // NOI18N
        getContentPane().add(BackGround, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 512, 384));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TextFieldEmailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TextFieldEmailFocusGained
        if (TextFieldEmail.getText().equals("Nhập email") || TextFieldEmail.getText().equals("Email không tồn tại") || TextFieldEmail.getText().equals("Chưa nhập email")) {
            TextFieldEmail.setText(null);
            TextFieldEmail.requestFocus();
            //remove placeholder stype
            removePlaceholderStype(TextFieldEmail);
        }
        
        if (PasswordField.getText().equals("Mật khẩu không đúng") || PasswordField.getText().equals("Chưa nhập mật khẩu")) {
            PasswordField.setText("Nhập mật khẩu");
            addPlaceholderStype(PasswordField);
            SeparatorPassword.setForeground(new Color(153,153,153));
        }
        SeparatorEmail.setForeground(new Color(0,153,255));
    }//GEN-LAST:event_TextFieldEmailFocusGained

    private void PasswordFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_PasswordFieldFocusGained
        SeparatorPassword.setForeground(new Color(0,153,255));
        if (PasswordField.getText().equals("Nhập mật khẩu") || PasswordField.getText().equals("Mật khẩu không đúng") || PasswordField.getText().equals("Chưa nhập mật khẩu")) {
            PasswordField.setText(null);
            PasswordField.requestFocus();
            //set password charater
            PasswordField.setEchoChar('\u2022');
            //remove placeholder stype
            removePlaceholderStype(PasswordField);
            SeparatorPassword.setForeground(new Color(0,153,255));
            
            if (TextFieldEmail.getText().equals("Email không tồn tại") || TextFieldEmail.getText().equals("Chưa nhập email")) {
                TextFieldEmail.setText("Nhập email");
                addPlaceholderStype(TextFieldEmail);
                SeparatorEmail.setForeground(new Color(153,153,153));
            }
        }
    }//GEN-LAST:event_PasswordFieldFocusGained

    private void TextFieldEmailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TextFieldEmailFocusLost
        if (TextFieldEmail.getText().length() == 0) {
            //add placeholder stype
            addPlaceholderStype(TextFieldEmail);
            TextFieldEmail.setText("Nhập email");
        }
        SeparatorEmail.setForeground(new Color(153,153,153));
    }//GEN-LAST:event_TextFieldEmailFocusLost

    private void PasswordFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_PasswordFieldFocusLost
        if (PasswordField.getText().length() == 0) {
            //add placeholder stype
            addPlaceholderStype(PasswordField);
            PasswordField.setText("Nhập mật khẩu");
            PasswordField.setEchoChar('\u0000');
        }
        SeparatorPassword.setForeground(new Color(153,153,153));
    }//GEN-LAST:event_PasswordFieldFocusLost

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        this.requestFocusInWindow();
    }//GEN-LAST:event_formWindowGainedFocus

    private void PasswordFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PasswordFieldKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            User user = getUser();
            if (userDao.checkEmail(user) && userDao.checkPassword(user)) {
                // nếu đăng nhập thành công, mở màn hình option
                optionView = new OptionView();
                OptionController optionController = new OptionController(optionView);
                optionController.showOptionView();
                setVisible(false);
            } else if (!userDao.checkEmailIsEmpty(user)) {
                EmailIsEmpty();
            } else if (userDao.checkEmail(user) && !userDao.checkPasswordIsEmpty(user)) {
                PasswordIsEmpty();
            } else if (!userDao.checkEmail(user)) {
                EmailErro();
            } else if (userDao.checkEmail(user) && !userDao.checkPassword(user)) {
                PasswordErro();
            }
        }
    }//GEN-LAST:event_PasswordFieldKeyPressed

    private void TextFieldEmailKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextFieldEmailKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            User user = getUser();
            if (userDao.checkEmail(user) && userDao.checkPassword(user)) {
                // nếu đăng nhập thành công, mở màn hình option
                optionView = new OptionView();
                OptionController optionController = new OptionController(optionView);
                optionController.showOptionView();
                setVisible(false);
            } else if (!userDao.checkEmailIsEmpty(user)) {
                EmailIsEmpty();
            } else if (userDao.checkEmail(user) && !userDao.checkPasswordIsEmpty(user)) {
                PasswordIsEmpty();
            } else if (!userDao.checkEmail(user)) {
                EmailErro();
            } else if (userDao.checkEmail(user) && !userDao.checkPassword(user)) {
                PasswordErro();
            }
            
            PasswordField.requestFocus();
        }
    }//GEN-LAST:event_TextFieldEmailKeyPressed
    
    private void addPlaceholderStype(JTextField textField) {
        textField.setForeground(Color.gray);
    }
    
    private void removePlaceholderStype(JTextField textField) {
        textField.setForeground(Color.black);
    }
    
    public User getUser() {
        return new User(TextFieldEmail.getText(), 
                String.copyValueOf(PasswordField.getPassword()));
    }
    
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
    
    public void addLoginListener(ActionListener listener) {
        ButtonLogin.addActionListener(listener);
    }
    
    public void addEmailKeyPressed(KeyListener listener) {
        TextFieldEmail.addKeyListener(listener);
    }
    
    public void addPasswordkeyPressed(KeyListener listener) {
        PasswordField.addKeyListener(listener);
    }
    
    public void EmailErro() {
        TextFieldEmail.setForeground(Color.red);
        TextFieldEmail.setText("Email không tồn tại");
        SeparatorEmail.setForeground(new Color(255,0,0));
        
        addPlaceholderStype(PasswordField);
        PasswordField.setText("Nhập mật khẩu");
        PasswordField.setEchoChar('\u0000');
        SeparatorPassword.setForeground(new Color(153,153,153));
    }
    
    public void PasswordErro() {
        PasswordField.setForeground(Color.red);
        PasswordField.setText("Mật khẩu không đúng");
        PasswordField.setEchoChar('\u0000');
        SeparatorPassword.setForeground(new Color(255,0,0));
    }
    
    public void EmailIsEmpty() {
        TextFieldEmail.setForeground(Color.red);
        TextFieldEmail.setText("Chưa nhập email");
        SeparatorEmail.setForeground(new Color(255,0,0));
        
        addPlaceholderStype(PasswordField);
        PasswordField.setText("Nhập mật khẩu");
        PasswordField.setEchoChar('\u0000');
        SeparatorPassword.setForeground(new Color(153,153,153));
        
    }
    
    public void PasswordIsEmpty() {
        PasswordField.setForeground(Color.red);
        PasswordField.setText("Chưa nhập mật khẩu");
        PasswordField.setEchoChar('\u0000');
        SeparatorPassword.setForeground(new Color(255,0,0));
    }

    public JPasswordField getPasswordField() {
        return PasswordField;
    }

    public JTextField getTextFieldEmail() {
        return TextFieldEmail;
    }

    public MyButton getButtonLogin() {
        return ButtonLogin;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new LoginView().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BackGround;
    private button.MyButton ButtonLogin;
    private javax.swing.JLabel LabelPassword;
    private javax.swing.JLabel LabelUserID;
    private javax.swing.JPasswordField PasswordField;
    private javax.swing.JSeparator SeparatorEmail;
    private javax.swing.JSeparator SeparatorPassword;
    private javax.swing.JTextField TextFieldEmail;
    // End of variables declaration//GEN-END:variables
}
