package converter;

public class NumeralSystem {
    private final int base;
    public static final int MAX_BASE = 36;
    public static final int MIN_BASE = 2;

    public NumeralSystem(int base) throws BaseError {
        this.base = base;
        if (base < MIN_BASE || MAX_BASE < base) {
            throw new BaseError();
        }
    }


    public NumberWithBase getNumber(String representation) throws WholeNumberWithBase.NumberBaseException, BaseError {
        if (representation.contains(".")) {
            return new FractionalNumberWithBase(representation, this);
        }
        return new WholeNumberWithBase(representation, this);
    }

    public int getBase() {
        return base;
    }

    class BaseError extends Exception {
        public BaseError() {
            super(String.format("Base %d is not in the range of integers between %d and %d inclusive.", base,
                    MIN_BASE, MAX_BASE));
        }
    }

}
