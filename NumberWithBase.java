package converter;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Objects;

public abstract class NumberWithBase {

    protected final String representation;
    protected final NumeralSystem numeralSystem;
    protected final BigDecimal decimalValue;

    public NumberWithBase(String representation, NumeralSystem numeralSystem) throws NumberBaseException, PluralRadixPointsError {
        if (representation.isBlank()) {
            throw new NumberBaseException();
        }
        if (representation.chars().filter(ch -> ch == '.').count() > 1) {
            throw new PluralRadixPointsError();
        }
        this.representation = representation;
        this.numeralSystem = numeralSystem;
        this.decimalValue = convertToDecimal();
    }

    protected abstract BigDecimal convertToDecimal() throws NumberBaseException;

    protected BigDecimal calculateEachDigitsValue(int digit, int power) {
        return BigDecimal.valueOf(getBase()).pow(power, MathContext.DECIMAL128).multiply(BigDecimal.valueOf(digit));
    }

    public String getRepresentation() {
        return representation;
    }

    public int getBase() {
        return numeralSystem.getBase();
    }

    public BigDecimal getDecimalValue() {
        return decimalValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NumberWithBase that = (NumberWithBase) o;
        return getRepresentation().equals(that.getRepresentation()) && getBase() == that.getBase();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRepresentation(), getBase());
    }

    @Override
    public String toString() {
        return getRepresentation();
    }

    static int convertCharToDecimal(char digit) {
        if ('0' <= digit && digit <= '9') {
            return digit - '0';
        }
        if ('a' <= digit && digit <= 'z') {
            return 10 + digit - 'a';
        }
        return -1;
    }

    class NumberBaseException extends Exception {
        public NumberBaseException() {
            super("The representation does not contain any characters.");
        }

        public NumberBaseException(char character, int index) {
            super(String.format("The digit %c at index %d from left does not represent a value between 0 and %d.",
                    character, index, getBase()));
        }
    }

    class PluralRadixPointsError extends Exception {
        PluralRadixPointsError() {
            super("The representation contains more than one radix points");
        }
    }
}
