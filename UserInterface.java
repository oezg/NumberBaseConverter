package converter;

import java.util.Scanner;

public class UserInterface {
    private final Scanner scanner;
    public UserInterface(Scanner scanner) {
        this.scanner = scanner;
    }

    public void run() {
        while (true) {
            printFirstPrompt();
            try {
                int baseOrigin = scanner.nextInt();
                int baseTarget = scanner.nextInt();
                NumberBaseConverter numberBaseConverter = new NumberBaseConverter(baseTarget);
                while (true) {
                    printSecondPrompt(baseOrigin, baseTarget);
                    String command = scanner.next();
                    if (command.equals("/back")) {
                        System.out.println();
                        break;
                    }
                    NumberWithBase numberWithBase = new NumberWithBase(command, baseOrigin);
                    NumberWithBase result = numberBaseConverter.convert(numberWithBase);
                    System.out.printf("Conversion result: %s%n%n", result.toString());
                }
            } catch (NumberBaseConverter.BaseConverterException | NumberWithBase.NumberBaseException e) {
                System.out.println(e.getMessage());
            }
            catch (Exception e) {
                break;
            }
        }
    }

    private void printSecondPrompt(int baseOrigin, int baseTarget) {
        System.out.printf("Enter number in base %d to convert to base %d (To go back type /back) ", baseOrigin, baseTarget);
    }

    private void printFirstPrompt() {
        System.out.print("Enter two numbers in format: {source base} {target base} (To quit type /exit) ");
    }
}
