package com.fedynets.entity;

import com.fedynets.constants.UserRole;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Simple User entity with getters and setters.
 * There is also a overrided method toString
 * @autor Yurii Fedynets
 */
public class User implements Serializable {
    private int userId;
    private String login;
    private String password;
    private String name;
    private String surname;
    private String email;
    private UserRole userRole;
    private boolean isActive;

    public User() {
    }

    public User(int userId, String login, String password, String name, String surname, String email,
                UserRole userRole, boolean isActive) {
        this.userId = userId;
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.userRole = userRole;
        this.isActive = isActive;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public static boolean checkPassword(String password){
        String regex ="^[A-Za-z\\p{InCyrillic}0-9_-]{5,15}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public static boolean checkLogin(String login){
        String regex ="^([A-Za-z_]+[A-Za-z0-9_\\.]*){3,15}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(login);
        return matcher.matches();
    }

    public static boolean checkNameAndSurname(String s){
        String regex ="^[A-Za-z\\p{InCyrillic}]{1,15}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s);
        return matcher.matches();
    }

    public static boolean checkEmail(String email) {
        String regex ="^[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*@"
                + "[A-Za-z0-9]+(\\.[A-Za-z0-9]+){0,1}(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


    @Override
    public String toString() {
        return "User[" +
                "userId=" + userId +
                ", login=" + login +
                ", password=" + password +
                ", name=" + name +
                ", surname=" + surname +
                ", email=" + email +
                ", userRole=" + userRole +
                ", isActive=" + isActive +
                "]";
    }
    public static class Builder{
        private User user;

        public Builder(){
            user = new User();
        }

        public Builder bSetUserId(int userId){
            user.userId = userId;
            return this;
        }

        public Builder bSetLogin(String login){
            user.login = login;
            return this;
        }

        public Builder bSetName(String name){
            user.name = name;
            return this;
        }

        public Builder bSetSurname(String surname){
            user.surname = surname;
            return this;
        }

        public Builder bSetEmail(String email){
            user.email = email;
            return this;
        }

        public Builder bSetPassword(String password){
            user.password = password;
            return this;
        }

        public Builder bSetUserRole(UserRole userRole){
            user.userRole = userRole;
            return this;
        }

        public Builder bSetActive(boolean active){
            user.isActive = active;
            return this;
        }

        public User getResult(){
            return user;
        }
    }
}

