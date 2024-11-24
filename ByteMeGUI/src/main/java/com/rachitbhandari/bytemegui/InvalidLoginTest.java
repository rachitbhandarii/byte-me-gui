package com.rachitbhandari.bytemegui;

import org.junit.Test;

import static org.junit.Assert.*;

public class InvalidLoginTest {

    @Test
    public void test1() {
        new Customer("test","test","test");
        User user = Admin.fetchCustomer("test");
        if (user!=null){
            try {
                user.login("test");
            } catch (InvalidLoginException exception) {
                fail();
            }
            if (user.isLoggedOut()) fail();
        }
        else fail();
    }

    @Test(expected = InvalidLoginException.class)
    public void test2() throws InvalidLoginException {
        new Customer("test","test","test");
        User user = Admin.fetchCustomer("test");
        if (user!=null) user.login("t");
        else fail();
        if (!user.isLoggedOut()) fail();
    }

    @Test
    public void test3() {
        User user = Admin.fetchCustomer("");
        if (user!=null) fail();
    }
}
