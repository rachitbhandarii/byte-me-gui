package com.rachitbhandari.bytemegui;

import java.io.Serializable;
import java.util.Objects;
import java.util.Scanner;

public abstract class User implements Person, Serializable {
    protected String name;
    protected String emailID;
    protected String pass;
    protected boolean isLogin;
    protected static final Scanner input = new Scanner(System.in);

    public User(){
        signup();
    }

    public User(String dummy) {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public void login() throws InvalidLoginException {
        System.out.println("Enter Password:");
        String pass = input.nextLine();
        this.isLogin = Objects.equals(this.pass, pass);
        if (isLogin) System.out.println("Login successful.");
        else throw new InvalidLoginException("Login failed.");
    }

    public void login(String pass) throws InvalidLoginException {
        this.isLogin = Objects.equals(this.pass, pass);
        if (!isLogin) throw new InvalidLoginException("Login failed.");
    }

    public void logout(){
        if (this.isLogin){
            this.isLogin = false;
            System.out.println("Logout successful.");
        }
        else System.out.println("You are already logged out.");
    }

    public boolean isLoggedOut(){
        return !isLogin;
    }

    @Override
    public boolean equals(Object that){
        return (this.getClass() == that.getClass() && this.hashCode() == that.hashCode());
    }

    public abstract void displayMenu();
    public abstract void signup();

}