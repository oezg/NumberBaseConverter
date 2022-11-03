package converter;

import java.math.BigDecimal;

public abstract class NumberWithBase {

    protected final String representation;
    protected final NumeralSystem numeralSystem;
    protected final BigDecimal decimalValue;

    public NumberWithBase(String representation, NumeralSystem numeralSystem) throws NumberBaseException {
        if (representation.isBlank()) {
            throw new NumberBaseException();
        }
        this.representation = representation;
        this.numeralSystem = numeralSystem;
        this.decimalValue = convertToDecimal();
    }

    protected abstract BigDecimal convertToDecimal() throws NumberBaseException;

    public BigDecimal getDecimalValue() {
        return decimalValue;
    }

    public String getRepresentation() {
        return representation;
    }

    public int getBase() {
        return base;
    }
    class NumberBaseException extends Exception {
        public NumberBaseException() {
            super("The string representation does not contain any characters.");
        }

        public NumberBaseException(char character, int index) {
            super(String.format("The digit %c at index %d from left does not represent a value between 0 and %d.",
                    character, index, base));
        }
    }
}
