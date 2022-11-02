package converter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;


public class NumberWithBase {
    public static final int MAX_BASE = 36;
    public static final int MIN_BASE = 2;
    private final String representation;
    private final int base;
    private final BigInteger decimalValue;

    public NumberWithBase(String representation, int base) throws NumberBaseException {
        this.representation = representation;
        this.base = base;
        validateNumberWithBase();
        this.decimalValue = convertToDecimal();
    }

    public BigInteger getDecimalValue() {
        return decimalValue;
    }

    private BigInteger convertToDecimal() throws NumberBaseException {
        BigInteger sum = BigInteger.ZERO;
        for (int index = 0; index < representation.length(); index++) {
            char character = representation.charAt(index);
            int digit = convertCharToDigit(character);
            if (digit < 0 || base <= digit) {
                throw new NumberBaseException(character, index, base);
            }
            int power = representation.length() - 1 - index;
            sum = sum.add(calculateEachDigitsValue(digit, power));
        }
        return sum;
    }
    private BigInteger calculateEachDigitsValue(int digit, int power) {
        return BigDecimal.valueOf(digit * Math.pow(base, power)).toBigInteger();
    }
    static int convertCharToDigit(char digit) {
        if ('0' <= digit && digit <= '9') {
            return digit - '0';
        }
        if ('a' <= digit && digit <= 'z') {
            return 10 + digit - 'a';
        }
        return -1;
    }

    private void validateNumberWithBase() throws NumberBaseException {
        if (base < MIN_BASE || MAX_BASE < base) {
            throw new NumberBaseException(base);
        }
        if (representation.isBlank()) {
            throw new NumberBaseException();
        }
    }

    public String getRepresentation() {
        return representation;
    }

    public int getBase() {
        return base;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NumberWithBase that = (NumberWithBase) o;
        return getBase() == that.getBase() && Objects.equals(getRepresentation(), that.getRepresentation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRepresentation(), getBase());
    }

    @Override
    public String toString() {
        return getRepresentation();
    }

    static class NumberBaseException extends Exception {
        public NumberBaseException() {
            super("The string representation does not contain any characters.");
        }
        public NumberBaseException(int base) {
            super(String.format("Base %d is not in the range of integers between %d and %d inclusive.", base,
                    MIN_BASE, MAX_BASE));
        }
        public NumberBaseException(char character, int index, int base) {
            super(String.format("The digit %c at index %d from left does not represent a value between 0 and %d.",
                    character, index, base));
        }
    }
}
