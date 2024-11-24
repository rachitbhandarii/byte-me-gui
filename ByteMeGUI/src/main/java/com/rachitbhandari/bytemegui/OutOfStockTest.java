package com.rachitbhandari.bytemegui;

import org.junit.Test;

import static org.junit.Assert.fail;

public class OutOfStockTest {

    @Test
    public void test1() {
        Admin.getMenu().add(new Food("Test1",Category.MAIN_COURSE,100,true));
        Food food = Admin.getFood("Test1");
        if (food == null){
            fail();
        }
        else if (!food.isAvailable()) {
            fail();
        }
    }

    @Test
    public void test2() {
        Admin.getMenu().add(new Food("Test2",Category.MAIN_COURSE,100,false));
        Food food = Admin.getFood("Test2");
        if (food == null){
            fail();
        }
        else if (food.isAvailable()) {
            fail();
        }
    }

    @Test
    public void test3() {
        Food food = Admin.getFood("");
        if (food != null) fail();
    }

}
