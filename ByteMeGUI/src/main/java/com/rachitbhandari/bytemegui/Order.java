package com.rachitbhandari.bytemegui;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Order implements Serializable {
    private static int no;
    private HashMap<Food,Integer> cart;
    private int ID;
    private Status status;
    private LocalDateTime time;
    private String specialRequest;

    static{
        try {
            FileInputStream file = new FileInputStream("orderID.ser");
            ObjectInputStream in = new ObjectInputStream(file);
            no = in.readInt();
            in.close();
            file.close();
        } catch(IOException ex) {
            no = 3000;
        } catch (Exception e) {
            System.exit(1);
        }
    }
    public Order(){
        cart = new HashMap<>();
    }

    public static int getNo(){return no;}

    public void addItemToCart(Food food, int qty){
        cart.put(food,qty);
        System.out.println("Item added Successfully");
    }

    public boolean changeQty(String dishName, int qty){
        for (Food food: cart.keySet()){
            if (Objects.equals(food.getName(),dishName)){
                cart.replace(food,qty);
                System.out.println("Quantity changed successfully.");
                return true;
            }
        }
        System.out.println("No such item in your cart.");
        return false;
    }

    public void checkout(boolean VIP){
        this.ID = no++;
        time = LocalDateTime.now();
        status = Status.RECEIVED;
        Admin.addOrder(this,VIP);
        System.out.println(this);
    }

    public double getOrderTotal(){
        double total = 0;
        for (Map.Entry<Food,Integer> item : cart.entrySet()){
            total += (item.getKey().getPrice() * item.getValue());
        }
        return total;
    }

    public HashMap<Food,Integer> getCart(){
        return this.cart;
    }

    public void setCart(HashMap<Food,Integer> cart){
        this.cart = cart;
    }

    public int getID(){
        return this.ID;
    }

    public void setID(int ID){
        this.ID = ID;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setSpecialRequest(String specialRequest){this.specialRequest = specialRequest;}

    public String getSpecialRequest(){return this.specialRequest;}

    @Override
    public String toString(){
        return "Order ID: "+this.ID+" Status: "+this.status+" Time: "+this.time+"\n"+this.cart;
    }

}
