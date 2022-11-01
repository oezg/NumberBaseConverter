package converter;

import java.math.BigInteger;
import java.util.Objects;

public class NumberWithBase {

    public static final int DEFAULT_BASE = 10;
    public static final int MAX_BASE = 36;
    private final String representation;
    private final int base;

    public NumberWithBase(String representation) throws NumberBaseException {
        this(representation, DEFAULT_BASE);
    }

    public NumberWithBase(long decimalValue) throws NumberBaseException {
        this(BigInteger.valueOf(decimalValue));
    }

    public NumberWithBase(BigInteger bigInteger) throws NumberBaseException {
        this(bigInteger.toString());
    }

    public NumberWithBase(String representation, int base) throws NumberBaseException {
        if (base > MAX_BASE) {
            throw new NumberBaseException(base);
        }
        this.representation = representation;
        this.base = base;
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

    public class NumberBaseException extends Exception {
        public NumberBaseException(int base) {
            super(String.format("%d is larger than the largest possible base %d.", base, MAX_BASE));
        }
    }
}
