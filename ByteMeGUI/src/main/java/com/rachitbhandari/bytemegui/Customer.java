package com.rachitbhandari.bytemegui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Objects;

public class Customer extends User implements Serializable {

    private boolean VIP;
    private Order currOrder;
    private ArrayList<Order> orders;
    private double wallet;

    public Customer(){}

    public Customer(String name, String emailID, String pass){
        super(name);
        this.name = name;
        this.emailID = emailID;
        this.pass = pass;
        VIP = false;
        this.orders = new ArrayList<>();
        currOrder = new Order();
        this.wallet = 0;
        Admin.addCustomer(this);
    }

    @Override
    public void signup() {
        System.out.println("Enter Name:");
        this.name = input.nextLine();
        System.out.println("Enter Email ID:");
        this.emailID = input.nextLine();
        System.out.println("Set Password:");
        this.pass = input.nextLine();
        System.out.println("If you are a VIP Customer enter VIP Code:");
        String code = input.nextLine();
        VIP = Objects.equals(code.toLowerCase(), "bytevip");
        if (VIP) System.out.println("Congrats on becoming the VIP Customer.");
        else System.out.println("Sorry incorrect code, you lost your chance to become a VIP Customer.");
        this.orders = new ArrayList<>();
        currOrder = new Order();
        this.wallet = 0;
        Admin.addCustomer(this);
    }

    @Override
    public void displayMenu(){
        if(isLoggedOut()) return;
        int in = 0;
        boolean t = true;
        while(t) {
            try {
                System.out.println("Wallet Amount: "+this.wallet);
                System.out.println("Please select from the following options:");
                System.out.println("1. Browse Menu");
                System.out.println("2. Cart Operations");
                System.out.println("3. Track Order");
                System.out.println("4. Reviews");
                System.out.println("5. Logout");
                in = User.input.nextInt();
                User.input.nextLine();
                t = false;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input received, kindly try again.");
                input.nextLine();
            }
        }
        switch (in) {
            case 1:
                displayBrowsingMenu();
                break;
            case 2:
                displayCartMenu();
                break;
            case 3:
                displayOrderTrackingMenu();
                break;
            case 4:
                displayReviewMenu();
                break;
            case 5:
                logout();
                return;
            default:
                System.out.println("Invalid input received. Kindly try again.");
        }
        displayMenu();
    }

    public void displayBrowsingMenu(){
        int in = 0;
        boolean t = true;
        while(t) {
            try {
                System.out.println("Please select from the following options:");
                System.out.println("1. View All Items");
                System.out.println("2. Search Items");
                System.out.println("3. Filter By Category");
                System.out.println("4. Sort By Price");
                System.out.println("5. Go Back");
                in = User.input.nextInt();
                User.input.nextLine();
                t = false;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input received, kindly try again.");
                input.nextLine();
            }
        }
        switch (in) {
            case 1:
                Admin.displayFoodMenu();
                break;
            case 2:
                Admin.displayFoodMenuItem();
                break;
            case 3:
                Admin.displayFoodMenuByCategory();
                break;
            case 4:
                Admin.displayFoodMenuByPrice();
                break;
            case 5:
                displayMenu();
                return;
            default:
                System.out.println("Invalid input received. Kindly try again.");
        }
        displayBrowsingMenu();
    }

    public void displayCartMenu(){
        int in = 0;
        boolean t = true;
        while(t) {
            try {
                System.out.println("Please select from the following options:");
                System.out.println("1. Add Items");
                System.out.println("2. Modify Quantities");
                System.out.println("3. Remove Items");
                System.out.println("4. View Total");
                System.out.println("5. Checkout");
                System.out.println("6. Go Back");
                in = User.input.nextInt();
                User.input.nextLine();
                t = false;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input received, kindly try again.");
                input.nextLine();
            }
        }
        switch (in) {
            case 1:
                addItem();
                break;
            case 2:
                modifyItem();
                break;
            case 3:
                removeItem();
                break;
            case 4:
                System.out.println(currOrder.getOrderTotal());
                break;
            case 5:
                System.out.println("Do you want to add special request?");
                System.out.println("1. Yes");
                System.out.println("2. No");
                int inpt = input.nextInt();
                input.nextLine();
                if (inpt == 1) {
                    currOrder.setSpecialRequest(input.nextLine());
                    System.out.println("Request added successfully.");
                }
                orders.add(currOrder);
                currOrder.checkout(VIP);
                currOrder = new Order();
                break;
            case 6:
                displayMenu();
                return;
            default:
                System.out.println("Invalid input received. Kindly try again.");
        }
        displayCartMenu();
    }

    public void addItem(){
        System.out.println("Enter the name of the Item to add:");
        Food food = Admin.getFood(input.nextLine());
        if (food == null){
            System.out.println("Incorrect item name provided.");
            return;
        }
        else if (!food.isAvailable()) {
            System.out.println("The item is not available for sale.");
            return;
        }
        System.out.println("Enter the quantity of the Item to add:");
        int qty = input.nextInt();
        input.nextLine();
        currOrder.addItemToCart(food,qty);
    }

    public void modifyItem(){
        System.out.println("Enter the name of the Item to modify:");
        Food food = Admin.getFood(input.nextLine());
        if (food == null){
            System.out.println("Incorrect item name provided.");
            return;
        }
        else if (!currOrder.getCart().containsKey(food)){
            System.out.println("No such item in the cart.");
            return;
        }
        System.out.println("Enter the new quantity of the Item:");
        int qty = input.nextInt();
        input.nextLine();
        currOrder.getCart().remove(food);
        currOrder.getCart().put(food,qty);
    }

    public void removeItem(){
        System.out.println("Enter the name of the Item to remove:");
        Food food = Admin.getFood(input.nextLine());
        if (food == null){
            System.out.println("Incorrect item name provided.");
            return;
        }
        else if (!currOrder.getCart().containsKey(food)){
            System.out.println("No such item in the cart.");
            return;
        }
        currOrder.getCart().remove(food);
        System.out.println("Item removed successfully.");
    }

    public void displayOrderTrackingMenu(){
        int in = 0;
        boolean t = true;
        while(t) {
            try {
                System.out.println("Please select from the following options:");
                System.out.println("1. View Order Status");
                System.out.println("2. Cancel Order");
                System.out.println("3. Order History");
                System.out.println("4. Go Back");
                in = User.input.nextInt();
                User.input.nextLine();
                t = false;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input received, kindly try again.");
                input.nextLine();
            }
        }
        switch (in) {
            case 1:
                viewOrderStatus();
                break;
            case 2:
                cancelOrder();
                break;
            case 3:
                viewOrderHistory();
                break;
            case 4:
                displayMenu();
                return;
            default:
                System.out.println("Invalid input received. Kindly try again.");
        }
        displayOrderTrackingMenu();
    }

    public void viewOrderStatus(){
        System.out.println("Enter the order ID:");
        int ID = input.nextInt();
        input.nextLine();
        for (Order order: orders){
            if (order.getID() == ID){
                System.out.println(order);
                return;
            }
        }
        System.out.println("No such order present.");
    }

    public void cancelOrder(){
        System.out.println("Enter the order ID:");
        int ID = input.nextInt();
        input.nextLine();
        for (Order order: orders){
            if (order.getID() == ID) {
                if (order.getStatus() == Status.RECEIVED || order.getStatus() == Status.COOKING) {
                    order.setStatus(Status.CANCELLED);
                    System.out.println("Order cancelled successfully, refund would be processed shortly");
                }
                else System.out.println("Order can't be cancelled at this point.");
                return;
            }
        }
        System.out.println("No such order present.");
    }

    public void viewOrderHistory() {
        for (Order order: orders) System.out.println(order);
    }

    public void displayReviewMenu(){
        int in = 0;
        boolean t = true;
        while(t) {
            try {
                System.out.println("Please select from the following options:");
                System.out.println("1. Provide Review");
                System.out.println("2. View Reviews");
                System.out.println("3. Go Back");
                in = User.input.nextInt();
                User.input.nextLine();
                t = false;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input received, kindly try again.");
                input.nextLine();
            }
        }
        switch (in) {
            case 1:
                provideReview();
                break;
            case 2:
                viewReviews();
                break;
            case 3:
                displayMenu();
                return;
            default:
                System.out.println("Invalid input received. Kindly try again.");
        }
        displayReviewMenu();
    }

    public void provideReview(){
        System.out.println("Enter the name of the Item:");
        Food food = Admin.getFood(input.nextLine());
        if (food == null){
            System.out.println("Incorrect item name provided.");
            return;
        }
        food.addReview();
    }

    public void viewReviews(){
        System.out.println("Enter the name of the Item:");
        Food food = Admin.getFood(input.nextLine());
        if (food == null){
            System.out.println("Incorrect item name provided.");
            return;
        }
        food.viewReviews();
    }

    public void addMoney(double money){
        this.wallet += money;
    }

    public ArrayList<Order> getOrders(){
        return this.orders;
    }
}
