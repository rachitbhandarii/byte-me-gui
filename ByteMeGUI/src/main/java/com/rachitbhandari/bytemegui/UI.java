package com.rachitbhandari.bytemegui;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UI implements Serializable {
    private User user;
    private final Scanner input = new Scanner(System.in);

    public void displayScreen(){
        int choice = 0;
        boolean t = true;
        while(t) {
            try {
                System.out.println("Welcome");
                System.out.println("Enter as:");
                System.out.println("1. Administrator");
                System.out.println("2. Customer");
                System.out.println("3. Exit Session");
                choice = input.nextInt();
                input.nextLine();
                t = false;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input received, kindly try again.");
                input.nextLine();
            }
        }
        switch(choice) {
            case 1:
                displayAdminScreen();
                break;
            case 2:
                displayCustomerScreen();
                break;
            case 3:
                try {
                    FileOutputStream file = new FileOutputStream("customers.ser");
                    ObjectOutputStream out = new ObjectOutputStream(file);
                    out.writeObject(Admin.getCustomers());
                    out.close();
                    file.close();

                } catch(IOException ex) {
                    System.out.println("Error storing data.");
                    System.exit(1);
                }

                try {
                    FileOutputStream file = new FileOutputStream("menu.ser");
                    ObjectOutputStream out = new ObjectOutputStream(file);
                    out.writeObject(Admin.getMenu());
                    out.close();
                    file.close();

                } catch(IOException ex) {
                    System.out.println("Error storing data.");
                    System.exit(1);
                }

                try {
                    FileOutputStream file = new FileOutputStream("orders.ser");
                    ObjectOutputStream out = new ObjectOutputStream(file);
                    out.writeObject(Admin.getOrders());
                    out.close();
                    file.close();

                } catch(IOException ex) {
                    System.out.println("Error storing data.");
                    System.exit(1);
                }

                try {
                    FileOutputStream file = new FileOutputStream("viporders.ser");
                    ObjectOutputStream out = new ObjectOutputStream(file);
                    out.writeObject(Admin.getVIPOrders());
                    out.close();
                    file.close();

                } catch(IOException ex) {
                    System.out.println("Error storing data.");
                    System.exit(1);
                }

                try {
                    FileOutputStream file = new FileOutputStream("orderID.ser");
                    ObjectOutputStream out = new ObjectOutputStream(file);
                    out.writeInt(Order.getNo());
                    out.close();
                    file.close();

                } catch(IOException ex) {
                    System.out.println("Error storing data.");
                    System.exit(1);
                }

                System.out.println("Thank you for using the Software.");
                System.out.println("Copyrights: Rachit Bhandari");
                System.exit(0);
                return;
            default:
                System.out.println("Wrong option selected.");
        }
        displayScreen();
    }

    private void displayAdminScreen(){
        int choice = 0;
        boolean t = true;
        while(t) {
            try {
                System.out.println("Choose to enter as an administrator: ");
                System.out.println("1. Login");
                System.out.println("2. Go Back");
                choice = input.nextInt();
                input.nextLine();
                t = false;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input received, kindly try again.");
                input.nextLine();
            }
        }
        switch(choice){
            case 1:
                user = new Admin();
                try {
                    user.login();
                } catch (InvalidLoginException exception) {
                    System.out.println(exception.getMessage());
                }
                if (user.isLoggedOut()) break;
                user.displayMenu();
                break;
            case 2:
                displayScreen();
                return;
            default:
                System.out.println("Wrong option selected.");
        }
        displayAdminScreen();
    }

    private void displayCustomerScreen(){
        int choice = 0;
        boolean t = true;
        while(t) {
            try {
                System.out.println("Choose to enter as a customer: ");
                System.out.println("1. Signup");
                System.out.println("2. Login");
                System.out.println("3. Go Back");
                choice = input.nextInt();
                input.nextLine();
                t = false;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input received, kindly try again.");
                input.nextLine();
            }
        }
        switch(choice){
            case 1:
                user = new Customer();
                break;
            case 2:
                System.out.println("Enter Email ID:");
                user = Admin.fetchCustomer(input.nextLine());
                if (user!=null){
                    try {
                        user.login();
                    } catch (InvalidLoginException exception) {
                        System.out.println(exception.getMessage());
                    }
                    if (user.isLoggedOut()) break;
                    user.displayMenu();
                }
                else System.out.println("No such customer found.");
                break;
            case 3:
                displayScreen();
                return;
            default:
                System.out.println("Wrong option selected.");
        }
        displayCustomerScreen();
    }
}
