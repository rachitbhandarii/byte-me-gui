package com.rachitbhandari.bytemegui;

import java.io.Serializable;

public interface Person extends Serializable {
    void login() throws InvalidLoginException;
    void logout();
    void signup();
    void displayMenu();
    boolean isLoggedOut();
}
