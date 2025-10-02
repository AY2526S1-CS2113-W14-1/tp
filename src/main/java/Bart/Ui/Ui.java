
/**
 * Handles user interaction for the chatbot, including input and output.
 * Provides methods for displaying messages and reading user commands.
 */
package Bart.Ui;

import java.util.Scanner;

public class Ui {

    private final Scanner in;


    /**
     * Constructs a Ui object for handling user input and output.
     */
    public Ui () {
        in = new Scanner(System.in);
    }


    /**
     * Prints a message to the user with indentation.
     * @param msg The message to print.
     */
    public void print(String msg) {
        System.out.println("    " + msg);
    }


    /**
     * Prints a divider line for separating output sections.
     */
    public void divider() {
        System.out.println("    ____________________________________________________________");
    }


    /**
     * Prints a message surrounded by divider lines.
     * @param text The message to print.
     */
    public void printWithDivider(String text) {
        divider();
        print(text);
        divider();
    }


    /**
     * Prints the ASCII art logo of the chatbot.
     */
    public void printASCIIName() {
        String[] logo = {
                " ____             _   _           _                               ",
                "| __ )  __ _ _ __| |_| |__   ___ | | ___  _ __ ___   _____      __",
                "|  _ \\ / _` | '__| __| '_ \\ / _ \\| |/ _ \\| '_ ` _ \\ / _ \\ \\ /\\ / /",
                "| |_) | (_| | |  | |_| | | | (_) | | (_) | | | | | |  __/\\ V  V / ",
                "|____/ \\__,_|_|   \\__|_| |_|\\___/|_|\\___/|_| |_| |_|\\___| \\_/\\_/  "};
        for (int i = 0; i < logo.length; i++) {
            System.out.println(logo[i]);
        }
    }


    /**
     * Displays the welcome message and logo to the user.
     */
    public void showWelcome() {
        print("Hello from");
        printASCIIName();
        printWithDivider("Hello! I'm Bartholomew, but you can call me Bart.Bart" + System.lineSeparator() + "      What can I do for you?");
    }


    /**
     * Reads the next command entered by the user.
     * @return The user's input as a string, or null if no input is available.
     */
    public String readCommand() {
        if (in.hasNextLine()) {
            return in.nextLine();
        } else {
            return null;
        }
    }

}
