package seedu.fitnessone.ui;

import java.util.Scanner;

public class Ui {

    private final Scanner in;


    /**
     * Constructs a Ui object for handling user input and output.
     */
    public Ui() {
        in = new Scanner(System.in);
    }


    /**
     * Prints a message to the user with indentation.
     *
     * @param msg The message to print.
     */
    public void println(String msg) {
        System.out.println("    " + msg);
    }

    public void print(String msg) {
        System.out.print("    " + msg);
    }


    /**
     * Prints a divider line for separating output sections.
     */
    public void divider() {
        System.out.println("    ____________________________________________________________");
    }


    /**
     * Prints a message surrounded by divider lines.
     *
     * @param text The message to print.
     */
    public void printWithDivider(String text) {
        divider();
        println(text);
        divider();
    }


    /**
     * Prints the ASCII art logo of the chatbot.
     */
    public void printASCIIName() {
        String[] logo = {
                "________  __    __                                              ______   __    __  ________ ",
                "/        |/  |  /  |                                            /      \\ /  \\  /  |/        |",
                "$$$$$$$$/ $$/  _$$ |_    _______    ______    _______  _______ /$$$$$$  |$$  \\ $$ |$$$$$$$$/ ",
                "$$ |__    /  |/ $$   |  /       \\  /      \\  /       |/       |$$ |  $$ |$$$  \\$$ |$$ |__    ",
                "$$    |   $$ |$$$$$$/   $$$$$$$  |/$$$$$$  |/$$$$$$$//$$$$$$$/ $$ |  $$ |$$$$  $$ |$$    |   ",
                "$$$$$/    $$ |  $$ | __ $$ |  $$ |$$    $$ |$$      \\$$      \\ $$ |  $$ |$$ $$ $$ |$$$$$/    ",
                "$$ |      $$ |  $$ |/  |$$ |  $$ |$$$$$$$$/  $$$$$$  |$$$$$$  |$$ \\__$$ |$$ |$$$$ |$$ |_____ ",
                "$$ |      $$ |  $$  $$/ $$ |  $$ |$$       |/     $$//     $$/ $$    $$/ $$ | $$$ |$$       |",
                "$$/       $$/    $$$$/  $$/   $$/  $$$$$$$/ $$$$$$$/ $$$$$$$/   $$$$$$/  $$/   $$/ $$$$$$$$/ "
        };
        for (String line : logo) {
            System.out.println(line);
        }
    }


    /**
     * Displays the welcome message and logo to the user.
     */
    public void showWelcome() {
        println("Hello from");
        printASCIIName();
        printWithDivider("Hey, welcome to FitnessONE!" + System.lineSeparator() + "      How may we help today?");
    }


    /**
     * Reads the next command entered by the user.
     *
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
