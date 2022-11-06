package converter;

import java.math.BigDecimal;
import java.math.MathContext;

public class NumberBaseConverter {

    public static final int LENGTH_FRACTIONAL_PART = 5;
    private final NumeralSystem numeralSystem;
    private final BigDecimal baseTargetBigDecimal;

    public NumberBaseConverter(NumeralSystem numeralSystem) throws NumeralSystem.BaseError {
        this.numeralSystem = numeralSystem;
        this.baseTargetBigDecimal = BigDecimal.valueOf(numeralSystem.getBase());
    }

    public NumberWithBase convert(NumberWithBase numberWithBase) throws NumberWithBase.NumberBaseException, NumeralSystem.BaseError, NumberWithBase.PluralRadixPointsError {
        String result;
        BigDecimal decimalEquivalent = numberWithBase.getDecimalValue();
        if (numberWithBase instanceof WholeNumberWithBase) {
            result = convertIntegralDecimalToStringWithBase(decimalEquivalent);
        } else {
            result = convertDecimalToStringWithBase(decimalEquivalent);
        }
        return numeralSystem.getNumber(result);
    }

    private String convertDecimalToStringWithBase(BigDecimal decimalEquivalent) {
        String integralPart;
        String fractionalPart;
        BigDecimal[] integralAndFractionalParts = decimalEquivalent.divideAndRemainder(
                BigDecimal.ONE,
                MathContext.DECIMAL128
        );
        BigDecimal integralDecimalEquivalent = integralAndFractionalParts[0];
        BigDecimal fractionalDecimalEquivalent = integralAndFractionalParts[1];
        if (integralDecimalEquivalent.compareTo(BigDecimal.ZERO) == 0) {
            integralPart = "0";
        } else {
            integralPart = convertIntegralDecimalToStringWithBase(integralDecimalEquivalent);
        }
        if (fractionalDecimalEquivalent.compareTo(BigDecimal.ZERO) == 0) {
            fractionalPart = "00000";
        } else {
            fractionalPart = convertFractionalDecimalToStringWithBase(fractionalDecimalEquivalent);
        }
        return String.format("%s.%s", integralPart, fractionalPart);
    }

    private String convertFractionalDecimalToStringWithBase(BigDecimal fractionalDecimalEquivalent) {
        StringBuilder stringBuilder = new StringBuilder();
        int digit;
        char character;
        int lengthFractionalPart = 0;
        while (lengthFractionalPart < LENGTH_FRACTIONAL_PART) {
            fractionalDecimalEquivalent = fractionalDecimalEquivalent.multiply(baseTargetBigDecimal);
            digit = fractionalDecimalEquivalent.intValue();
            character = convertDigitToChar(digit);
            stringBuilder.append(character);
            lengthFractionalPart++;
            fractionalDecimalEquivalent = fractionalDecimalEquivalent.remainder(BigDecimal.ONE);
        }
        return stringBuilder.toString();
    }

    private String convertIntegralDecimalToStringWithBase(BigDecimal integralDecimalEquivalent) {
        StringBuilder stringBuilder = new StringBuilder();
        int digit;
        char character;
        BigDecimal[] quotientAndRemainder;
        while (integralDecimalEquivalent.compareTo(BigDecimal.ONE) >= 0) {
            quotientAndRemainder = integralDecimalEquivalent.divideAndRemainder(
                    baseTargetBigDecimal,
                    MathContext.DECIMAL128
            );
            digit = quotientAndRemainder[1].intValue();
            character = convertDigitToChar(digit);
            stringBuilder.insert(0, character);
            integralDecimalEquivalent = quotientAndRemainder[0];
        }
        return stringBuilder.toString();
    }

    static char convertDigitToChar(int digit) {
        if (digit < 10) {
            return (char) ('0' + digit);
        }
        return (char) ('a' + digit - 10);
    }
}
