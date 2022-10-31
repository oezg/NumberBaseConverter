package converter;

import java.util.Objects;

public class NumberWithBase {

    private final String representation;
    private final int base;

    public NumberWithBase(String representation) {
        this(representation, 10);
    }

    public NumberWithBase(long decimalValue) {
        this(String.valueOf(decimalValue));
    }

    public NumberWithBase(String representation, int base) {
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
}
