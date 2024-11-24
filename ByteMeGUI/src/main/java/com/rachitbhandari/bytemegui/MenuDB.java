package com.rachitbhandari.bytemegui;

import java.io.Serializable;
import java.util.HashSet;

public class MenuDB implements Serializable {
    private HashSet<Food> menu;

    public MenuDB(){
        setup();
    }

    private void setup(){

        this.menu = new HashSet<>();
        this.menu.add(new Food("Pizza", Category.MAIN_COURSE,500,true));
        this.menu.add(new Food("Fries", Category.SIDES,120,true));
        this.menu.add(new Food("Ketchup", Category.ACCOMPANIMENTS,1.93,true));
        this.menu.add(new Food("Cold Coffee", Category.BEVERAGES,150,false));
    }

    public HashSet<Food> getMenu(){
        return this.menu;
    }
}
