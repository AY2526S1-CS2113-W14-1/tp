package seedu.fitnessone.UI;

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
//        // split up to prevent line length > 120 checkstyle errro
//        String[] logo = {
//            "▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄        ▄  ▄▄▄▄▄▄▄▄▄▄▄  "
//                    + "▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄",
//            "▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░▌      ▐░▌▐░░░░░░░░░░░▌▐░░░░░░░░"
//                    + "░░░▌▐░░░░░░░░░░░▌▐░░▌      ▐░▌▐░░░░░░░░░░░▌",
//            "▐░█▀▀▀▀▀▀▀▀▀  ▀▀▀▀█░█▀▀▀▀  ▀▀▀▀█░█▀▀▀▀ ▐░▌░▌     ▐░▌▐░█▀▀▀▀▀▀▀▀▀ "
//                    + "▐░█▀▀▀▀▀▀▀▀▀ ▐░█▀▀▀▀▀▀▀█░▌▐░▌░▌     ▐░▌",
//            "▐░▌               ▐░▌          ▐░▌     ▐░▌▐░▌    ▐░▌▐░▌          "
//                    + "▐░▌          ▐░▌       ▐░▌▐░▌▐░▌    ▐░▌",
//            "▐░█▄▄▄▄▄▄▄▄▄      ▐░▌          ▐░▌     ▐░▌ ▐░▌   ▐░▌▐░█▄▄▄▄▄▄▄▄▄ "
//                    + "▐░█▄▄▄▄▄▄▄▄▄ ▐░▌       ▐░▌",
//            "▐░░░░░░░░░░░▌     ▐░▌          ▐░▌     ▐░▌  ▐░▌  ▐░▌▐░░░░░░░░░░░▌"
//                    + "▐░░░░░░░░░░░▌▐░▌       ▐░▌",
//            "▐░█▀▀▀▀▀▀▀▀▀      ▐░▌          ▐░▌     ▐░▌   ▐░▌ ▐░▌▐░█▀▀▀▀▀▀▀▀▀ "
//                    + "▀▀▀▀▀▀▀▀▀█░▌ ▀▀▀▀▀▀▀▀▀█░▌▐░▌       ▐░▌",
//            "▐░▌               ▐░▌          ▐░▌     ▐░▌    ▐░▌▐░▌▐░▌                    "
//                    + "▐░▌          ▐░▌▐░▌       ▐░▌",
//            "▐░▌           ▄▄▄▄█░█▄▄▄▄      ▐░▌     ▐░▌     ▐░▐░▌▐░█▄▄▄▄▄▄▄▄▄  "
//                    + "▄▄▄▄▄▄▄▄█░▌ ▄▄▄▄▄▄▄▄▄█░▌▐░█▄▄▄▄▄▄▄█░▌▐░▌     ▐░▐░▌",
//            "▐░▌          ▐░░░░░░░░░░░▌     ▐░▌     ▐░▌      ▐░░▌▐░░░░░░░░░░░▌"
//                    + "▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░▌      ▐░░▌"
//        };

//        for (String s : logo) {
//            System.out.println(s);
//        }
    }



    /**
     * Displays the welcome message and logo to the user.
     */
    public void showWelcome() {
        print("Hello from");
        printASCIIName();
        printWithDivider("Hey, welcome to FitnessONE!" + System.lineSeparator() + "      How may we help today?");
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
