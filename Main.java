package converter;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        UserInterface ui = new CommandLineInterface(scanner);

        ui.run();
    }
}
