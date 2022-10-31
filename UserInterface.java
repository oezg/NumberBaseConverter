package converter;

import java.util.Scanner;

public class UserInterface {
    private final Scanner scanner;
    public UserInterface(Scanner scanner) {
        this.scanner = scanner;
    }

    public void run() {
        String command = "";
        while (!command.equals("/exit")) {
            printPrompt();
            command = scanner.next();
            switch (command) {
                case "/from":
                    convertFromDecimal();
                    break;
                case "/to":
                    convertToDecimal();
                    break;
                default:
                    break;
            }
        }
    }

    private void convertToDecimal() {
        System.out.print("Enter source number: ");
        String number = scanner.next().toLowerCase();
        System.out.print("Enter source base: ");
        int baseOrigin = scanner.nextInt();
        NumberWithBase origin = new NumberWithBase(number, baseOrigin);
        convertNumberToBase(origin, 10);

    }

    private void convertFromDecimal() {
        System.out.print("Enter a number in decimal system: ");
        long number = scanner.nextLong();
        NumberWithBase origin = new NumberWithBase(number);
        System.out.print("Enter target base: ");
        int baseTarget = scanner.nextInt();
        convertNumberToBase(origin, baseTarget);
    }

    private void convertNumberToBase(NumberWithBase origin, int baseTarget) {
        try {
            NumberBaseConverter numberBaseConverter = new NumberBaseConverter(baseTarget);
            NumberWithBase result = numberBaseConverter.convert(origin);
            String toDecimal = baseTarget == 10 ? "to decimal " : "";
            System.out.printf("Conversion %sresult: %s%n%n", toDecimal , result.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void printPrompt() {
        System.out.print("Do you want to convert /from decimal or /to decimal? (To quit type /exit) ");
    }
}
