package converter;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        UserInterface ui = new UserInterface(scanner);

        ui.run();
    }
}
