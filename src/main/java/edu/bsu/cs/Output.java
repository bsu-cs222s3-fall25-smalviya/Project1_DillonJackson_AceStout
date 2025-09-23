package edu.bsu.cs;

import java.util.Scanner;

public class Output {
    private final Scanner scanner = new Scanner(System.in);

    public String userSearchPrompt() {
        System.out.println("Enter your search: ");
        return scanner.nextLine();
    }

    public void printRawJson (String jsonData){
        System.out.println(jsonData);
    }
}
