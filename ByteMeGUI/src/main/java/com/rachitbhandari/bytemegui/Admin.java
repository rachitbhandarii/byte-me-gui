package com.rachitbhandari.bytemegui;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class Admin extends User implements Serializable {

    private static HashSet<Customer> customers;
    private static HashSet<Food> menu;
    private static Queue<Order> orders;
    private static Queue<Order> VIPOrders;

    static {
        try {
            FileInputStream file = new FileInputStream("customers.ser");
            ObjectInputStream in = new ObjectInputStream(file);
            customers = (HashSet<Customer>) in.readObject();
            in.close();
            file.close();
        } catch(IOException ex) {
            customers = new HashSet<>();
        } catch (Exception e) {
            System.exit(1);
        }

        try {
            FileInputStream file = new FileInputStream("menu.ser");
            ObjectInputStream in = new ObjectInputStream(file);
            menu = (HashSet<Food>) in.readObject();

            in.close();
            file.close();
        } catch(IOException ex) {
            menu = new MenuDB().getMenu();
        } catch (Exception e) {
            System.exit(1);
        }

        try {
            FileInputStream file = new FileInputStream("orders.ser");
            ObjectInputStream in = new ObjectInputStream(file);
            orders = (Queue<Order>) in.readObject();

            in.close();
            file.close();
        } catch(IOException ex) {
            orders = new ArrayDeque<>();
        } catch (Exception e) {
            System.exit(1);
        }

        try {
            FileInputStream file = new FileInputStream("viporders.ser");
            ObjectInputStream in = new ObjectInputStream(file);
            VIPOrders = (Queue<Order>) in.readObject();

            in.close();
            file.close();
        } catch(IOException ex) {
            VIPOrders = new ArrayDeque<>();
        } catch (Exception e) {
            System.exit(1);
        }

    }
    public Admin(){}

    @Override
    public void signup() {
        this.name = "Admin";
        this.emailID = "admin@byte.me.inc";
        this.pass = "emptyStomach";
    }

    @Override
    public void displayMenu(){
        if(isLoggedOut()) return;
        int in = 0;
        boolean t = true;
        while(t) {
            try {
                System.out.println("Please select from the following options:");
                System.out.println("1. Menu Management");
                System.out.println("2. Order Management");
                System.out.println("3. Generate Report");
                System.out.println("4. Logout");
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
                displayMenuManagementMenu();
                break;
            case 2:
                displayOrderManagementMenu();
                break;
            case 3:
                generateReport();
                break;
            case 4:
                logout();
                return;
            default:
                System.out.println("Invalid input received. Kindly try again.");
        }
        displayMenu();
    }

    public static HashSet<Food> getMenu() {
        return menu;
    }

    public static void setMenu(HashSet<Food> menu) {
        Admin.menu = menu;
    }

    public static HashSet<Customer> getCustomers() {
        return customers;
    }

    public static void setCustomers(HashSet<Customer> customers) {
        Admin.customers = customers;
    }

    public static Queue<Order> getOrders() {
        return orders;
    }

    public static void setOrders(Queue<Order> orders) {
        Admin.orders = orders;
    }

    public static Queue<Order> getVIPOrders() {
        return VIPOrders;
    }

    public static void setVIPOrders(Queue<Order> VIPOrders) {
        Admin.VIPOrders = VIPOrders;
    }

    public static User fetchCustomer(String emailID) {
        for (Customer customer: customers) if (Objects.equals(customer.getEmailID(),emailID)) return customer;
        return null;
    }

    public static void addCustomer(Customer customer){
        customers.add(customer);
    }

    public static Order fetchOrder(int orderID){
        for (Order order: orders) if (order.getID() == orderID) return order;
        return null;
    }

    public static void addOrder(Order order, boolean VIP) {
        if (VIP) VIPOrders.add(order);
        else orders.add(order);
    }

    public void displayMenuManagementMenu(){
        int in = 0;
        boolean t = true;
        while(t) {
            try {
                System.out.println("Please select from the following options:");
                System.out.println("1. Add New Items");
                System.out.println("2. Update Existing Items");
                System.out.println("3. Remove Items");
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
                addItemToMenu();
                break;
            case 2:
                updateMenu();
                break;
            case 3:
                removeItemFromMenu();
                break;
            case 4:
                displayMenu();
                return;
            default:
                System.out.println("Invalid input received. Kindly try again.");
        }
        displayMenuManagementMenu();
    }

    public void addItemToMenu(){
        menu.add(new Food());
    }

    public void removeItemFromMenu(){
        System.out.println("Enter the name of the dish:");
        String dishName = input.nextLine();
        for (Food food:menu) {
            if (Objects.equals(food.getName(),dishName)) {
                menu.remove(food);
                System.out.println("Item removed successfully.");
                for (Order order: orders){
                    if (order.getCart().containsKey(food)) order.setStatus(Status.CANCELLED);
                }
                return;
            }
        }
        System.out.println("No such item in menu.");
    }

    public void updateMenu(){
        boolean t = true;
        while (t) {
            try {
                System.out.println("Enter the name of the dish:");
                String dishName = input.nextLine();
                Food dish = null;
                for (Food food:menu) {
                    if (Objects.equals(food.getName(),dishName)) {
                        dish = food;
                        break;
                    }
                }
                if (dish == null){
                    System.out.println("No such item in menu.");
                    return;
                }
                int in;
                System.out.println("1. Change Dish Name");
                System.out.println("2. Change Category");
                System.out.println("3. Change Price");
                System.out.println("4. Change Availability");
                int choice = input.nextInt();
                input.nextLine();
                switch(choice){
                    case 1:
                        System.out.println("Enter new dish name:");
                        dish.setName(input.nextLine());
                        System.out.println("Dish name updated successfully");
                        break;
                    case 2:
                        System.out.println("Choose the category you want to put the dish into:");
                        System.out.println("1. ByteMeGUI Course");
                        System.out.println("2. Sides");
                        System.out.println("3. Sweets");
                        System.out.println("4. Beverages");
                        System.out.println("5. Breads");
                        System.out.println("6. Accompaniments");
                        in = input.nextInt();
                        input.nextLine();
                        dish.setCategory(Category.values()[in-1]);
                        System.out.println("Category updated successfully.");
                        break;
                    case 3:
                        System.out.println("Enter the price:");
                        double price = input.nextDouble();
                        input.nextLine();
                        dish.setPrice(price);
                        System.out.println("Price updated successfully.");
                        break;
                    case 4:
                        System.out.println("Is the item available for sale:");
                        System.out.println("1. Yes");
                        System.out.println("2. No");
                        in = input.nextInt();
                        input.nextLine();
                        if (in == 1) dish.setAvailable(true);
                        else if (in == 2) dish.setAvailable(false);
                        break;
                    default:
                        System.out.println("Wrong option selected.");
                }
                t = false;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input received, kindly try again.");
                input.nextLine();
            }
        }
    }

    public void displayOrderManagementMenu(){
        int in = 0;
        boolean t = true;
        while(t) {
            try {
                System.out.println("Please select from the following options:");
                System.out.println("1. View Pending Orders");
                System.out.println("2. Update Order Status");
                System.out.println("3. Process Refund");
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
                viewPendingOrders();
                break;
            case 2:
                updateOrderStatus();
                break;
            case 3:
                processRefund();
                break;
            case 4:
                displayMenu();
                return;
            default:
                System.out.println("Invalid input received. Kindly try again.");
        }
        displayOrderManagementMenu();
    }

    public void viewPendingOrders(){
        for (Order order:VIPOrders){
            if (order.getStatus() != Status.DELIVERED) System.out.println(order);
        }
        for (Order order:orders){
            if (order.getStatus() != Status.DELIVERED) System.out.println(order);
        }
    }

    public void updateOrderStatus(){
        int in = 0;
        boolean t = true;
        while(t) {
            try {
                System.out.println("Enter Order ID:");
                in = User.input.nextInt();
                User.input.nextLine();
                t = false;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input received, kindly try again.");
                input.nextLine();
            }
        }
        Order order = null;
        for (Order o:VIPOrders){
            if (o.getID() == in) order = o;
        }
        for (Order o:orders){
            if (o.getID() == in) order = o;
        }
        if (order == null){
            System.out.println("No such order found.");
            return;
        }
        t = true;
        while(t) {
            try {
                System.out.println("Change the order status to:");
                System.out.println("1. Received");
                System.out.println("2. Cooking");
                System.out.println("3. Out For Delivery");
                System.out.println("4. Delivered");
                in = User.input.nextInt();
                User.input.nextLine();
                order.setStatus(Status.values()[in-1]);
                if (order.getStatus() == Status.DELIVERED) orders.remove(order);
                System.out.println("Status Updated Successfully.");
                t = false;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input received, kindly try again.");
                input.nextLine();
            }
        }

    }

    public void processRefund(){
        for (Customer customer: customers){
            for(Order order:customer.getOrders()){
                if (order.getStatus() == Status.CANCELLED) customer.addMoney(order.getOrderTotal());
            }
        }
        System.out.println("All necessary refunds processed successfully.");
    }

    public void generateReport(){
        LocalDateTime time = LocalDateTime.now();
        int noOfSales = 0;
        double revenue = 0;
        for (Order order:VIPOrders){
            if (order.getTime().getDayOfYear() == time.getDayOfYear() && order.getStatus()!=Status.CANCELLED){
                noOfSales++;
                revenue+=order.getOrderTotal();
            }
        }
        System.out.println("Total Sales: "+noOfSales+"\nTotal Revenue: "+revenue);

    }

    public static void displayFoodMenu(){
        for (Food food:menu) System.out.println(food);
    }

    public static void displayFoodMenuItem(){
        System.out.println("Enter the name of the item:");
        String dishName = input.nextLine();
        for (Food food:menu) {
            if (Objects.equals(food.getName(), dishName)){
                System.out.println(food);
                return;
            }
        }
        System.out.println("No such item found.");
    }

    public static void displayFoodMenuByCategory(){
        System.out.println("Choose the category you want to search for:");
        System.out.println("1. ByteMeGUI Course");
        System.out.println("2. Sides");
        System.out.println("3. Sweets");
        System.out.println("4. Beverages");
        System.out.println("5. Breads");
        System.out.println("6. Accompaniments");
        int in = input.nextInt();
        input.nextLine();
        for (Food food:menu){
            if (food.getCategory() == Category.values()[in-1]) System.out.println(food);
        }
    }

    public static void displayFoodMenuByPrice(){
        HashMap<Double,Food> sortedMenu = new HashMap<>();
        for (Food food:menu){
           sortedMenu.put(food.getPrice(),food);
        }
        sortedMenu.forEach((key, value) -> System.out.println(value));
    }

    public static Food getFood(String dishName){
        for (Food food:menu){
            if (Objects.equals(food.getName(),dishName)) return food;
        }
        return null;
    }
}
