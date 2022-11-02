package converter;

import java.math.BigDecimal;
import java.math.BigInteger;

public class NumberBaseConverter {

    public static final int MAX_BASE_TARGET = 36;
    public static final int MIN_BASE_TARGET = 2;
    public static final int DEFAULT_BASE_TARGET = 10;
    private final int baseTarget;
    private final BigInteger baseTargetBigInteger;


    public NumberBaseConverter(int baseTarget) throws BaseConverterException {
        this.baseTarget = baseTarget;
        this.baseTargetBigInteger = BigInteger.valueOf(baseTarget);
        validateBase();
    }

    private void validateBase() throws BaseConverterException {
        if (baseTarget < MIN_BASE_TARGET || MAX_BASE_TARGET < baseTarget) {
            throw new BaseConverterException(baseTarget);
        }
    }

    public NumberWithBase convert(NumberWithBase numberWithBase) throws BaseConverterException, NumberWithBase.NumberBaseException {
        BigInteger decimalEquivalent = numberWithBase.getDecimalValue();
        String result = convertDecimalToTargetBase(decimalEquivalent);
        return new NumberWithBase(result, baseTarget);
    }

    private String convertDecimalToTargetBase(BigInteger decimalEquivalent) throws BaseConverterException {
        StringBuilder stringBuilder = new StringBuilder();
        while (decimalEquivalent.compareTo(BigInteger.ZERO) > 0) {
            int digit = decimalEquivalent.remainder(baseTargetBigInteger).intValue();
            char character = validateAndConvertDigitToChar(digit);
            stringBuilder.insert(0, character);
            decimalEquivalent = decimalEquivalent.divide(baseTargetBigInteger);
        }
        return stringBuilder.toString();
    }

    private char validateAndConvertDigitToChar(int digit) throws BaseConverterException {
        validateDigitInRange(digit);
        return convertDigitToChar(digit);
    }

    static char convertDigitToChar(int digit) {
        if (digit < 10) {
            return (char) ('0' + digit);
        }
        return (char) ('a' + digit - 10);
    }

    private void validateDigitInRange(int digit) throws BaseConverterException {
        if (digit < 0 || digit >= baseTarget) {
            throw new BaseConverterException(baseTarget, digit);
        }
    }

}
class BaseConverterException extends Exception {
    public BaseConverterException(int baseTarget) {
        super(String.format("Target base %d is not in the range of integers between %d and %d inclusive.%n",
                baseTarget, NumberBaseConverter.MIN_BASE_TARGET, NumberBaseConverter.MAX_BASE_TARGET));
    }
    public BaseConverterException(int baseTarget, int digit) {
        super(String.format("%d cannot be converted to a digit in base %d%n", digit, baseTarget));
    }
    public BaseConverterException(char digit) {
        super(String.format("%c is not included in the range [0-9a-z]%n", digit));
    }
}
