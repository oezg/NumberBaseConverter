package converter;

import java.util.Scanner;

public class UserInterface {
    private final Scanner scanner;
    public UserInterface(Scanner scanner) {
        this.scanner = scanner;
    }

    public void run() {
        System.out.print("Enter number in decimal system: ");
        int number = scanner.nextInt();
        NumberWithBase numberWithBase = new NumberWithBase(number);
        System.out.print("Enter target base: ");
        int baseTarget = scanner.nextInt();
        try {
            NumberBaseConverter numberBaseConverter = new NumberBaseConverter(baseTarget);
            NumberWithBase result = numberBaseConverter.convert(numberWithBase);
            System.out.printf("Conversion result: %s", result.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
