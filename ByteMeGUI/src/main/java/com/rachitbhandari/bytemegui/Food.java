package com.rachitbhandari.bytemegui;

import java.io.Serializable;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Food implements Serializable {

    private String name;
    private Category category;
    private double price;
    private boolean available;
    private static final Scanner input = new Scanner(System.in);
    private HashSet<Review> reviews;

    public Food() {
        boolean t = true;
        while (t) {
            try {
                System.out.println("Enter the name of the dish:");
                this.name = input.nextLine();
                System.out.println("Choose the category you want to put the dish into:");
                System.out.println("1. ByteMeGUI Course");
                System.out.println("2. Sides");
                System.out.println("3. Sweets");
                System.out.println("4. Beverages");
                System.out.println("5. Breads");
                System.out.println("6. Accompaniments");
                int in = input.nextInt();
                input.nextLine();
                this.category = Category.values()[in-1];
                System.out.println("Enter the price:");
                this.price = input.nextDouble();
                input.nextLine();
                System.out.println("Is the item available for sale:");
                System.out.println("1. Yes");
                System.out.println("2. No");
                int choice = input.nextInt();
                input.nextLine();
                if (choice == 1) this.available = true;
                else if (choice == 2) this.available = false;
                reviews = new HashSet<>();
                t = false;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input received, kindly try again.");
                input.nextLine();
            }
        }
    }

    public Food(String name, Category category, double price, boolean available){
        this.name = name;
        this.category = category;
        this.price = price;
        this.available = available;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void addReview(){
        this.reviews.add(new Review());
    }

    public void viewReviews(){
        for (Review review: reviews) System.out.println(review);
    }

    @Override
    public String toString(){
        return "Dish Name: "+this.name+" | Price: "+this.price+"\nCategory: "+this.category+(available ? "\nAvailable":"\nNot Available");
    }

}
