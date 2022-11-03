package converter;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CommandLineInterface implements UserInterface {
    private final Scanner scanner;
    public CommandLineInterface(Scanner scanner) {
        this.scanner = scanner;
    }

    public void run() {
        while (true) {
            printFirstPrompt();
            String firstCommand = scanner.next();
            if (firstCommand.equals("/exit")) {
                break;
            }
            try {
                int baseOrigin = Integer.parseInt(firstCommand);
                int baseTarget = scanner.nextInt();
                NumeralSystem numeralSystem = new NumeralSystem(baseOrigin);
                NumberBaseConverter numberBaseConverter = new NumberBaseConverter(baseTarget);
                while (true) {
                    printSecondPrompt(baseOrigin, baseTarget);
                    String secondCommand = scanner.next().trim().toLowerCase();
                    if (secondCommand.equals("/back")) {
                        System.out.println();
                        break;
                    }
                    NumberWithBase numberWithBase = numeralSystem.getNumber(secondCommand);
                    NumberWithBase result = numberBaseConverter.convert(numberWithBase);
                    System.out.printf("Conversion result: %s%n%n", result.toString());
                }
            } catch (BaseConverterException | WholeNumberWithBase.NumberBaseException | NumeralSystem.BaseError e) {
                System.out.println(e.getMessage());
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("Please enter two integer numbers separated by a space as source base and target " +
                        "base (between 2 and 36 inclusive)!");
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
