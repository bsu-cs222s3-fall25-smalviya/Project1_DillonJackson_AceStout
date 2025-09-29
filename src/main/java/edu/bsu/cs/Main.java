package edu.bsu.cs;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose which way to run the program");
        System.out.println("     1.)In the Console");
        System.out.println("     2.)In the GUI");
        System.out.println("Enter Options 1 or 2: ");

        String choice = scanner.nextLine();

        if ("2".equals(choice)) {
            GUI.main(args); // add this method later
        } else {
            new ConsoleFunctions().run();
        }
    }
}