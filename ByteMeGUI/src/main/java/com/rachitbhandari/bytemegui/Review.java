package com.rachitbhandari.bytemegui;

import java.io.Serializable;
import java.util.Scanner;

public class Review implements Serializable {
    int rating;
    String comments;
    private static final Scanner input = new Scanner(System.in);

    public Review(){
        System.out.println("Enter Rating (1-5) out of 5");
        int rating = input.nextInt();
        input.nextLine();
        if (rating < 1 ) {
            System.out.println("Lowest possible rating is 1, setting rating to 1.");
            this.rating = 1;
        }
        else if (rating > 5) {
            System.out.println("Highest possible rating is 5, setting rating to 5.");
            this.rating = 5;
        }
        else {
            System.out.println("Rating set to "+rating);
            this.rating = rating;
        }
        System.out.println("Enter Comments:");
        this.comments = input.nextLine();
        System.out.println("Review added successfully.");
    }

    @Override
    public String toString(){
        return "Rating: "+this.rating+"\n Comments:\n"+this.comments;
    }
}
