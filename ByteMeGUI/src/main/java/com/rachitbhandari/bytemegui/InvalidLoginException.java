package com.rachitbhandari.bytemegui;

import java.io.Serializable;

public class InvalidLoginException extends Exception implements Serializable {
    public InvalidLoginException(String message) {
        super(message);
    }
}
