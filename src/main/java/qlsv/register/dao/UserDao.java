package qlsv.register.dao;

import qlsv.register.entity.User;

public class UserDao {
    public boolean checkEmailIsEmpty(User user) {
        return !user.getEmail().equals("Nhập email") && !user.getEmail().equals("Chưa nhập email") && !user.getEmail().equals("");
    }
    
    public boolean checkPasswordIsEmpty(User user) {
        return !user.getPassword().equals("Nhập mật khẩu") && !user.getPassword().equals("");
    }
    
    public boolean checkEmail(User user) {
        return "hvan@edu.vn".equals(user.getEmail());
    }
    
    public boolean checkPassword(User user) {
        return "matkhau".equals(user.getPassword());
    }
}
