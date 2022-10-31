package converter;

import java.util.stream.Stream;

public class NumberBaseConverter {

    public static final int MAX_BASE_TARGET = 35;
    private int baseTarget;


    public NumberBaseConverter(int baseTarget) throws Exception {
        if (baseTarget > MAX_BASE_TARGET) {
            throw new Exception(
                    String.format("%d is larger than the maximum base %d", baseTarget, MAX_BASE_TARGET)
            );
        }
        this.baseTarget = baseTarget;
    }

    public NumberWithBase convert(NumberWithBase numberWithBase) throws Exception {
        long decimalEquivalent = convertOriginToDecimal(numberWithBase);
        String result = convertDecimalToTargetBase(decimalEquivalent);
        return new NumberWithBase(result, baseTarget);
    }

    private String convertDecimalToTargetBase(long decimalEquivalent) throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        while (decimalEquivalent > 0) {
            int digit = (int) decimalEquivalent % baseTarget;
            char character = convertDigitToChar(digit);
            stringBuilder.insert(0, character);
            decimalEquivalent /= baseTarget;
        }
        return stringBuilder.toString();
    }

    private char convertDigitToChar(int digit) throws Exception {
        if (digit < 0 || digit >= baseTarget) {
            throw new Exception(String.format("%d cannot be converted to a digit in base %d", digit, baseTarget));
        }
        if (digit < 10) {
            return String.valueOf(digit).charAt(0);
        }
        return (char) ('A' + digit - 10);
    }

    long convertOriginToDecimal(NumberWithBase numberWithBase) throws Exception {
        char[] digits = numberWithBase.getRepresentation().toCharArray();
        int base = numberWithBase.getBase();
        long sum = 0;
        for (int i = 0; i < digits.length; i++) {
            int digit = convertCharToDigit(digits[i]);
            int power = digits.length - i - 1;
            sum += calculateEachDigitsValue(digit, base, power);
        }
        return sum;
    }

    private long calculateEachDigitsValue(int digit, int base, int power) {
        return (long) (digit * Math.pow(base, power));
    }

    private int convertCharToDigit(char digit) throws Exception {
        char zero = '0';
        char nine = '9';
        if (zero <= digit && digit <= nine) {
            return digit - zero;
        }
        char A = 'A';
        char Z = 'Z';
        if (A <= digit && digit <= Z) {
            return 10 + digit - A;
        }
        throw new Exception(
                String.format("%c is not included in the range [0-9A-Z]", digit)
        );
    }
}
