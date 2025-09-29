package edu.bsu.cs;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose which way to run the program");
        System.out.println("     1.)In the Console");
        System.out.println("     2.)In the GUI");
        System.out.println("Enter Optins 1 or 2: ");

        String choice = scanner.nextLine();

        if (choice == "2") {
            JavaFX.main(args);
        } else {
            new ConsoleFunctions().run();
        }
    }
}