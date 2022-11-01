package converter;

import java.math.BigDecimal;
import java.math.BigInteger;

public class NumberBaseConverter {

    public static final int MAX_BASE_TARGET = 36;
    public static final int DEFAULT_BASE_TARGET = 10;
    private final int baseTarget;
    private final BigInteger baseTargetBigInteger;

    public NumberBaseConverter() throws BaseConverterException {
        this(DEFAULT_BASE_TARGET);
    }

    public NumberBaseConverter(int baseTarget) throws BaseConverterException {
        if (baseTarget > MAX_BASE_TARGET) {
            throw new BaseConverterException(

            );
        }
        this.baseTarget = baseTarget;
        this.baseTargetBigInteger = BigInteger.valueOf(baseTarget);
    }

    public NumberWithBase convert(NumberWithBase numberWithBase) throws BaseConverterException, NumberWithBase.NumberBaseException {
        BigInteger decimalEquivalent = convertOriginToDecimal(numberWithBase);
        String result = convertDecimalToTargetBase(decimalEquivalent);
        return new NumberWithBase(result, baseTarget);
    }

    private String convertDecimalToTargetBase(BigInteger decimalEquivalent) throws BaseConverterException {
        StringBuilder stringBuilder = new StringBuilder();
        while (decimalEquivalent.compareTo(BigInteger.ZERO) > 0) {
            int digit = decimalEquivalent.remainder(baseTargetBigInteger).intValue();
            char character = convertDigitToChar(digit);
            stringBuilder.insert(0, character);
            decimalEquivalent = decimalEquivalent.divide(baseTargetBigInteger);
        }
        return stringBuilder.toString();
    }

    BigInteger convertOriginToDecimal(NumberWithBase numberWithBase) throws BaseConverterException {
        char[] digits = numberWithBase.getRepresentation().toCharArray();
        int base = numberWithBase.getBase();
        BigInteger sum = BigInteger.ZERO;
        for (int i = 0; i < digits.length; i++) {
            int digit = convertCharToDigit(digits[i]);
            int power = digits.length - i - 1;
            sum = sum.add(calculateEachDigitsValue(digit, base, power));
        }
        return sum;
    }

    private BigInteger calculateEachDigitsValue(int digit, int base, int power) {
        return BigDecimal.valueOf(digit * Math.pow(base, power)).toBigInteger();
    }

    private int convertCharToDigit(char digit) throws BaseConverterException {
        char zero = '0';
        char nine = '9';
        if (zero <= digit && digit <= nine) {
            return digit - zero;
        }
        char a = 'a';
        char z = 'z';
        if (a <= digit && digit <= z) {
            return 10 + digit - a;
        }
        throw new BaseConverterException(digit);
    }

    private char convertDigitToChar(int digit) throws BaseConverterException {
        if (digit < 0 || digit >= baseTarget) {
            throw new BaseConverterException(digit);
        }
        if (digit < 10) {
            return String.valueOf(digit).charAt(0);
        }
        return (char) ('a' + digit - 10);
    }

    public class BaseConverterException extends Exception {
        public BaseConverterException() {
            super(String.format("%d is larger than the maximum base %d%n", baseTarget, MAX_BASE_TARGET));
        }
        public BaseConverterException(int digit) {
            super(String.format("%d cannot be converted to a digit in base %d%n", digit, baseTarget));
        }
        public BaseConverterException(char digit) {
            super(String.format("%c is not included in the range [0-9a-z]%n", digit));
        }
    }
}
